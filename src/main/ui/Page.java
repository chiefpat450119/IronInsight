package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public abstract class Page extends JPanel {
    protected LoggerGUI gui;

    public Page(LayoutManager layout, LoggerGUI gui) {
        super(layout);
        this.gui = gui;
    }

    public static JLabel addLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(LoggerGUI.getFont(25f));
        panel.add(label);
        return label;
    }

    public static JLabel addLabel(JPanel panel, String text, float size) {
        JLabel label = new JLabel(text);
        label.setFont(LoggerGUI.getFont(size));
        panel.add(label);
        return label;
    }

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

    protected abstract void addComponents();
}
