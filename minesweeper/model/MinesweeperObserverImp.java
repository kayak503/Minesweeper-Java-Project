package minesweeper.model;

import javafx.scene.control.Button;

public class MinesweeperObserverImp implements MinesweeperObserver {

    private final Button[][] buttonGrid;
    private final Minesweeper minesweeper;
    
    public MinesweeperObserverImp(Button[][] buttonGrid, Minesweeper minesweeper) {
        this.buttonGrid = buttonGrid;
        this.minesweeper = minesweeper;

    }

    @Override
    public void cellUpdated(Location location) {
        Button button = buttonGrid[location.getCol()][location.getRow()];

        int display = minesweeper.getMineCount(location);
        
    }
    
}
