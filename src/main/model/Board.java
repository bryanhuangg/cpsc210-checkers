package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Simulates a checkers board and it's pieces
public class Board implements Writable {
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
    private List<Piece> pieces;

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
            return true;
        }
        return false;
    }

    // REQUIRE: current piece on a black tile
    // MODIFIES: this
    // EFFECT: if given a placeable tile, move a piece to given coordinate and produce true, else false
    public void movePiece(Piece p, int x, int y) {
        if (withinBoard(x,y) && placeableTile(x,y) && emptyTile(x,y)) {
            p.setXPos(x);
            p.setYPos(y);
        }
    }


    // REQUIRES: index < pieces.size()
    // MODIFIES: this
    // EFFECT: removes the piece on given index of pieces and produce true, else false
    public void deletePiece(Piece piece) {
        pieces.remove(piece);
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
            if (x == p.getXPos() && y == p.getYPos()) {
                return false;
            }
        }
        return true;
    }

    // EFFECT: return list of pieces on the board;
    public List<Piece> getPieces() {
        return pieces;
    }

    // RETURN: return matrix representing an empty checkers board
    public int[][] getBaseBoard() {

        int[][] baseBoard =
                {{ 0, 1, 0, 1, 0, 1, 0, 1},
                { 1, 0, 1, 0, 1, 0, 1, 0},
                { 0, 1, 0, 1, 0, 1, 0, 1},
                { 1, 0, 1, 0, 1, 0, 1, 0},
                { 0, 1, 0, 1, 0, 1, 0, 1},
                { 1, 0, 1, 0, 1, 0, 1, 0},
                { 0, 1, 0, 1, 0, 1, 0, 1},
                { 1, 0, 1, 0, 1, 0, 1, 0}};

        return baseBoard;
    }

    @Override
    public JSONObject toJson() {
        //TODO
        return null;
    }
}
