// src/client/rpc/RPCClientNetworkHandler.java
package src.client.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCClientNetworkHandler {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public RPCClientNetworkHandler(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws Exception {
        socket = new Socket(serverAddress, serverPort);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public void sendRequest(String methodName, Object... args) throws Exception {
        output.writeObject(methodName);
        output.writeObject(args);
        output.flush();
    }

    public Object receiveResponse() throws Exception {
        return input.readObject();
    }

    public void close() throws Exception {
        input.close();
        output.close();
        socket.close();
    }

    // Additional methods for other types of communication
}
