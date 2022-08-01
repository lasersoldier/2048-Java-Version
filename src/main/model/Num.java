package model;

import org.json.JSONObject;
import ui.Two048;

import java.awt.*;


public class Num {
    // I'm using SuppressWarnings because I need the value of Num be used outside the Num class
    @SuppressWarnings({"checkstyle:VisibilityModifier", "checkstyle:SuppressWarnings"})
    public Integer value;

    private static int horizontal = 10;
    private static int vertical = 60;
    private static int width = 80;
    private static int height = 80;
    private static int arcWidth = 4;
    private static int arcHeight = 4;
    private static int colWidth = 10;
    private static Color numberColor = new Color(255, 68, 0, 255);
    private static Font numberFont = new Font("Times New Roman", Font.BOLD, 36);

    public Num(int n) {
        this.value = n;

    }

    public void draw(Graphics graphics, int x, int y) {
        Color color = changeColor();
        graphics.setColor(color);
        graphics.fillRoundRect(horizontal + y * width + (y + 1) * colWidth,
                vertical + x * height + (x + 1) * colWidth, width, height, arcWidth, arcHeight);
        if (this.value != 0) {
            graphics.setColor(numberColor);
            graphics.setFont(numberFont);
            String text = String.valueOf(this.value);
            int fixedHorizontal = horizontal + y * width + (y + 1) * colWidth + calculatePos();
            int fixedVertical = vertical + x * height + (x + 1) * colWidth + 50;
            graphics.drawString(text, fixedHorizontal, fixedVertical);
        }
    }

    private int calculatePos() {
        int length = 0;
        switch (String.valueOf(this.value).length()) {
            case 1:
                length = 30;
                break;
            case 2:
                length = 20;
                break;
            case 3:
                length = 10;
                break;
            case 4:
                length = 5;
                break;
            default:
                length = 0;
                break;
        }
        return length;
    }

    //I'm using suppress warning here because of the various cases for each number
    //and the rgb value for color is not linear
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Color changeColor() {
        Color color = null;
        switch (this.value) {
            case 2:
                color = new Color(222, 184, 135);
                break;
            case 4:
                color = new Color(210, 180, 140);
                break;
            case 8:
                color = new Color(188, 143, 143);
                break;
            case 16:
                color = new Color(244, 164, 96);
                break;
            case 32:
                color = new Color(218, 165, 32);
                break;
            case 64:
                color = new Color(184, 134, 11);
                break;
            case 128:
                color = new Color(205, 133, 63);
                break;
            case 256:
                color = new Color(210, 105, 30);
                break;
            case 512:
                color = new Color(139, 69, 19);
                break;
            case 1024:
                color = new Color(160, 82, 45);
                break;
            case 2048:
                color = new Color(165, 42, 42);
                break;
            case 4096:
                color = new Color(171, 22, 22);
                break;
            case 8192:
                color = new Color(162, 8, 8);
                break;
            default:
                color = new Color(134, 0, 0);
        }
        return color;
    }

}

