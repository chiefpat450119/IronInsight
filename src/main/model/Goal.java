package model;

import java.util.Date;

// A log entry representing a strength target with a list of exercises
public class Goal extends Record {
    private Date targetDate;
    private int targetWeight;
    private String description;
    private boolean completed;

    // REQUIRES: name is not empty
    // EFFECTS: Instantiates a Goal with given date and name, not completed
    public Goal(Date date, String name, String description) {
        super(date, name);
        this.name = name + " goal set on " + date.toString();
        this.description = description;
        this.completed = false;
    }

    // REQUIRES: targetWeight >= 0, exerciseName not empty or null
    // MODIFIES: this
    // EFFECTS: Modifies the goal by setting the date to achieve and the target weight on exercise
    // with given name.
    public void addTarget(Date targetDate, int targetWeight, String exerciseName) {
        this.targetDate = targetDate;
        addExercise(new Exercise(exerciseName, targetWeight, 1, 10));
    }

    // EFFECTS: returns a string summary of the goal
    @Override
    public String summary() {
        return null; // TODO: Implement
    }

    // MODIFIES: this
    // EFFECTS: sets completed to true
    public void setCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
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
