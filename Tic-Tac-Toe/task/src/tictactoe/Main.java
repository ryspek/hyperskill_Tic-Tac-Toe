package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();

        char[][] field = prepareField(cells);
        drawField(field);
        promptMove(field, scanner);
        drawField(field);
    }

    private static char[][] prepareField(String cells) {
        int idx = 0;
        char[][] field = new char[3][3];
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if ('X' == cells.charAt(idx) || 'O' == cells.charAt(idx)) {
                    field[i][j] = cells.charAt(idx);
                } else {
                    field[i][j] = ' ';
                }
                idx++;
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

    private static boolean checkWinner(char ch, String cells) {
        int rows = 3;
        int cols = 3;
        int cellIdx = 0;

        // checking rows
        for (int i = 0; i < rows; i++) {
            int rowCount = 0;
            for (int j = 0; j < cols; j++) {
                if (cells.charAt(cellIdx) == ch) {
                    rowCount++;
                }
                cellIdx++;
            }
            if (rowCount == rows) {
                return true;
            }
        }

        // checking cols
        cellIdx = 0;
        for (int i = 0; i < cols; i++) {
            cellIdx = i;
            int colCount = 0;
            for (int j = 0; j < rows; j++) {
                if (cells.charAt(cellIdx) == ch) {
                    colCount++;
                    cellIdx += 3;
                }
            }
            if (colCount == cols) {
                return true;
            }
        }

        // checking diagonals
        if (cells.charAt(0) == ch && cells.charAt(4) == ch && cells.charAt(8) == ch
            || cells.charAt(2) == ch && cells.charAt(4) == ch && cells.charAt(6) == ch) {
            return true;
        }
        return false;
    }

}
