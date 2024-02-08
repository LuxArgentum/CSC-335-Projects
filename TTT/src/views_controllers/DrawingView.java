package views_controllers;

import model.OurObserver;
import model.TicTacToeGame;

public class DrawingView implements OurObserver {

    private TicTacToeGame theGame;

    public DrawingView(TicTacToeGame theGame) {
        this.theGame = theGame;
        createGUI();
    }

    private void createGUI() {
        // TODO: Implement DrawingView.createGUI()

    }

    @Override
    public void update(Object observable) {
        // TODO: Implement DrawingView.update()

    }
}
