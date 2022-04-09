package minesweeper.model;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Solve implements EventHandler<ActionEvent>{
    Minesweeper game;

    public Solve(Minesweeper game){
        this.game = game;
    }

    @Override
    public void handle(ActionEvent arg0) {
        MinesweeperSolver solution = MinesweeperSolver.solveGame(game);
        if(solution != null){
            List<Location> steps = solution.getLocationSolution();

            for(Location step : steps){
                try{
                    game.makeSelection(step);
                } catch(Exception e){
                    //squish
                }
                

            }
        } 
    }
        
}
    

