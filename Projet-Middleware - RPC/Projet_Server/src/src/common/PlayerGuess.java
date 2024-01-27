package src.common;
import java.io.Serializable;

public class PlayerGuess implements Serializable {
    private String playerName;
    private int guessedNumber;

    public PlayerGuess(String playerName, int guessedNumber) {
        this.playerName = playerName;
        this.guessedNumber = guessedNumber;
    }

    // Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public int getGuessedNumber() {
        return guessedNumber;
    }

    // You might add additional methods or fields as needed
}

