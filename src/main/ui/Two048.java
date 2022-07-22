package ui;

import model.Num;
import model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Two048 {
    // The boolean to decide if the game is over
    private static Boolean gameOver;

    // Scanner
    static Scanner scanner = new Scanner(System.in);

    // String to start the game
    private static String start;

    // The user's input operation
    private static String op;

    // The game board
    private static Num[][] playBoard;

    // The boolean to decide if there are no more zero's on the board
    private static Boolean nonZero = false;

    // Backup board to test if the game is over of if it's movable on one direction
    private static Num[][] backUp = new Num[4][4];
    private static Num[][] backUp2 = new Num[4][4];
    private static Num[][] backUp3 = new Num[4][4];
    private static Num[][] backUp4 = new Num[4][4];
    private static Num[][] backUp5 = new Num[4][4];
    private static Num[][] backUp6 = new Num[4][4];

    // ui start interface
    public Two048() {
        System.out.println("Welcome to 2048 Java Version.");
        System.out.println("Press 'b' to start the game.");
        start = scanner.next();
        if (start.equals("b")) {
            generateNew();
            generateNext(playBoard);
            Board.printBoard(playBoard);
            while (!gameOver) {
                System.out.println("Press 'wasd' to move your board in the direction you want.");
                operation(playBoard);
                Board.printBoard(playBoard);
            }
            System.out.println("You lose!");
        }

    }


    // Generate a new game board
    public static Num[][] generateNew() {
        gameOver = false;
        return playBoard = new Num[][]{
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)}
        };
    }

    // Generate a random 2 or 4 on game board before player's next move
    public static Num[][] generateNext(Num[][] mm) {
        int xdirection = (int) (Math.random() * 4);
        int ydirection = (int) (Math.random() * 4);
        int randomValue = (1 + (int) (Math.random() * 2)) * 2;
        if (!nonZero) {
            if (mm[xdirection][ydirection].value == 0) {
                mm[xdirection][ydirection].value = randomValue;
                return mm;
            } else {
                generateNext(mm);
            }
        } else {
            return mm;
        }
        return mm;
    }

    // Check if there is space to generate new random number
    public static void checkStatus(Num[][] mm) {
        nonZero = true;
        for (Num[] m : mm) {
            for (Num n : m) {
                if (n.value == 0) {
                    nonZero = false;
                }
            }
        }
    }

    // Move the board according to the user input
    // If it's not movable, warn the user with "Ineffective movement"
    // Otherwise output the board after user's movement
    public static Num[][] operation(Num[][] mm) {
        op = scanner.next();
        setBackUps(mm);
        if (op.equals("w") && !arraysCompare(Board.actUp(backUp), backUp2)) {
            Board.actUp(mm);
        } else if (op.equals("s") && !arraysCompare(Board.actDown(backUp), backUp2)) {
            Board.actDown(mm);
        } else if (op.equals("a") && !arraysCompare(Board.actLeft(backUp), backUp2)) {
            Board.actLeft(mm);
        } else if (op.equals("d") && !arraysCompare(Board.actRight(backUp), backUp2)) {
            Board.actRight(mm);
        } else if (arraysCompare(Board.actUp(backUp3), Board.actDown(backUp5))
                && arraysCompare(Board.actLeft(backUp4), Board.actRight(backUp6))) {
            gameOver = true;
        } else {
            System.out.println("Ineffective movement.");
            return mm;
        }

        checkStatus(mm);
        generateNext(mm);
        return mm;
    }

    // Check if two board are the same
    public static boolean arraysCompare(Num[][] mm1, Num[][] mm2) {
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (mm1[i][k].value == mm2[i][k].value) {
                    result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    // Create 6 backups for the game board
    public static void setBackUps(Num[][] mm) {
        for (int i = 0; i < 4; i++) {
            backUp[i] = new Num[4];
            backUp2[i] = new Num[4];
            backUp3[i] = new Num[4];
            backUp4[i] = new Num[4];
            backUp5[i] = new Num[4];
            backUp6[i] = new Num[4];
            System.arraycopy(mm[i], 0, backUp[i], 0, backUp.length);
            System.arraycopy(mm[i], 0, backUp2[i], 0, backUp.length);
            System.arraycopy(mm[i], 0, backUp3[i], 0, backUp.length);
            System.arraycopy(mm[i], 0, backUp4[i], 0, backUp.length);
            System.arraycopy(mm[i], 0, backUp5[i], 0, backUp.length);
            System.arraycopy(mm[i], 0, backUp6[i], 0, backUp.length);
        }
    }
}
