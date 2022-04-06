package minesweeper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Configuration;

public class MinesweeperSolver implements Configuration{
    private Minesweeper minesweeper;
    private List<Location> locationSolution;

    public MinesweeperSolver(Minesweeper minesweeper){
        this.minesweeper = minesweeper;
        this.locationSolution = new ArrayList<>();
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();
        
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
}