package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class Two048Test {
    Board b = new Board();
    Num[][] TestBoard = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(2), new Num(0), new Num(0)}};
    Num[][] TestBoard2 = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(2)},
            {new Num(0), new Num(0), new Num(0), new Num(2)},
            {new Num(0), new Num(0), new Num(0), new Num(4)},
            {new Num(0), new Num(0), new Num(0), new Num(0)}};
    Num[][] TestBoard3 = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(2), new Num(2), new Num(4), new Num(0)}};
    Num[][] TestBoard4 = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(128), new Num(128)}};

    @Test
    public void testPrintBoard() {
        Board.printBoard(TestBoard);
        Board.printBoard(TestBoard4);
    }

    @Test
    public void TestMergeAndRevMerge() {
        Num num1 = new Num(2);
        Num num2 = new Num(2);
        Num num3 = new Num(4);
        Assertions.assertEquals(0, b.merge(num1, num2, false).get(0).value);
        Assertions.assertEquals(4, b.merge(num1, num2, false).get(1).value);
        Assertions.assertEquals(0, b.revMerge(num1, num2, false).get(0).value);
        Assertions.assertEquals(4, b.revMerge(num1, num2, false).get(1).value);
        Assertions.assertEquals(2, b.merge(num1, num3, false).get(0).value);
        Assertions.assertEquals(4, b.merge(num1, num3, false).get(1).value);
        Assertions.assertEquals(4, b.revMerge(num1, num3, false).get(0).value);
        Assertions.assertEquals(2, b.revMerge(num1, num3, false).get(1).value);
    }

    @Test
    public void TestMovement() {
        Assertions.assertEquals(2, b.moveUp(1, TestBoard)[0][1].value);
        Assertions.assertEquals(2, b.moveRight(0, TestBoard)[0][3].value);
        Assertions.assertEquals(2, b.moveDown(3, TestBoard)[3][3].value);
        Assertions.assertEquals(2, b.moveLeft(3, TestBoard)[3][0].value);

    }

    @Test
    public void TestAction() {
        Assertions.assertEquals(4, Board.actUp(TestBoard2, false)[0][3].value);
        Assertions.assertEquals(4, Board.actLeft(TestBoard3, false)[3][0].value);
        Assertions.assertEquals(8, Board.actDown(TestBoard2, false)[3][3].value);
        Assertions.assertEquals(8, Board.actRight(TestBoard3, false)[3][3].value);
        Assertions.assertEquals(256, Board.actRight(TestBoard4, false)[3][3].value);
    }

    @Test
    public void TestScoreBoard() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.addScore(2, false);
        scoreBoard.addScore(4, false);
        Assertions.assertEquals(1, scoreBoard.getScoreList().get(2));
        Assertions.assertEquals(1, scoreBoard.getScoreList().get(3));
        scoreBoard.printScoreBoard();
        Board.getScoreBoard().printScoreBoard();
        scoreBoard.setNewScoreList();
        Assertions.assertEquals(0,scoreBoard.scoreList.get(3));
    }

    @Test
    public void Num() {
        Num num1 = new Num(2);
        Num num2 = new Num(4);
        Num num3 = new Num(8);
        Num num4 = new Num(16);
        Num num5 = new Num(32);
        Num num6 = new Num(64);
        Num num7 = new Num(128);
        Num num8 = new Num(256);
        Num num9 = new Num(512);
        Num num10 = new Num(1024);
        Num num11 = new Num(2048);
        Num num12 = new Num(4096);
        Num num13 = new Num(8192);
        Num num14 = new Num(16384);

        Assertions.assertEquals(30, num1.calculatePos());
        Assertions.assertEquals(20, num4.calculatePos());
        Assertions.assertEquals(10, num7.calculatePos());
        Assertions.assertEquals(5, num10.calculatePos());
        Assertions.assertEquals(0, num14.calculatePos());

        Assertions.assertEquals(new Color(222, 184, 135),num1.changeColor());
        Assertions.assertEquals(new Color(210, 180, 140),num2.changeColor());
        Assertions.assertEquals(new Color(188, 143, 143),num3.changeColor());
        Assertions.assertEquals(new Color(244, 164, 96),num4.changeColor());
        Assertions.assertEquals(new Color(218, 165, 32),num5.changeColor());
        Assertions.assertEquals(new Color(184, 134, 11),num6.changeColor());
        Assertions.assertEquals(new Color(205, 133, 63),num7.changeColor());
        Assertions.assertEquals(new Color(210, 105, 30),num8.changeColor());
        Assertions.assertEquals(new Color(139, 69, 19),num9.changeColor());
        Assertions.assertEquals(new Color(160, 82, 45),num10.changeColor());
        Assertions.assertEquals(new Color(165, 42, 42),num11.changeColor());
        Assertions.assertEquals(new Color(171, 22, 22),num12.changeColor());
        Assertions.assertEquals(new Color(162, 8, 8),num13.changeColor());
        Assertions.assertEquals(new Color(234, 140, 46),num14.changeColor());


    }
}