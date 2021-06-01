package tictactoe;

import java.util.Scanner;

public class Main {

    private static final char X = 'X';
    private static final char O = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
    }

    private static void playGame(Scanner scanner) {
        char[][] field = prepareField();
        drawField(field);
        while (!checkForWinner(field, X) && !checkForWinner(field, O)) {
            if (isDraw(field)) {
                System.out.println("Draw");
                break;
            }
            promptMove(field, scanner);
        }
        if (checkForWinner(field, X)) {
            System.out.println("X wins");
            return;
        }
        if (checkForWinner(field, O)) {
            System.out.println("O wins");
            return;
        }
    }

    private static char[][] prepareField() {
        char[][] field = new char[3][3];
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                field[i][j] = ' ';
            }
        }
        return field;
    }

    private static void drawField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void promptMove(char[][] field, Scanner scanner) {
        System.out.print("Enter the coordinates: ");
        String coordinates = scanner.nextLine();
        char row = coordinates.charAt(0);
        char col = coordinates.charAt(coordinates.length() - 1);

        checkInput(field, row, col, scanner);
    }

    private static void checkInput(char[][] field, char row, char col, Scanner scanner) {
        int rowNum = Character.getNumericValue(row);
        int colNum = Character.getNumericValue(col);
        if (!isNumber(row) || !isNumber(col)) {
            System.out.println("You should enter numbers!");
            promptMove(field, scanner);
            return;
        }
        if ((rowNum < 1 || rowNum > 3) || (colNum < 1 || colNum > 3)) {
            System.out.println("Coordinates should be from 1 to 3!");
            promptMove(field, scanner);
            return;
        }
        if (isCellOccupied(field, rowNum, colNum)) {
            System.out.println("This cell is occupied! Choose another one!");
            promptMove(field, scanner);
            return;
        }
        setSymbol(field, rowNum, colNum);
        drawField(field);
    }

    private static boolean isNumber(char ch) {
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCellOccupied(char[][] field, int row, int col) {
        int rowNum = --row;
        int colNum = --col;
        if ('X' == field[rowNum][colNum] || 'O' == field[rowNum][colNum]) {
            return true;
        }
        return false;
    }

    private static void setSymbol(char[][] field, int row, int col) {
        char x = 'X';
        char o = 'O';
        if((countChar(x, field) + countChar(o, field)) % 2 == 0) {
            field[--row][--col] = x;
            return;
        }
        field[--row][--col] = o;
    }

    private static int countChar(char ch, char[][] field) {
        int count = 0;

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if (field[i][j] == ch) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isDraw(char[][] field) {
        return 9 == (countChar(X, field) + countChar(O, field));
    }

    private static boolean checkForWinner(char[][] field, char ch) {
        int rows = field.length;
        int cols = field[0].length;

        // checking rows
        for (int i = 0; i < rows; i++) {
            int rowCount = 0;
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == ch) {
                    rowCount++;
                }
            }
            if (rowCount == rows) {
                return true;
            }
        }

        // checking cols
        for (int i = 0; i < cols; i++) {
            int colCount = 0;
            for (int j = 0; j < rows; j++) {
                if (field[j][i] == ch) {
                    colCount++;
                }
            }
            if (colCount == cols) {
                return true;
            }
        }

        // checking diagonals
        if ((field[0][0] == ch && field[1][1] == ch && field[2][2] == ch)
            || (field[2][0] == ch && field[1][1] == ch && field[0][2] == ch)) {
            return true;
        }
        return false;
    }

}
