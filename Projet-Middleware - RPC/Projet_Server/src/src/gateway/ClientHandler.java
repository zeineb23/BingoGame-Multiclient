// src/gateway/rpc/RPCClientHandler.java
package src.gateway.rpc;

import src.common.PlayerGuess;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCClientHandler implements Runnable {

    private final Socket clientSocket;

    public RPCClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private boolean processGuess(PlayerGuess guess) {
        return false; // Placeholder, replace with actual logic
        // Logic to process the guess, possibly communicating with the RPC server
        // Return true or false based on whether the guess is correct
    }

    @Override
    public void run() {
        try (
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {

            // Communication logic here
            // Example: Read a message from the client
            Object request = in.readObject();

            // Process the request based on your game protocol
            // For example, if it's a guess, validate it, and then respond accordingly
            if (request instanceof PlayerGuess) {
                PlayerGuess guess = (PlayerGuess) request;
                boolean isCorrect = processGuess(guess);
                out.writeObject(isCorrect);
            }
            // Example: read client requests, process them, and write responses back

        } catch (Exception e) {
            System.err.println("Error in RPCClientHandler: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
