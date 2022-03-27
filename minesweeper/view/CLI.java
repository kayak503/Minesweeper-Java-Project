package minesweeper.view;

import java.util.Scanner;

import minesweeper.model.Minesweeper;

public class CLI {
    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(4, 4, 2); //any values

        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands:");
        System.out.println("\t help - this help message");
        System.out.println("\t pick <row> <col> - uncovers cell a <row> <col>");
        System.out.println("\t hint - displays a safe selection");
        System.out.println("\t reset - resets to a new game");
        System.out.println("\t quit - quits the game");
    }
}