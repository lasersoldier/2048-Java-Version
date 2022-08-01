package ui;

import model.Board;
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
    Num[][] testBoard = new Num[][]{
            {new Num(2), new Num(16), new Num(32), new Num(128)},
            {new Num(1024), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)}
    };

    public Panel(JFrame frame) throws Exception {
        this.setLayout(null);
        this.setOpaque(false);
        this.frame = frame;
        this.panel = this;
        menu();
        createOperator();
    }

    //I'm using suppress warning because it's the core operator for the user input in GUI
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void createOperator() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_W:
                        two048.operation(board,"w");
                        repaint();
                        break;
                    case KeyEvent.VK_S:
                        two048.operation(board,"s");
                        repaint();
                        break;
                    case KeyEvent.VK_A:
                        two048.operation(board,"a");
                        repaint();
                        break;
                    case KeyEvent.VK_D:
                        two048.operation(board,"d");
                        repaint();
                        break;
                }
            }
        };
        frame.addKeyListener(keyAdapter);

    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Game");
        JMenu menu2 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);

        JMenuItem menuItem1 = new JMenuItem("New Game");
        JMenuItem menuItem2 = new JMenuItem("Save Game");
        JMenuItem menuItem3 = new JMenuItem("Load Game");

        menu1.setFont(createFont());
        menu2.setFont(createFont());
        menuItem2.setFont(createFont());
        menuItem1.setFont(createFont());
        menuItem3.setFont(createFont());
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);

        menuItem1.setActionCommand("start");
        menuItem2.setActionCommand("save");
        menuItem3.setActionCommand("load");
        frame.setJMenuBar(menuBar);
    }

    private Font createFont() {
        return new Font("Times New Roman", Font.BOLD, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("start")) {
            System.out.println("start");
        }
        if (command.equals("save")) {
            System.out.println("save");
        }
        if (command.equals("load")) {
            System.out.println("load");
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawBlock(graphics);
    }

    private void drawBlock(Graphics graphics) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Num n = board[x][y];
                n.draw(graphics, x, y);
            }
        }
    }
}
