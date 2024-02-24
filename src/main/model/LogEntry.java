package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// Represents an entry in a training log, with a name and date.
public abstract class LogEntry implements Writable {
    protected LocalDate date;
    protected String name;

    // REQUIRES: name is not empty
    // EFFECTS: Creates a training log entry with given date and name.
    public LogEntry(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date.toString());
        return json;
    }

    public abstract String summary();

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
