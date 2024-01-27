package src.server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.server.rmi.BingoGameInterface;

public class BingoServer extends UnicastRemoteObject implements BingoGameInterface {

    private List<Integer> bingoNumbers;
    private boolean gameStarted;
    private int bestScore = 0;
    private String player = "";

    public BingoServer() throws RemoteException {
        super();
        bingoNumbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) { // Assuming a 75-ball bingo
            bingoNumbers.add(i);
        }
        gameStarted = true;
    }
    
    @Override
    public void startGame() throws RemoteException {
        Collections.shuffle(bingoNumbers);
        gameStarted = true;
        // Additional code to notify clients that the game has started
    }

    @Override
    public int drawNumber() throws RemoteException {
        if (!bingoNumbers.isEmpty() && gameStarted) {
            return bingoNumbers.remove(0); // Draw the top number
        }
        return -1; // Indicate that no numbers are left or game hasn't started
    }

	@Override
	public boolean receivePlayerGuess(String playerName, int guess) throws RemoteException {
		int n = drawNumber();
		if(guess == n) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkWinner(String playerName, int score) throws RemoteException {
			if(score>=bestScore) {
				bestScore=score;
				player=playerName;
				return true;
			}
		return false;
	}
	
	@Override
	public String getWinnerName() throws RemoteException {
		return player;
	}
	
	@Override
	public int getWinnerScore() throws RemoteException {
		return bestScore;
	}

}

