package minesweeper.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

        char display;
        try {
            display = minesweeper.getSymbol(location);
            if(display == minesweeper.MINE){
                button.setGraphic(new ImageView(new Image("file:media/images/mine24.png")));
            } else if(display == minesweeper.COVERED){
                return;
            } else if(display != '0'){
                button.setText(display + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setDisable(true);
    }   
}