package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// TODO
public class LogManager {
    private static final String JSON_STORE = "./data/logs.json";

    private List<LogEntry> logs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LogManager() {
        logs = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void addLogEntry(String name, List<Exercise> exercises) {
        Record record = new Record(LocalDate.now(), name);
        for (Exercise e: exercises) {
            record.addExercise(e);
        }
        logs.add(record);
    }

    public void addGoal(String name, LocalDate date, String exerciseName, int weight, String description) {
        Goal goal = new Goal(LocalDate.now(), name, description);
        goal.addTarget(date, weight, exerciseName);
        logs.add(goal);
    }

    // EFFECTS: saves the training logs to file
    public void saveLogger() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.logs);
            jsonWriter.close();
            System.out.println("Saved training logs to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads training logs from file
    public void loadLogger() {
        try {
            this.logs = jsonReader.read();
            System.out.println("Loaded training logs from " + JSON_STORE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Exercise createExercise(String name, String weight, String reps, String rpe) {
        return new Exercise(name, Integer.parseInt(weight), Integer.parseInt(reps), Integer.parseInt(rpe));
    }

    public List<LogEntry> getLogs() {
        return logs;
    }

    public void addPersonalBest(String name, int weight) {
        logs.add(new PersonalBest(LocalDate.now(), name, weight));
    }
}
