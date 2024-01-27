// src/server/rpc/RPCBingoServer.java
package src.server.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RPCBingoServer {
    private List<Integer> bingoNumbers;
    private boolean gameStarted;
    private int bestScore = 0;
    private String player = "";

    public RPCBingoServer() {
        bingoNumbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) { // Assuming a 75-ball bingo
            bingoNumbers.add(i);
        }
        gameStarted = true;
    }

    public void startGame() {
        Collections.shuffle(bingoNumbers);
        gameStarted = true;
        // Additional code to notify clients that the game has started
    }

    public int drawNumber() {
        if (!bingoNumbers.isEmpty() && gameStarted) {
            return bingoNumbers.remove(0); // Draw the top number
        }
        return -1; // Indicate that no numbers are left or game hasn't started
    }

    public boolean receivePlayerGuess(String playerName, int guess) {
        int n = drawNumber();
        return guess == n;
    }

    public boolean checkWinner(String playerName, int score) {
        if (score >= bestScore) {
            bestScore = score;
            player = playerName;
            return true;
        }
        return false;
    }

    public String getWinnerName() {
        return player;
    }

    public int getWinnerScore() {
        return bestScore;
    }

    public static void main(String[] args) {
        try {
            RPCServer rpcServer = new RPCServer(new RPCBingoServer());
            rpcServer.start(1314); // Choose a different port
            System.out.println("RPC Bingo Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
