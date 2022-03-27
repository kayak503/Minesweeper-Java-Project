package minesweeper.view;

import java.util.HashSet;
import java.util.Scanner;
import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

/**
 * This is the basic CLI for the minesweeper game, it has all of the commands
 * necessary to play, reset, and give the player hints
 */
public class CLI {
    /**
     * basic game loop
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final int ROW = 4;
        final int COL = 4;
        final int MINES = 2;
        Minesweeper game = new Minesweeper(ROW, COL, MINES);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Mines: " + MINES);
        System.out.println("Commands:");
        System.out.println("\t help - this help message");
        System.out.println("\t pick <row> <col> - uncovers cell a <row> <col>");
        System.out.println("\t hint - displays a safe selection");
        System.out.println("\t reset - resets to a new game");
        System.out.println("\t quit - quits the game");
        System.out.println("\n" + game);

        while (game.getGameState() != GameState.WON && game.getGameState() != GameState.LOST){ // loops until the game state is won or lost
            System.out.println("\nMoves: " + game.getMovesCount());
            System.out.print("\nEnter a command: ");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("help")){ // prints out the possible commands 
                System.out.println("Commands:");
                System.out.println("\t help - this help message");
                System.out.println("\t pick <row> <col> - uncovers cell a <row> <col>");
                System.out.println("\t hint - displays a safe selection");
                System.out.println("\t reset - resets to a new game");
                System.out.println("\t quit - quits the game");
                System.out.println(game);
            } else if(tokens[0].equals("pick" )){ // makes a choice on the game board
                Location newMove = new Location(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                game.makeSelection(newMove);
                if (game.getGameState() == GameState.LOST){
                    System.out.println("BOOM! You lost");
                    System.out.println(game.showBoard());
                }else if(game.getGameState() == GameState.WON){
                    System.out.println("Congratulations");
                    System.out.println(game.showBoard());
                } else{
                    System.out.println(game);
                }
            } else if (tokens[0].equals("quit")){ // quits out of the game
                break;
            } else if (tokens[0].equals("reset")){ // makes a new game board of the same size and number of mines
                System.out.println("Resetting the game");
                Minesweeper newGame  = new Minesweeper(ROW, COL, MINES);
                game = newGame;
                System.out.println(game);
            } else if(tokens[0].equals("hint")){ // gives the user a possible option left on the board
                HashSet<Location> moves = game.getPossibleSelections();
                Location[] arrayMoves = moves.toArray(new Location[moves.size()]);
                System.out.println("Give " + arrayMoves[0] + " a try!");
                System.out.println(game);
            }

            }
            scanner.close(); // closes the scanner at the end
        }
    }
