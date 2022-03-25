package minesweeper.model;

public class Location {
    private int row;
    private int col;

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString(){
        return "( " + this.row + ", " + this.col + ")";
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Location){
            Location l1 = (Location)other;
            return l1.getRow() == this.getRow() && l1.getCol() == this.getCol();
        }
        return false;
    }

    @Override
    public int hashCode(){
        int rowHash = Integer.toString(this.row).hashCode();
        int colHash = Integer.toString(this.col).hashCode();
        return rowHash * colHash;
    }
}