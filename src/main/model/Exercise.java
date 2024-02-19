package model;

// Represents a set of a strength training exercise with a weight and number of repetitions
public class Exercise {
    private String name;
    private int reps;
    private int rpe;
    private int weight;

    // REQUIRES: name is not empty, other arguments >= 0, rpe <= 10
    // EFFECTS: Instantiates an exercise with given arguments
    public Exercise(String name, int weight, int reps, int rpe) {
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.rpe = rpe;
    }

    // EFFECTS: Returns the calculated 1-repetition maximum for the exercise based on weight and reps
    public double calculateMax() {
        return weight / (1.0278 - 0.0278 * reps);
    }

    // EFFECTS: returns a summary of the exercise set
    public String summary() {
        return reps + " reps of " + weight + " lbs on " + name + " at " + rpe + " RPE";
    }

    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public int getRpe() {
        return rpe;
    }

    public int getWeight() {
        return weight;
    }
}
