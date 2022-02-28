package persistence;

import model.Board;
import model.Piece;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Board b = new Board();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Board b = new Board();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBoard.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBoard.json");
            b = reader.read();
            assertEquals(0, b.getPieces().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Board b = new Board();
            b.addPiece(0, 1, true);
            b.addPiece(0, 3, false);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBoard.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBoard.json");
            b = reader.read();
            List<Piece> pieces = b.getPieces();
            assertEquals(2, b.getPieces().size());
            checkPieces(0, 1, true, pieces.get(0));
            checkPieces(0, 3, false, pieces.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

