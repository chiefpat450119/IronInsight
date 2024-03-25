package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel for the home page tab of the UI
public class HomePage extends Page {
    CardLayout cards;
    JPanel cardContainer;

    public HomePage(JFrame gui) {
        super(new BorderLayout(), gui);
        this.cards = new CardLayout();
        cardContainer = new JPanel(cards);
        addComponents();
    }

    @Override
    protected void addComponents() {
        JLabel title = new JLabel("Welcome to Iron Insight, your personal strength training tracker.",
                SwingConstants.CENTER);
        title.setFont(LoggerGUI.getFont(30f));
        add(title, BorderLayout.NORTH);

        JPanel buttonContainer = createSideBar();

        JPanel logsMenu = createLogsMenu();
        JPanel goalsMenu = createGoalsMenu();

        cardContainer.add(logsMenu, "logs");
        cardContainer.add(goalsMenu, "goals");
        cards.show(cardContainer, "logs");

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonContainer, cardContainer);
        add(sl, BorderLayout.CENTER);
    }

    private JPanel createGoalsMenu() {
        JPanel goalsMenu = new JPanel(new BorderLayout());
        goalsMenu.add(new JLabel("Add Goals"), BorderLayout.NORTH);
        // TODO: add text fields
        return goalsMenu;
    }

    private JPanel createLogsMenu() {
        JPanel logsMenu = new JPanel(new BorderLayout());
        logsMenu.add(new JLabel("Add Training Logs"), BorderLayout.NORTH);
        // TODO: add text fields, use radio button to hide or reveal components depending on PB status
        return logsMenu;
    }

    private JPanel createSideBar() {
        JPanel buttonContainer = new JPanel(new GridLayout(3, 1));
        JButton logsButton = new JButton("Add Training Logs");
        logsButton.setFont(LoggerGUI.getFont(20f));
        JButton goalsButton = new JButton("Add Goals");
        goalsButton.setFont(LoggerGUI.getFont(20f));
        logsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(cardContainer, "logs");
            }
        });
        goalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(cardContainer, "goals");
            }
        });
        buttonContainer.add(logsButton);
        buttonContainer.add(goalsButton);
        return buttonContainer;
    }
}
