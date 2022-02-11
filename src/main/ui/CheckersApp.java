package ui;

import model.Board;
import model.Piece;


import java.util.LinkedList;
import java.util.List;
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
            updateBoard();
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
        System.out.println("\tm -> move a pieces");
        System.out.println("\tc -> count number of pieces");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPiece();
        } else if (command.equals("r")) {
            removePiece();
        } else if (command.equals("m")) {
            movePiece();
        } else if (command.equals("c")) {
            countPieces();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: print out how many pieces are currently on the board
    private void countPieces() {
        List<Piece> empty = new LinkedList<>();

        if (!board.getPieces().equals(empty)) {
            System.out.println();
            System.out.println("Current number of pieces on the board: " + board.getPieces().size());
        } else {
            System.out.println();
            System.out.println("There are no pieces currently on the board.");
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
            if (x == p.getXPos() && y == p.getYPos()) {
                remove = p;
            } else {
                System.out.println();
                System.out.println("Error - piece not found");
            }
        }
        
        board.deletePiece(remove);
        System.out.println();
        System.out.println("Piece successfully removed");
    }


    // MODIFIES: this
    // EFFECTS: moves a piece onto a black tile based on user's input
    public void movePiece() {
        Scanner moveInput = new Scanner(System.in);

        System.out.println("Which piece would you like moved?");
        System.out.println("Enter the piece's current x and y coordinates");

        int x = moveInput.nextInt();
        int y = moveInput.nextInt();
        Piece move = null;

        System.out.println("Enter the piece's target x and y coordinates");

        int targetX = moveInput.nextInt();
        int targetY = moveInput.nextInt();


        for (Piece p : board.getPieces()) {
            if (x == p.getXPos() && y == p.getYPos()) {
                move = p;
                break;
            } else {
                System.out.println();
                System.out.println("Error - piece not found");
            }
        }
        board.movePiece(move, targetX, targetY);
        System.out.println();
        System.out.println("Piece successfully moved");
    }


    // MODIFIES: this
    // EFFECT: place all in-play pieces onto a blank checkers board and renders it
    private void updateBoard() {
        int[][] currentBoard = board.getBaseBoard();

        for (Piece p: board.getPieces()) {
            if (p.getIsBlackPiece()) {
                currentBoard[p.getYPos()][p.getXPos()] = 2;
            } else {
                currentBoard[p.getYPos()][p.getXPos()] = 3;
            }
        }

        renderBoard(currentBoard);
    }


    // EFFECT: print out current checkers board
    private void renderBoard(int[][] board) {
        System.out.println("   0  1  2  3  4  5  6  7");
        for (int y = 0; y <= 7; y++) {
            System.out.print(y + " ");
            for (int x = 0; x <= 7; x++) {

                switch (board[y][x]) {
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
