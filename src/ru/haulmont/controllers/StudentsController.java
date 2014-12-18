package ru.haulmont.controllers;

import ru.haulmont.daoclasses.entities.Student;
import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;

/**
 * Класс {@code StudentsController} соединяет между собой
 * модель {@code StudentsTableModel} и представление
 * (компонент на форме). Класс служит проводником
 * для выполнения операций, а также обновляет
 * содержимое модели и перерисовывает представление.
 */
public class StudentsController {
    private StudentsTableModel studentsTableModel;
    private JTable view;

    /**
     * Конструирует контроллер.
     * @param studentsModel ссылка на модель данных
     * @param studentsTable ссылка на представление данных
     */
    public StudentsController(StudentsTableModel studentsModel, JTable studentsTable) {
        studentsTableModel = studentsModel;
        view = studentsTable;
        view.setModel(studentsTableModel);
    }

    /**
     * Метод обновляет модель данных.
     */
    public void updateModel() {
        studentsTableModel.updateModel();
    }

    /**
     * Метод перерисовыает представление.
     */
    public void updateView() {
        view.revalidate();
        view.repaint();
    }

    /**
     * Добавление студента в модель.
     * @param addingStudent ссылка на класс, добавляемого студента
     */
    public void addStudent(Student addingStudent) {
        studentsTableModel.addStudent(addingStudent);
    }

    /**
     * Редактирование студента в модели.
     * @param editingStudent ссылка на класс, редактируемого студента
     */
    public void editStudent(Student editingStudent) {
        studentsTableModel.editStudent(editingStudent);
    }

    /**
     * Удаление студента
     * @param deletingStudent ссылка на класс, удаляемого студента
     */
    public void deleteStudent(Student deletingStudent) {
        studentsTableModel.deleteStudent(deletingStudent);
    }

    /**
     * Метод фильтрует данные по заданной фамилии студента и номеру группы.
     * @param surname строка, содержащая фамилию студента
     * @param groupNumber четырехзначное целое, содержащее номер группы
     */
    public void setFilter(String surname, int groupNumber) {
        studentsTableModel.filter(surname, groupNumber);
    }
}
