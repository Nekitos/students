package ru.haulmont.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nikita on 12/13/14.
 */
public class MainWindow extends JFrame {
    private JTabbedPane tabsPanel;

    public MainWindow() {
        tabsPanel = new JTabbedPane();
        tabsPanel.addTab("Students list", new JButton("Hello"));
        tabsPanel.addTab("Group list", new JButton("World"));
        add(tabsPanel);
        setPreferredSize(new Dimension(500, 500));
        pack();
    }
}
