package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a set of a strength training exercise with a weight and number of repetitions,
// as well as name and rating of perceived exertion.
public class Exercise implements Writable {
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

    // EFFECTS: returns a summary of the exercise set
    public String summary() {
        return reps + " reps of " + weight + " lbs on " + name + " at RPE " + rpe;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("reps", reps);
        json.put("rpe", rpe);
        return json;
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
