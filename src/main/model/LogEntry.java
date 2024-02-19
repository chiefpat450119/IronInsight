package model;

import java.time.LocalDate;

// Represents an entry in a training log, with a name and date.
public abstract class LogEntry {
    protected LocalDate date;
    protected String name;

    // REQUIRES: name is not empty
    // EFFECTS: Creates a training log entry with given date and name.
    public LogEntry(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public abstract String summary();

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

}
