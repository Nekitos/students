package ru.haulmont;

import ru.haulmont.daoclasses.DerbyDBStudents;
import ru.haulmont.entities.Group;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Group groups[] = new Group[]{new Group(), new Group()};
        groups[0].setFaculty("Летательных аппаратов");
        groups[0].setGroupNumber(1105);
        groups[1].setFaculty("Факультет информатики");
        groups[1].setGroupNumber(1605);

        DerbyDBStudents database = new DerbyDBStudents();
        database.loadDatabase("jdbc:derby:/home/nikita/Documents/CodingTime/jworkspace/students/studdb;", null, null);
        /*database.addGroup(groups[0]);
        database.addGroup(groups[1]);*/
        database.closeConnection();
    }
}
