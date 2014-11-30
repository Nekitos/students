package ru.haulmont.daoclasses;

import ru.haulmont.entities.Group;
import ru.haulmont.entities.Student;

import java.sql.*;
import java.util.Enumeration;

/**
 * Created by nikita on 11/29/14.
 */
public class DerbyDBStudents implements DBStudentsDAO {
    private Connection conn;

    @Override
    public void loadDatabase(String url, String userName, String password) throws SQLException {
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select * from students");
        while (result.next()) {
            for (int i = 1; i < result.getMetaData().getColumnCount(); i++) {
                System.out.print(result.getString(i) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void addGroup(Group newGroup) {

    }

    @Override
    public void editGroup(Group editedGroup) {

    }

    @Override
    public void deleteGroup(Group removedGroup) {

    }

    @Override
    public void addStudent(Student newStudent) {

    }

    @Override
    public void editStudent(Student editedStudent) {

    }

    @Override
    public void deleteStudent(Student removedStudent) {

    }
}
