package ru.haulmont.controllers;

import ru.haulmont.daoclasses.entities.Student;
import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsController {
    private StudentsTableModel studentsTableModel;
    private JTable view;

    public StudentsController(StudentsTableModel studentsModel, JTable studentsTable) {
        studentsTableModel = studentsModel;
        view = studentsTable;
        view.setModel(studentsTableModel);
    }

    public void updateView() {
        studentsTableModel.updateModel();
        view.revalidate();
        view.repaint();
    }

    public void addStudent(Student addingStudent) {
        studentsTableModel.addStudent(addingStudent);
    }

    public void editStudent(Student editingStudent) {
        studentsTableModel.editStudent(editingStudent);
    }

    public void deleteStudent(Student deletingStudent) {
        studentsTableModel.deleteStudent(deletingStudent);
    }
}
