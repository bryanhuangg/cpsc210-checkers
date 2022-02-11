package ui;

import model.Board;
import model.Piece;


import java.util.Scanner;

public class CheckersApp {
    protected Board board;
    private Scanner input;

    //EFFECT: run the checkers application
    public CheckersApp() {
        runCheckers();
    }

    //MODIFIES: this
    //EFFECT: process user input
    private void runCheckers() {
        boolean isRunning = true;
        String command;

        init();

        while (isRunning) {
            displayMenu();
            System.out.println("\n =======================");
            renderBoard();
            System.out.println("\n =======================");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println();
        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a new checker game
    private void init() {
        board = new Board();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nConsole Checkers Commands:");
        System.out.println("\ta -> add a piece");
        System.out.println("\tr -> remove a piece");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPiece();
        } else if (command.equals("r")) {
            removePiece();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds piece based on user's input
    private void addPiece() {
        Scanner addInput = new Scanner(System.in);

        System.out.println("Creating a new piece...");
        System.out.println("Enter x coord, y coord and 'true' if black piece");

        int x = addInput.nextInt();
        int y = addInput.nextInt();
        boolean blackPiece = addInput.nextBoolean();

        if (board.addPiece(x, y, blackPiece)) {
            board.addPiece(x, y, blackPiece);
            System.out.println();
            System.out.println("Piece successfully added!");
            System.out.println("Total Pieces: " + board.getPieces().size());

        } else {
            System.out.println();
            System.out.println("Error - can not add piece there");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a piece from the board based on user's input
    public void removePiece() {
        Scanner removeInput = new Scanner(System.in);

        System.out.println("Which piece would you like removed?");
        System.out.println("Enter the piece's x and y coordinates");

        int x = removeInput.nextInt();
        int y = removeInput.nextInt();
        Piece remove = null;

        for (Piece p : board.getPieces()) {
            if (x == p.getX() && y == p.getY()) {
                remove = p;
            } else {
                System.out.println("Error - piece not found");
            }
        }
        
        board.deletePiece(remove);
        System.out.println("Piece successfully removed");
        System.out.println("Total Pieces: " + board.getPieces().size());


    }



    // MODIFIES: this
    // EFFECT: display current checkers board
    private void renderBoard() {

        System.out.println("   0  1  2  3  4  5  6  7");
        for (int y = 0; y <= 7; y++) {
            System.out.print(y + " ");
            for (int x = 0; x <= 7; x++) {

                switch (board.getBoard()[y][x]) {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print("[ ]");
                        break;

                    case 2:
                        System.out.print("[B]");
                        break;

                    default:
                        System.out.print("[W]");
                }
            }
            System.out.println();
        }
    }
}
