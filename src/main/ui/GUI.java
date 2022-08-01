package ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private Color backGroundColor = new Color(245, 222, 179);

    public GUI() {
        setTitle("2048 Java Version");
        setSize(400,500);
        getContentPane().setBackground(backGroundColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
