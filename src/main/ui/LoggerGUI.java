package ui;

import model.Exercise;
import model.Goal;
import model.LogEntry;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// Represents the main GUI for the logger program.
public class LoggerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final ImageIcon DUMBBELL;
    public static final Font FONT;
    public static final ImageIcon BODY_BUILDER_IMAGE;

    // EFFECTS: loads and scales body builder image and font.
    static {
        ImageIcon bodyBuilderImage = new ImageIcon("./assets/bodybuilder.png"); // Source: Vecteezy
        BODY_BUILDER_IMAGE = getSmallerImage(bodyBuilderImage);
        ImageIcon dumbbellImage = new ImageIcon("./assets/icon.png"); // Source: Vecteezy
        DUMBBELL = getSmallerImage(dumbbellImage);
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/8bit.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECTS: Static method that returns scaled down version of img
    private static ImageIcon getSmallerImage(ImageIcon img) {
        Image image = img.getImage();
        Image smallerImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(smallerImage);
    }

    private JTabbedPane pane;
    private HomePage homePage;
    private GoalsPage goalsPage;
    private ProgressPage progressPage;
    private LogManager logManager;

    // EFFECTS: Sets up the GUI with a LogManager and all GUI components.
    public LoggerGUI() {
        super("Iron Insight");
        logManager = new LogManager();
        pane = new JTabbedPane();

        setIconImage(DUMBBELL.getImage());
        initializeInterface();
    }

    // MODIFIES: this
    // EFFECTS:  sets up the JFrame window of the GUI and adds all components.
    private void initializeInterface() {
        setLayout(new BorderLayout());
        addFileMenu();
        setupTabs();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: Adds a menu bar with a file menu to the top of the GUI frame.
    private void addFileMenu() {
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadLogsButton = new JMenuItem("Load saved logs");
        loadLogsButton.addActionListener(e -> logManager.loadLogger());
        JMenuItem saveLogsButton = new JMenuItem("Save logs to file");
        saveLogsButton.addActionListener(e -> logManager.saveLogger());
        fileMenu.add(loadLogsButton);
        fileMenu.add(saveLogsButton);
        fileMenuBar.add(fileMenu);
        add(fileMenuBar, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Initializes a field for each page and adds them as tabs to the frame.
    private void setupTabs() {
        this.homePage = new HomePage(this);
        this.goalsPage = new GoalsPage(this);
        this.progressPage = new ProgressPage(this);
        pane.addTab("Home", homePage);
        pane.addTab("Progress", progressPage);
        pane.addTab("Goals", goalsPage);
        pane.setFont(getFont(15f));
        add(pane, BorderLayout.CENTER);
    }

    // EFFECTS: Static method to retrieve the GUI's themed font.
    public static Font getFont(float size) {
        return FONT.deriveFont(size);
    }

    // MODIFIES: this
    // EFFECTS: Adds a goal to the logManager logs based on given parameters. Shows error message on fail.
    public void addGoal(String name, String date, String exerciseName, String weight, String description) {
        try {
            logManager.addGoal(name, LocalDate.parse(date), exerciseName, Integer.parseInt(weight), description);
        } catch (DateTimeParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error parsing inputs, please try again",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a training log entry to the logManager logs based on given parameters.
    public void addLogEntry(String name, List<Exercise> exercises) {
        logManager.addLogEntry(name, exercises);
    }

    // MODIFIES: this
    // EFFECTS: Adds a personal best entry to the logManager logs based on given parameters. Shows error message on fail
    public void addPersonalBest(String name, String pbWeight) {
        try {
            logManager.addPersonalBest(name, Integer.parseInt(pbWeight));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error parsing inputs, please try again",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Returns all logs in logManager matching given date. Shows error message on fail.
    public List<LogEntry> getLogsByDate(String dateText) {
        try {
            return logManager.getLogsByDate(LocalDate.parse(dateText));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Error parsing inputs, please try again",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        return new ArrayList<>();
    }

    // EFFECTS: Returns all goals in logManager logs.
    public List<Goal> getGoals() {
        return logManager.getGoals();
    }

    // EFFECTS: Returns all log entries in logManager logs.
    public List<LogEntry> getLogs() {
        return logManager.getLogs();
    }
}
