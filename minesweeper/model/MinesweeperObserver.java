/**
 * This interface houses the Minesweeper observer which houses two methods
 */

package minesweeper.model;

public interface MinesweeperObserver {
    public abstract void cellUpdated(Location location); // updates the state of the cells on the board
    public abstract void reDrawBoard(Minesweeper minesweeper); // redraws the board after the game is over, reset button
}