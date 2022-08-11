package model;

import java.util.ArrayList;

public class ScoreBoard {
    // I'm using suppress warnings because I want the other method to change the list directly
    @SuppressWarnings({"checkstyle:VisibilityModifier", "checkstyle:SuppressWarnings"})
    public ArrayList<Integer> scoreList;

    // Create a scoreBoard which contains the counting list of each number the user merges
    public ScoreBoard() {
        this.scoreList = new ArrayList<>();
        for (int n = 16; n > 0; n--) {
            scoreList.add(0);
        }
    }

    // Add one count to the corresponding number user merges
    public void addScore(int score, boolean backUp) {
        double var = Math.log(score * 2) / Math.log(2);
        int original = scoreList.get((int) var);
        if (!backUp) {
            original += 1;
            EventLog.getInstance().logEvent(new Event("You have successfully merged a " + score * 2
                    + " and added it to your score list."));
        }
        scoreList.set((int) var, original);

    }

    // print out the score board
    public void printScoreBoard() {
        int count = 0;
        for (int i : scoreList) {
            if (count != 0 && count != 1 && i != 0) {
                System.out.println((int) Math.pow(2, count) + ":" + i);
            }
            count++;
        }
    }

    public ArrayList getScoreList() {
        return scoreList;
    }

    public void setNewScoreList() {
        for (int n = 0; n < scoreList.size(); n++) {
            scoreList.set(n, 0);
        }
    }
}
