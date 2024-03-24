package ui;

import javax.swing.*;
import java.awt.*;

// Represents a panel for the home page tab of the UI
public class HomePage extends Page {

    public HomePage(JFrame gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    @Override
    protected void addComponents() {
        JLabel title = new JLabel("Welcome to Iron Insight, your personal strength training tracker.", SwingConstants.CENTER);
        title.setFont(LoggerGUI.FONT.deriveFont(30f));
        add(title, BorderLayout.NORTH);
        // TODO: Add a card layout to do different things
    }
}
