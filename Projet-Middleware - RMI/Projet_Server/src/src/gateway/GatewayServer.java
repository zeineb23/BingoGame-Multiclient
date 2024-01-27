package src.gateway;
import java.net.ServerSocket;
import java.net.Socket;

public class GatewayServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // Replace 12345 with your desired port number
            while (true) {
                System.out.println("Gateway Server is waiting for clients...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread for each connected client
                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            System.err.println("Gateway Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
