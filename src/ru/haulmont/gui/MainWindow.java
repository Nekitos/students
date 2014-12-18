package ru.haulmont.gui;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.factory.DataSourceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс, содержащий вкладки для работы с таблицой студентов
 * и групп. Основной класс отображающий окно приложения.
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
                //Если окно закрывается, закрыть безопасно соединение с базой
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
