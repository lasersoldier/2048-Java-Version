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
            {new Num(0), new Num(0), new Num(0), new Num(2)},
            {new Num(0), new Num(0), new Num(0), new Num(4)},
            {new Num(0), new Num(0), new Num(0), new Num(0)}};
    Num[][] TestBoard3 = new Num[][]{
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(0), new Num(0), new Num(0), new Num(0)},
            {new Num(2), new Num(2), new Num(4), new Num(0)}};
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
        Assertions.assertEquals(0,Board.merge(num1,num2).get(0).value);
        Assertions.assertEquals(4,Board.merge(num1,num2).get(1).value);
        Assertions.assertEquals(0,Board.revMerge(num1,num2).get(0).value);
        Assertions.assertEquals(4,Board.revMerge(num1,num2).get(1).value);
        Assertions.assertEquals(2,Board.merge(num1,num3).get(0).value);
        Assertions.assertEquals(4,Board.merge(num1,num3).get(1).value);
        Assertions.assertEquals(4,Board.revMerge(num1,num3).get(0).value);
        Assertions.assertEquals(2,Board.revMerge(num1,num3).get(1).value);
    }

    @Test
    public void TestMovement(){
        Assertions.assertEquals(2,Board.moveUp(1,TestBoard)[0][1].value);
        Assertions.assertEquals(2,Board.moveRight(0,TestBoard)[0][3].value);
        Assertions.assertEquals(2,Board.moveDown(3,TestBoard)[3][3].value);
        Assertions.assertEquals(2,Board.moveLeft(3,TestBoard)[3][0].value);

    }
    @Test
            public void TestAction(){
        Assertions.assertEquals(4,Board.actUp(TestBoard2)[0][3].value);
        Assertions.assertEquals(4,Board.actLeft(TestBoard3)[3][0].value);
        Assertions.assertEquals(8,Board.actDown(TestBoard2)[3][3].value);
        Assertions.assertEquals(8,Board.actRight(TestBoard3)[3][3].value);
    }

}