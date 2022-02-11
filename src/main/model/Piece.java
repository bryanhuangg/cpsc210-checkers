package model;


// Represents a Checkers piece on a board
public class Piece {

    private static int x;               // X position of a piece
    private static int y;               // Y position of a piece
    private static boolean blackPiece;  // true if black piece, false if white piece

    // EFFECT: create a new checkers piece on (x,y) with a boolean to clarify piece colour
    public Piece(int x, int y, boolean blackPiece) {
        Piece.x = x;
        Piece.y = y;
        Piece.blackPiece = blackPiece;
    }


    // MODIFY: this
    // EFFECT: set x position of the checkers piece
    public void setX(int x) {
        Piece.x = x;
    }

    // MODIFY: this
    // EFFECT: set y position of the checkers piece
    public void setY(int y) {
        Piece.y = y;
    }

    // EFFECT: return x position of the checkers piece
    public int getX() {
        return x;
    }

    // EFFECT: return y position of the checkers piece
    public int getY() {
        return y;
    }


    // EFFECT: return true if a black piece, else false;
    public boolean isBlackPiece() {
        return blackPiece;
    }
}
