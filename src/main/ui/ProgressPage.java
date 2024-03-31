package ui;

import model.LogEntry;
import model.LogManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

// Represents a page displaying all logs that have been added to the list, allowing details to be viewed.
public class ProgressPage extends Page {
    JList<String> displayList;
    JPanel infoPanel;
    JLabel infoLabel;
    JTextField dateField;

    // EFFECTS: Instantiates a progress page with all its components.
    public ProgressPage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: Adds all components to this page, including displayed list and buttons to refresh and search by date.
    @Override
    protected void addComponents() {
        displayList = new JList<>(new LogListModel(gui.getLogs()));

        dateField = new JTextField(LocalDate.now().toString());
        JButton searchButton = new JButton("Search");
        searchButton.setFont(LoggerGUI.getFont(25f));
        searchButton.addActionListener(e -> {
            displayList.setModel(new LogListModel(gui.getLogsByDate(dateField.getText())));
            repaint();
        });
        JPanel searchPanel = new JPanel(new GridLayout(0, 3));
        addLabel(searchPanel, "Enter a date to filter (YYYY-MM-DD)", 20f);
        searchPanel.add(dateField);
        searchPanel.add(searchButton);

        JButton refreshButton = new JButton("Refresh Logs List");
        refreshButton.setFont(LoggerGUI.getFont(25f));
        refreshButton.addActionListener(e -> {
            displayList.setModel(new LogListModel(gui.getLogs()));
            repaint();
        });
        add(searchPanel, BorderLayout.NORTH);
        addLogsListDisplay();
        add(refreshButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Adds the JList displaying the log entries to this page, along with panel for displaying details.
    private void addLogsListDisplay() {
        infoPanel = new JPanel();
        infoLabel = addLabel(infoPanel, "Select a log entry to view details", 15f);
        displayList.addListSelectionListener(e -> {
            int selectedIndex = displayList.getSelectedIndex();
            if (displayList.getSelectedIndex() != -1) {
                LogEntry selectedEntry = ((LogListModel) displayList.getModel()).getLogAt(selectedIndex);
                infoLabel.setText(LogManager.getDetails(selectedEntry));
                infoPanel.repaint();
            } else {
                infoLabel.setText("Select a log entry to view details");
                repaint();
            }
        });
        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(displayList), infoPanel), BorderLayout.CENTER);
    }

    // Represents a list model which displays the name string of all log entries in its logs.
    private static class LogListModel extends AbstractListModel<String> {
        private java.util.List<LogEntry> logs;

        public LogListModel(List<LogEntry> logs) {
            this.logs = logs;
        }

        @Override
        public int getSize() {
            return logs.size();
        }

        @Override
        public String getElementAt(int index) {
            return logs.get(index).getName();
        }

        public LogEntry getLogAt(int index) {
            return logs.get(index);
        }
    }
}
