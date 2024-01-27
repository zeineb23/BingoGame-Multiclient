// src/gateway/rpc/RPCClientSetup.java
package src.gateway.rpc;

import src.server.rpc.RPCBingoServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCClientSetup {

    private RPCBingoServer gameServer;

    public RPCClientSetup(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            gameServer = new RPCBingoServer(socket);
        } catch (IOException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public RPCBingoServer getGameServer() {
        return gameServer;
    }

    // Other client-related methods
}
