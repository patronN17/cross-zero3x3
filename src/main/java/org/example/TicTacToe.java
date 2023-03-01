package org.example;

import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = ' ';
    private static final int DOTS_TO_WIN = 3;

    private static char[][] field;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил!");
                break;
            }
        }
    }

    private static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int rowNumber;
        int colNumber;
        do {
            System.out.println("Ход игрока. Введите номер строки и столбца (например, 1 2):");
            rowNumber = scanner.nextInt() - 1;
            colNumber = scanner.nextInt() - 1;
        } while (!isValidCell(rowNumber, colNumber) || !isEmptyCell(rowNumber, colNumber));
        field[rowNumber][colNumber] = DOT_X;
    }

    private static void aiTurn() {
        int rowNumber;
        int colNumber;
        do {
            rowNumber = (int) (Math.random() * SIZE);
            colNumber = (int) (Math.random() * SIZE);
        } while (!isEmptyCell(rowNumber, colNumber));
        field[rowNumber][colNumber] = DOT_O;
        System.out.println("Ход компьютера: " + (rowNumber + 1) + " " + (colNumber + 1));
    }
    private static boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidCell(int rowNumber, int colNumber) {
        return rowNumber >= 0 && rowNumber < SIZE && colNumber >= 0 && colNumber < SIZE;
    }

    private static boolean isEmptyCell(int rowNumber, int colNumber) {
        return field[rowNumber][colNumber] == DOT_EMPTY;
    }

    private static boolean checkWin(char dot) {

        for (int i = 0; i < SIZE; i++) {
            if (field[0][i] == dot && field[1][i] == dot && field[2][i] == dot) {
                return true;
            }
        }


        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) {
            return true;
        }
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) {
            return true;
        }


        return false;
    }
}