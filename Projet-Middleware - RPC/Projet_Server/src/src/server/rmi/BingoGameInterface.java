// src/server/rpc/RPCBingoGameInterface.java
package src.server.rpc;

import java.io.Serializable;

public interface RPCBingoGameInterface extends Serializable {
    void startGame();
    int drawNumber();
    boolean receivePlayerGuess(String playerName, int guess);
    boolean checkWinner(String playerName, int score);
    int getWinnerScore();
    String getWinnerName();
    // Other necessary methods as per your game requirements
}
