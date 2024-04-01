package model;

import org.json.JSONObject;

import java.time.LocalDate;

// A log entry representing a strength target with target date a list of exercises, can be marked completed.
// Has a name, date and description.
public class Goal extends Record {
    private LocalDate targetDate;
    private String description;
    private boolean completed;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a Goal with given date and name, not completed
    public Goal(LocalDate date, String name, String description) {
        super(date, name);
        this.description = description;
        this.completed = false;
    }

    // REQUIRES: targetWeight >= 0, exerciseName not empty or null
    // MODIFIES: this
    // EFFECTS: Modifies the goal by setting the date to achieve and the target weight on exercise
    // with given name.
    public void addTarget(LocalDate targetDate, int targetWeight, String exerciseName) {
        this.targetDate = targetDate;
        EventLog.getInstance().logEvent(new Event("Target date for " + name + " set as "
                + targetDate.toString()));
        addExercise(new Exercise(exerciseName, targetWeight, 1, 10));
    }

    // EFFECTS: returns a string summary of the goal
    @Override
    public String summary() {
        String prefix;
        if (completed) {
            prefix = "Completed goal ";
        } else {
            prefix = "Incomplete goal ";
        }
        return prefix + name + ", " + description;
    }

    // MODIFIES: this
    // EFFECTS: sets completed to true and records event
    public void setCompleted() {
        completed = true;
        EventLog.getInstance().logEvent(new Event("Goal named " + name + " marked as completed."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("description", description);
        json.put("target date", targetDate);
        json.put("completed", completed);
        json.remove("type");
        json.put("type", "goal");
        return json;
    }

    // EFFECTS: returns true if this goal is completed
    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public String getDescription() {
        return description;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
