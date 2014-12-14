package ru.haulmont.gui;

import ru.haulmont.daoclasses.DataSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by nikita on 12/13/14.
 */
public class MainWindow extends JFrame {
    private JTabbedPane tabsPanel;
    private StudentsPanel studentsPanel;
    private GroupsPanel groupsPanel;
    private final DataSource data;

    public MainWindow(DataSource data) {
        this.data = data;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.data.closeConnection();
                super.windowClosing(e);
            }
        });
        setTitle("Система управления данных студентов");
        studentsPanel = new StudentsPanel(data);
        groupsPanel = new GroupsPanel(data);
        tabsPanel = new JTabbedPane();
        tabsPanel.addTab("Students list", studentsPanel);
        tabsPanel.addTab("Group list", groupsPanel);
        add(tabsPanel);
        setPreferredSize(new Dimension(500, 500));
        pack();
    }
}
