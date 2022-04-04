package minesweeper.model;

import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Hint implements EventHandler<ActionEvent>{
    private Minesweeper minesweeper;
    private Button[][] buttonGrid;

    public Hint(Minesweeper sweeper, Button[][] buttonGrid){
        this.minesweeper = sweeper;
        this.buttonGrid = buttonGrid;
    }

    @Override
    public void handle(ActionEvent arg0) {
        HashSet<Location> moves = minesweeper.getPossibleSelections();
        Location[] arrayMoves = moves.toArray(new Location[moves.size()]);
        Location hintSpot = arrayMoves[0];

        Button toUpdate = this.buttonGrid[hintSpot.getRow()][hintSpot.getCol()];
        toUpdate.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
    }
    
}
