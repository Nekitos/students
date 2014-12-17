package ru.haulmont.controllers;

import ru.haulmont.entities.Student;
import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsController {
    private TableModel studentsTableModel;
    private JTable view;

    public StudentsController(TableModel studentsModel, JTable studentsTable) {
        studentsTableModel = studentsModel;
        view = studentsTable;
        view.setModel(studentsTableModel);
    }

    public void updateView() {
        ((StudentsTableModel)studentsTableModel).updateModel();
        view.revalidate();
    }

    public void deleteStudent(Student deletedStudent) {
        ((StudentsTableModel)studentsTableModel).deleteStudent(deletedStudent);
    }
}
