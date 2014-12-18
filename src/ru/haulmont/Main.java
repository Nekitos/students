package ru.haulmont;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.DerbyDataSource;
import ru.haulmont.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                //Перенести создание экземпляра БД в класс MainWindow
                DataSource data = new DerbyDataSource();
                data.loadDatabase("jdbc:derby:resources/studdb;create=true", null, null);
                JFrame frame = new MainWindow(data);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
