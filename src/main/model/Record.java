package model;

import java.time.LocalDate;
import java.util.ArrayList;

// A log entry representing a record of a singular training session with a list of exercises.
public class Record extends LogEntry {
    protected ArrayList<Exercise> exercises;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a strength training log entry with empty list of exercises
    public Record(LocalDate date, String name) {
        super(date, name);
        this.exercises = new ArrayList<Exercise>();
    }

    // REQUIRES: Exercise is not already in exercises
    // MODIFIES: this
    // EFFECTS: Adds the given exercise to the end of the record's list of exercises
    public void addExercise(Exercise e) {
        exercises.add(e);
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

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
