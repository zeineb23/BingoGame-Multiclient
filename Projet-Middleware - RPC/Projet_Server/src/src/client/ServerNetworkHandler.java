package src.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkHandler implements Runnable {
    private ServerSocket serverSocket;

    public ServerNetworkHandler(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClientRequest(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClientRequest(Socket clientSocket) {
        // Handle the client request in a new thread or directly here
    }

    // Additional methods for specific request handling
}
