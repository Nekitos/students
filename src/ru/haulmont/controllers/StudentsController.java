package ru.haulmont.controllers;

import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsController {
    private TableModel studentsTableModel;
    private JTable studentsTable;

    public StudentsController(TableModel studentsModel, JTable view) {
        studentsTableModel = studentsModel;
        studentsTable = view;
    }

    public void updateView() {
        ((StudentsTableModel)studentsTableModel).updateModel();
        studentsTable.setModel(studentsTableModel);
    }
}
