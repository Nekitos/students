package ru.haulmont.gui;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.factory.DataSourceFactory;

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
    private DataSource data;


    public MainWindow() {
        data = DataSourceFactory.newDataSource(DataSourceFactory.DERBY_DATA_SOURCE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.closeConnection();
                super.windowClosing(e);
            }
        });
        setTitle("Система управления данных студентов");
        studentsPanel = new StudentsPanel(data, this);
        groupsPanel = new GroupsPanel(data, this);
        tabsPanel = new JTabbedPane();
        tabsPanel.addTab("Студенты", studentsPanel);
        tabsPanel.addTab("Группы", groupsPanel);
        add(tabsPanel);
        setPreferredSize(new Dimension(500, 500));
        pack();
    }
}
