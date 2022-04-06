package minesweeper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Configuration;

public class MinesweeperSolver implements Configuration{
    private Minesweeper minesweeper;
    private List<Location> locationSolution;
    private final int ROWS;
    private final int COLS;

    public MinesweeperSolver(Minesweeper minesweeper,int ROWS,int COLS){
        this.minesweeper = minesweeper;
        this.locationSolution = new ArrayList<>();
        this.ROWS = ROWS;
        this.COLS = COLS;
    }
    private MinesweeperSolver(Minesweeper minesweeper,MinesweeperSolver old){
        this.minesweeper = minesweeper;
        this.locationSolution = old.locationSolution;
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
        while (successors.size() != 8){

            Minesweeper nextMinesweeper = new Minesweeper(minesweeper);

            try {
                nextMinesweeper.makeSelection(nextLocation);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            MinesweeperSolver nextMinesweeperSolver =new MinesweeperSolver(nextMinesweeper,this);
            nextMinesweeperSolver.locationSolution.add(nextLocation);

            successors.add( nextMinesweeperSolver);

            nextLocation = getNextLocation(nextLocation);
        }
        
        return successors;
    }

    @Override
    public boolean isValid() {
        return this.minesweeper.getGameState() != GameState.LOST;
    }

    @Override
    public boolean isGoal() {
        return this.minesweeper.getGameState() == GameState.WON;
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
            return new Location( (location.getRow() +1) % COLS ,0);
        }
        return new Location( (location.getRow()), location.getCol()+1 );
    }
}