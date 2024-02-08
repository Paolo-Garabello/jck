import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;

public class Main extends WebSocketServer {

    public Main(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully.");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + message);
        // Send a reply message
        conn.send("Server received: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            System.err.println("Error on connection " + conn.getRemoteSocketAddress() + ": " + ex);
        } else {
            System.err.println("Error on connection: " + ex);
        }
    }

    public static void main(String[] args) {
        int port = 8887; // Change port as needed
        Main server = new Main(new InetSocketAddress(port));
        server.start();
        System.out.println("WebSocket server started on port " + port);
    
        // Add shutdown hook to stop the server gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down WebSocket server...");
            try {
                server.stop();
            } catch (InterruptedException e) {
                // Ignore InterruptedException during shutdown
                System.err.println("Interrupted while stopping the server: " + e.getMessage());
            }
            System.out.println("WebSocket server stopped.");
        }));
    }
    
}
