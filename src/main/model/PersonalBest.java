package model;

import java.time.LocalDate;

// A log entry of a personal best weight for just one exercise
public class PersonalBest extends Record {
    private String exerciseName;
    private double pbWeight;

    // REQUIRES: name is not empty, weight >= 0
    // EFFECTS: Instantiates a personal best log entry with given record weight and exercise name.
    public PersonalBest(LocalDate date, String name, int weight) {
        super(date, name);
        this.exercises.add(new Exercise(name, weight, 1, 10));
        this.exerciseName = name;
        this.pbWeight = weight;
    }

    // EFFECTS: returns a string summary of the personal best
    @Override
    public String summary() {
        return "PB on " + exerciseName + " of " + pbWeight + " lbs";
    }

    public double getPbWeight() {
        return pbWeight;
    }

    public String getExerciseName() {
        return exerciseName;
    }
}
