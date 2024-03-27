package ui;

import model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

// Represents a panel for the home page tab of the UI
public class HomePage extends Page implements ActionListener {
    CardLayout cards;
    JPanel cardContainer;
    JLabel pbWeightLabel;
    JTextField pbWeightField;
    JLabel message;
    JLabel message2;
    JPanel exerciseFields;
    boolean isPb = false;
    List<Exercise> exerciseList;

    public HomePage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        this.cards = new CardLayout();
        cardContainer = new JPanel(cards);
        addComponents();
    }

    @Override
    protected void addComponents() {
        JLabel title = new JLabel("Welcome to Iron Insight, your personal strength training tracker.",
                SwingConstants.CENTER);
        title.setFont(LoggerGUI.getFont(30f));
        add(title, BorderLayout.NORTH);

        JPanel buttonContainer = createSideBar();

        JPanel logsMenu = createLogsMenu();
        JPanel goalsMenu = createGoalsMenu();

        cardContainer.add(logsMenu, "logs");
        cardContainer.add(goalsMenu, "goals");
        cards.show(cardContainer, "logs");

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonContainer, cardContainer);
        add(sl, BorderLayout.CENTER);
    }

    private JPanel createSideBar() {
        JPanel buttonContainer = new JPanel(new GridLayout(3, 1));
        JButton logsButton = new JButton("Add Training Logs");
        logsButton.setFont(LoggerGUI.getFont(20f));
        JButton goalsButton = new JButton("Add Goals");
        goalsButton.setFont(LoggerGUI.getFont(20f));

        logsButton.addActionListener(this);
        logsButton.setActionCommand("Switch to logs tab");
        goalsButton.addActionListener(this);
        goalsButton.setActionCommand("Switch to goals tab");
        buttonContainer.add(logsButton);
        buttonContainer.add(goalsButton);
        return buttonContainer;
    }

    private JPanel createLogsMenu() {
        JPanel logsMenu = new JPanel(new GridLayout(0, 2, 20, 10));
        addLabel(logsMenu, "Name");
        JTextField nameField = addTextField(logsMenu, "Patrick's Log Entry");
        addPbSelector(logsMenu);
        addExerciseFields(logsMenu);

        JButton confirmButton = new JButton("Add to Log");
        confirmButton.setFont(LoggerGUI.getFont(20f));
        confirmButton.addActionListener(e -> {
            if (isPb) {
                gui.addPersonalBest(nameField.getText(), pbWeightField.getText());
            } else {
                gui.addLogEntry(nameField.getText(), exerciseList);
            }
            clearFields(logsMenu);
            message2.setVisible(true);
        });
        logsMenu.add(confirmButton);
        message2 = addLabel(logsMenu, "Added successfully!", 15f);
        message2.setVisible(false);  // Initially hidden success message

        return logsMenu;
    }

    private void addExerciseFields(JPanel logsMenu) {
        exerciseList = new ArrayList<>();
        exerciseFields = new JPanel(new GridLayout(0, 2, 10, 10));
        addLabel(exerciseFields, "Exercise name", 12f);
        JTextField exerciseNameField =  addTextField(exerciseFields, "Deadlift");
        addLabel(exerciseFields, "Weight (lbs)", 12f);
        JTextField exerciseWeightField = addTextField(exerciseFields, "135");
        addLabel(exerciseFields, "Number of reps", 12f);
        JTextField repsField = addTextField(exerciseFields, "8");
        addLabel(exerciseFields, "RPE (1-10)", 12f);
        JTextField rpeField = addTextField(exerciseFields, "5");
        JButton addExerciseButton = new JButton("Add Exercise");
        addExerciseButton.setFont(LoggerGUI.getFont(12f));
        addExerciseButton.addActionListener(e -> {
            Exercise exercise = LogManager.createExercise(exerciseNameField.getText(),
                    exerciseWeightField.getText(), repsField.getText(), rpeField.getText());
            exerciseList.add(exercise);
            clearFields(exerciseFields);
        });
        exerciseFields.add(addExerciseButton);
        logsMenu.add(exerciseFields);
    }

    // MODIFIES: logsMenu
    // EFFECTS: creates radio buttons to choose whether to add a pb entry, along with corresponding fields.
    private void addPbSelector(JPanel logsMenu) {
        addLabel(logsMenu, "Is this a personal best entry?");
        JRadioButton yesButton = new JRadioButton("Yes");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Reveal pb fields");
        JRadioButton noButton = new JRadioButton("No");
        noButton.addActionListener(this);
        noButton.setActionCommand("Hide pb fields");
        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(yesButton, BorderLayout.WEST);
        buttonPanel.add(noButton, BorderLayout.CENTER);
        logsMenu.add(buttonPanel);
        pbWeightLabel = addLabel(logsMenu, "Personal Best Weight");
        pbWeightField = addTextField(logsMenu, "225");
        pbWeightField.setVisible(false);
        pbWeightLabel.setVisible(false);
    }

    private JPanel createGoalsMenu() {
        JPanel goalsMenu = new JPanel(new GridLayout(0, 2, 20, 10));
        addLabel(goalsMenu, "Name");
        JTextField nameField = addTextField(goalsMenu, "Patrick's Goal");
        addLabel(goalsMenu, "Target Completion Date (YYYY-MM-DD)");
        JTextField dateField = addTextField(goalsMenu, "2024-03-24");
        addLabel(goalsMenu, "Exercise Name");
        JTextField exerciseField = addTextField(goalsMenu, "Bench Press");
        addLabel(goalsMenu, "Target Weight (lbs)");
        JTextField weightField = addTextField(goalsMenu, "100");
        addLabel(goalsMenu, "Description");
        JTextArea descriptionField = new JTextArea();
        goalsMenu.add(descriptionField);
        addConfirmButton(goalsMenu, nameField, dateField, exerciseField, weightField, descriptionField);
        message = addLabel(goalsMenu, "Added successfully!", 15f);
        message.setVisible(false);  // Initially hidden success message
        return goalsMenu;
    }

    // MODIFIES: goalsMenu
    // EFFECTS: Adds a confirm button to the goals menu
    private void addConfirmButton(JPanel goalsMenu, JTextField nameField, JTextField dateField,
                                  JTextField exerciseField, JTextField weightField, JTextArea descriptionField) {
        JButton confirmButton = new JButton("Add Goal");
        confirmButton.setFont(LoggerGUI.getFont(20f));
        confirmButton.addActionListener(e -> {
            gui.addGoal(nameField.getText(), dateField.getText(), exerciseField.getText(),
                    weightField.getText(), descriptionField.getText());
            message.setVisible(true);
            clearFields(goalsMenu);
        });
        goalsMenu.add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Switch to logs tab")) {
            cards.show(cardContainer, "logs");
        } else if (e.getActionCommand().equals("Switch to goals tab")) {
            cards.show(cardContainer, "goals");
        } else if (e.getActionCommand().equals("Reveal pb fields")) {
            pbWeightField.setVisible(true);
            pbWeightLabel.setVisible(true);
            exerciseFields.setVisible(false);
            isPb = true;
        } else if (e.getActionCommand().equals("Hide pb fields")) {
            pbWeightField.setVisible(false);
            pbWeightLabel.setVisible(false);
            exerciseFields.setVisible(true);
            isPb = false;
        }
    }
}
