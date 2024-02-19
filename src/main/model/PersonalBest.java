package model;

import java.time.LocalDate;

// A log entry of a personal best weight for just one exercise, with date, name and the PB weight.
public class PersonalBest extends Record {
    private int pbWeight;

    // REQUIRES: name is not empty, weight >= 0
    // EFFECTS: Instantiates a personal best log entry with given record weight and exercise name.
    public PersonalBest(LocalDate date, String name, int weight) {
        super(date, name);
        this.exercises.add(new Exercise(name, weight, 1, 10));
        this.pbWeight = weight;
    }

    // EFFECTS: returns a string summary of the personal best
    @Override
    public String summary() {
        return "PB on " + name + " of " + pbWeight + " lbs";
    }

    public int getPbWeight() {
        return pbWeight;
    }

}
