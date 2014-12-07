package ru.haulmont.daoclasses;

import ru.haulmont.entities.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikita on 11/29/14.
 */
public interface DBStudentsDAO {
    public static final String ADD_GROUP_QUERY = "INSERT INTO groups (number, faculty) VALUES (?, ?)";
    public static final String ADD_STUDENT_QUERY = "INSERT INTO students (name, surname, patronymic, birthday, group_id) VALUES (?, ?, ?, ?, ?)";
    public static final String EDIT_GROUP_QUERY = "UPDATE groups SET number = ?, faculty = ? WHERE id = ?";
    public static final String EDIT_STUDENT_QUERY = "UPDATE students SET name = ?, surname = ?, patronymic = ?, birthday = ?, group_id = ? WHERE id = ?";
    public static final String REMOVE_GROUP_QUERY = "DELETE FROM groups WHERE id = ?";
    public static final String REMOVE_STUDENT_QUERY = "DELETE FROM students WHERE id = ?";
    public static final String SELECT_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    public static final String SELECT_ALL_GROUPS_QUERY = "SELECT * FROM groups";

    /**
     * Метод загружает базу данных по заданным параметрам.
     * @param url  строка содержащая драйвер БД, имя БД и расположение;
     * @param userName  строка содержащая имя пользователя;
     * @param password  строка содержащая пароль пользователя.
     */
    public void loadDatabase(String url, String userName, String password) throws SQLException;

    /**
     * Добавление новой группы в таблицу "Groups".
     * @param newGroup  ссылка на класс новой группы, подлежащей добавлению.
     */
    public void addGroup(Group newGroup);

    /**
     * Редактирование выбранной группы.
     * @param editedGroup  ссылка на класс группы, подлежащей редактированию.
     */
    public void editGroup(Group editedGroup);

    /**
     * Удаление существующей группы из таблицы.
     * @param removedGroup  ссылка на класс группы, подлежащей удалению из таблицы.
     */
    public void deleteGroup(Group removedGroup);

    /**
     * Добавление нового студента в таблицу.
     * @param newStudent  ссылка на класс студента, добавляемого в таблицу.
     */
    public void addStudent(Student newStudent);

    /**
     * Редактирование выбранного студента.
     * @param editedStudent  ссылка на класс редактируемого студента.
     */
    public void editStudent(Student editedStudent);

    /**
     * Удаление существующего студента из таблицы.
     * @param removedStudent  ссылка на класс удаляемого студента.
     */
    public void deleteStudent(Student removedStudent);

    /**
     * Метод возвращает список всех студентов хранящихся в БД.
     * @return список студентов
     */
    public List<Student> getAllStudents();

    /**
     * Метод возвращает список всех групп хранящихся в БД.
     * @return список групп
     */
    public List<Group> getAllGroups();

    /**
     * Закрытие соединения с Базой Данных.
     */
    public void closeConnection();
}
