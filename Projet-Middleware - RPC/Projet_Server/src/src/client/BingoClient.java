package src.client;
import java.util.Scanner;

import src.gateway.RMIClientSetup;
import src.server.rmi.BingoGameInterface;

public class BingoClient {

    private RMIClientSetup rmiClientSetup;
    private BingoGameInterface gameStub;
    private int score;
    String name="";

    public BingoClient(String host, int port) {
        rmiClientSetup = new RMIClientSetup(host, port);
        gameStub = rmiClientSetup.getGameStub();
        score=0;     
    }

    private void startGameSession() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your name:");
            name = scanner.nextLine();
            String input;
            int guess;
            boolean isRunning = true;
            int n=0;

            while (isRunning&&(n<=9)) {
            	System.out.println("**********************************");
            	System.out.println("Score: "+score+"\t\tPlayer:"+name);
                System.out.println("\n1- Enter your guess (0-9) \n2- Enter 'best score' to see best score \n3- Enter 'exit' to quit:");
                input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    isRunning = false;
                    System.out.println("**********************************");
                    System.out.println("\tGame Over "+name+" !\nYour score is :"+score);
                    System.out.println("**********************************");
                	if(gameStub.checkWinner(name, score)) {
                		System.out.println("\tCongrats ! You got best score !");
                	}
                    continue;
                }

                try {
                	if("score".equalsIgnoreCase(input)) {
                		System.out.println("**********************************");
                        System.out.println("\tBest score: "+gameStub.getWinnerScore()+"\n\tBy player :"+gameStub.getWinnerName());
                        System.out.println("**********************************");
                	}else {
	                    guess = Integer.parseInt(input);
	                    if (guess < 0 || guess > 9) {
	                        System.out.println("Invalid guess. Please try again.");
	                        continue;
	                    }
	                    n++;
	                    // Sending guess to the server
	                    boolean result = gameStub.receivePlayerGuess(name, guess); // Replace "PlayerName" with actual player identifier
	                    if (result) {
	                    	score++;
	                    	System.out.println("Correct guess!");
	                    }else {
	                    	System.out.println("Incorrect guess.");
	                    }
                	}

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            if(n==10){
            	System.out.println("**********************************");
            	System.out.println("\tGame Over "+name+" !\n\tYour score is :"+score);
            	System.out.println("**********************************");
            	if(gameStub.checkWinner(name, score)) {
            		System.out.println("\tCongrats ! You got best score !");
            	}
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        BingoClient client = new BingoClient("localhost", 1313); // Replace with actual host and port
        client.startGameSession();
    }
}
