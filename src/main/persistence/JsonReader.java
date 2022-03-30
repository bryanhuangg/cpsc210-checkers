package persistence;

import model.Board;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Event;
import model.EventLog;
import model.Piece;
import org.json.*;


// Represents a reader that reads workroom from JSON data stored in file
// Source: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Board read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
            EventLog.getInstance().logEvent(new Event("Loaded prior saved checkers board"));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses board from JSON object and returns it
    private Board parseBoard(JSONObject jsonObject) {
        Board b = new Board();
        addPieces(b, jsonObject);
        return b;
    }

    // MODIFIES: b
    // EFFECTS: parses pieces from JSON object and adds them to board
    private void addPieces(Board b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Pieces");
        for (Object json : jsonArray) {
            JSONObject nextPiece = (JSONObject) json;
            addPiece(b, nextPiece);
        }
    }

    // MODIFIES: b
    // EFFECTS: parses singular piece from JSON object and adds it to board
    private void addPiece(Board b, JSONObject jsonObject) {
        b.addPiece(jsonObject.getInt("x position"),
                jsonObject.getInt("y position"),
                jsonObject.getBoolean("is black piece?"));
    }
}
