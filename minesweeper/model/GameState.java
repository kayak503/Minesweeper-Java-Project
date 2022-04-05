/**
 * This enumeration houses the 4 possible game states
 * either it has not started, it is still going, the game
 * has been lost, or the game has been won
 */

package minesweeper.model;

public enum GameState {
    NOT_STARTED,
    IN_PROGRESS,
    WON,
    LOST;
}