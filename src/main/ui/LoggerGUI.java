package ui;

import model.LogEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 TODO: Plan: it's gonna be tabbed panes one tab with buttons to add different types of log entries
 TODO: fields to enter info
 TODO: Button to view progress (all), button to view by date on progress tab
 TODO: Button to mark a goal as completed with confetti (on manage goals tab), with dropdown selection
 TODO: Progress screen as an indented list with a back button (on progress tab)
 TODO: Buttons to load and save (file tab)
 TODO: Each tab has a panel
 TODO: Jacked dude image, weights etc in 8 bit style
 */

public class LoggerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final Font FONT;
    private static final String JSON_STORE = "./data/logs.json";

    static {
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/8bit.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JTabbedPane pane;
    private HomePage homePage;
    private FilePage filePage;
    private GoalsPage goalsPage;
    private ProgressPage progressPage;
    private List<LogEntry> logs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LoggerGUI() {
        super("Iron Insight");
        logs = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
        ImageIcon img = new ImageIcon("./assets/icon.png");
        setIconImage(img.getImage());
        setupTabs();
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
        pane = new JTabbedPane();
    }

    private void setupTabs() {
        this.homePage = new HomePage(this);
        this.goalsPage = new GoalsPage(this);
        this.filePage = new FilePage(this);
        this.progressPage = new ProgressPage(this);
        pane.addTab("Home", homePage);
        pane.addTab("Progress", progressPage);
        pane.addTab("Goals", goalsPage);
        pane.addTab("File", filePage);
        // TODO
        add(pane);
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

}
