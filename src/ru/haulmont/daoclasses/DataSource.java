package ru.haulmont.daoclasses;

import ru.haulmont.daoclasses.entities.*;

import java.util.List;

/**
 * Created by nikita on 11/29/14.
 */
public interface DataSource {
    public static final String ADD_GROUP_QUERY = "INSERT INTO groups (g_number, faculty) VALUES (?, ?)";
    public static final String ADD_STUDENT_QUERY = "INSERT INTO students (name, surname, patronymic, birthday, group_id) VALUES (?, ?, ?, ?, ?)";
    public static final String EDIT_GROUP_QUERY = "UPDATE groups SET g_number = ?, faculty = ? WHERE id = ?";
    public static final String EDIT_STUDENT_QUERY = "UPDATE students SET name = ?, surname = ?, patronymic = ?, birthday = ?, group_id = ? WHERE id = ?";
    public static final String REMOVE_GROUP_QUERY = "DELETE FROM groups WHERE id = ?";
    public static final String REMOVE_STUDENT_QUERY = "DELETE FROM students WHERE id = ?";
    public static final String SELECT_ALL_STUDENTS_QUERY = "SELECT s.*, g.g_number FROM students s INNER JOIN groups g ON g.id = s.group_id";
    public static final String SELECT_ALL_GROUPS_QUERY = "SELECT * FROM groups";
    public static final String FILTER_STUDENTS_BY_NAME_AND_GROUP_NUMBER = "SELECT s.* FROM students s INNER JOIN groups g ON s.group_id = g.id WHERE s.surname = ? AND g.g_number = ?";
    public static final String GET_GROUP_ID_BY_NUMBER_QUERY = "SELECT id FROM groups WHERE g_number = ?";

    /**
     * Метод загружает базу данных по заданным параметрам.
     * @param url  строка содержащая драйвер БД, имя БД и расположение;
     * @param userName  строка содержащая имя пользователя;
     * @param password  строка содержащая пароль пользователя.
     */
    public void loadDatabase(String url, String userName, String password);

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
     * Запрос в БД на получение ID группы по ее номеру.
     * @param groupNumber номер той группы, чей ID нужно узнать.
     * @return возвращает номер группы или -1, если такой группы нет
     */
    public long getGroupIDByGroupNumber(int groupNumber);

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
     * Метод фильтрует студентов по заданным фамилии и номеру группу.
     * @param surname фамилия студента
     * @param groupNumber группа
     * @return возвращает список студентов отфильтрованных по фамилии и номеру группы
     */
    public List<Student> filter(String surname, int groupNumber);

    /**
     * Закрытие соединения с Базой Данных.
     */
    public void closeConnection();
}
