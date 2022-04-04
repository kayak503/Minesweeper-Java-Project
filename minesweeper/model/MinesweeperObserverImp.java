package minesweeper.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MinesweeperObserverImp implements MinesweeperObserver {

    private final Button[][] buttonGrid;
    private final Minesweeper minesweeper;
    private Label status;
    
    public MinesweeperObserverImp(Button[][] buttonGrid, Minesweeper minesweeper, Label status) {
        this.buttonGrid = buttonGrid;
        this.minesweeper = minesweeper;
        this.status = status;
    }

    @Override
    public void cellUpdated(Location location) {
        Button button = buttonGrid[location.getRow()][location.getCol()];

        assignButton(location,button);
        if (minesweeper.getGameState() == GameState.LOST || minesweeper.getGameState() == GameState.WON){
            showBoardImages();
        }
        if(minesweeper.getGameState() == GameState.LOST){
            this.status.setText("BOOOOOOOMMMMMMM!!!!!!");
        } else if(minesweeper.getGameState() == GameState.WON){
            this.status.setText("CONGRATULATIONS!!!!!!");
        } else if(minesweeper.getGameState() == GameState.NOT_STARTED){
            this.status.setText("New Game");
        } else if(minesweeper.getGameState() == GameState.IN_PROGRESS){
            this.status.setText("Keep Sweeping");
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
    public void reDrawBoard(){
        for(int i = 0; i < buttonGrid.length; i++){
            for(int j = 0; j < buttonGrid.length; j++){
                Button button = buttonGrid[i][j];
                button.setText("");
                button.setDisable(false);
            }
        }
    }
}