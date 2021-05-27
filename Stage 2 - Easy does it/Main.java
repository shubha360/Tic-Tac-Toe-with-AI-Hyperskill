package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.letsPlay();
    }
}

class TicTacToe {

    protected final static char[][] mainGrid = new char[3][3];
    protected final Scanner scanner = new Scanner(System.in);

    int occupiedCellCount = 0;

    static StringBuilder rowTracker = new StringBuilder("000111222");
    static StringBuilder columnTracker = new StringBuilder("000111222");

    TicTacToe() {

        for (char[] arr : mainGrid) {
            Arrays.fill(arr,' ');
        }
    }

    void letsPlay() {

        printGrid();

        EasyCPU easyCPU = new EasyCPU();

        for (int i = 0; i < 9; i++) {

            if (i % 2 == 0) {
                processUserInput();
                printGrid();

                if (checkGrid('X')) {
                    System.out.println("X wins");
                    return;
                }
            } else {
                easyCPU.makeMove();
                printGrid();

                if (checkGrid('O')) {
                    System.out.println("O wins");
                    return;
                }
            }
        }
        System.out.println("Draw");
    }

    void printGrid() {

        System.out.println("---------");
        for (char[] arr : mainGrid) {
            System.out.print("| ");
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private void processUserInput() {

        int row;
        int column;

        while (true) {

            System.out.print("Enter the coordinates: ");

            try {
                row = scanner.nextInt();
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }

            if (row < 1 || row > 3 || column < 1 || column > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (mainGrid[row - 1][column - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                break;
            }
        }
        mainGrid[row - 1][column - 1] = 'X';
        rowTracker.deleteCharAt(rowTracker.indexOf(String.valueOf(row - 1)));
        columnTracker.deleteCharAt(columnTracker.indexOf(String.valueOf(column - 1)));
        occupiedCellCount++;
    }

    private boolean checkGrid(char symbol) {

        return (mainGrid[0][0] == symbol && mainGrid[0][1] == symbol && mainGrid[0][2] == symbol) ||
                (mainGrid[1][0] == symbol && mainGrid[1][1] == symbol && mainGrid[1][2] == symbol) ||
                (mainGrid[2][0] == symbol && mainGrid[2][1] == symbol && mainGrid[2][2] == symbol) ||
                (mainGrid[0][0] == symbol && mainGrid[1][0] == symbol && mainGrid[2][0] == symbol) ||
                (mainGrid[0][1] == symbol && mainGrid[1][1] == symbol && mainGrid[2][1] == symbol) ||
                (mainGrid[0][2] == symbol && mainGrid[1][2] == symbol && mainGrid[2][2] == symbol) ||
                (mainGrid[0][0] == symbol && mainGrid[1][1] == symbol && mainGrid[2][2] == symbol) ||
                (mainGrid[0][2] == symbol && mainGrid[1][1] == symbol && mainGrid[2][0] == symbol);
    }
}

class EasyCPU extends TicTacToe {

    void makeMove() {

        System.out.println("Making move level \"easy\"");

        Random random = new Random();

        int x, y, row, column;

        while (true) {

            // Determining the row
            x = random.nextInt(rowTracker.length());
            row = rowTracker.charAt(x) - 48;

            //Determining the column
            y = random.nextInt(columnTracker.length());
            column = columnTracker.charAt(y) - 48;

            if (mainGrid[row][column] == ' ') {
                break;
            }
        }
        rowTracker.deleteCharAt(x);
        columnTracker.deleteCharAt(y);
        mainGrid[row][column] = 'O';
    }
}