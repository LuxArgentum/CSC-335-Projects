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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.IntermediateAI;
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
        setObservers();

        menu();
        setViewTo(textAreaView);
        stage.setScene(scene);
        stage.show();
    }

    private void setObservers() {
        buttonView = new ButtonView(theGame);
        drawingView = new DrawingView(theGame);
        textAreaView = new TextAreaView(theGame);

        theGame.addObserver(buttonView);
        theGame.addObserver(drawingView);
        theGame.addObserver(textAreaView);
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

    private void menu() {

        // Menu at top that says "Options"
        // Options: New Game, Strategies, Views
        // Strategies: Random, IntermediateAI
        // Views: TextAreaView, ButtonView, DrawingView

        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("Options");

        MenuItem newGame = new MenuItem("New Game");
        Menu strategies = new Menu("Strategies");
        Menu views = new Menu("Views");

        MenuItem random = new MenuItem("Random");
        MenuItem intermediateAI = new MenuItem("IntermediateAI");
        MenuItem textAreaView = new MenuItem("TextAreaView");
        MenuItem buttonView = new MenuItem("ButtonView");
        MenuItem drawingView = new MenuItem("DrawingView");

        strategies.getItems().addAll(random, intermediateAI);
        views.getItems().addAll(textAreaView, buttonView, drawingView);

        options.getItems().addAll(newGame, strategies, views);

        menuBar.getMenus().add(options);

        // Change font and style of menuBar
        menuBar.setStyle("-fx-font-size: 16;" +
                "-fx-font-family: 'JetBrains Mono'");

        window.setTop(menuBar);

        menuHandler(newGame, random, intermediateAI, textAreaView, buttonView, drawingView);
    }

    private void menuHandler(MenuItem newGame, MenuItem random, MenuItem intermediateAI, MenuItem textAreaView, MenuItem buttonView, MenuItem drawingView) {
        EventHandler<ActionEvent> menuHandler = new MenuHandler();
        newGame.setOnAction(menuHandler);
        random.setOnAction(menuHandler);
        intermediateAI.setOnAction(menuHandler);
        textAreaView.setOnAction(menuHandler);
        buttonView.setOnAction(menuHandler);
        drawingView.setOnAction(menuHandler);
    }

    private class MenuHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            String menuItem = ((MenuItem) arg0.getSource()).getText();
            switch (menuItem) {
                case "New Game":
                    theGame.startNewGame();
                    break;
                case "Random":
                    theGame.setComputerPlayerStrategy(new RandomAI());
                    break;
                case "IntermediateAI":
                    theGame.setComputerPlayerStrategy(new IntermediateAI());
                    break;
                case "TextAreaView":
                    setViewTo(textAreaView);
                    break;
                case "ButtonView":
                    setViewTo(buttonView);
                    break;
                case "DrawingView":
                    setViewTo(drawingView);
                    break;
            }
        }
    }

}