/**
 * This program houses the custom exception that the game will throw
 */

package minesweeper.model;

public class MinesweeperException extends java.lang.Exception{
    public MinesweeperException(String message) throws Exception{
        throw new java.lang.Exception(message);
    }   
}