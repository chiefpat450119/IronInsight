package model;

import java.util.Date;

// Represents an entry in a training log, with a name and date.
public abstract class LogEntry {
    private final Date date;
    private String name;

    public LogEntry(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }
}
