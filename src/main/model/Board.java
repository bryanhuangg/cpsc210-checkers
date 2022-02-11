package model;

import java.util.LinkedList;
import java.util.List;

// Simulates a checkers board and it's pieces
public class Board {
    // A 8x8 checkers board with 0 representing white tiles and 1 representing black tiles
    private static final int[][] board =
            {{ 0, 1, 0, 1, 0, 1, 0, 1},
            { 1, 0, 1, 0, 1, 0, 1, 0},
            { 0, 1, 0, 1, 0, 1, 0, 1},
            { 1, 0, 1, 0, 1, 0, 1, 0},
            { 0, 1, 0, 1, 0, 1, 0, 1},
            { 1, 0, 1, 0, 1, 0, 1, 0},
            { 0, 1, 0, 1, 0, 1, 0, 1},
            { 1, 0, 1, 0, 1, 0, 1, 0}};

    // List of all the pieces currently on the board
    private final List<Piece> pieces;

    // EFFECT: create an empty board with no pieces
    public Board() {
        pieces = new LinkedList<>();

    }

    // MODIFIES: this
    // EFFECT: if a valid location place piece with a unique id on board and return true, else return false
    public boolean addPiece(int x, int y, boolean blackPiece) {

        if (withinBoard(x,y) && placeableTile(x,y) && emptyTile(x,y)) {


            Piece newPiece = new Piece(x,y,blackPiece);
            pieces.add(pieces.size(), newPiece);

            if (blackPiece) {
                board[y][x] = 2;
            } else {
                board[y][x] = 3;
            }
            return true;
        }
        return false;
    }



    // REQUIRES: index < pieces.size()
    // MODIFIES: this
    // EFFECT: removes the piece on given index of pieces and produce true, else false
    public void deletePiece(Piece piece) {
        pieces.remove(piece);
        board[piece.getY()][piece.getX()] = 1;
        System.out.println(pieces);
    }

    // EFFECT: produce true if coordinate is within the board, else false
    public boolean withinBoard(int x, int y) {

        if (x >= 0 && x <= 7) {
            return y >= 0 && y <= 7;
        }

        return false;
    }

    // REQUIRE: x & y are within [0, 7]
    // EFFECT: return true if tile can hold a piece (aka black tile), else false
    public boolean placeableTile(int x, int y) {

        return (board[y][x] == 1);
    }

    // EFFECT: produce true if no piece is already on a tile, else false
    public boolean emptyTile(int x, int y) {

        for (Piece p : pieces) {

            if (x == p.getX()) {
                if (y == p.getY()) {
                    return false;
                }
            }
        }

        return true;
    }

    // EFFECT: return list of pieces on the board;
    public List<Piece> getPieces() {
        return pieces;
    }

    // RETURN: return matrix representing the board
    public int[][] getBaseBoard() {
        return board;
    }
}
