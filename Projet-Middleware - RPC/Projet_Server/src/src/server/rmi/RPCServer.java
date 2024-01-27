// src/server/rpc/RPCServer.java
package src.server.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    private final RPCBingoServer bingoServer;

    public RPCServer(RPCBingoServer bingoServer) {
        this.bingoServer = bingoServer;
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                handleClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            String methodName = (String) in.readObject();
            Object[] args = (Object[]) in.readObject();

            switch (methodName) {
                case "startGame":
                    bingoServer.startGame();
                    break;
                case "drawNumber":
                    int result = bingoServer.drawNumber();
                    out.writeObject(result);
                    break;
                case "receivePlayerGuess":
                    String playerName = (String) args[0];
                    int guess = (int) args[1];
                    boolean guessResult = bingoServer.receivePlayerGuess(playerName, guess);
                    out.writeObject(guessResult);
                    break;
                case "checkWinner":
                    String winnerName = (String) args[0];
                    int score = (int) args[1];
                    boolean checkWinnerResult = bingoServer.checkWinner(winnerName, score);
                    out.writeObject(checkWinnerResult);
                    break;
                case "getWinnerScore":
                    int winnerScore = bingoServer.getWinnerScore();
                    out.writeObject(winnerScore);
                    break;
                case "getWinnerName":
                    String winner = bingoServer.getWinnerName();
                    out.writeObject(winner);
                    break;
                default:
                    // Handle unknown method
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
