package src.gateway;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import src.server.rmi.BingoGameInterface;

public class RMIClientSetup {

    private BingoGameInterface gameStub;

    public RMIClientSetup(String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            gameStub = (BingoGameInterface) registry.lookup("BingoGame");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public BingoGameInterface getGameStub() {
        return gameStub;
    }

    // Other client-related methods
}
