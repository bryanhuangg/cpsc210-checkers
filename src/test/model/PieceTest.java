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
        assertEquals(1, testPiece.getX());
        assertEquals(0, testPiece.getY());
        assertTrue(testPiece.isBlackPiece());
    }

    @Test
    void testSetX() {
        testPiece.setX(3);
        assertEquals(3, testPiece.getX());
    }

    @Test
    void testSetY() {
        testPiece.setY(1);
        assertEquals(1, testPiece.getY());
    }
}
