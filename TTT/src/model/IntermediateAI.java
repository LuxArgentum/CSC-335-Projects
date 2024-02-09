/**
 * Rick suggests, the IntermediateAI first check to stop a win of the opponent,
 * then look for its own win. If neither is found, select any other open
 * spot randomly. You may use any other strategy as long as it beats RandomAI.
 *
 * @authors Rick Mercer and Matthew Song
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntermediateAI implements TicTacToeStrategy {
    private int size = 3;

    @Override
    public OurPoint desiredMove(TicTacToeGame theGame) {
        char[][] currBoard = theGame.getTicTacToeBoard().clone();
        this.size = currBoard.length;
        return chooseMove(currBoard, 'O');
    }

    private OurPoint chooseMove(char[][] currBoard, char playerChar) {
        char enemyChar;
        if (playerChar == 'O') {
            enemyChar = 'X';
        } else {
            enemyChar = 'O';
        }

        Map<String, Integer> winningMoves = getWinningMoves(currBoard, playerChar, enemyChar);
        Map<String, Integer> enemyWinningMoves = getWinningMoves(currBoard, enemyChar, playerChar);

        if (winningMoves.isEmpty()) {
            if (enemyWinningMoves.isEmpty()) {
                return randomMoves(currBoard);
            }

            String tieMove = "";
            int highVal = -1;
            for (String key : enemyWinningMoves.keySet()) {
                if (enemyWinningMoves.get(key) > highVal) {
                    highVal = enemyWinningMoves.get(key);
                    tieMove = key;
                }
            }

            return findCoordinates(currBoard, tieMove);
        }

        String winningMove = "";
        int highVal = -1;
        for (String key : winningMoves.keySet()) {
            if (winningMoves.get(key) > highVal) {
                highVal = winningMoves.get(key);
                winningMove = key;
            }
        }

        String enemyWinningMove = "";
        int enemyHighVal = -1;
        for (String key : enemyWinningMoves.keySet()) {
            if (enemyWinningMoves.get(key) > enemyHighVal) {
                enemyHighVal = enemyWinningMoves.get(key);
                enemyWinningMove = key;
            }
        }

        if (highVal != 2 && enemyHighVal == 2) {
            return findCoordinates(currBoard, enemyWinningMove);
        }

        return findCoordinates(currBoard, winningMove);
    }

    private OurPoint randomMoves(char[][] currBoard) {
        List<String> moves = availableMoves(currBoard);
        int random = (int) (Math.random() * moves.size());
        String[] move = moves.get(random).split(" ");
        return new OurPoint(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
    }

    private OurPoint findCoordinates(char[][] currBoard, String winningMove) {
        String[] move = winningMove.split(" ");
        int row = 0;
        int col = 0;
        switch (move[0]) {
            case "Row" -> {
                for (int c = 0; c < size; c++) {
                    if (currBoard[Integer.parseInt(move[1])][c] == '_') {
                        row = Integer.parseInt(move[1]);
                        col = c;
                    }
                }
            }
            case "Col" -> {
                for (int r = 0; r < size; r++) {
                    if (currBoard[r][Integer.parseInt(move[1])] == '_') {
                        row = r;
                        col = Integer.parseInt(move[1]);
                    }
                }
            }
            case "Dia" -> {
                if (Integer.parseInt(move[1]) == 0) {
                    for (int r = 0; r < size; r++) {
                        if (currBoard[r][r] == '_') {
                            row = r;
                            col = r;
                        }
                    }
                } else {
                    for (int r = size - 1; r >= 0; r--) {
                        if (currBoard[r][r] == '_') {
                            row = r;
                            col = r;
                        }
                    }
                }
            }
        }
        return new OurPoint(row, col);
    }

    private Map<String, Integer> getWinningMoves(char[][] currBoard, char playerChar, char enemyChar) {
        Map<String, Integer> moves = new HashMap<>();

        moves = fillRows(playerChar, enemyChar, currBoard, moves);
        moves = fillCols(playerChar, enemyChar, currBoard, moves);
        moves = fillDiagonals(playerChar, enemyChar, currBoard, moves);
        return moves;
    }

    private Map<String, Integer> fillRows(char playerChar, char enemyChar, char[][] board, Map<String, Integer> moves) {
        for (int r = 0; r < size; r++) {
            boolean enemyBlocking = false;
            int playerCount = 0;
            for (int c = 0; c < size; c++)
                if (board[r][c] == enemyChar)
                    enemyBlocking = true;
                else if (board[r][c] == playerChar)
                    playerCount++;
            if (!enemyBlocking) {
                moves.put("Row " + r, playerCount);
            }
        }
        return moves;
    }

    private Map<String, Integer> fillCols(char playerChar, char enemyChar, char[][] board, Map<String, Integer> moves) {
        for (int c = 0; c < size; c++) {
            boolean enemyBlocking = false;
            int playerCount = 0;
            for (int r = 0; r < size; r++)
                if (board[r][c] == enemyChar)
                    enemyBlocking = true;
                else if (board[r][c] == playerChar)
                    playerCount++;
            if (!enemyBlocking) {
                moves.put("Col " + c, playerCount);
            }
        }
        return moves;
    }

    private Map<String, Integer> fillDiagonals(char playerChar, char enemyChar, char[][] board, Map<String, Integer> moves) {
        // Check Diagonal from upper left to lower right
        boolean enemyBlocking = false;
        int playerCount = 0;
        for (int r = 0; r < size; r++)
            if (board[r][r] == enemyChar) {
                enemyBlocking = true;
            } else if (board[r][r] == playerChar) {
                playerCount++;
            }
        if (!enemyBlocking) {
            moves.put("Dia " + 0, playerCount);
        }

        // Check Diagonal from upper right to lower left
        enemyBlocking = false;
        playerCount = 0;
        for (int r = size - 1; r >= 0; r--)
            if (board[r][r] == enemyChar) {
                enemyBlocking = true;
            } else if (board[r][r] == playerChar) {
                playerCount++;
            }
        if (!enemyBlocking) {
            moves.put("Dia " + 1, playerCount);
        }

        return moves;
    }

    private List<String> availableMoves(char[][] currBoard) {
        List<String> moves = new ArrayList<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (currBoard[r][c] == '_')
                    moves.add(r + " " + c);
            }
        }
        return moves;
    }
}
