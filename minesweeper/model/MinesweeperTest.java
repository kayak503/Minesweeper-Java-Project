package minesweeper.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

//WARNING: all tests were created with a random seed value of 1
//Failure to add this seed to the minesweeper class will result in 
//all tests failing

@Testable
public class MinesweeperTest {
    @Test
    public void testGetMovesCount(){
        Minesweeper sweeper = new Minesweeper(4,4,2);
        int expected = 0;
        int actual = sweeper.getMovesCount();
        assertTrue(expected == actual);
    }

    @Test
    public void testGetMineCountZero(){
        Location location = new Location (4,4);
        Minesweeper sweeper = new Minesweeper(4,4,2);
        int expected = 0;
        int actual = sweeper.getMineCount(location);
        assertTrue(expected == actual);
    }

    @Test
    public void testGetMineCountOne(){
        Location location = new Location (1,1);
        Minesweeper sweeper = new Minesweeper(4,4,2);
        int expected = 1;
        int actual = sweeper.getMineCount(location);
        assertTrue(expected == actual);
    }

    @Test
    public void testGetMineCountTwo(){
        Location location = new Location (2,1);
        Minesweeper sweeper = new Minesweeper(4,4,2);
        int expected = 2;
        int actual = sweeper.getMineCount(location);
        assertTrue(expected == actual);
    }

    @Test
    public void testLosingMove() throws Exception{
        Minesweeper sweeper = new Minesweeper(4,4,2);
        sweeper.makeSelection(new Location(1,1));

        assertTrue(GameState.LOST.equals(sweeper.getGameState()));
    }

    @Test
    public void testToString(){
        Minesweeper sweeper = new Minesweeper(4,4,2);
        String expected = "----\n----\n----\n----\n";
        String actual = sweeper.toString();
        assertTrue(expected.equals(actual));
    }

    @Test 
    public void testShowBoard(){
        Minesweeper sweeper = new Minesweeper(4,4,2);
        String expected = "1110\n2M10\nM210\n1100\n";
        String actual = sweeper.showBoard();
        assertTrue(expected.equals(actual));
    }

    @Test 
    public void getSymbolCovered() throws Exception{
        Minesweeper sweeper = new Minesweeper(4,4,2);
        char expected = '-';
        char actual = sweeper.getSymbol(new Location(3,3));
        assertTrue(expected== actual);
    }

    @Test 
    public void getSymbolMine() throws Exception{
        Minesweeper sweeper = new Minesweeper(4,4,2);
        char expected = 'M';
        sweeper.makeSelection(new Location(1,1));
        char actual = sweeper.getSymbol(new Location(1,1));
        assertTrue(expected== actual);
    }

    @Test 
    public void getSymbolValue() throws Exception{
        Minesweeper sweeper = new Minesweeper(4,4,2);
        char expected = '2';
        sweeper.makeSelection(new Location(1,0));
        char actual = sweeper.getSymbol(new Location(1,0));
        assertTrue(expected== actual);
    }


}
