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


    public Minesweeper(int rows, int cols, int mineCount){
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.currentState = GameState.NOT_STARTED;
        this.movesCount = 0;
        this.mines = new HashSet<>();
        this.validSpots = new HashSet<>();
        Random rng = new Random();

        while(this.mines.size() != mineCount){
            int newRow = rng.nextInt(rows);
            int newCol = rng.nextInt(cols);
            this.mines.add(new Location(newRow, newCol));
        }

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Location l = new Location(row, col);
                if(!mines.contains(l)){
                    validSpots.add(l);
                }
            }
        }
    }

    public int getMovesCount(){return this.movesCount;}
    public GameState getGameState(){return this.currentState;}

    // returns the number of mines around the current location
    public int getMineCount(Location location){
        int count = 0;
        int row = location.getRow();
        int col = location.getCol();
        if(mines.contains(new Location(row - 1, col - 1))){
            count++;
        }
        if(mines.contains(new Location(row, col - 1))){
            count++;
        }
        if(mines.contains(new Location(row + 1, col - 1))){
            count++;
        }
        if(mines.contains(new Location(row + 1, col))){
            count++;
        }
        if(mines.contains(new Location(row + 1, col + 1))){
            count++;
        }
        if(mines.contains(new Location(row, col + 1))){
            count++;
        }
        if(mines.contains(new Location(row - 1, col + 1))){
            count++;
        }
        if(mines.contains(new Location(row - 1, col))){
            count++;
        }
        return count;
    }

    // updates the game state method
    public void makeSelection(Location location) throws Exception{ // should be minesweeper exception !!!
        if(location.getRow() > rows || location.getCol() > cols){
            throw new MinesweeperException("Location out of Bounds");
        } else {
            if(this.mines.contains(location)){
                this.currentState = GameState.LOST;
                movesCount ++;
            }
            if(this.validSpots.contains(location)){
                validSpots.remove(location);
                movesCount ++;
                this.currentState = GameState.IN_PROGRESS;
                if (this.validSpots.size() == 0) {
                    this.currentState = GameState.WON;
                }
            }
        }
    }

    //tostring
    //getpossible collection thing

}