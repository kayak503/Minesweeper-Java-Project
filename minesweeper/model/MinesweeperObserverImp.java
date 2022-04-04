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
        Button button = buttonGrid[location.getRow()][location.getCol()];

        assignButton(location,button);
        if (minesweeper.getGameState() == GameState.LOST || minesweeper.getGameState() == GameState.WON){
            showBoardImages();
        }
        button.setDisable(true);
    }
    
    public void showBoardImages(){
        for(int i = 0; i < buttonGrid.length; i++){
            for(int j = 0; j < buttonGrid.length; j++){
                Button button = buttonGrid[i][j];
                assignButton(new Location(i, j), button);
                button.setDisable(true);
            }
        }
    }
    public void assignButton(Location location, Button button){
        try {
            char display = minesweeper.getSymbol(location);
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
        
    }
}