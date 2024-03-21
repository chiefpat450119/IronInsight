package ui;

import model.LogEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 TODO: Plan: it's gonna be tabbed panes one tab with buttons to add different types of log entries
 TODO: fields to enter info
 TODO: Button to view progress (all), button to view by date
 TODO: Button to mark a goal as completed with confetti (on manage goals tab), with dropdown selection
 TODO: Progress screen as an indented list with a back button (on progress tab)
 TODO: Buttons to load and save (file tab)
 TODO: Each tab has a panel
 TODO: Jacked dude image, weights etc in 8 bit style
 */

public class LoggerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/logs.json";

    private JTabbedPane pane;
    private List<LogEntry> logs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LoggerGUI() {
        super("Iron Insight");
        logs = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
        createButtons();
        ImageIcon img = new ImageIcon("./assets/icon.png");
        setIconImage(img.getImage());
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createButtons() {

    }

    // EFFECTS: saves the training logs to file
    private void saveLogger() {
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
    private void loadLogger() {
        try {
            this.logs = jsonReader.read();
            System.out.println("Loaded training logs from " + JSON_STORE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
