package minesweeper.model;

public class Minesweeper{
    public final char MINE = 'M';
    public final char COVERED = '-';

    private int rows;
    private int cols;
    private int mineCount;
    private int movesCount;
    private GameState currentState;

    public Minesweeper(int rows, int cols, int mineCount){
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.currentState = GameState.NOT_STARTED;
        this.movesCount = 0;
    }

    public int getMovesCount(){return this.movesCount;}
    public GameState getGameState(){return this.currentState;}

    public void makeSelection() throws MinesweeperException{
        
    }
}