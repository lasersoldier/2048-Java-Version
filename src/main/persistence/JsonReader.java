package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private static String source = "./data/two048.json";

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads 2048 board from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static JSONArray readBoard() throws Exception {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTwo048(jsonObject);
    }

    // EFFECTS: reads 2048 score list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static JSONArray readScore() throws Exception {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parse 2048 board from JSON file and return it
    private static JSONArray parseTwo048(JSONObject jsonObject) {
        JSONArray jsonList = jsonObject.getJSONArray("BOARD");
        return jsonList;
    }

    // EFFECTS: parse last game's score from JSON file and return it
    private static JSONArray parseScoreList(JSONObject jsonObject) {
        JSONArray jsonList = jsonObject.getJSONArray("SCORELIST");
        return jsonList;
    }



}




