package minesweeper.view;

import java.util.HashSet;
import java.util.Scanner;

import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

public class CLI {
    public static void main(String[] args) throws Exception {
        final int ROW = 4;
        final int COL = 4;
        final int MINES = 2;
        Minesweeper game = new Minesweeper(ROW, COL, MINES); //any values

        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands:");
        System.out.println("\t help - this help message");
        System.out.println("\t pick <row> <col> - uncovers cell a <row> <col>");
        System.out.println("\t hint - displays a safe selection");
        System.out.println("\t reset - resets to a new game");
        System.out.println("\t quit - quits the game");

        while (game.getGameState() != GameState.WON && game.getGameState() != GameState.LOST){
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("help")){
                System.out.println("Commands:");
                System.out.println("\t help - this help message");
                System.out.println("\t pick <row> <col> - uncovers cell a <row> <col>");
                System.out.println("\t hint - displays a safe selection");
                System.out.println("\t reset - resets to a new game");
                System.out.println("\t quit - quits the game");
                System.out.println(game);
            } else if(tokens[0].equals("pick" )){
                Location newMove = new Location(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                game.makeSelection(newMove);
                if (game.getGameState() == GameState.LOST){
                    System.out.println("BOOM! You lost");
                    System.out.println(game.showBoard());
                }else if(game.getGameState() == GameState.WON){
                    System.out.println("Congratulations");
                } else{
                    System.out.println(game);
                }
            } else if (tokens[0].equals("quit")){
                break;
            } else if (tokens[0].equals("reset")){
                System.out.println("Resetting the game");
                Minesweeper newGame  = new Minesweeper(ROW, COL, MINES);
                game = newGame;
                System.out.println(game);
            } else if(tokens[0].equals("hint")){
                HashSet<Location> moves = new HashSet<>();
                Location[] arrayMoves = moves.toArray(new Location[moves.size()]);
                System.out.println("Give " + arrayMoves[0] + " a try!");
                System.out.println(game);
            }

            }
            scanner.close();
        }
    }
