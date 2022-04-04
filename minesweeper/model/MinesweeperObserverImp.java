package minesweeper.model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MinesweeperObserverImp implements MinesweeperObserver {

    private final Button[][] buttonGrid;
    private Minesweeper minesweeper;
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
        
        updateStatusText();

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
                int count = minesweeper.getMineCount(location);
                if (count != 0){
                    button.setText(minesweeper.getMineCount(location) + "");
                }
            } else if(display != '0'){
                button.setText(display + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reDrawBoard(Minesweeper minesweeper){
        this.minesweeper = minesweeper;

        updateStatusText();
        for(int i = 0; i < buttonGrid.length; i++){
            for(int j = 0; j < buttonGrid.length; j++){
                Button button = buttonGrid[i][j];
                button.setText("");
                button.setDisable(false);
                button.setOnAction(new ButtonClick(minesweeper, new Location(i, j), button));
                button.setGraphic(null);
                button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    private void updateStatusText() {
        if(minesweeper.getGameState() == GameState.LOST){
            this.status.setText("BOOOOOOOMMMMMMM!!!!!!");
        } else if(minesweeper.getGameState() == GameState.WON){
            this.status.setText("CONGRATULATIONS!!!!!!");
        } else if(minesweeper.getGameState() == GameState.NOT_STARTED){
            this.status.setText("New Game");
        } else if(minesweeper.getGameState() == GameState.IN_PROGRESS){
            this.status.setText("Keep Sweeping");
        }
    }
}