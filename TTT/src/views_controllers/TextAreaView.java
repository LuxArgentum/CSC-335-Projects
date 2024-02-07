// Matthew Song

package views_controllers;

/**
 * This is the beginning of one view of a Tic Tac Toe game using
 * two TextField objects and one TextArea. The other two views
 * of ButtonView and DrawingView follow the same structure as this.
 *
 * @author Rick Mercer and Matthew Song
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.OurObserver;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements OurObserver {

    private TicTacToeGame theGame;
    private TextArea message = new TextArea();
    private Label rowLabel = new Label("Enter Row");
    private Label colLabel = new Label("Enter Column");
    private Button moveButton = new Button("Make Move");
    private TextField rowField = new TextField();
    private TextField colField = new TextField();
    private VBox getMove = new VBox();
    private boolean gameOver = false;

    public TextAreaView(TicTacToeGame theModel) {
        theGame = theModel;
        initializePanel();
    }

    private void initializePanel() {

        message.setStyle("-fx-border-color: blue; border-width: 10;");

        Font font = Font.font("Space Mono", FontWeight.EXTRA_BOLD, 30);
        String boardString = getBoardString();
        message.setFont(font);
        message.setText(boardString);

        getMove.setPadding(new Insets(20, 20, 20, 20));
        getMove.setSpacing(10);

        getMove.getChildren().addAll(rowLabel, rowField, colLabel, colField, moveButton);

        this.setCenter(getMove);
        this.setTop(new Label("Options"));
        this.setBottom(message);

        registerHandlers();
    }

    private String getBoardString() {
        char[][] board = theGame.getTicTacToeBoard();
        String boardString = "";
        for (int r = 0; r < 3; r++) {
            boardString += "           ";
            for (int c = 0; c < 3; c++) {
                boardString += board[r][c] + "  ";
            }
            boardString = boardString.stripTrailing();
            boardString += "\n";
        }
        return boardString;
    }

    private void registerHandlers() {
        EventHandler<ActionEvent> buttonHandler = new buttonHandler();
        moveButton.setOnAction(buttonHandler);
    }

    private class buttonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            if (gameOver) return;
            if (rowField.getText().trim().equals("") || colField.getText().trim().equals("")) {
                moveButton.setText("Invalid Choice");
            } else if (!rowField.getText().trim().matches("[0-2]+") || !colField.getText().trim().matches("[0-2]+")) {
                moveButton.setText("Invalid Choice");
            } else {
                int row = Integer.parseInt(rowField.getText().trim());
                int col = Integer.parseInt(colField.getText().trim());
                if (row > 2 || row < 0 || col > 2 || col < 0) {
                    moveButton.setText("Invalid Choice");
                } else if (!theGame.available(row, col)) {
                    moveButton.setText("Invalid Choice");
                } else {
                    theGame.humanMove(row, col, false);
                    rowField.clear();
                    colField.clear();
                }
            }
        }
    }

    // This method is called by Observable's notifyObservers()
    @Override
    public void update(Object observable) {
        theGame = (TicTacToeGame) observable;
        updateView();
        if (theGame.didWin('X')) {
            moveButton.setText("X wins");
            gameOver = true;
        } else if (theGame.didWin('O')) {
            moveButton.setText("O wins");
            gameOver = true;
        } else if (theGame.tied()) {
            moveButton.setText("Tie");
            gameOver = true;
        }
//        System.out.println("update called from OurObservable TicTacToeGame " + theGame);
    }

    private void updateView() {
        message.setText(getBoardString());
    }
}