package ru.haulmont.controllers;

import ru.haulmont.daoclasses.entities.Group;
import ru.haulmont.tablemodels.GroupsTableModel;

import javax.swing.*;

/**
 * Created by nikita on 12/14/14.
 */
public class GroupsController {
    private GroupsTableModel model;
    private JTable view;

    public GroupsController(GroupsTableModel model, JTable view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
    }

    public void updateView() {
        model.updateModel();
        view.revalidate();
        view.repaint();
    }

    public void addGroup(Group addingGroup) {
        model.addGroup(addingGroup);
    }

    public void editGroup(Group editingGroup) {
        model.editGroup(editingGroup);
    }

    public void deleteGroup(Group deletingGroup) {
        model.deleteGroup(deletingGroup);
    }
}
