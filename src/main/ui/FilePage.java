package ui;

import javax.swing.*;
import java.awt.*;

public class FilePage extends Page {
    // TODO: Add this as a menu to the Jframe instead
    public FilePage(LoggerGUI gui) {
        super(new BorderLayout(), gui);
        addComponents();
    }

    @Override
    protected void addComponents() {
        add(new JLabel("File placeholder"), BorderLayout.NORTH);
    }
}
