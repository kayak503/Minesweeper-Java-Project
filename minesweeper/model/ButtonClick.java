package minesweeper.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonClick implements EventHandler<ActionEvent> {


    private final Minesweeper minesweeper;
    private final Location location;

    public ButtonClick(Minesweeper minesweeper, Location location) {
        this.minesweeper = minesweeper;
        this.location = location;
    }

    @Override
    public void handle(ActionEvent clickAction) {
        try {
            minesweeper.makeSelection(location);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
