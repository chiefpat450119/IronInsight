package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// A log entry representing a record of a singular training session with a list of exercises.
public class Record extends LogEntry {
    protected List<Exercise> exercises;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a strength training log entry with empty list of exercises
    public Record(LocalDate date, String name) {
        super(date, name);
        this.exercises = new ArrayList<>();
    }

    // REQUIRES: Exercise is not already in exercises
    // MODIFIES: this
    // EFFECTS: Adds the given exercise to the end of the record's list of exercises and records event
    public void addExercise(Exercise e) {
        exercises.add(e);
        EventLog.getInstance().logEvent(new Event("Exercise named " + e.getName() + " added to "
                + name));
    }

    // EFFECTS: returns a string summary of the log entry with exercise summaries.
    @Override
    public String summary() {
        String summary = name + " on " + date.toString() + " with " + exercises.size() + " exercises:";
        for (Exercise e: exercises) {
            String newLine = System.getProperty("line.separator");
            summary = summary.concat(newLine);
            summary = summary.concat(e.summary());
        }
        return summary;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("exercises", exercisesToJson());
        json.put("type", "record");
        return json;
    }

    // EFFECTS: returns exercises in this record as a JSON array.
    protected JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e: exercises) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
