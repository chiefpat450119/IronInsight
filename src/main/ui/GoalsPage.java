package ui;

import model.Goal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents a page where all goals are displayed and can be marked as completed.
public class GoalsPage extends Page {
    private JList<String> displayList;

    // EFFECTS: Creates a goal page with a GUI field and border layout, and adds all components.
    public GoalsPage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: Adds components to display all goals and mark them as completed.
    @Override
    protected void addComponents() {
        JLabel label = new JLabel("Select a goal to mark as completed");
        label.setFont(LoggerGUI.getFont(20f));
        displayList = new JList<>(new GoalListModel(gui.getGoals()));
        JButton refreshButton = new JButton("Refresh Goal List");
        refreshButton.setFont(LoggerGUI.getFont(25f));
        refreshButton.addActionListener(e -> {
            displayList.setModel(new GoalListModel(gui.getGoals()));
            repaint();
        });
        JButton completeButton = createCompleteButton();
        add(label, BorderLayout.WEST);
        add(refreshButton, BorderLayout.NORTH);
        add(new JScrollPane(displayList), BorderLayout.CENTER);
        add(completeButton, BorderLayout.SOUTH);
    }

    // EFFECTS: Returns the button to mark the selected goal as completed. Creates a visual popup dialog on click.
    private JButton createCompleteButton() {
        JButton completeButton = new JButton("Mark as Completed");
        completeButton.addActionListener(e -> {
            int selectedIndex = displayList.getSelectedIndex();
            if (selectedIndex != -1) {
                Goal selectedGoal = ((GoalListModel) displayList.getModel()).getGoalAt(selectedIndex);
                selectedGoal.setCompleted();
                displayList.repaint();
                JOptionPane.showMessageDialog(this, "Congrats for completing a goal!",
                        "Goal Completed! 🎉", JOptionPane.INFORMATION_MESSAGE, LoggerGUI.BODY_BUILDER_IMAGE);
            }
        });
        completeButton.setFont(LoggerGUI.getFont(25f));
        return completeButton;
    }

    // Represents a list model which displays the string summary of each goal in goals.
    private static class GoalListModel extends AbstractListModel<String> {
        private List<Goal> goals;

        public GoalListModel(List<Goal> goals) {
            this.goals = goals;
        }

        @Override
        public int getSize() {
            return goals.size();
        }

        @Override
        public String getElementAt(int index) {
            return goals.get(index).summary();
        }

        public Goal getGoalAt(int index) {
            return goals.get(index);
        }
    }
}
