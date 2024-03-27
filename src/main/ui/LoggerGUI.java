package ui;

import model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 TODO: Button to view progress (all), button to view by date on progress tab
 TODO: Button to mark a goal as completed with confetti (on manage goals tab), with dropdown selection
 TODO: Progress screen as an indented list with a back button (on progress tab)
 TODO: Buttons to load and save (file tab)
 TODO: Jacked dude image, weights etc in 8 bit style
 */

public class LoggerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final Font FONT;

    static {
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/8bit.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JTabbedPane pane;
    private HomePage homePage;
    private GoalsPage goalsPage;
    private ProgressPage progressPage;
    private LogManager logManager;

    public LoggerGUI() {
        super("Iron Insight");
        logManager = new LogManager();
        pane = new JTabbedPane();

        ImageIcon img = new ImageIcon("./assets/icon.png");
        setIconImage(img.getImage());
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        addFileMenu();
        setupTabs();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        setResizable(false);
    }

    private void addFileMenu() {
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadLogsButton = new JMenuItem("Load saved logs");
        // TODO: add confirmation and failure message
        loadLogsButton.addActionListener(e -> logManager.loadLogger());
        JMenuItem saveLogsButton = new JMenuItem("Save logs to file");
        saveLogsButton.addActionListener(e -> logManager.saveLogger());
        fileMenu.add(loadLogsButton);
        fileMenu.add(saveLogsButton);
        fileMenuBar.add(fileMenu);
        add(fileMenuBar, BorderLayout.NORTH);
    }

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

    public static Font getFont(float size) {
        return FONT.deriveFont(size);
    }

    public void addGoal(String name, String date, String exerciseName, String weight, String description) {
        logManager.addGoal(name, LocalDate.parse(date), exerciseName, Integer.parseInt(weight), description);
    }

    public void addLogEntry(String name, List<Exercise> exercises) {
        logManager.addLogEntry(name, exercises);
    }

    public void addPersonalBest(String name, String pbWeight) {
        logManager.addPersonalBest(name, Integer.parseInt(pbWeight));
    }
}
