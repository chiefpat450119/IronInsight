package ui;

import javax.swing.*;
import java.awt.*;

public class GoalsPage extends Page {
    public GoalsPage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    @Override
    protected void addComponents() {
        add(new JLabel("Goals placeholder"), BorderLayout.NORTH);
    }
}
