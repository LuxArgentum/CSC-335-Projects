// Matthew Song

package views_controllers;

import model.TicTacToeGame;

import java.util.Scanner;

public class RunTTT {
    public static void main(String[] args) {
        boolean testing = false;
        TicTacToeGame game = new TicTacToeGame();
        Scanner scanner = new Scanner(System.in);
        if (testing) game.setComputerPlayerStrategy(new model.IntermediateAI());
        game.startNewGame();

        // Loop through the game until it is over
        while (game.stillRunning()) {
            playGame(scanner, game);
        }
    }

    private static void playGame(Scanner scanner, TicTacToeGame game) {
        System.out.print("Enter row and column: ");
        int row = 0;
        int col = 0;
        try {
            String[] inputString = scanner.nextLine().split(" ");
            row = Integer.parseInt(inputString[0]);
            col = Integer.parseInt(inputString[1]);

            if (row > 2 || row < 0 || col > 2 || col < 0) {
                System.out.println("Invalid input, try again.");
            }
            else if (!game.available(row, col)) {
                System.out.println("Square taken, try again.");
            } else {
                game.humanMove(row, col, false);
            }
        } catch (Exception e) {
            System.out.println("Invalid input, try again.");
        }

        gameState(game);
    }

    private static void gameState(TicTacToeGame game) {
        System.out.println(game.toString() + "\n");

        if (game.didWin('X')) {
            System.out.println("X wins");
        } else if (game.didWin('O')) {
            System.out.println("O wins");
        } else if (game.tied()) {
            System.out.println("Tie");
        }
    }
}
