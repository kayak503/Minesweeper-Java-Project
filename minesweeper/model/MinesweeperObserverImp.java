package minesweeper.model;


import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MinesweeperObserverImp implements MinesweeperObserver {

    private final Button[][] buttonGrid; // A 2D array of buttons that represents the button grid that the gui is displaying.
    private Minesweeper minesweeper; // the instance of the Minesweeper game
    private Label status; // the status label
    private Label moveCount; // the moveCount label

    private static Map<Integer, Color> colorMap; // a color map that shows the colors fonts should be depending on the amount of adjacent mines
    
    static {
        colorMap = new HashMap<Integer, Color>();
        colorMap.put(1, Color.WHITE);
        colorMap.put(2, Color.AQUA);
        colorMap.put(3, Color.PINK);
        colorMap.put(4, Color.LIGHTGREEN);
        colorMap.put(5, Color.GREENYELLOW);
        colorMap.put(6, Color.YELLOW);
        colorMap.put(7, Color.BISQUE);
        colorMap.put(8, Color.CORAL);
    }
    

    /**
     * The MinesweeperObserverImp is used to communicate from the minesweeper game to the GUI
     * MinesweeperObserverImp handles the Gui changes that result from the change of the minesweeper game 
     * 
     * @param buttonGrid  2D array of buttons that represents the button grid that the gui is displaying. 
     * @param minesweeper the instance of the Minesweeper game
     * @param status the status label that will be updated based on the minesweeper game state
     * @param moveCount the moveCount label that will be updated based on the minesweeper game moves
     */
    public MinesweeperObserverImp(Button[][] buttonGrid, Minesweeper minesweeper, Label status, Label moveCount) {
        this.buttonGrid = buttonGrid;
        this.minesweeper = minesweeper;
        this.status = status;
        this.moveCount = moveCount;
    }


    /**
     * This method should be called when the game state of the minesweeper changes because of user input.
     * This method will update the GUI board to reflect the changes made such as disabling the button passed and updating move counts and the text label.
     * If the game is over, this method will reveal the full board for the player to see.
     * 
     * @param location the location of the button that should be disabled (has been clicked)
     */
    @Override
    public void cellUpdated(Location location) {
        // check if the game is over. If it is show the entire board. 
        if (minesweeper.getGameState() == GameState.LOST || minesweeper.getGameState() == GameState.WON){ 
            // reveals the full board for the player to see
            revealFullBoard();
        } 
        
        else { // if the game is not over, the button of the given location Should be revealed to the player 
            
            // update button at the given location to display the adjacent mine count or mine image  
            uncoverButton(location);
        }

        // update the status label to reflect the game state
        updateStatusText();
        // update the moves label to reflect the number of moves.
        this.moveCount.setText("MOVES \n" + minesweeper.getMovesCount() + "");
    }


    /**
     * This method should be called when a new game is started on an existing board.
     * This method will reset the GUI board to the start state and allow for the player to play the game again.
     *  
     * @param minesweeper a minesweeper Instance 
     */
    @Override
    public void reCoverBoard(Minesweeper minesweeper){
        // Updates minesweeper to new instance
        this.minesweeper = minesweeper;
        // updates the Status Text
        updateStatusText();

        for(int row = 0; row < buttonGrid.length; row++){ // loop through all rows
            for(int col = 0; col < buttonGrid[row].length; col++){ // loop through all columns
                // get the button from the location given.
                Button button = buttonGrid[row][col];

                // removes any text the button may have 
                button.setText("");
                // removes any graphics the button may have 
                button.setGraphic(null);
                // sets the Background back to the default
                button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

                // enables the button clicked events without visible changes
                button.setMouseTransparent(false);
                // sets a new button click event to replace the old one
                button.setOnAction(new ButtonClick(minesweeper, new Location(row, col)));

            }
        }
        
    }
    

    /**
     * This HELPER method should be called when the game is over.
     * This method will reveal the full board to the player by updating ALL the GUI board's buttons showing all their states.
     */
    private void revealFullBoard(){
        for(int row = 0; row < buttonGrid.length; row++){ // loop through all rows
            for(int col = 0; col < buttonGrid[row].length; col++){ // loop through all columns
                uncoverButton(new Location(row, col)); // uncover every button in all the rows and columns.
            }
        }
    }
    
    /**
     * This HELPER method should be called to uncover a button and disabled it.
     * this method will reveal what is under a button. this could be a mine or the number of mines that it is adjacent to.
     * if the button has no mines adjacent to it it will display a blank disabled button not a 0. the number of mines adjacent to a button is also color coded
     * 
     * @param location the location of the button that is to be uncovered
     */
    public void uncoverButton(Location location){
        // get the button from the location given.
        Button button = buttonGrid[location.getRow()][location.getCol()];
        try { 
            // get what the button is from the minesweeper game instance
            char textualizedDisplay = minesweeper.getSymbol(location);

            
            if(textualizedDisplay == minesweeper.MINE){ // if this button is a mine show the image of a mine
                // sets the button to image of a mine
                button.setGraphic(new ImageView(new Image("file:media/images/mine24.png")));
            } 

            else { // if this is not a mine show the adjacent mines instead.
                // get adjacent mines regardless of whether textualizedDisplay is covered (we can't use textualizedDisplay because it returns - if covered)
                int adjacentMinesCount = minesweeper.getMineCount(location);
                
                // if adjacentMinesCount is not 0 
                if (adjacentMinesCount != 0){
                    button.setText(minesweeper.getMineCount(location) + "");
                    button.setTextFill(colorMap.get(adjacentMinesCount)); // sets the color text to a unique color based on the adjacentMinesCount
                    button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));

                }
            }
            // shows that this button has been clicked
            button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            
            // this disables the button clicked events without visible changes
            button.setMouseTransparent(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This HELPER method should be when the GameState changes to update the StatusText.
     * this method will update the status text to show the relevant game state.
     * 
     */
    private void updateStatusText() {
        if(minesweeper.getGameState() == GameState.LOST){ // Lost
            this.status.setText("BOOOOOOOMMMMMMM!!!!!!");
        } else if(minesweeper.getGameState() == GameState.WON){ // Won
            this.status.setText("CONGRATULATIONS!!!!!!");
        } else if(minesweeper.getGameState() == GameState.NOT_STARTED){ // New Game
            this.status.setText("New Game");
        } else if(minesweeper.getGameState() == GameState.IN_PROGRESS){ // IN_PROGRESS
            this.status.setText("Keep Sweeping");
        }
    }
}