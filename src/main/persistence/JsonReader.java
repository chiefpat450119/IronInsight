package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a reader that reads a training logger from JSON data stored in file
// Data persistence implementation is based on JsonSerializationDemo.
public class JsonReader {
    private String source;

    // TODO: Fix all this

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads training logs list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<LogEntry> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrainingLogs(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses log entry list from JSON object and returns it
    private List<LogEntry> parseTrainingLogs(JSONObject jsonObject) {
        List<LogEntry> lst = new ArrayList<>();
        addEntries(lst, jsonObject);
        return lst;
    }

    // MODIFIES: lst
    // EFFECTS: parses log entries from JSON object and adds them to the given list
    private void addEntries(List<LogEntry> lst, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("logs");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(lst, nextEntry);
        }
    }

    // MODIFIES: lst
    // EFFECTS: parses entry from JSON object and adds it to the list
    private void addEntry(List<LogEntry> lst, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));

        String type = jsonObject.getString("type");
        if (type.equals("record") | type.equals("goal")) {
            List<Exercise> exercises = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("exercises");
            for (Object json : jsonArray) {
                JSONObject nextExercise = (JSONObject) json;
                addExercise(exercises, nextExercise);
            }
            if (type.equals("goal")) {
                lst.add(createGoal(jsonObject, name, date, exercises));
            } else {
                Record record = new Record(date, name);
                record.setExercises(exercises);
                lst.add(record);
            }
        } else if (type.equals("pb")) {
            lst.add(createPB(jsonObject, name, date));
        }
    }

    // EFFECTS: parses goal from JSON object with given name and date and returns it
    private Goal createGoal(JSONObject jsonObject, String name, LocalDate date, List<Exercise> exercises) {
        String description = jsonObject.getString("description");
        LocalDate targetDate = LocalDate.parse(jsonObject.getString("target date"));
        boolean completed = jsonObject.getBoolean("completed");

        Goal goal = new Goal(date, name, description);
        goal.setExercises(exercises);
        goal.setTargetDate(targetDate);
        if (completed) {
            goal.setCompleted();
        }
        return goal;
    }

    // EFFECTS: parses Personal Best from JSON object with given name and date and returns it
    private PersonalBest createPB(JSONObject jsonObject, String name, LocalDate date) {
        int pbWeight = jsonObject.getInt("pb weight");
        return new PersonalBest(date, name, pbWeight);
    }

    // MODIFIES: exercises
    // EFFECTS: parses an exercise from json object and adds it to the list
    private void addExercise(List<Exercise> exercises, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        int reps = jsonObject.getInt("reps");
        int rpe = jsonObject.getInt("rpe");

        exercises.add(new Exercise(name, weight, reps, rpe));
    }
}