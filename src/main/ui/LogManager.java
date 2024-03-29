package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Represents a manager for the program's list of log entries, responsible for handling relevant updates and requests.
public class LogManager {
    private static final String JSON_STORE = "./data/logs.json";

    private List<LogEntry> logs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Instantiates a log manager with no logs and a writer and reader for JSON.
    public LogManager() {
        logs = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Static method that returns the detailed breakdown of a given log entry.
    public static String getDetails(LogEntry selectedEntry) {
        if (selectedEntry.getClass().equals(Goal.class)) {
            Goal g = (Goal) selectedEntry;
            return g.summary() + " set on " + g.getDate().toString()
                    + ". \nTarget completion: " + g.getTargetDate().toString();
        } else if (selectedEntry.getClass().equals(Record.class)) {
            Record r = (Record) selectedEntry;
            return r.summary() + " on " + r.getDate().toString();
        } else if (selectedEntry.getClass().equals(PersonalBest.class)) {
            PersonalBest p = (PersonalBest) selectedEntry;
            return p.summary() + " on " + p.getDate().toString();
        }
        return "";
    }

    // EFFECTS: Adds a log entry to the logs list based on given parameters.
    public void addLogEntry(String name, List<Exercise> exercises) {
        Record record = new Record(LocalDate.now(), name);
        for (Exercise e: exercises) {
            record.addExercise(e);
        }
        logs.add(record);
    }

    // EFFECTS: Adds a goal to the logs list based on given parameters.
    public void addGoal(String name, LocalDate date, String exerciseName, int weight, String description) {
        Goal goal = new Goal(LocalDate.now(), name, description);
        goal.addTarget(date, weight, exerciseName);
        logs.add(goal);
    }

    // EFFECTS: Adds a personal best entry to the logs list based on given parameters.
    public void addPersonalBest(String name, int weight) {
        logs.add(new PersonalBest(LocalDate.now(), name, weight));
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

    // EFFECTS: Static method that returns an Exercise instance from given parameters.
    public static Exercise createExercise(String name, String weight, String reps, String rpe) {
        return new Exercise(name, Integer.parseInt(weight), Integer.parseInt(reps), Integer.parseInt(rpe));
    }

    // EFFECTS: Returns a list of all log entries of type Goal in the logs list.
    public List<Goal> getGoals() {
        List<Goal> result = new ArrayList<>();
        for (LogEntry l: logs) {
            if (l instanceof Goal) {
                Goal goal = (Goal) l;
                result.add(goal);
            }
        }
        return result;
    }

    // EFFECTS: Returns all log entries in the logs list matching given date.
    public List<LogEntry> getLogsByDate(LocalDate date) {
        List<LogEntry> result = new ArrayList<>();
        for (LogEntry e : logs) {
            if (e.getDate().isEqual(date)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<LogEntry> getLogs() {
        return logs;
    }
}
