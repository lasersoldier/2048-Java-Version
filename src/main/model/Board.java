package model;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    // The boolean to decide whether it needs more movements before the final graph.
    private static Boolean oneMore = true;

    // The temporary arraylist to save the Num for fixation after merging.
    private static ArrayList<Num> fixed;


    private static ScoreBoard scoreBoard = new ScoreBoard();


    // EFFECT: print out the visible graph of the board on the console.
    public static void printBoard(Num[][] board) {
        for (int n = 0; n <= 3; n++) {
            for (int k = 0; k <= 3; k++) {
                Integer space = 5 - String.valueOf(board[n][k].value).length();
                for (int s = space; s > 0; s--) {
                    System.out.print(" ");
                }
                for (int l = String.valueOf(board[n][k].value).length() - 1; l > 0; l--) {
                    System.out.print(" ");
                }
                System.out.print(board[n][k].value);
                space = 5 - String.valueOf(board[n][k].value).length();
                for (int s = space; s > 0; s--) {
                    System.out.print(" ");
                }
                System.out.print("|");
                //System.out.print(board[n][k].value + "|");
            }
            System.out.println("");
            System.out.println("----------------------------------------");
        }
    }


    // EFFECT: if two numbers are the same
    //          - instantiate a Num(0) at the front of the arraylist
    //          - add two Num's value and make it a Num after Num(0)
    //          otherwise, create an arraylist and put this two Num in original serial.
    public static ArrayList<Num> merge(Num a, Num b) {
        if (Math.abs(b.value - a.value) > 1) {
            return new ArrayList<Num>(Arrays.asList(a, b));
        } else {
            if (a.value != 0) {
                scoreBoard.addScore(a.value);
            }
            return new ArrayList<Num>(Arrays.asList(new Num(0), new Num(a.value * 2)));
        }
    }

    // EFFECT: if two numbers are the same
    //          - instantiate a Num(0) at the front of the arraylist
    //          - add two Num's value and make it a Num after Num(0)
    //          otherwise, create an arraylist and put this two Num in reversed serial.
    public static ArrayList<Num> revMerge(Num a, Num b) {
        if (Math.abs(b.value - a.value) > 1) {
            return new ArrayList<Num>(Arrays.asList(b, a));
        } else {
            if (a.value != 0) {
                scoreBoard.addScore(a.value);
            }
            return new ArrayList<Num>(Arrays.asList(new Num(0), new Num(a.value * 2)));
        }
    }


    // EFFECT: Move all numbers on the board to their most right.
    public static Num[][] moveRight(int q, Num[][] mat) {
        oneMore = true;
        while (oneMore) {
            oneMore = false;
            for (int o = 0; o <= 2; o++) {
                if (mat[q][o].value != 0 & mat[q][o + 1].value == 0) {
                    mat[q][o + 1] = mat[q][o];
                    mat[q][o] = new Num(0);
                    oneMore = true;
                } else {
                    continue;
                }
            }
        }
        return mat;
    }

    // EFFECT: Move all numbers on the board to their most left.
    public static Num[][] moveLeft(int q, Num[][] mat) {
        oneMore = true;
        while (oneMore) {
            oneMore = false;
            for (int o = 3; o >= 1; o--) {
                if (mat[q][o].value != 0 & mat[q][o - 1].value == 0) {
                    mat[q][o - 1] = mat[q][o];
                    mat[q][o] = new Num(0);
                    oneMore = true;
                } else {
                    continue;
                }
            }
        }
        return mat;
    }

    // EFFECT: Move all numbers on the board to their most up.
    public static Num[][] moveUp(int q, Num[][] mat) {
        oneMore = true;
        while (oneMore) {
            oneMore = false;
            for (int o = 3; o >= 1; o--) {
                if (mat[o][q].value != 0 & mat[o - 1][q].value == 0) {
                    mat[o - 1][q] = mat[o][q];
                    mat[o][q] = new Num(0);
                    oneMore = true;
                } else {
                    continue;
                }
            }
        }
        return mat;
    }

    // EFFECT: Move all numbers on the board to their most down.
    public static Num[][] moveDown(int q, Num[][] mat) {
        oneMore = true;
        while (oneMore) {
            oneMore = false;
            for (int o = 0; o <= 2; o++) {
                if (mat[o][q].value != 0 & mat[o + 1][q].value == 0) {
                    mat[o + 1][q] = mat[o][q];
                    mat[o][q] = new Num(0);
                    oneMore = true;
                } else {
                    continue;
                }
            }
        }
        return mat;
    }

    // EFFECT: Apply revMerge() on every number and the number on their left to get a merged graph
    public static Num[][] actLeft(Num[][] nn) {
        for (int k = 3; k >= 0; k--) {
            Num[][] fixed1 = moveLeft(k, nn);
            for (int p = 0; p <= 2; p++) {
                fixed = revMerge(fixed1[k][p], fixed1[k][p + 1]);
                fixed1[k][p] = fixed.get(1);
                fixed1[k][p + 1] = fixed.get(0);
            }
            Num[][] fixed2 = moveLeft(k, fixed1);
            nn = fixed2;
        }
        return nn;
    }

    // EFFECT: Apply merge() on every number and the number on their right to get a merged graph
    public static Num[][] actRight(Num[][] nn) {
        for (int k = 3; k >= 0; k--) {
            Num[][] fixed1 = moveRight(k, nn);
            for (int p = 2; p >= 0; p--) {
                fixed = merge(nn[k][p], nn[k][p + 1]);
                nn[k][p] = fixed.get(0);
                nn[k][p + 1] = fixed.get(1);
            }
            Num[][] fixed2 = moveRight(k, fixed1);
            nn = fixed2;
        }
        return nn;
    }

    // EFFECT: Apply revMerge() on every number and the number on their top to get a merged graph
    public static Num[][] actUp(Num[][] nn) {
        for (int k = 3; k >= 0; k--) {
            Num[][] fixed1 = moveUp(k, nn);
            for (int p = 0; p <= 2; p++) {
                fixed = revMerge(nn[p][k], nn[p + 1][k]);
                nn[p + 1][k] = fixed.get(0);
                nn[p][k] = fixed.get(1);
            }
            Num[][] fixed2 = moveUp(k, fixed1);
            nn = fixed2;
        }
        return nn;
    }

    // EFFECT: Apply revMerge() on every number and the number on their bot to get a merged graph
    public static Num[][] actDown(Num[][] nn) {
        for (int k = 3; k >= 0; k--) {
            Num[][] fixed1 = moveDown(k, nn);
            for (int p = 2; p >= 0; p--) {
                fixed = merge(nn[p][k], nn[p + 1][k]);
                nn[p][k] = fixed.get(0);
                nn[p + 1][k] = fixed.get(1);
            }
            Num[][] fixed2 = moveDown(k, fixed1);
            nn = fixed2;
        }
        return nn;
    }

    // EFFECT: Return the score board of this game.
    public static ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}







