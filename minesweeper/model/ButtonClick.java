/**
 * This program house the ButtonClick class, this class is the eventhandler for all of the buttons
 * on the screen
 */

package minesweeper.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonClick implements EventHandler<ActionEvent> {
    private final Minesweeper minesweeper; //gameboard
    private final Location location; //the location of the button object on the grid

    /**
     * Basic constructor, take in three parameters and initilized them all
     * @param minesweeper
     * @param location
     * @param button
     */
    public ButtonClick(Minesweeper minesweeper, Location location) {
        this.minesweeper = minesweeper;
        this.location = location;
    }

    @Override
    public void handle(ActionEvent clickAction) {
        try {
            minesweeper.makeSelection(location);
        } catch (Exception e) { // squash This should never be an issue.
            e.printStackTrace();
        }        
    }
}