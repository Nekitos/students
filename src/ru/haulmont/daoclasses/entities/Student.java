package ru.haulmont.daoclasses.entities;

import java.sql.Date;

/**
 * Класс представляющий сущность "Студент".
 * Инкапсулирует данные о студенте.
 * Данные:
 * <ul>
 *     <li>ID(Код студента)</li>
 *     <li>Name(Имя)</li>
 *     <li>Surname(Фамилия)</li>
 *     <li>Patronymic(Отчество)</li>
 *     <li>Birthday(Дата рождения)</li>
 *     <li>groupID(Порядковый номер группы)</li>
 * </ul>
 */
public class Student {
    private long studentID;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private long groupID;
    private int groupNumber;

    /**
     * Создает пустой экземпляр класса.
     */
    public Student() {    }

    /**
     * Метод возвращает ID студента
     * @return длинное целое -- ID студента
     */
    public long getStudentID() {
        return studentID;
    }

    /**
     * Метод записывает ID студента
     * @param studentID длинное целое -- ID студента
     */
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    /**
     * Метод возвращает имя студента
     * @return строку, содержащую имя студента
     */
    public String getName() {
        return name;
    }

    /**
     * Метод записывает имя студента
     * @param name строка, содержащая имя студента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает фамилию студента
     * @return строку, содержащую фамилию студента
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Метод записывает фамилию студента
     * @param surname строка, содержащая фамилию студента
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Метод возвращает отчество студента
     * @return возвращает строку, содержащую отчество студента
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Метод записывает отчество студента
     * @param patronymic строка, содержащая имя студента
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Метод возвращает дату рождения студента в формате гггг-мм-дд
     * @return экземпляр {@code Date} дата рождения студента.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Метод записывает дату рождения студента
     * @param birthday ссылка на {@code Date} -- дату рождения студента
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Получить ID группы студента.
     * @return длинное целое -- ID группы
     */
    public long getGroupID() {
        return groupID;
    }

    /**
     * Записать ID группы студента.
     * @param groupID длинное целое -- ID группы
     */
    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    /**
     * Метод возвращает номер группы студента
     * @return четырехзначное число -- номер группы
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Метод записывает номер группы студента
     * @param groupNumber четырехзначное число -- номер группы
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
