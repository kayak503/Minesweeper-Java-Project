package minesweeper.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonClick implements EventHandler<ActionEvent> {


    private final Minesweeper minesweeper;
    private final Location location;
    private final Button button;


    public ButtonClick(Minesweeper minesweeper, Location location, Button button) {
        this.minesweeper = minesweeper;
        this.location = location;
        this.button = button;
    }

    @Override
    public void handle(ActionEvent clickAction) {
        if (button.isDisabled()){return;}

        try {
            minesweeper.makeSelection(location);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
