package ru.haulmont.daoclasses;

import ru.haulmont.entities.Group;
import ru.haulmont.entities.Student;

import java.sql.*;

/**
 * Created by nikita on 11/29/14.
 */
public class DerbyDBStudents implements DBStudentsDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Savepoint savepoint;

    public DerbyDBStudents() {

    }

    @Override
    public void loadDatabase(String url, String userName, String password) throws SQLException {
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
    }

    @Override
    public void addGroup(Group newGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(ADD_GROUP_QUERY);
            preparedStatement.setInt(1, newGroup.getGroupNumber());
            preparedStatement.setString(2, newGroup.getFaculty());
            //TODO Write code where throw exception if group not added.
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                if (savepoint != null)
                    conn.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.releaseSavepoint(savepoint);
                preparedStatement.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void editGroup(Group editedGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(EDIT_GROUP_QUERY);
            preparedStatement.setInt(1, editedGroup.getGroupNumber());
            preparedStatement.setString(2, editedGroup.getFaculty());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                if (savepoint != null)
                    conn.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.releaseSavepoint(savepoint);
                preparedStatement.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteGroup(Group removedGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(REMOVE_GROUP_QUERY);
            preparedStatement.setLong(1, removedGroup.getGroupID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                if (savepoint != null)
                    conn.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.releaseSavepoint(savepoint);
                preparedStatement.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addStudent(Student newStudent) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(ADD_STUDENT_QUERY);
            //TODO Write code where throw exception if group not added.
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                if (savepoint != null)
                    conn.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.releaseSavepoint(savepoint);
                preparedStatement.close();
                conn.commit();
            }  catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void editStudent(Student editedStudent) {
        //TODO Write your code for edit student in db
    }

    @Override
    public void deleteStudent(Student removedStudent) {
        //TODO Write your code for delete student in db
    }

    @Override
    public void closeConnection() {
        try {
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
