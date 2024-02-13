package ui;

import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// A strength training log, with a list of log entries of various types.
// Logs can be accessed or reports generated based on user input.
public class TrainingLogger {
    private ArrayList<LogEntry> logs;
    private Scanner input;

    // EFFECTS: Runs the logger application
    public TrainingLogger() {
        runLogger();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLogger() {
        String command = null;

        init();

        System.out.println("Welcome to IronInsight, your personal strength training tracker.");

        // Main app loop
        while (true) {
            break;
        }

        System.out.print("Bye! Thanks for using IronInsight");
    }

    // MODIFIES: this
    // EFFECTS: Initializes empty training log list and input
    private void init() {
        logs = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays main menu of options to user
    private void mainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Create a goal");
        System.out.println("\ta -> Add a training log entry");
        System.out.println("\tv -> View your progress");
        System.out.println("\tq -> quit");
    }

    private void goalMenu() {
        // stub
    }

    private void logsMenu() {
        // stub
    }

    private void progressMenu() {
        // stub
    }

    private void addGoal() throws ParseException {
        System.out.println("Enter a name for your goal");
        String goalName = input.next();
        System.out.println("Enter your target completion date in the format YYYY-MM-DD");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateString = input.next();
        Date goalDate = formatter.parse(dateString);
        System.out.println("Enter your exercise name");
        String exerciseName = input.next();
        System.out.println("Enter your target weight");
        int weight = input.nextInt();
        System.out.println("Enter a brief description for your goal");
        String description = input.next();
        Goal goal = new Goal(new Date(), goalName, description);
        goal.addTarget(goalDate, weight, exerciseName);
        logs.add(goal);
    }
}
