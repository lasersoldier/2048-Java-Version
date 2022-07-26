package persistence;

import jdk.nashorn.internal.parser.JSONParser;
import model.Num;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Two048;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private static String source = "./data/two048.json";

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static JSONArray read() throws Exception {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTwo048(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private static JSONArray parseTwo048(JSONObject jsonObject) {
        ArrayList<String> string = new ArrayList<>();
        JSONArray jsonList = jsonObject.getJSONArray("BOARD");
        return jsonList;
    }

    // EFFECTS: parses workroom from JSON object and returns it

}




