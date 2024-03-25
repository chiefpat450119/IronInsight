package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel for the home page tab of the UI
public class HomePage extends Page {
    CardLayout cards;
    JPanel cardContainer;

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
        JLabel message = addLabel(goalsMenu, "Log entry added successfully!", 15f);
        message.setVisible(false);

        JButton confirmButton = new JButton("Add to Log");
        confirmButton.setFont(LoggerGUI.getFont(20f));
        confirmButton.addActionListener(e -> {
            gui.addGoal(nameField.getText(), dateField.getText(), exerciseField.getText(),
                    weightField.getText(), descriptionField.getText());
            message.setVisible(true);
        });
        goalsMenu.add(confirmButton);
        return goalsMenu;
    }

    private JPanel createLogsMenu() {
        JPanel logsMenu = new JPanel(new GridLayout(0, 2, 20, 10));
        // TODO: add text fields, use radio button to hide or reveal components depending on PB status
        addLabel(logsMenu, "Name");
        JTextField nameField = addTextField(logsMenu, "Patrick's Log Entry");
        addPbSelector(logsMenu);

        // TODO: Add a button to add exercises that creates a popup, and a text display of added exercises

        return logsMenu;
    }

    // MODIFIES: logsMenu
    // EFFECTS: creates radio buttons to choose whether to add a pb entry
    private void addPbSelector(JPanel logsMenu) {
        addLabel(logsMenu, "Is this a personal best entry?");
        JRadioButton yesButton = new JRadioButton("Yes");
        JRadioButton noButton = new JRadioButton("No");
        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(yesButton, BorderLayout.WEST);
        buttonPanel.add(noButton, BorderLayout.CENTER);
        logsMenu.add(buttonPanel);
        addLabel(logsMenu, "Personal Best Weight");
        JTextField weightField = addTextField(logsMenu, "100");
    }

    private JPanel createSideBar() {
        JPanel buttonContainer = new JPanel(new GridLayout(3, 1));
        JButton logsButton = new JButton("Add Training Logs");
        logsButton.setFont(LoggerGUI.getFont(20f));
        JButton goalsButton = new JButton("Add Goals");
        goalsButton.setFont(LoggerGUI.getFont(20f));

        logsButton.addActionListener(e -> cards.show(cardContainer, "logs"));
        goalsButton.addActionListener(e -> cards.show(cardContainer, "goals"));
        buttonContainer.add(logsButton);
        buttonContainer.add(goalsButton);
        return buttonContainer;
    }
}
