package ru.haulmont;

import ru.haulmont.daoclasses.DerbyDBStudents;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DerbyDBStudents database = new DerbyDBStudents();
        database.loadDatabase("jdbc:derby:/home/nikita/Documents/CodingTime/jworkspace/students/studdb;", null, null);
    }
}
