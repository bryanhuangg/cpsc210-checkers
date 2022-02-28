package persistence;

import model.Board;
import model.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPieces(int x, int y, boolean isBlack, Piece piece) {
        assertEquals(x, piece.getXPos());
        assertEquals(y, piece.getYPos());
        assertEquals(isBlack, piece.getIsBlackPiece());
    }
}
