package minesweeper.model;

import java.util.HashSet;
import java.util.Random;

public class Minesweeper{
    public final char MINE = 'M';
    public final char COVERED = '-';

    private int rows;
    private int cols;
    private int mineCount;
    private int movesCount;
    private GameState currentState;
    private HashSet<Location> validSpots;
    private HashSet<Location> mines;
    private MinesweeperObserver observer;


    /**
     * A constructor that sets up all fields to default values. Fills the set of mines
     * with random mines. Fills the validSpots set with all remaining positions, as they are 
     * all clickable by default
     * @param rows The amount of rows in this board
     * @param cols The amount of columns in this board
     * @param mineCount The desired amount of mines in this board
     */
    public Minesweeper(int rows, int cols, int mineCount){
        //Initalise all variables
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.currentState = GameState.NOT_STARTED;
        this.movesCount = 0;
        this.mines = new HashSet<>();
        this.validSpots = new HashSet<>();
        Random rng = new Random();
        rng.setSeed(1);
        this.observer = null;

        //Until the set reaches the desired size, add unique locations for mines
        while(this.mines.size() != this.mineCount){
            int newRow = rng.nextInt(rows);
            int newCol = rng.nextInt(cols);
            this.mines.add(new Location(newRow, newCol));
        }

        //Once all mines have been decided, make every other position valid
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Location l = new Location(row, col);
                if(!mines.contains(l)){
                    validSpots.add(l);
                }
            }
        }
    }


    public Minesweeper(Minesweeper oldBoard){

        this.rows = oldBoard.rows;
        this.cols = oldBoard.cols;
        this.mineCount = oldBoard.mineCount;
        this.currentState = GameState.NOT_STARTED;
        this.movesCount = 0;
        this.mines = new HashSet<>();
        this.validSpots = new HashSet<>();
        Random rng = new Random();
        this.observer = oldBoard.observer;

        // if the old board had an observer tell it to reset itself for the new game
        if (this.observer != null) {observer.reCoverBoard(this);
        }

        //Until the set reaches the desired size, add unique locations for mines
        while(this.mines.size() != this.mineCount){
            int newRow = rng.nextInt(rows);
            int newCol = rng.nextInt(cols);
            this.mines.add(new Location(newRow, newCol));
        }

        //Once all mines have been decided, make every other position valid
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Location l = new Location(row, col);
                if(!mines.contains(l)){
                    validSpots.add(l);
                }
            }
        }
    }
    /**
     * 
     * @return the amount of moves so far in the game
     */
    public int getMovesCount(){return this.movesCount;}

    /**
     * 
     * @return the current state of the game (in progress, not started, won or lost)
     */
    public GameState getGameState(){return this.currentState;}

    public int getTotalMines(){return mineCount;}

    
    /**
     * A helper function. Given a valid location, it will determine the quantity of
     * mines in the 8 surrounding cells
     * @param location The location we want to check at
     * @return The number of mines that are found
     */
    public int getMineCount(Location location){
        int count = 0;
        int row = location.getRow();
        int col = location.getCol();
        //Bottom left
        if(mines.contains(new Location(row - 1, col - 1))){
            count++;
        }
        //Left
        if(mines.contains(new Location(row, col - 1))){
            count++;
        }
        //Upper left
        if(mines.contains(new Location(row + 1, col - 1))){
            count++;
        }
        //Upper
        if(mines.contains(new Location(row + 1, col))){
            count++;
        }
        //Upper right
        if(mines.contains(new Location(row + 1, col + 1))){
            count++;
        }
        //Right
        if(mines.contains(new Location(row, col + 1))){
            count++;
        }
        //Bottom right
        if(mines.contains(new Location(row - 1, col + 1))){
            count++;
        }
        //Bottom
        if(mines.contains(new Location(row - 1, col))){
            count++;
        }
        return count;
    }

    /**
     * The game logic. Determines if a location is a valid move,
     * bomb, or out of bounds.
     * Bomb: sets game state to lost
     * Out of bounds: throws a minesweeper exception
     * valid: removes the move from set of valid moves. If the set is empty, set state to won
     * @param location The location at which we want to make a move
     * @throws Exception Called if the move is out of bounds
     */
    public void makeSelection(Location location) throws Exception{ // should be minesweeper exception !!!
        if(location.getRow() > rows || location.getCol() > cols){
            //Its not a calid move, throw the exception
            throw new MinesweeperException("Location out of Bounds");
        } else {
            if(this.mines.contains(location)){
                //BOOM. End the game
                this.currentState = GameState.LOST;
                movesCount ++;
            }
            if(this.validSpots.contains(location)){
                //Its valid, take it out of the set, increment moves, determine if game over
                validSpots.remove(location);
                movesCount ++;
                this.currentState = GameState.IN_PROGRESS;
                if (this.validSpots.size() == 0) {
                    this.currentState = GameState.WON;
                }
            }
            notifyObserver(location);
        }
    }


    @Override
    public String toString() {

        String returnString = ""; 

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Location space = new Location(row, col);
                if (validSpots.contains(space) || mines.contains(space)) {
                    returnString += COVERED;
                }
                else {
                    returnString += Integer.toString(getMineCount(space));
                }
                
            }
            returnString += "\n";
        }

        return returnString;
    }

    /**
     * showBoard should be called when a user selects a mine and the game ends.
     * this method will show the entire board without covers
     * @return A string representation of the completed board without any covers
     */
    public String showBoard() {

        String returnString = ""; 

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Location space = new Location(row, col);
                if (mines.contains(space)) {
                    returnString += MINE;
                }
                else {
                    returnString += Integer.toString(getMineCount(space));
                }
                
            }
            returnString += "\n";
        }

        return returnString;
    }
    //getpossible collection thing
    //We also need a function for resetting the game - see instruction 4c


    /**
     * a standard geter function for validSpots
     * @return all of the validSpots on the board  
     */
    public HashSet<Location> getPossibleSelections(){
        return this.validSpots;
    }

    public void register(MinesweeperObserver observer){
        this.observer = observer;
    }

    private void notifyObserver(Location location){
        if (observer != null){
            observer.cellUpdated(location);
        }
    }

    /**
     * Returns the current character for this position, either covered, mine
     * or the value of the getMineCount function
     * @param location The location that we wish to check
     * @return The character that represents this location
     * @throws Exception Handles invalid exceptions
     */
    public char getSymbol(Location location) throws Exception{
        if(validSpots.contains(location)){
            return COVERED;
        } else if(mines.contains(location) && (this.currentState.equals(GameState.LOST) || this.currentState.equals(GameState.WON))){
            return MINE;
        } else {
            return Character.forDigit(getMineCount(location), 10);
        }
    }

    /**
     * Determines if a given location is covered
     * @param location The location we wish to check
     * @return A boolean to indicate whether it is covered or not
     * @throws Exception Handles invalid locations
     */
    public boolean isCovered(Location location) throws Exception{
        if(validSpots.contains(location) || mines.contains(location)){
            return true;
        }
        return false;
    }

}