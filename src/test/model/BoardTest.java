package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board testBoard;

    @BeforeEach
    void runBefore() {
        testBoard = new Board();
    }

    @Test
    void testConstructor() {
        // Check if start with an empty list of pieces
        List<Piece> empty = new LinkedList<>();
        assertEquals(empty, testBoard.getPieces());
    }

    @Test
    void testWithinBoard() {
        // Test x & y are within [0, 7]
        assertTrue(testBoard.withinBoard(0,0));
        assertTrue(testBoard.withinBoard(7,0));
        assertTrue(testBoard.withinBoard(3,7));
        assertTrue(testBoard.withinBoard(4,2));

        assertFalse(testBoard.withinBoard(-1,0));
        assertFalse(testBoard.withinBoard(1,-1));
        assertFalse(testBoard.withinBoard(8,0));
        assertFalse(testBoard.withinBoard(3,8));
    }

    @Test
    void testPlaceableTile() {
        assertTrue(testBoard.placeableTile(1, 0));
        assertTrue(testBoard.placeableTile(0, 1));
        assertTrue(testBoard.placeableTile(2, 7));

        assertFalse(testBoard.placeableTile(0,0));
        assertFalse(testBoard.placeableTile(7,7));
        assertFalse(testBoard.placeableTile(1,3));
    }

    @Test
    void testAddPiece() {
        // Check tile validity
        assertFalse(testBoard.addPiece(8,0, true ));
        assertFalse(testBoard.addPiece(1,-1, true ));
        assertFalse(testBoard.addPiece(0,0,false));
        assertFalse(testBoard.addPiece(2,4, false));
        assertFalse(testBoard.addPiece(2,6,true));

        // Check overlapping
        assertTrue(testBoard.addPiece(3, 0,  true));
        assertFalse(testBoard.addPiece(3, 0,  true));
        assertFalse(testBoard.addPiece(3, 0 , false));

        // Check if pieces are actually added to the board
        assertEquals(1, testBoard.getPieces().size());

        assertTrue(testBoard.addPiece(0, 7, false));
        assertEquals(2, testBoard.getPieces().size());

        assertTrue(testBoard.addPiece(7, 0, true));
        assertEquals(3, testBoard.getPieces().size());
    }

    @Test
    void testEmptyTile() {
        testBoard.addPiece(1, 0,  true);
        assertTrue(testBoard.emptyTile(3,2));
        assertFalse(testBoard.emptyTile(1,0));

        testBoard.addPiece(0, 1,  true);
        assertFalse(testBoard.emptyTile(0,1));
        assertTrue(testBoard.emptyTile(3,1));
    }

    @Test
    void testDeletePiece() {
        testBoard.addPiece(7, 0, true);
        testBoard.deletePiece(testBoard.getPieces().get(0));
        assertEquals(0,testBoard.getPieces().size());
    }

    @Test
    void testMovePiece() {
        testBoard.addPiece(0,1, false);
        testBoard.movePiece(testBoard.getPieces().get(0), 2, 3);
        assertEquals(2, testBoard.getPieces().get(0).getXPos());
        assertEquals(3, testBoard.getPieces().get(0).getYPos());

        testBoard.movePiece(testBoard.getPieces().get(0), 0, 0);
        assertEquals(2, testBoard.getPieces().get(0).getXPos());
        assertEquals(3, testBoard.getPieces().get(0).getYPos());

        testBoard.addPiece(0,1, false);
        testBoard.movePiece(testBoard.getPieces().get(1), 2, 3);
        assertEquals(0, testBoard.getPieces().get(1).getXPos());
        assertEquals(1, testBoard.getPieces().get(1).getYPos());

        testBoard.movePiece(testBoard.getPieces().get(1), 8, -1);
        assertEquals(0, testBoard.getPieces().get(1).getXPos());
        assertEquals(1, testBoard.getPieces().get(1).getYPos());
    }
}