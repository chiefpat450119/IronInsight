package model;

import java.util.Date;

// Represents a log entry recording body weight.
public class WeightRecord extends LogEntry {
    private int bodyWeight;

    // REQUIRES: name is not empty, bodyWeight >= 0
    // EFFECTS: Creates a log entry of the user's bodyweight with a given date and name
    public WeightRecord(Date date, String name, int bodyWeight) {
        super(date, name);
        this.bodyWeight = bodyWeight;
    }

    public int getBodyWeight() {
        return bodyWeight;
    }

    // EFFECTS: Returns a string summary of the log entry.
    @Override
    public String summary() {
        return null;
    }
}
