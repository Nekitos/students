package ru.haulmont.daoclasses;

import ru.haulmont.daoclasses.entities.Group;
import ru.haulmont.daoclasses.entities.Student;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс DerbyDataSource предназначен для загрузки встроенной
 * БД Apache Derby< поставляемой вместе с JDK.
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

    /**
     * Загружает БД Apache Derby (Embedded). Поскольку для встроенной БД
     * не является обходимым пароль и имя пользователя, то передается null.
     * Если БД не существовала или ее кто-то удалил, то она создается
     * с нуля.
     * @param url  строка содержащая драйвер БД, имя БД и расположение;
     * @param userName  null;
     * @param password  null.
     */
    @Override
    public void loadDatabase(String url, String userName, String password) {
        try {
            conn = DriverManager.getConnection(url);
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet resultSet = dbMeta.getTables(null, "APP", "STUDENTS", null);
            if (!resultSet.next()) {
                Statement statement = conn.createStatement();
                statement.execute(CREATE_STUD_TABLE);
            }
            resultSet = dbMeta.getTables(null, "APP", "GROUPS", null);
            if (!resultSet.next()) {
                Statement statement = conn.createStatement();
                statement.execute(CREATE_GROUPS_TABLE);
            }
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
    public void addGroup(Group addingGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(ADD_GROUP_QUERY);
            preparedStatement.setInt(1, addingGroup.getGroupNumber());
            preparedStatement.setString(2, addingGroup.getFaculty());
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
    public void editGroup(Group editingGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(EDIT_GROUP_QUERY);
            preparedStatement.setInt(1, editingGroup.getGroupNumber());
            preparedStatement.setString(2, editingGroup.getFaculty());
            preparedStatement.setLong(3, editingGroup.getGroupID());
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
    public void deleteGroup(Group deletingGroup) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(REMOVE_GROUP_QUERY);
            preparedStatement.setLong(1, deletingGroup.getGroupID());
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
    public void addStudent(Student addingStudent) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(ADD_STUDENT_QUERY);
            preparedStatement.setString(1, addingStudent.getName());
            preparedStatement.setString(2, addingStudent.getSurname());
            preparedStatement.setString(3, addingStudent.getPatronymic());
            preparedStatement.setDate(4, addingStudent.getBirthday());
            preparedStatement.setLong(5, addingStudent.getGroupID());
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
    public void editStudent(Student editingStudent) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(EDIT_STUDENT_QUERY);
            preparedStatement.setString(1, editingStudent.getName());
            preparedStatement.setString(2, editingStudent.getSurname());
            preparedStatement.setString(3, editingStudent.getPatronymic());
            preparedStatement.setDate(4, editingStudent.getBirthday());
            preparedStatement.setLong(5, editingStudent.getGroupID());
            preparedStatement.setLong(6, editingStudent.getStudentID());
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
    public void deleteStudent(Student deletingStudent) {
        try {
            savepoint = conn.setSavepoint();
            preparedStatement = conn.prepareStatement(REMOVE_STUDENT_QUERY);
            preparedStatement.setLong(1, deletingStudent.getStudentID());
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
