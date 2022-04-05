/**
 * This interface houses the Minesweeper observer which houses two methods
 */

package minesweeper.model;

public interface MinesweeperObserver {
    public abstract void cellUpdated(Location location); // updates the state of the cells on the board
    public abstract void reCoverBoard(Minesweeper minesweeper); // this method is used to reset the board to a new game state. THIS WILL COVER ALL TILES 
}