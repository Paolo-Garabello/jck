import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import types.User;
import types.JSON.DirectMessage;
import types.JSON.Messages;
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
import java.math.BigInteger;
import java.net.InetSocketAddress;

public class MainWebSocketServer extends WebSocketServer {

    private BiMap<WebSocket, User> tokens = HashBiMap.create();
    private ObjectMapper mapper = new ObjectMapper();
    private static int id = 0;
    private Connection connection = null;
    private Statement statement;

    private String randomUsername() {
        return (new Faker()).name().firstName() + id++;
    }

    public static String createToken() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(new Timestamp(System.currentTimeMillis()).toString().getBytes());
            md.update((md.digest().toString() + Math.random()).getBytes());
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch(NoSuchAlgorithmException e) {
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
        try {
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private User searchForToken(String token) {
        for (User user : tokens.values()) {
            if(user.getToken().equals(token))
                return user;
        }
        return null;
    }

    public String processMessage(String message) {
        String string = message;
        String result = string.substring(0, (string.indexOf('\\') != -1 ? string.indexOf('\\') : string.length()));
        while(string.indexOf('\\') != -1){
            String str = string.substring(string.indexOf('\\')+1);
            string = str.substring(str.indexOf('\\')+1);
            str = str.substring(0, str.indexOf('\\'));

            switch (str.substring(0, str.indexOf(' '))) {
                case "c":   //pag 194
                case "calc":
                    result += " `" + Rotaluklak.calc(new Expression(str.substring(str.indexOf(' '))).toPolski()) + "` ";
                    break;
                case "k":   
                case "rotaluklak":
                    result += " `" + Rotaluklak.calc(str.substring(str.indexOf(' ')-1)) + "` ";
                    break;
                default:
                    break;
            }
            
        result += string.substring(0, (string.indexOf('\\') != -1 ? string.indexOf('\\') : string.length()));
        }

        return result;
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try{
            System.out.println(message);
            ResultSet res;
            User user;
            Request req = mapper.readValue(message, Request.class);

            switch (req.getRequest()) {
                case "sendMessage":
                    if(req.getContent().getChat().equals("public")) {    
                        System.out.println("Received public message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                        this.broadcast(mapper.writeValueAsString(new PublicMessage(processMessage(req.getContent().getMessage()), tokens.get(conn).getPublicName())));
                    } else if(req.getContent().getChat().indexOf('@') == 0) {
                        System.out.println("Received direct message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                        statement.execute("INSERT INTO messages(text, sender, recipient) VALUES('" + req.getContent().getMessage() + "', '" + tokens.get(conn).getPrivateUser().getId() + "', '" + req.getContent().getChat().substring(1) + "');");
                        user = tokens.get(conn);
                        while (user.hasNext()) {
                            conn.send(mapper.writeValueAsString(new DirectMessage(req.getContent().getMessage(), user.getPrivateUser().getUsername(), user.getPrivateUser().getId(), Integer.valueOf(req.getContent().getChat().substring(1)), statement.executeQuery("SELECT last_insert_rowid()").getInt(1))));
                            user = user.getNext();
                        }    
                        user = tokens.get(conn);
                        while (user.hasPrevious()) {
                            user = user.getPrevious();
                            conn.send(mapper.writeValueAsString(new DirectMessage(req.getContent().getMessage(), user.getPrivateUser().getUsername(), user.getPrivateUser().getId(), Integer.valueOf(req.getContent().getChat().substring(1)), statement.executeQuery("SELECT last_insert_rowid()").getInt(1))));
                        }    
                    }
                    break;

                case "signup":
                    try {
                        statement.execute("INSERT INTO users(username, password) VALUES('" + req.getUserInfo().getUsername() + "', '" + req.getUserInfo().getPassword() + "');");
                        conn.send(mapper.writeValueAsString(new Response(true, 201)));
                    } catch(SQLException e) {
                        conn.send(mapper.writeValueAsString(new Response(false, 40, "User already exists")));
                    }
                    break;
                
                case "login": //TODO fare tutte le istanze degli user
                    res = statement.executeQuery("SELECT id, username FROM users WHERE username='" + req.getUserInfo().getUsername() + "'AND password='" + req.getUserInfo().getPassword() + "';");
                    if(res.getString("username") != null) {
                        conn.send(mapper.writeValueAsString(new Response(true, 204)));
                        tokens.get(conn).setPrivateUser(res);
                        System.out.println("Logged " + tokens.get(conn).getPrivateUser());
                    } else {
                        conn.send(mapper.writeValueAsString(new Response(false, 401, "Wrong credentials")));
                    }
                    break;

                case "auth":
                    if(req.getData() == null) {
                        String token = createToken();
                        tokens.put(conn, new User(token, randomUsername()));
                        conn.send(mapper.writeValueAsString(new Response(true, 205, token)));
                        conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                    } else { 
                        if((user = searchForToken(req.getData())) != null) {
                            tokens.put(conn, new User(user));
                            conn.send(mapper.writeValueAsString(new Response(true, 200)));
                            conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                        } else {
                            String token = createToken();
                            tokens.put(conn, new User(token, randomUsername()));
                            conn.send(mapper.writeValueAsString(new Response(false, 205, token)));
                            conn.send(mapper.writeValueAsString(new SessionUsername(tokens.get(conn).getPublicName())));
                        }
                    }
                    break;
                
                case "getChats": //TODO modificare Messages
                    int userID = tokens.get(conn).getPrivateUser().getId();
                    String id = (req.getData() != null ? req.getData() : "=0");
                    String username = statement.executeQuery("SELECT * FROM users WHERE id=" + id).getString(1);
                    String query = "SELECT messages.*, username FROM users INNER JOIN messages WHERE messages.id>" + id + " AND " + userID + " IN (sender, recipient) AND messages.recipient = users.id";
                    res = statement.executeQuery(query);
                    conn.send(mapper.writeValueAsString(new Response(true, 206, new Messages(res, username))));
                    break;
                    
                default:
                    conn.send(mapper.writeValueAsString(new Response(false, 400, "Bad request")));
                    break;
            }
        // } catch(NullPointerException e) {
        //     try {
        //         conn.send(mapper.writeValueAsString(new Response(false, 404, "User not found")));
        //     } catch(JsonProcessingException ex) {
        //         ex.printStackTrace();
        //     }
        } catch(Exception e) {
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
