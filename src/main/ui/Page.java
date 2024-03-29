package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Represents an abstract page of the GUI.
public abstract class Page extends JPanel {
    protected LoggerGUI gui;

    // EFFECTS: Sets gui field to call methods on the main GUI frame, and sets given layout manager.
    public Page(LayoutManager layout, LoggerGUI gui) {
        super(layout);
        this.gui = gui;
    }

    // MODIFIES: panel
    // EFFECTS: Adds a label with the given text to panel.
    public static JLabel addLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(LoggerGUI.getFont(25f));
        panel.add(label);
        return label;
    }

    // MODIFIES: panel
    // EFFECTS: Adds a label with the given text and size to panel.
    public static JLabel addLabel(JPanel panel, String text, float size) {
        JLabel label = new JLabel(text);
        label.setFont(LoggerGUI.getFont(size));
        panel.add(label);
        return label;
    }

    // MODIFIES: panel
    // EFFECTS: Adds a text field to the given panel with given text. Clears default text when clicked.
    public static JTextField addTextField(JPanel panel, String defaultText) {
        JTextField textField = new JTextField(defaultText);
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }

            public void focusLost(FocusEvent e) {
                // do nothing
            }
        });
        panel.add(textField);
        return textField;
    }

    // MODIFIES: container
    // EFFECTS: Static method that clears all the textFields and textAreas in given container.
    protected static void clearFields(JComponent container) {
        for (Component c: container.getComponents()) {
            if (c instanceof JTextComponent) {
                JTextComponent textField = (JTextComponent) c;
                textField.setText("");
                container.repaint();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds all components to this page.
    protected abstract void addComponents();
}
