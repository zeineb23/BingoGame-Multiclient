package src.server.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BingoGameInterface extends Remote {
    void startGame() throws RemoteException;
    int drawNumber() throws RemoteException;
    boolean receivePlayerGuess(String playerName, int guess) throws RemoteException;
    //List<Integer> getDrawnNumbers() throws RemoteException;
    boolean checkWinner(String playerName, int score) throws RemoteException;
    int getWinnerScore() throws RemoteException;
    String getWinnerName() throws RemoteException;
    //void resetGame() throws RemoteException;
    //void registerPlayer(String playerName) throws RemoteException;
    //List<Integer> getPlayerScores() throws RemoteException;
    //void endGame() throws RemoteException;
    // Other necessary methods as per your game requirements
}
