package ui;

import model.Num;
import model.Board;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import model.ScoreBoard;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;

public class Two048 {
    private static final String JSON_STORE = "./data/two048.json";
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

    // Create a list to save user's score
    private static JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    // ui start interface
    public Two048() throws Exception {
        System.out.println("Welcome to 2048 Java Version.");
        System.out.println("Press 'b' to start the game.");
        System.out.println("Press 'l' to load your last saved game.");
        start = scanner.next();
        if (start.equals("b")) {
            startGame();
        } else if (start.equals("l")) {
            resumeGame(playBoard);
        }

    }


    // Generate a new game board
    public Num[][] generateNew() {
        gameOver = false;
        return playBoard = new Num[][]{
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)},
                {new Num(0), new Num(0), new Num(0), new Num(0)}
        };
    }

    // Generate a random 2 or 4 on game board before player's next move
    public Num[][] generateNext(Num[][] mm) {
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
    public void checkStatus(Num[][] mm) {
        nonZero = true;
        for (Num[] m : mm) {
            for (Num n : m) {
                if (n.value == 0) {
                    nonZero = false;
                    break;
                }
            }
        }
    }

    // Move the board according to the user input
    // If it's not movable, warn the user with "Ineffective movement"
    // Otherwise output the board after user's movement
    // I'm using the suppress warnings because this part of the code is the core part for the user input.
    // It contains most cases to operate according to the user input.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Num[][] operation(Num[][] mm) throws Exception {
        op = scanner.next();
        setBackUps(mm);
        if (op.equals("m")) {
            saveTwo048();
            return mm;
        } else if (op.equals("w") && !arraysCompare(Board.actUp(backUp), backUp2)) {
            Board.actUp(mm);
        } else if (op.equals("s") && !arraysCompare(Board.actDown(backUp), backUp2)) {
            Board.actDown(mm);
        } else if (op.equals("a") && !arraysCompare(Board.actLeft(backUp), backUp2)) {
            Board.actLeft(mm);
        } else if (op.equals("d") && !arraysCompare(Board.actRight(backUp), backUp2)) {
            Board.actRight(mm);
        } else if (op.equals("p")) {
            gameOver = true;
            return mm;
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
    public boolean arraysCompare(Num[][] mm1, Num[][] mm2) {
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
    public void setBackUps(Num[][] mm) {
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

    // EFFECTS: Parse the json file to get the last 2048 board and score lsit
    public void jsonParser() throws Exception {
        JSONArray arr = JsonReader.readBoard();
        JSONArray score = JsonReader.readScore();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject object = arr.getJSONObject(i);
            Integer horizontal = Integer.parseInt(object.get("serial").toString().substring(0, 1)) - 1;
            Integer vertical = Integer.parseInt(object.get("serial").toString().substring(1, 2)) - 1;
            Integer value = Integer.parseInt((object.get("value").toString()));
            playBoard[horizontal][vertical] = new Num(value);
        }
        for (int i = 0; i < score.length(); i++) {
            JSONObject tempScore = score.getJSONObject(i);
            Integer temp = Integer.parseInt(tempScore.get("score").toString());
            Board.getScoreBoard().scoreList.set(i,temp);
        }

    }

    // EFFECTS: Create a new 2048 game
    public void startGame() throws Exception {
        generateNew();
        generateNext(playBoard);
        Board.printBoard(playBoard);

        while (!gameOver) {
            System.out.println("Press 'wasd' to move your board in the direction you want.");
            System.out.println("Press 'm' to save your game");
            System.out.println("Press 'p' to quit the game");
            operation(playBoard);
            Board.printBoard(playBoard);

        }
        System.out.println("You lose!");
        System.out.println("Your score:");
        Board.getScoreBoard().printScoreBoard();
    }

    // EFFECTS: Resume your last 2048 game
    public void resumeGame(Num[][] board) throws Exception {

        generateNew();
        try {
            jsonParser();
        } catch (Exception e) {
            System.out.println("No saved game found and we create a new one for you.");
            generateNext(playBoard);
        }
        Board.printBoard(playBoard);

        while (!gameOver) {
            System.out.println("Press 'wasd' to move your board in the direction you want.");
            System.out.println("Press 'm' to save your game");
            System.out.println("Press 'p' to quit the game");
            operation(playBoard);
            Board.printBoard(playBoard);

        }
        System.out.println("You lose!");
        System.out.println("Your score:");
        Board.getScoreBoard().printScoreBoard();
    }

    // EFFECTS: Save current 2048 game progress to JSON file
    public JSONObject toJson() {
        Num[][] board = Two048.playBoard;
        JSONObject jsonObject = new JSONObject();
        Integer serial = 11;
        for (Num[] l : board) {
            for (Num n : l) {
                JSONObject subObject = new JSONObject();
                subObject.put("serial", serial);
                subObject.put("value", n.value);
                jsonObject.accumulate("BOARD", subObject);
                serial++;
            }
            serial += 6;
        }
        ScoreBoard tempScoreBoard = Board.getScoreBoard();
        ArrayList<Integer> tempScoreList = tempScoreBoard.getScoreList();
        for (Integer s : tempScoreList) {
            JSONObject score = new JSONObject();
            score.put("score", s);
            jsonObject.accumulate("SCORELIST",score);
        }
        return jsonObject;
    }

    // EFFECTS: Save your 2048 game and print out the address for the saved JSON file
    public void saveTwo048() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}

