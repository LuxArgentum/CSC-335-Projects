package model;

import java.util.ArrayList;
import java.util.List;

public class AdvancedAI implements TicTacToeStrategy {
    @Override
    public OurPoint desiredMove(TicTacToeGame theGame) {
        char[][] currBoard = theGame.getTicTacToeBoard().clone();
        int row = -1;
        int col = -1;
        OurPoint point = new OurPoint(row, col);


        return point;
    }

    private List<String> availableMoves(TicTacToeGame theGame, char[][] currBoard) {
        int size = theGame.getTicTacToeBoard().length;
        List<String> moves = new ArrayList<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (currBoard[r][c] == '_')
                    moves.add(r + " " + c);
            }
        }
        return moves;
    }

    private void minMax(TicTacToeGame theGame, char[][] currBoard, char player) {
        char opponent;
        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        int score;
        if (theGame.didWin(player)) {
            score = 10;
        } else if (theGame.didWin(opponent)) {
            score = -10;
        } else if (theGame.maxMovesRemaining() == 0) {
            score = 0;
        }


    }
}
