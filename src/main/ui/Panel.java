package ui;

import model.Board;
import model.Event;
import model.EventLog;
import model.Num;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel implements ActionListener {
    private JFrame frame = null;
    private Panel panel = null;
    private Two048 two048 = new Two048();
    private Num[][] board = two048.generateNext(two048.generateNew());
    private boolean gameOver = false;

    // Initialize the game panel
    public Panel(JFrame frame) throws Exception {

        this.setLayout(null);
        this.setOpaque(false);
        this.frame = frame;
        this.panel = this;

        menu();
        two048.setBackUps(board);
        gameOver = two048.noMoreMove();
        createOperator();

    }

    //I'm using suppress warning because it's the core operator for the user input in GUI
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // The method that receives user input and execute the corresponding code
    private void createOperator() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                two048.setBackUps(two048.getPlayBoard());
                super.keyPressed(e);
                int key = e.getKeyCode();
                gameOver = two048.noMoreMove();
                if (gameOver) {
                    showScore();
                }
                switch (key) {
                    case KeyEvent.VK_W:
                        if (!two048.arraysCompare(Board.actUp(two048.getBackUp(1), true), two048.getBackUp(2))) {
                            two048.operation(board, "w");
                            repaint();
                            gameOver = two048.noMoreMove();
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (!two048.arraysCompare(Board.actDown(two048.getBackUp(1), true), two048.getBackUp(2))) {
                            two048.operation(board, "s");
                            repaint();
                            gameOver = two048.noMoreMove();
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (!two048.arraysCompare(Board.actLeft(two048.getBackUp(1), true), two048.getBackUp(2))) {
                            two048.operation(board, "a");
                            repaint();
                            gameOver = two048.noMoreMove();
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (!two048.arraysCompare(Board.actRight(two048.getBackUp(1), true), two048.getBackUp(2))) {
                            two048.operation(board, "d");
                            repaint();
                            gameOver = two048.noMoreMove();
                        }
                        break;
                }
            }
        };
        frame.addKeyListener(keyAdapter);

    }

    // Create the menu of the game including creating new game, saving and loading the game, exiting the game,
    // getting the tutorial and getting the current score board.
    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Game");
        JMenu menu2 = new JMenu("Help");
        JMenu menu3 = new JMenu("Check Score");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        JMenuItem menuItem1 = new JMenuItem("New Game");
        JMenuItem menuItem2 = new JMenuItem("Save Game");
        JMenuItem menuItem3 = new JMenuItem("Load Game");
        JMenuItem menuItem4 = new JMenuItem("Exit Game");
        JMenuItem menuItem5 = new JMenuItem("Tutorial");
        JMenuItem menuItem6 = new JMenuItem("Score List");
        JMenuItem menuItem7 = new JMenuItem("Log");

        setMenuItems(menu1, menu2, menu3, menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6, menuItem7);

        frame.setJMenuBar(menuBar);
    }

    // create a Font
    private Font createFont() {
        return new Font("Times New Roman", Font.BOLD, 20);
    }

    // I'm using suppress warning because it contains all action for the menu bar.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // Writing the corresponding command for the menu items
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("start")) {
            startGame();
        }
        if (command.equals("save")) {
            two048.saveTwo048();
        }
        if (command.equals("load")) {
            try {
                two048.jsonParser();
                repaint();
            } catch (Exception ex) {
                startGame();
            }
        }
        if (command.equals("exit")) {
            Object[] options = {"Yes", "No"};
            int option = JOptionPane.showOptionDialog(this,
                    "Are you sure you want to exit the game?",
                    "Warning", JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (option == 0) {
                String logString = "";
                for (Event event : EventLog.getInstance()) {
                    logString += event.toString() + "\n" + "\n";
                }
                System.out.println(logString);
                System.exit(0);
            }
        }
        if (command.equals("tutorial")) {
            JOptionPane.showMessageDialog(null, "Use WASD to move the blocks in one "
                            + "direction to get 2048.",
                    "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("check score")) {
            showScore();
        }
        if (command.equals("log")) {
            JFrame log = new JFrame("Log");
            log.setVisible(true);
            log.setSize(480, 400);
            showLog(log);
        }
    }

    private void showLog(JFrame frame) {
        String logString = "";
        frame.setLayout(null);
        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setBounds(13, 10, 420, 340);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jta.setEditable(false);
        for (Event e : EventLog.getInstance()) {
            logString += e.toString() + "\n" + "\n";
        }
        jta.setText(logString);
        frame.add(jsp);
    }

    // The code demonstrating the score board when the user gets one or when the game end
    private void showScore() {
        if (!gameOver) {
            JOptionPane.showMessageDialog(null, "Your current score is : "
                            + produceScore() + "\n" + produceScoreString(),
                    "Summary", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You lose! Your score is : "
                            + produceScore() + "\n" + produceScoreString(),
                    "Summary", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Paint the board and current blocks
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawBlock(graphics);
        drawScore(graphics);
    }

    // Paint the live score of the player on the top of the user interface
    private void drawScore(Graphics graphics) {
        graphics.setColor(new Color(224, 168, 115));
        graphics.fillRoundRect(20, 22, 140, 40, 0, 0);
        graphics.setColor(new Color(255, 255, 255));
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 22));
        graphics.drawString("Score : " + produceScore(), 30, 50);
    }

    // Draw the board of the game
    private void drawBlock(Graphics graphics) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Num n = board[x][y];
                n.draw(graphics, x, y);
            }
        }
    }

    // Start a new game function
    public void startGame() {
        gameOver = false;
        board = two048.generateNew();
        board = two048.generateNext(board);
        repaint();
        Board.getScoreBoard().setNewScoreList();

    }

    // Initialize the menu items in the menu
    public void setMenuItems(JMenu mb1, JMenu mb2, JMenu mb3, JMenuItem m1, JMenuItem m2, JMenuItem m3,
                             JMenuItem m4, JMenuItem m5, JMenuItem m6, JMenuItem m7) {
        mb1.add(m1);
        mb1.add(m2);
        mb1.add(m3);
        mb1.add(m4);
        mb2.add(m5);
        mb3.add(m6);
        mb3.add(m7);
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m1.setActionCommand("start");
        m2.setActionCommand("save");
        m3.setActionCommand("load");
        m4.setActionCommand("exit");
        m5.setActionCommand("tutorial");
        m6.setActionCommand("check score");
        m7.setActionCommand("log");
    }

    // Produce the string that shows the blocks that the user successfully merged
    public String produceScoreString() {
        String scoreString = "\nThe blocks you merged :\n";
        int count = 0;
        for (int n : Board.getScoreBoard().scoreList) {
            if (count != 0 && count != 1 && n != 0) {
                scoreString += (int) Math.pow(2, count) + " : " + n + "\n";
            }
            count++;
        }
        return scoreString;
    }

    // produce the calculated score string that the user gets so far
    public int produceScore() {
        int score = 0;
        int count = 0;
        for (int n : Board.getScoreBoard().scoreList) {
            score += (int) Math.pow(2, count) * n;
            count++;
        }
        return score;
    }
}

