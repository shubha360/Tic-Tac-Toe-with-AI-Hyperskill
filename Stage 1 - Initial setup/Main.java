package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new FirstStageGameplay();
    }
}

class FirstStageGameplay {

    private final char[][] mainGrid = new char[3][3];
    private final Scanner scanner = new Scanner(System.in);

    private int xCount = 0;
    private int oCount = 0;
    private char nextMove;
    private int occupiedCellCount = 0;

    public FirstStageGameplay() {
        playGame();
    }

    private void playGame() {

        System.out.print("Enter the cells: ");
        String initialInput = scanner.nextLine();
        toMainGrid(initialInput);

        this.nextMove = xCount == oCount ? 'X' : 'O';

        printMainGrid();
        processUserInput();
        printMainGrid();
        checkGrid();
    }

    private void toMainGrid(String str) {

        for (int i = 0; i < str.length(); i++) {

            char symbol = str.charAt(i) == '_' ? ' ' : str.charAt(i);

            if (symbol == 'X') {
                xCount++;
                occupiedCellCount++;
            } else if (symbol == 'O') {
                oCount++;
                occupiedCellCount++;
            }

            if (i <= 2) {
                this.mainGrid[0][i] = symbol;
            } else if (i <= 5) {
                this.mainGrid[1][i - 3] = symbol;
            } else {
                this.mainGrid[2][i - 6] = symbol;
            }
        }
    }

    private void printMainGrid() {

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(mainGrid[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private void processUserInput() {

        int row = -1;
        int column = -1;

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
        mainGrid[row - 1][column - 1] = nextMove;
        occupiedCellCount++;
    }

    private void checkGrid() {

        if ((mainGrid[0][0] == nextMove && mainGrid[0][1] == nextMove && mainGrid[0][2] == nextMove) ||
                (mainGrid[1][0] == nextMove && mainGrid[1][1] == nextMove && mainGrid[1][2] == nextMove) ||
                (mainGrid[2][0] == nextMove && mainGrid[2][1] == nextMove && mainGrid[2][2] == nextMove) ||
                (mainGrid[0][0] == nextMove && mainGrid[1][0] == nextMove && mainGrid[2][0] == nextMove) ||
                (mainGrid[0][1] == nextMove && mainGrid[1][1] == nextMove && mainGrid[2][1] == nextMove) ||
                (mainGrid[0][2] == nextMove && mainGrid[1][2] == nextMove && mainGrid[2][2] == nextMove) ||
                (mainGrid[0][0] == nextMove && mainGrid[1][1] == nextMove && mainGrid[2][2] == nextMove) ||
                (mainGrid[0][2] == nextMove && mainGrid[1][1] == nextMove && mainGrid[2][0] == nextMove)
        ) {
            System.out.println(nextMove + " wins");
        } else if (occupiedCellCount == 9) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }
}