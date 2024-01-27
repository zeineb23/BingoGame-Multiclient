package src.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientNetworkHandler {
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientNetworkHandler(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws Exception {
        socket = new Socket(serverAddress, serverPort);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public void sendGuess(int guess) throws Exception {
        output.writeObject(guess);
        output.flush();
    }

    public boolean receiveResponse() throws Exception {
        return (boolean) input.readObject();
    }

    public void close() throws Exception {
        input.close();
        output.close();
        socket.close();
    }

    // Additional methods for other types of communication
}

