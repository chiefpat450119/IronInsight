package ui;

import model.Goal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GoalsPage extends Page {
    private JList displayList;

    public GoalsPage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    private class GoalListModel extends AbstractListModel<String> {
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

    @Override
    protected void addComponents() {
        List<Goal> goalList = gui.getGoals();
        displayList = new JList<>(new GoalListModel(goalList));
        JButton refreshButton = new JButton("Refresh Goal List");
        refreshButton.addActionListener(e -> {
            displayList.setModel(new GoalListModel(gui.getGoals()));
            repaint();
        });

        JButton completeButton = new JButton("Mark as Completed");

        completeButton.addActionListener(e -> {
            int selectedIndex = displayList.getSelectedIndex();
            if (selectedIndex != -1) {
                Goal selectedGoal = ((GoalListModel) displayList.getModel()).getGoalAt(selectedIndex);
                selectedGoal.setCompleted();
                displayList.repaint();
            }
        });
        add(refreshButton, BorderLayout.NORTH);
        add(new JScrollPane(displayList), BorderLayout.CENTER);
        add(completeButton, BorderLayout.SOUTH);
    }
}
