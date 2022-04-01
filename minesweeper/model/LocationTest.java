package minesweeper.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class LocationTest {
    @Test
    public void testToString(){
        Location l = new Location(1,2);
        String expected = "(1, 2)";
        String actual = l.toString();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void testEquals(){
        Location one = new Location(1,2);
        Location alsoOne  = new Location(1,2);
        Location notOne = new Location(1,3);

        assertTrue(one.equals(alsoOne));
        assertTrue(! one.equals(notOne));
    }

    @Test
    public void testHash(){
        Location l = new Location(5,2);
        String row = "5";
        String col = "2";
        int expected = row.hashCode() * col.hashCode();
        int actual = l.hashCode();

        assertTrue(expected == actual);

    }
}
