package minesweeper.model;

public class Location {
    private int row;
    private int col;


    /**
     * A simple constructor that creates a Location object with given row and column
     */
    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * 
     * @return the row of the given location object
     */
    public int getCol() {
        return col;
    }

    /**
     * 
     * @return the column of the given location object
     */
    public int getRow() {
        return row;
    }

    /**
     * @return a string in the form (row, col)
     */
    @Override
    public String toString(){
        return "( " + this.row + ", " + this.col + ")";
    }

    /**
     * A method to determine if this location is equal to the given object
     * First ensures that the object is a location, then compares row and col
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Location){
            Location l1 = (Location)other;
            return l1.getRow() == this.getRow() && l1.getCol() == this.getCol();
        }
        return false;
    }

    /**
     * Simple hashcode. Converts row and column to strings and hashes them.
     * @return the multiplication of the 2 hashcodes 
     */
    @Override
    public int hashCode(){
        int rowHash = Integer.toString(this.row).hashCode();
        int colHash = Integer.toString(this.col).hashCode();
        return rowHash * colHash;
    }
}