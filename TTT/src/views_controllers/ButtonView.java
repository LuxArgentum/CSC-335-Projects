package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.OurObserver;
import model.TicTacToeGame;

public class ButtonView extends BorderPane implements OurObserver {

    private TicTacToeGame theGame;

    private GridPane buttonGrid;
    private Label gameStateLabel;

    private boolean gameOver = false;

    public ButtonView(TicTacToeGame theGame) {
        this.theGame = theGame;
        createGUI();
    }

    private void createGUI() {

        // Text at top that says "Options"
        Label optionLabel = new Label("Options");
        optionLabel.setFont(new Font("Arial", 15));
        optionLabel.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        optionLabel.setAlignment(Pos.BASELINE_LEFT);

        this.setTop(optionLabel);

        // 3x3 grid of buttons
        createButtonGrid();
        this.setCenter(buttonGrid);

        // Text at bottom that says "Click to make a move"
        Font gameStateFont = new Font("Arial", 20);
        gameStateLabel = new Label("Click to make a move");
        gameStateLabel.setFont(gameStateFont);
        gameStateLabel.setStyle("-fx-alignment: center;");
        this.setBottom(gameStateLabel);
    }

    private void createButtonGrid() {
        buttonGrid = new GridPane();
        Button[][] buttons = new Button[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button("_");
                buttons[row][col].setMinSize(70, 70);
                buttons[row][col].setOnAction(new ButtonHandler());
                buttonGrid.add(buttons[row][col], col, row);
            }
        }
        buttonGrid.setAlignment(javafx.geometry.Pos.CENTER);        // Center the buttons
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
    }

    @Override
    public void update(Object observable) {
        // TODO: Implement update() method
        theGame = (TicTacToeGame) observable;
        checkGameState();
    }

    private void checkGameState() {
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

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            // TODO: If button is clicked, change state to 'X'

            // Don't allow moves if game is over
            if (gameOver) return;

            Button buttonClicked = (Button) arg0.getSource();
//            ...

//            if (buttons[row][col] == buttonClicked) {
//
//            }
        }
    }
}
