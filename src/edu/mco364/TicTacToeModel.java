package edu.mco364;

import java.awt.*;
import java.util.Scanner;

/**
 * Many of the methods in the currrent code were developed specifcally for a command-line front end. SOme are not needed for a GUI based and some some need to be changed
 * to represent a more geenric "model" of the game
 *
 * Make the code yours...change as needed and following best practices
 */
public class TicTacToeModel {
    public enum Cell {
        NONE, O, X
    };

    private boolean isPlayerOneMove;
    private Cell[][] grid;

    TicTacToeModel() {
        grid = new Cell[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Cell.NONE;
            }
        }
        isPlayerOneMove = true;
    }

    public void displayBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(convertToString(grid[row][col]));
            }
            System.out.println();
        }
    }

    /**
     * Sets cell a specified location. Returnes true if successful (i.e. was a legal move)
     * @param row
     * @param col
     * @param cell
     * @return
     */
    boolean setCell(int row, int col, Cell cell) {
        if (grid[row][col] == Cell.NONE) {
            grid[row][col] = cell;
            return true;
        }
        return false;
    }

    Point prompt() {
        Scanner kb = new Scanner(System.in);
        int col, row;
        do {
            System.out.println("Enter your Move (A1 - C3, Mr." + (isPlayerOneMove ? "X" : "O"));
//TODO Validation
            String userMove = kb.next();
            col = userMove.charAt(0) - 'A';
            row = userMove.charAt(1) - '1';
        }
        while (!isValidMove(col, row));
        return new Point(col, row);
    }

    /**
     * Checks that move in on board (0-3) and cell is available
     */
    boolean isValidMove(int col, int row) {
        return false;
        //TODO
    }

    void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    public void promptAndMakeMove() {
        Point move = prompt();
        setCell(move.y, move.x, isPlayerOneMove ? Cell.X : Cell.O);
        //TODO make safe ..add validation A-C, 1-3
    }

    public boolean isGameOver() {
        // diag #1
        if (grid[0][0] != Cell.NONE
                && grid[0][0] == grid[1][1]
                && grid[2][2] == grid[1][1]) {
            return true;
        }

        //TO diag2 , rows, cols, no legal moves
        return false;
    }

    public Cell getWinner() {
        if (isGameOver()) {
            return currentPlayer();
        }
        return Cell.NONE;
    }

    private String convertToString(Cell cell) {
        switch (cell) {
            case NONE:
                return " ";
            case O:
                return "O";
            case X:
                return "X";
            default:
                throw new RuntimeException(cell.toString());
        }
    }

    public Cell currentPlayer() {
        return isPlayerOneMove ? Cell.X : Cell.O;
    }
}
