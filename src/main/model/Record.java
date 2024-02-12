package model;

import java.util.ArrayList;
import java.util.Date;

// A log entry representing a record of a singular training session with a list of exercises.
public class Record extends LogEntry {
    protected ArrayList<Exercise> exercises;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a strength training log entry with empty list of exercises
    public Record(Date date, String name) {
        super(date, name +  " " + date.toString());
        this.exercises = new ArrayList<Exercise>();
    }

    // EFFECTS: Returns the calculated maximum weight for a given exercise, or -1 if not found.
    public double getMaxWeight(String exerciseName) {
        for (Exercise e: exercises) {
            if (exerciseName.equals(e.getName())) {
                return e.calculateMax();
            }
        }
        return -1;
    }

    // REQUIRES: Exercise is not already in exercises
    // MODIFIES: this
    // EFFECTS: Adds the given exercise to the record's list of exercises
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    // EFFECTS: returns a string summary of the log entry
    @Override
    public String summary() {
        return null;
    }

    public String getSummary() {
        return null;
    }
}
