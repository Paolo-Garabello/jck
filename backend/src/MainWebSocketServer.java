import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import types.User;
import types.JSON.PublicMessage;
import types.JSON.SessionUsername;
import types.JSON.Response;
import types.JSON.Request;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.net.InetSocketAddress;

public class MainWebSocketServer extends WebSocketServer{

    private BiMap<WebSocket, User> tokens = HashBiMap.create();
    private ObjectMapper mapper = new ObjectMapper();
    private static int id = 0;
    private Connection connection = null;
    Statement statement;

    private String randomUsername(){
        return (new Faker()).name().firstName() + id++;
    }

    private String createToken(){ //TODO non funge
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(new Timestamp(System.currentTimeMillis()).toString().getBytes());
            md.update((md.digest().toString() + Math.random()).getBytes());
            return md.digest().toString();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public MainWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully.");

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:db/database.db");

            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    private User searchForToken(String token){
        for (User user : tokens.values()) {
            if(user.getToken().equals(token))
                return user;
        }
        return null;
    }

    @Override
    public void onMessage(WebSocket conn, String message){
        try{
            System.out.println(message);
            Request req = mapper.readValue(message, Request.class);

            switch (req.getRequest()) {
                case "sendMessage":
                    if(req.getContent().getChat().equals("public")) {    
                        System.out.println("Received public message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                        this.broadcast(mapper.writeValueAsString(new PublicMessage(mapper.readValue(message, Request.class).getContent().getMessage(), tokens.get(conn).getPublicName())));
                    } else if(req.getContent().getChat().indexOf('@') == 0) {
                        System.out.println("Received direct message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                        statement.execute("INSERT INTO messages(text, sender, recipient) VALUES('" + req.getContent().getMessage() + "', '" + tokens.get(conn).getPrivateName().getInt("id") + "', '" + req.getContent().getChat().substring(1) + "');");
                    }
                    break;

                case "signup":
                    try {
                        statement.execute("INSERT INTO users(username, password) VALUES('" + req.getData().getUsername() + "', '" + req.getData().getPassword() + "');");
                        conn.send(mapper.writeValueAsString(new Response(true, 201)));
                    } catch(Exception e) {
                        conn.send(mapper.writeValueAsString(new Response(false, 401, "Wrong credentials")));
                    }
                    break;
                
                case "login":
                    ResultSet res = statement.executeQuery("SELECT id, username FROM users WHERE username='" + req.getData().getUsername() + "'AND password='" + req.getData().getPassword() + "';");
                    if(res.getString("username") != null) {
                        conn.send(mapper.writeValueAsString(new Response(true, 204)));
                        tokens.get(conn).setPrivateName(res);
                    } else {
                        conn.send(mapper.writeValueAsString(new Response(false, 401, "Wrong credentials")));
                    }
                    break;

                case "auth":
                    if(req.getToken() == null) {
                        String token = createToken();
                        tokens.put(conn, new User(token, randomUsername(), null));
                        conn.send(mapper.writeValueAsString(new Response(true, 205, token)));
                        conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                    } else { 
                        User user;
                        if((user = searchForToken(req.getToken())) != null){
                            tokens.inverse().replace(user, conn);
                            conn.send(mapper.writeValueAsString(new Response(true, 200)));
                            conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                        } else {
                            String token = createToken();
                            tokens.put(conn, new User(token, randomUsername(), null));
                            conn.send(mapper.writeValueAsString(new Response(false, 205, token)));
                            conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                        }
                    }
                    break;
                default:
                    conn.send(mapper.writeValueAsString(new Response(false, 400, "Bad request")));
                    break;
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            System.err.println("Error on connection " + conn.getRemoteSocketAddress() + ": " + ex);
        } else {
            System.err.println("Error on connection: " + ex);
        }
    }
}
