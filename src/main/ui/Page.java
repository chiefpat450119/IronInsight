package ui;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JPanel {
    protected JFrame gui;

    public Page(LayoutManager layout, JFrame gui) {
        super(layout);
        this.gui = gui;
    }

    protected abstract void addComponents();
}
