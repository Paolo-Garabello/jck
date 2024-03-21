import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) {
        int port = 8886; // Change port as needed
        MainWebSocketServer server = new MainWebSocketServer(new InetSocketAddress(port));
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
