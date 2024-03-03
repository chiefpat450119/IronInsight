package ui;

import model.*;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// A strength training log, with a list of log entries of various types.
// Logs can be accessed or summaries generated based on user input.
// ATTRIBUTION: Structure of this class and the user input implementation are sourced from the TellerApp project.
public class TrainingLogger implements Writable {
    private static final String JSON_STORE = "./data/logs.json";
    private ArrayList<LogEntry> logs;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // TODO: Implement loading and saving

    // EFFECTS: Runs the logger application
    public TrainingLogger() {
        runLogger();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLogger() {
        String command;

        init();

        System.out.println("Welcome to IronInsight, your personal strength training tracker.");

        // Main app loop
        while (true) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                processCommand(command);
            }

        }

        System.out.print("Bye! Thanks for using IronInsight");
    }

    // MODIFIES: this
    // EFFECTS: Processes the user's input at the main menu
    private void processCommand(String command) {
        if (command.equals("g")) {
            try {
                addGoal();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date, please try again.");
            }
        } else if (command.equals("c")) {
            markCompleted();
        } else if (command.equals("a")) {
            addLogEntry();
        } else if (command.equals("p")) {
            try {
                generateSummary();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date, please try again.");
            }
        } else if (command.equals("m")) {
            calculateMax();
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes empty training log list and input
    private void init() {
        logs = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays main menu of options to user
    private void mainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Create a goal");
        System.out.println("\tc -> Mark a goal as completed");
        System.out.println("\ta -> Add a training log entry");
        System.out.println("\tp -> Get progress summary");
        System.out.println("\tm -> Calculate 1-repetition max");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Adds a goal to the logs list based on user inputs
    private void addGoal() throws DateTimeParseException {
        System.out.println("Enter a name for your goal");
        String goalName = input.next();
        System.out.println("Enter your target completion date in the format YYYY-MM-DD");
        LocalDate goalDate = LocalDate.parse(input.next());
        System.out.println("Enter your exercise name");
        String exerciseName = input.next();
        System.out.println("Enter your target weight");
        int weight = input.nextInt();
        System.out.println("Enter a brief description for your goal");
        String description = input.next();
        Goal goal = new Goal(LocalDate.now(), goalName, description);
        goal.addTarget(goalDate, weight, exerciseName);
        logs.add(goal);
    }

    // MODIFIES: this
    // EFFECTS: Adds either a personal best entry or a regular training log entry to logs list
    // based on user inputs.
    private void addLogEntry() {
        Record entry;
        System.out.println("Enter a name for your entry");
        String logName = input.next();
        System.out.println("Is this a personal best record? true/false");
        boolean isPb = Boolean.parseBoolean(input.next());
        if (isPb) {
            System.out.println("Enter your personal best weight:");
            entry = new PersonalBest(LocalDate.now(), logName, input.nextInt());
        } else {
            entry = new Record(LocalDate.now(), logName);
            while (true) {
                System.out.println("Enter 'a' to add exercises or 'c' to complete log entry.");
                String command = input.next();
                if (command.equals("c")) {
                    break;
                } else if (command.equals("a")) {
                    entry.addExercise(createExercise());
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            }
        }
        logs.add(entry);
    }

    // Helper method for creating exercises
    // EFFECTS: Returns an exercise based on user specifications.
    private Exercise createExercise() {
        System.out.println("Enter your exercise name");
        String exerciseName = input.next();
        System.out.println("Enter the amount of weight");
        int exerciseWeight = input.nextInt();
        System.out.println("Enter the number of reps performed");
        int reps = input.nextInt();
        System.out.println("Enter your rating of perceived exertion (RPE) from 0-10");
        int rpe = input.nextInt();
        return new Exercise(exerciseName, exerciseWeight, reps, rpe);
    }

    // REQUIRES: logs contains at least one instance of Goal
    // MODIFIES: this
    // EFFECTS: Marks a goal as completed as directed by user input
    private void markCompleted() {
        System.out.println("Select the goal to mark as completed: ");
        for (int i = 0; i < logs.size(); i++) {
            LogEntry g = logs.get(i);
            if (g instanceof Goal) {
                System.out.println((i + 1) + ". " + g.getName());
            }
        }
        int index = input.nextInt();
        Goal g = (Goal) logs.get(index - 1);
        g.setCompleted();
        System.out.println("Congratulations! You have successfully completed a goal!");
    }

    // REQUIRES: logs is not empty
    // EFFECTS: Prints summary of all logs on given date to the console based on user input
    private void generateSummary() throws DateTimeParseException {
        System.out.println("Enter the date you want a summary for in the format YYYY-MM-DD "
                + "or 'all' to view all records");
        String cmd = input.next();
        if (cmd.equalsIgnoreCase("all")) {
            for (LogEntry e : logs) {
                System.out.println(e.summary());
            }
        } else {
            LocalDate summaryDate = LocalDate.parse(cmd);
            for (LogEntry e : logs) {
                if (e.getDate().isEqual(summaryDate)) {
                    System.out.println(e.summary());
                }
            }
        }
    }

    // EFFECTS: Prints out the calculated 1-repetition maximum weight based on user input
    private void calculateMax() {
        System.out.println("Enter your weight and reps");
        int weight = input.nextInt();
        int reps = input.nextInt();
        System.out.print("Your 1-rep max is: " + Math.round(weight / (1.0278 - 0.0278 * reps)));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("logs", logsToJson());
        return json;
    }

    // EFFECTS: returns log entries in this logger as a json array
    private JSONArray logsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry l: logs) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
