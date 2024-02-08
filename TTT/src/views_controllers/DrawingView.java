// Author: Matthew Song

package views_controllers;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.OurObserver;
import model.TicTacToeGame;

public class DrawingView extends BorderPane implements OurObserver {

    private TicTacToeGame theGame;
    private Label gameStateLabel;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean gameOver = false;

    public DrawingView(TicTacToeGame theGame) {
        this.theGame = theGame;
        createGUI();
    }

    private void createGUI() {
        // TODO: Implement DrawingView.createGUI()
        // Center is text that says "Make move"
        Font gameStateFont = new Font("Arial", 20);
        gameStateLabel = new Label("Make move");
        gameStateLabel.setFont(gameStateFont);
        gameStateLabel.setAlignment(Pos.CENTER);
        StackPane gameStatePane = new StackPane();
        gameStatePane.getChildren().add(gameStateLabel);
        gameStatePane.setAlignment(Pos.CENTER);
        gameStatePane.setPadding(new javafx.geometry.Insets(30, 10, 30, 10));

        // Bottom is drawing of the board

        canvas = new Canvas(70 * 3, 70 * 3);
        gc = canvas.getGraphicsContext2D();
        canvas.setStyle("-fx-alignment: center");

        drawBoard();

        VBox drawingPane = new VBox();
        drawingPane.getChildren().addAll(gameStatePane, canvas);
        drawingPane.setAlignment(Pos.CENTER);
        this.setCenter(drawingPane);

        registerHandlers(canvas);
    }

    private void updateBoard() {
        char[][] board = theGame.getTicTacToeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    // FIXME: Might need to add "file:" before the url on Eclipse
                    gc.drawImage(new Image("images/x.png", false), col * 70, row * 70, 70, 70);
                } else if (board[row][col] == 'O') {
                    gc.drawImage(new Image("images/o.png", false), col * 70, row * 70, 70, 70);
                }
            }
        }
    }

    private void drawBoard() {
        gc.strokeRect(0, 0, 70, 70);
        gc.strokeRect(70, 0, 70, 70);
        gc.strokeRect(140, 0, 70, 70);
        gc.strokeRect(0, 70, 70, 70);
        gc.strokeRect(70, 70, 70, 70);
        gc.strokeRect(140, 70, 70, 70);
        gc.strokeRect(0, 140, 70, 70);
        gc.strokeRect(70, 140, 70, 70);
        gc.strokeRect(140, 140, 70, 70);
    }

    @Override
    public void update(Object observable) {
        theGame = (TicTacToeGame) observable;
        updateBoard();
        checkGameState();
    }

    private void checkGameState() {
        if (theGame.stillRunning()) {
            gameOver = false;
            gameStateLabel.setText("Click to make a move");
        } else {
            if (theGame.didWin('X')) {
                gameStateLabel.setText("X wins");
                gameOver = true;
            } else if (theGame.didWin('O')) {
                gameStateLabel.setText("O wins");
                gameOver = true;
            } else if (theGame.tied()) {
                gameStateLabel.setText("Tie");
                gameOver = true;
            }
        }
    }

    private void registerHandlers(Canvas canvas) {

        canvas.setOnMousePressed(event -> {
            if (gameOver) return;

            double currentX = event.getX();
            double currentY = event.getY();
            int row = (int) (currentY / 70);
            int col = (int) (currentX / 70);

            if (row > 2 || col > 2) return;
            if (row < 0 || col < 0) return;
            if (theGame.stillRunning()) {
                theGame.humanMove(row, col, false);
            }
        });
    }
}
