package ru.haulmont.controllers;

import ru.haulmont.tablemodels.GroupsTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Created by nikita on 12/14/14.
 */
public class GroupsController {
    private TableModel model;
    private JTable view;

    public GroupsController(TableModel model, JTable view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        ((GroupsTableModel)model).updateModel();
        view.setModel(model);
    }
}
