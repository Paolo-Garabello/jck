import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import types.JSON.PublicMessage;
import types.JSON.Message;
import types.JSON.SessionUsername;
import java.util.HashMap;

public class MainWebSocketServer extends WebSocketServer{

    private HashMap<WebSocket, String> hashmap = new HashMap<WebSocket, String>();
    private ObjectMapper mapper = new ObjectMapper();
    private static int id = 0;

    private String randomUsername(){
        return (new Faker()).name().firstName() + id++;
    }

    public MainWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully.");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        hashmap.put(conn, randomUsername());
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
        try{
            conn.send(mapper.writeValueAsString(new SessionUsername(hashmap.get(conn))));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message){
        try{
            Message msg = mapper.readValue(message, Message.class);
            if(msg.getChat().equals("public")){    
                System.out.println("Received public message from " + conn.getRemoteSocketAddress() + ": " + msg.getMessage());
                this.broadcast(mapper.writeValueAsString(new PublicMessage(mapper.readValue(message, Message.class).getMessage(), hashmap.get(conn))));
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
