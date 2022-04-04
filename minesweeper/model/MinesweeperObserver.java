package minesweeper.model;

public interface MinesweeperObserver {
    public abstract void cellUpdated(Location location);
    public abstract void reDrawBoard();
}