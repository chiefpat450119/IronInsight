package model;

import java.util.Date;

// A log entry of a personal best weight for just one exercise
public class PersonalBest extends Record {
    private String exerciseName;
    private double pbWeight;

    // REQUIRES: name is not empty, weight >= 0
    // EFFECTS: Instantiates a personal best log entry with given record weight and exercise name.
    public PersonalBest(Date date, String name, int weight) {
        super(date, name + " PB");
        this.exercises.add(new Exercise(name, weight, 1, 10));
        this.exerciseName = name;
        this.pbWeight = weight;
    }

    @Override
    public String summary() {
        return null;
    }

    public double getPbWeight() {
        return pbWeight;
    }

    public String getExerciseName() {
        return exerciseName;
    }
}
