// src/client/rpc/RPCBingoClient.java
package src.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BingoClient {

    private final String host;
    private final int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int score;
    private String name = "";

    public BingoClient(String host, int port) {
        this.host = host;
        this.port = port;
        score = 0;
    }

    private void connect() throws IOException {
        socket = new Socket(host, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    private void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    private void sendRequest(BingoRequest request) throws IOException {
        output.writeObject(request);
        output.flush();
    }

    private Object receiveResponse() throws IOException, ClassNotFoundException {
        return input.readObject();
    }

    private void startGameSession() throws ClassNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            connect();

            System.out.println("Enter your name:");
            name = scanner.nextLine();
            String input;
            int guess;
            boolean isRunning = true;
            int n = 0;

            while (isRunning && (n <= 9)) {
                System.out.println("**********************************");
                System.out.println("Score: " + score + "\t\tPlayer:" + name);
                System.out.println("\n1- Enter your guess (0-9) \n2- Enter 'best score' to see best score \n3- Enter 'exit' to quit:");
                input = scanner.nextLine();
                System.out.println(input);
                if ("exit".equalsIgnoreCase(input)) {
                    isRunning = false;
                    System.out.println("**********************************");
                    System.out.println("\tGame Over " + name + " !\nYour score is :" + score);
                    System.out.println("**********************************");
                    sendRequest(new BingoRequest(name, "exit", 0));
                    if (receiveResponse().equals("winner")) {
                        System.out.println("\tCongrats ! You got the best score !");
                    }
                    continue;
                }

                try {
                    if ("best score".equalsIgnoreCase(input)) {
                        System.out.println("**********************************");
                        sendRequest(new BingoRequest(name, "best score", 0));
                        BingoResponse response = (BingoResponse) receiveResponse();
                        System.out.println("\tBest score: " + response.getWinnerScore() + "\n\tBy player :" + response.getWinnerName());
                        System.out.println("**********************************");
                    } else {
                        guess = Integer.parseInt(input);
                        if (guess < 0 || guess > 9) {
                            System.out.println("Invalid guess. Please try again.");
                            continue;
                        }
                        n++;
                        sendRequest(new BingoRequest(name, "guess", guess));
                        BingoResponse response = (BingoResponse) receiveResponse();
                        if (response.isCorrect()) {
                            score++;
                            System.out.println("Correct guess!");
                        } else {
                            System.out.println("Incorrect guess.");
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (n == 10) {
                System.out.println("**********************************");
                System.out.println("\tGame Over " + name + " !\n\tYour score is :" + score);
                System.out.println("**********************************");
                sendRequest(new BingoRequest(name, "exit", 0));
                if (receiveResponse().equals("winner")) {
                    System.out.println("\tCongrats ! You got the best score !");
                }
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        BingoClient client = new BingoClient("localhost", 12345); // Replace with actual host and port
        client.startGameSession();
    }
}
