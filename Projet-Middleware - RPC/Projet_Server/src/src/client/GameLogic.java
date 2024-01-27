package src.client;
public class GameLogic {

    private int currentScore;
    private ClientNetworkHandler networkHandler;

    public GameLogic(ClientNetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
        currentScore = 0;
    }

    public void makeGuess(int number) {
        try {
            networkHandler.sendGuess(number);
            boolean isCorrect = networkHandler.receiveResponse();
            if (isCorrect) {
                currentScore++;
                System.out.println("Correct! Your score: " + currentScore);
            } else {
                System.out.println("Incorrect. Try again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Additional game logic methods
}
