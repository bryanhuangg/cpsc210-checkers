package model;

import org.json.JSONArray;
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

    // EFFECTS: create an empty board with no pieces
    public Board() {
        pieces = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: if a valid location place piece with a unique id on board and return true, else return false
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
    // EFFECTS: if given a placeable tile, move a piece to given coordinate and produce true, else false
    public void movePiece(Piece p, int x, int y) {
        if (withinBoard(x,y) && placeableTile(x,y) && emptyTile(x,y)) {
            p.setXPos(x);
            p.setYPos(y);
        }
    }


    // REQUIRES: index < pieces.size()
    // MODIFIES: this
    // EFFECTS: removes the piece on given index of pieces and produce true, else false
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


    // EFFECTS: produce true if no piece is already on a tile, else false
    public boolean emptyTile(int x, int y) {

        for (Piece p : pieces) {
            if (x == p.getXPos() && y == p.getYPos()) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: return list of pieces on the board;
    public List<Piece> getPieces() {
        return pieces;
    }

    // EFFECTS: return matrix representing an empty checkers board
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

    // EFFECTS: return matrix representing current checkers board
    //         0 = white tile, 1 = black tile, 2 = black piece, 3 = white piece
    public int[][] getCurrentBoard() {
        int[][] currentBoard = getBaseBoard();

        for (Piece p: pieces) {
            if (p.getIsBlackPiece()) {
                currentBoard[p.getYPos()][p.getXPos()] = 2;
            } else {
                currentBoard[p.getYPos()][p.getXPos()] = 3;
            }
        }

        return currentBoard;
    }

    // MODIFIES: this
    // EFFECT: removes all pieces
    public void clearBoard() {
        pieces = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets pieces to standard starting position
    public void newGame() {
        clearBoard();
        for (int i = 1; i <= 7; i += 2) {
            addPiece(i,0,true);
        }
        for (int i = 0; i <= 7; i += 2) {
            addPiece(i,1,true);
        }

        for (int i = 1; i <= 7; i += 2) {
            addPiece(i,6,false);
        }
        for (int i = 0; i <= 7; i += 2) {
            addPiece(i,7,false);
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Pieces", piecesToJson());
        return json;
    }

    // EFFECTS: returns pieces on this board to JSON array
    private JSONArray piecesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Piece p : pieces) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
