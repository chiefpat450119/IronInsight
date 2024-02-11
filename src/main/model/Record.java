package model;

import java.util.Date;

// A log entry representing a record of a singular training session.
public class Record extends LogEntry {
    public Record(Date date, String name) {
        super(date, name);
    }
}
