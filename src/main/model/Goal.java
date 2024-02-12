package model;

import java.util.Date;

// A log entry representing a strength target
public class Goal extends LogEntry {
    private Date targetDate;
    private int targetWeight;
    private String description;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a Goal with given date and name
    public Goal(Date date, String name, String description) {
        super(date, name);
        this.description = description;
    }

    // REQUIRES: targetWeight >= 0
    // MODIFIES: this
    // EFFECTS: Modifies the goal by setting the date to achieve and the target weight.
    public void setTarget(Date targetDate, int targetWeight) {
        this.targetDate = targetDate;
        this.targetWeight = targetWeight;
    }

    // EFFECTS: returns a string summary of the goal
    @Override
    public String summary() {
        return null; // TODO: Implement
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public String getDescription() {
        return description;
    }
}
