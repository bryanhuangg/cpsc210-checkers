package persistence;


import model.Board;
import model.Piece;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Source: JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Board b = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testEmptyBoard.json");
        try {
            Board b = reader.read();
            assertEquals(0, b.getPieces().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBoard() {
        JsonReader reader = new JsonReader("./data/testGeneralBoard.json");
        try {
            Board b = reader.read();
            List<Piece> pieces = b.getPieces();
            assertEquals(2, pieces.size());
            checkPieces(1, 0, true, pieces.get(0));
            checkPieces(0, 3, false, pieces.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
