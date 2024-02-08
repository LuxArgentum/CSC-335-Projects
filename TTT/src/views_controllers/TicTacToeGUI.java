// Matthew Song

package views_controllers;

/*
  Play TicTacToe the computer that can have different AIs to beat you.
  Select the Options menus to begin a new game, switch strategies for
  the computer player (BOT or AI), and to switch between the two views.
  <p>
  This class represents an event-driven program with a graphical user
  interface as a controller between the view and the model. It has
  event handlers to mediate between the view and the model.
  <p>
  This controller employs the Observer design pattern that updates two
  views every time the state of the Tic Tac Toe game changes:
  <p>
  1) whenever you make a move by clicking a button or an area of either view
  2) whenever the computer AI makes a move
  3) whenever there is a win or a tie
  <p>
  You can also select two different strategies to play against from the menus

  @author Rick Mercer and Matthew Song
 */

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.OurObserver;
import model.RandomAI;
import model.TicTacToeGame;

public class TicTacToeGUI extends Application {

    public static final int width = 254;
    public static final int height = 360;
    private TicTacToeGame theGame;
    private OurObserver currentView;
    // TBA:
    private OurObserver buttonView;
    private OurObserver drawingView;
    private OurObserver textAreaView;
    private BorderPane window;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Tic Tac Toe");
        window = new BorderPane();
        Scene scene = new Scene(window, width, height);
        initializeGameForTheFirstTime();

        // TBA: Set up the views in Sprint 2
        buttonView = new ButtonView(theGame);
        drawingView = new DrawingView(theGame);
        textAreaView = new TextAreaView(theGame);

        theGame.addObserver(buttonView);
        theGame.addObserver(drawingView);
        theGame.addObserver(textAreaView);

        // FIXME: Change the view back to textAreaView
        setViewTo(buttonView);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set the game to the default of an empty board and the random AI.
     */
    public void initializeGameForTheFirstTime() {
        theGame = new TicTacToeGame();
        // This event driven program will always have
        // a computer player who takes the second turn
        theGame.setComputerPlayerStrategy(new RandomAI());
    }

    private void setViewTo(OurObserver newView) {
        window.setCenter(null);
        currentView = newView;
        window.setCenter((Node) currentView);
    }

}