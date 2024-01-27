package src.gateway;
import java.net.Socket;

import src.common.PlayerGuess;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    private boolean processGuess(PlayerGuess guess) {
		return false;
        // Logic to process the guess, possibly communicating with the RMI server
        // Return true or false based on whether the guess is correct
    }
    
    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

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
            System.err.println("Error in ClientHandler: " + e.getMessage());
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

