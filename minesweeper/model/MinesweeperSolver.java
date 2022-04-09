package minesweeper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Backtracker;
import backtracker.Configuration;

public class MinesweeperSolver implements Configuration{
    private Minesweeper minesweeper;
    private List<Location> locationSolution;
    private final int ROWS;
    private final int COLS;

    /**
     * A constructor to create a new solver model based off of rows and columns
     */
    public MinesweeperSolver(Minesweeper minesweeper,int ROWS,int COLS){
        this.minesweeper = minesweeper;
        this.locationSolution = new ArrayList<>();
        this.ROWS = ROWS;
        this.COLS = COLS;
    }
    
    /**
     * A constructor to create a new solvere based off of an old solver - a deep copy
     * @param minesweeper The game state 
     * @param old The old solver configuration that we are copying
     */
    private MinesweeperSolver(Minesweeper minesweeper, MinesweeperSolver old){
        this.minesweeper = minesweeper;
        this.locationSolution = new ArrayList<>(old.locationSolution);
        this.ROWS = old.ROWS;
        this.COLS = old.COLS;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        Location nextLocation;
        
        if (locationSolution.size() == 0){
            nextLocation = new Location(0,0);
        }
        else{
            nextLocation = getNextLocation( 
                locationSolution.get(locationSolution.size()-1) 
            );
        }
        for (int i = 0; i < 8; i++){
            Minesweeper nextMinesweeper = new Minesweeper(minesweeper);
            MinesweeperSolver nextMinesweeperSolver = new MinesweeperSolver(nextMinesweeper,this);
            nextMinesweeperSolver.locationSolution.add(nextLocation);
            successors.add( nextMinesweeperSolver);
            
            nextLocation = getNextLocation(nextLocation);
        }
        
        return successors;
    }

    @Override
    public boolean isValid() {
        try {
            minesweeper.makeSelection(locationSolution.get(locationSolution.size()-1));
        } catch (Exception e) {
            return false;
        }
        return this.minesweeper.getGameState() != GameState.LOST;
    }

    @Override
    public boolean isGoal() {
        return this.minesweeper.getGameState().equals(GameState.WON);
    }

    public List<Location> getLocationSolution() {
        return locationSolution;
    }

    @Override
    public String toString(){
        return "";
    }

    private Location getNextLocation(Location location){
        if (location.getCol() == COLS){
            return new Location( (location.getRow() +1) ,0);
        }
        return new Location( (location.getRow()), location.getCol()+1 );
    }

    public static MinesweeperSolver solveGame(Minesweeper minesweeper){
        MinesweeperSolver config = new MinesweeperSolver(minesweeper, minesweeper.getRows(), minesweeper.getCols());
        Backtracker backtracker = new Backtracker(false);
        MinesweeperSolver solution = (MinesweeperSolver)backtracker.solve(config);
        return solution;
    }
}