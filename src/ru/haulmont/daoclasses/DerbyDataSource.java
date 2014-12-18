package ru.haulmont.daoclasses;

import ru.haulmont.daoclasses.entities.Group;
import ru.haulmont.daoclasses.entities.Student;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 11/29/14.
 */
public class DerbyDataSource implements DataSource {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Savepoint savepoint;
    private boolean groupsUpdate;
    private boolean studentsUpdate;
    private List<Student> students;
    private List<Group> groups;

    public DerbyDataSource() {
        students = new ArrayList<Student>();
        groups = new ArrayList<Group>();
    }

    @Override
    public void loadDatabase(String url, String userName, String password) {
        try {
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            groupsUpdate = false;
            studentsUpdate = false;
            updateGroupsList();
            updateStudentsList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGroup(Group newGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(ADD_GROUP_QUERY);
            preparedStatement.setInt(1, newGroup.getGroupNumber());
            preparedStatement.setString(2, newGroup.getFaculty());
            preparedStatement.executeUpdate();
            groupsUpdate = true;
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
            preparedStatement.setLong(3, editedGroup.getGroupID());
            preparedStatement.executeUpdate();
            groupsUpdate = true;
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
            groupsUpdate = true;
        } catch (SQLException e) {
            try {
                if (savepoint != null)
                    conn.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Невозможно удалить группу содержащую студентов", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setString(2, newStudent.getSurname());
            preparedStatement.setString(3, newStudent.getPatronymic());
            preparedStatement.setDate(4, newStudent.getBirthday());
            preparedStatement.setLong(5, newStudent.getGroupID());
            preparedStatement.executeUpdate();
            studentsUpdate = true;
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
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(EDIT_STUDENT_QUERY);
            preparedStatement.setString(1, editedStudent.getName());
            preparedStatement.setString(2, editedStudent.getSurname());
            preparedStatement.setString(3, editedStudent.getPatronymic());
            preparedStatement.setDate(4, editedStudent.getBirthday());
            preparedStatement.setLong(5, editedStudent.getGroupID());
            preparedStatement.setLong(6, editedStudent.getStudentID());
            preparedStatement.executeUpdate();
            studentsUpdate = true;
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
    public void deleteStudent(Student removedStudent) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(REMOVE_STUDENT_QUERY);
            preparedStatement.setLong(1, removedStudent.getStudentID());
            preparedStatement.executeUpdate();
            studentsUpdate = true;
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
    public long getGroupIDByGroupNumber(int groupNumber) {
        long result = -1;
        try {
            preparedStatement = conn.prepareStatement(GET_GROUP_ID_BY_NUMBER_QUERY);
            preparedStatement.setInt(1, groupNumber);
            ResultSet rslt = preparedStatement.executeQuery();
            rslt.next();
            result = rslt.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public List<Student> getAllStudents() {
        if (studentsUpdate)
            updateStudentsList();
        return students;
    }

    @Override
    public List<Group> getAllGroups() {
        if (groupsUpdate)
            updateGroupsList();
        return groups;
    }

    @Override
    public List<Student> filter(String surname, int groupNumber) {
        List<Student> result = new ArrayList<Student>();

        try {
            preparedStatement = conn.prepareStatement(FILTER_STUDENTS_BY_NAME_AND_GROUP_NUMBER);
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, groupNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setSurname(resultSet.getString(3));
                student.setPatronymic(resultSet.getString(4));
                student.setBirthday(resultSet.getDate(5));
                student.setGroupID(resultSet.getLong(6));
                student.setGroupNumber(resultSet.getInt(7));
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
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

    private void updateStudentsList() {
        Statement statement = null;
        students.clear();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_STUDENTS_QUERY);
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setSurname(resultSet.getString(3));
                student.setPatronymic(resultSet.getString(4));
                student.setBirthday(resultSet.getDate(5));
                student.setGroupID(resultSet.getLong(6));
                student.setGroupNumber(resultSet.getInt(7));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGroupsList() {
        Statement statement = null;
        groups.clear();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_GROUPS_QUERY);
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupID(resultSet.getLong(1));
                group.setGroupNumber(resultSet.getInt(2));
                group.setFaculty(resultSet.getString(3));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
