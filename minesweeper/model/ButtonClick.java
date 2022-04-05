/**
 * This program house the ButtonClick class, this class is the eventhandler for all of the buttons
 * on the screen
 */

package minesweeper.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonClick implements EventHandler<ActionEvent> {
    private final Minesweeper minesweeper; //gameboard
    private final Location location; //the location of the button object on the grid
    private final Button button; //the button itself to be changed

    /**
     * Basic constructor, take in three parameters and initilized them all
     * @param minesweeper
     * @param location
     * @param button
     */
    public ButtonClick(Minesweeper minesweeper, Location location, Button button) {
        this.minesweeper = minesweeper;
        this.location = location;
        this.button = button;
    }

    @Override
    public void handle(ActionEvent clickAction) {
        if (button.isDisabled()){return;} // if the button is disabled, do nothing else make a selection

        try {
            minesweeper.makeSelection(location);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}