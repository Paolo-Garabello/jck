import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import types.JSON.PublicMessage;
import types.JSON.SessionUsername;
import types.JSON.Response;
import types.JSON.Request;
import java.util.HashMap;

public class MainWebSocketServer extends WebSocketServer{

    private HashMap<WebSocket, String> publicUsers = new HashMap<WebSocket, String>();
    private HashMap<WebSocket, ResultSet> privateUsers = new HashMap<WebSocket, ResultSet>();
    private ObjectMapper mapper = new ObjectMapper();
    private static int id = 0;
    private Connection connection = null;
    Statement statement;

    private String randomUsername(){
        return (new Faker()).name().firstName() + id++;
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
        publicUsers.put(conn, randomUsername());
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
        try{
            conn.send(mapper.writeValueAsString(new SessionUsername(publicUsers.get(conn))));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
        publicUsers.remove(conn);
        privateUsers.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message){
        try{
            System.out.println(message);
            Request req = mapper.readValue(message, Request.class);

            if(req.getRequest().equals("sendMessage")){
                
                if(req.getContent().getChat().equals("public")){    
                    System.out.println("Received public message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                    this.broadcast(mapper.writeValueAsString(new PublicMessage(mapper.readValue(message, Request.class).getContent().getMessage(), publicUsers.get(conn))));
                } else if(req.getContent().getChat().indexOf('@') == 0){
                    System.out.println("Received direct message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                    statement.execute("INSERT INTO messages(text, sender, recipient) VALUES('" + req.getContent().getMessage() + "', '" + privateUsers.get(conn).getInt("id") + "', '" + req.getContent().getChat() + "');");
                }  
            } else if(req.getRequest().equals("signup")){
                statement.execute("INSERT INTO users(username, password) VALUES('" + req.getData().getUsername() + "', '" + req.getData().getPassword() + "');");
                conn.send(mapper.writeValueAsString(new Response(true, 201)));
            } else if(req.getRequest().equals("login")){
                ResultSet res = statement.executeQuery("SELECT id, username FROM users WHERE username='" + req.getData().getUsername() + "'AND password='" + req.getData().getPassword() + "';");
                if(res.getString("username") != null){
                    conn.send(mapper.writeValueAsString(new Response(true, 204)));
                    privateUsers.put(conn, res);
                } else {
                    conn.send(mapper.writeValueAsString(new Response(false, 401, "Wrong credentials")));
                }
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
