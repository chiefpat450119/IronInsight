package ui;

import javax.swing.*;
import java.awt.*;

public class ProgressPage extends Page {
    public ProgressPage(JFrame gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    @Override
    protected void addComponents() {
        add(new JLabel("Progress placeholder"), BorderLayout.NORTH);
    }
}
