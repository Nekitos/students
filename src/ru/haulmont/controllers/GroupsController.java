package ru.haulmont.controllers;

import ru.haulmont.daoclasses.entities.Group;
import ru.haulmont.tablemodels.GroupsTableModel;

import javax.swing.*;

/**
 * Класс {@code GroupsController} соединяет между собой
 * модель {@code GroupsTableModel} и представление
 * (компонент на форме). Класс служит проводником
 * для выполнения операций, а также обновляет модель и
 * представление.
 */
public class GroupsController {
    private GroupsTableModel model;
    private JTable view;

    /**
     * Конструирование класса контроллера.
     * @param model ссылка на модель данных
     * @param view ссылка на представление данных
     */
    public GroupsController(GroupsTableModel model, JTable view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
    }

    /**
     * Метод обновляет модель и перерисовывает таблицу
     * с обновленной моделью
     */
    public void updateView() {
        model.updateModel();
        view.revalidate();
        view.repaint();
    }

    /**
     * Добавление группы в модель.
     * @param addingGroup ссылка на класс добавляемой группы
     */
    public void addGroup(Group addingGroup) {
        model.addGroup(addingGroup);
    }

    /**
     * Редактирование группы в модели.
     * @param editingGroup ссылка на класс редактируемой группы
     */
    public void editGroup(Group editingGroup) {
        model.editGroup(editingGroup);
    }

    /**
     * Удаление группы из модели
     * @param deletingGroup ссылка на класс удаляемой группы
     */
    public void deleteGroup(Group deletingGroup) {
        model.deleteGroup(deletingGroup);
    }
}
