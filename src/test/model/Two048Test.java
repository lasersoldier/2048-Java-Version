package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Two048Test {
    Board b= new Board();
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
    public void testPrintBoard()
    {
        Board.printBoard(TestBoard);
        Board.printBoard(TestBoard4);
    }

    @Test
    public void TestMergeAndRevMerge() {
        Num num1 = new Num(2);
        Num num2 = new Num(2);
        Num num3 = new Num(4);
        Assertions.assertEquals(0, b.merge(num1,num2,true).get(0).value);
        Assertions.assertEquals(4, b.merge(num1,num2,true).get(1).value);
        Assertions.assertEquals(0, b.revMerge(num1,num2,true).get(0).value);
        Assertions.assertEquals(4, b.revMerge(num1,num2,true).get(1).value);
        Assertions.assertEquals(2, b.merge(num1,num3,true).get(0).value);
        Assertions.assertEquals(4, b.merge(num1,num3,true).get(1).value);
        Assertions.assertEquals(4, b.revMerge(num1,num3,false).get(0).value);
        Assertions.assertEquals(2, b.revMerge(num1,num3,false).get(1).value);
    }

    @Test
    public void TestMovement(){
        Assertions.assertEquals(2, b.moveUp(1,TestBoard)[0][1].value);
        Assertions.assertEquals(2, b.moveRight(0,TestBoard)[0][3].value);
        Assertions.assertEquals(2, b.moveDown(3,TestBoard)[3][3].value);
        Assertions.assertEquals(2, b.moveLeft(3,TestBoard)[3][0].value);

    }
    @Test
            public void TestAction(){
        Assertions.assertEquals(4, Board.actUp(TestBoard2)[0][3].value);
        Assertions.assertEquals(4, Board.actLeft(TestBoard3)[3][0].value);
        Assertions.assertEquals(8, Board.actDown(TestBoard2)[3][3].value);
        Assertions.assertEquals(8, Board.actRight(TestBoard3)[3][3].value);
        Assertions.assertEquals(256, Board.actRight(TestBoard4)[3][3].value);
    }

    @Test
    public void TestScoreBoard(){

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.addScore(2,true);
        scoreBoard.addScore(4,true);
        Assertions.assertEquals(1,scoreBoard.getScoreList().get(2));
        Assertions.assertEquals(1,scoreBoard.getScoreList().get(3));
        scoreBoard.printScoreBoard();
        Board.getScoreBoard().printScoreBoard();
    }
}