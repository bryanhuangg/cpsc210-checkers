package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    private Piece testPiece;

    @BeforeEach
    void runBefore() {
        testPiece = new Piece(1, 0, true);
    }

    @Test
    void testConstructor() {
        assertEquals(1, testPiece.getXPos());
        assertEquals(0, testPiece.getYPos());
        assertTrue(testPiece.getIsBlackPiece());
    }

    @Test
    void testSetX() {
        testPiece.setXPos(3);
        assertEquals(3, testPiece.getXPos());
    }

    @Test
    void testSetY() {
        testPiece.setYPos(1);
        assertEquals(1, testPiece.getYPos());
    }
}
