package src.server.rmi;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import src.server.BingoServer;

public class RMIServer {
    public static void main(String[] args) {
        try {
            BingoServer server = new BingoServer();
            // BingoGameInterface stub = (BingoGameInterface) UnicastRemoteObject.exportObject(server, 0);
 
            BingoGameInterface stub = null;
            try {
                stub = (BingoGameInterface) UnicastRemoteObject.exportObject(server, 0);
            } catch (java.rmi.server.ExportException e) {
                stub = (BingoGameInterface) UnicastRemoteObject.toStub(server);
            }
            Registry registry = LocateRegistry.createRegistry(1313);
            registry.rebind("BingoGame", stub);
            System.out.println("Bingo RMI Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

