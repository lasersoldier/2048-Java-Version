package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class Two048Test {
    Num[][] TestBoard = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(2), new Num(0), new Num(0)}};
    Num[][] TestBoard2 = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(2)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(16), new Num(0), new Num(0), new Num(2)},
            {new Num(32), new Num(16), new Num(4), new Num(16)}};
    @Test
    public void testPrintBoard()
    {
        Board.printBoard(TestBoard);
    }

    @Test
    public void TestMergeAndRevMerge() {
        Num num1 = new Num(2);
        Num num2 = new Num(2);
        Num num3 = new Num(4);
        Board.merge(num1,num2);
        Board.revMerge(num1,num2);
        Board.merge(num1,num3);
        Board.revMerge(num1,num3);
    }

    @Test
    public void TestMovement(){
        Board.moveUp(1,TestBoard);
        Board.moveDown(1,TestBoard);
        Board.moveRight(3,TestBoard);
        Board.moveLeft(3,TestBoard);

    }
    @Test
            public void TestAction(){
        Board.actUp(TestBoard2);
        Board.actLeft(TestBoard2);
        Board.actDown(TestBoard2);
        Board.actRight(TestBoard2);
    }

}