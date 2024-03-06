package persistence;

import model.LogEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.TrainingLogger;


import java.io.*;
import java.util.List;

// Represents a writer that writes JSON representation of training logger to file.
// Data persistence implementation is based on JsonSerializationDemo.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of training logger object to file
    public void write(TrainingLogger tl) {
        JSONObject json = tl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a training logs list to file
    // For testing purposes
    public void write(List<LogEntry> list) {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry l: list) {
            jsonArray.put(l.toJson());
        }

        JSONObject json = new JSONObject();
        json.put("logs", jsonArray);

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}