import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import types.User;
import types.JSON.*;
import types.JSON.Error;
import utilities.*;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class MainWebSocketServer extends WebSocketServer {

    private BiMap<WebSocket, User> tokens = HashBiMap.create();
    private HashMap<Integer, WebSocket> usernames = new HashMap<Integer, WebSocket>();
    private ObjectMapper mapper = new ObjectMapper();
    private static int id = 0;
    private Connection connection = null;
    private Statement statement;

    private String randomUsername() {
        return (new Faker()).name().firstName() + id++;
    }

    public MainWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onStart() {
        
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
                        this.broadcast(mapper.writeValueAsString(new Response<PublicMessage>(true, 202, new PublicMessage(MessageProcesser.process(req.getContent().getMessage()), tokens.get(conn).getPublicName()))));
                    } else if(req.getContent().getChat().indexOf('@') == 0) {
                        System.out.println("Received direct message from " + conn.getRemoteSocketAddress() + ": " + req.getContent().getMessage());
                        statement.execute("INSERT INTO messages(text, sender, recipient) VALUES('" + req.getContent().getMessage() + "', '" + tokens.get(conn).getPrivateUser().getId() + "', '" + req.getContent().getChat().substring(1) + "');");
                        user = tokens.get(conn);
                        Integer recipientUser = Integer.valueOf(req.getContent().getChat().substring(1));
                        conn.send(mapper.writeValueAsString(new Response<DirectMessage>(true, 202, new DirectMessage(req.getContent().getMessage(), user.getPrivateUser().getUsername(), user.getPrivateUser().getId(), recipientUser, statement.executeQuery("SELECT last_insert_rowid()").getInt(1))))); 
                        usernames.get(recipientUser).send(mapper.writeValueAsString(new Response<DirectMessage>(true, 202, new DirectMessage(req.getContent().getMessage(), user.getPrivateUser().getUsername(), user.getPrivateUser().getId(), recipientUser, statement.executeQuery("SELECT last_insert_rowid()").getInt(1)))));
                    }
                    break;

                case "signup":
                    try {
                        statement.execute("INSERT INTO users(username, password) VALUES('" + req.getUserInfo().getUsername() + "', '" + req.getUserInfo().getPassword() + "');");
                        conn.send(mapper.writeValueAsString(new Response<ResponseMessage>(true, 201, new ResponseMessage("Signup successfull"))));
                    } catch(SQLException e) {
                        conn.send(mapper.writeValueAsString(new Response<Error>(false, 409, new Error("User already exists"))));
                    }
                    break;
                
                case "login":
                    res = statement.executeQuery("SELECT id, username FROM users WHERE username='" + req.getUserInfo().getUsername() + "'AND password='" + req.getUserInfo().getPassword() + "';");
                    if(res.getString("username") != null) {
                        conn.send(mapper.writeValueAsString(new Response<Logged>(true, 200, new Logged(res.getString("username"), res.getInt("id")))));
                        tokens.get(conn).setPrivateUser(res);
                        usernames.put(tokens.get(conn).getPrivateUser().getId(), conn);
                        System.out.println("Logged " + tokens.get(conn).getPrivateUser());
                    } else {
                        conn.send(mapper.writeValueAsString(new Response<Error>(false, 401, new Error("Wrong credentials"))));
                    }
                    break;

                case "auth":
                    if(req.getData() == null) {
                        String token = Hashing.createToken();
                        tokens.put(conn, new User(token, randomUsername()));
                        conn.send(mapper.writeValueAsString(new Response<AuthResponse>(true, 205, new AuthResponse(token, tokens.get(conn).getPublicName()))));
                    } else { 
                        if((user = searchForToken(req.getData())) != null) {
                            tokens.put(conn, new User(user));
                            if(user.getPrivateUser() != null)
                                usernames.put(tokens.get(conn).getPrivateUser().getId(), conn);
                            
                            conn.send(mapper.writeValueAsString(new Response<AuthResponse>(true, 205, new AuthResponse(tokens.get(conn).getToken(), tokens.get(conn).getPublicName()))));
                        } else {
                            String token = Hashing.createToken();
                            tokens.put(conn, new User(token, randomUsername()));
                            if(tokens.get(conn).getPrivateUser() != null)
                                usernames.put(tokens.get(conn).getPrivateUser().getId(), conn);
                            
                            conn.send(mapper.writeValueAsString(new Response<AuthResponse>(false, 205, new AuthResponse(token, tokens.get(conn).getPublicName()))));
                        }
                    }
                    break;
                
                case "getChats":
                    try {
                        int userID = tokens.get(conn).getPrivateUser().getId();
                        String id = (req.getData() != null ? req.getData() : "=0");
                        String query = "SELECT * FROM (SELECT messages.*, username FROM users INNER JOIN messages WHERE messages.id>" + id + " AND " + userID + " IN (sender, recipient) AND messages.recipient = users.id) AS data INNER JOIN (SELECT username, id FROM users) AS name ON data.sender = name.id;";
                        res = statement.executeQuery(query);
                        Messages msgs = new Messages(res);
                        conn.send(mapper.writeValueAsString(new Response<Messages>(true, 206, msgs)));
                    } catch (NullPointerException e) {
                        System.out.println("User not logged");
                        conn.send(mapper.writeValueAsString(new Response<>(false, 403, new Error("User not logged"))));
                    }
                    break;
                    
                default:
                    conn.send(mapper.writeValueAsString(new Response<Error>(false, 400, new Error("Bad request"))));
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
