package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.entities.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsTableModel extends AbstractTableModel {
    private static final String columnNames[] = {
            "Имя",
            "Фамилия",
            "Отчество",
            "Дата рождения",
            "Номер группы"
    };

    private List<Student> studentsList;
    private DataSource data;

    public StudentsTableModel(DataSource dataSource) {
        data = dataSource;
        studentsList = data.getAllStudents();
    }

    public void updateModel() {
        studentsList = data.getAllStudents();
    }

    @Override
    public int getRowCount() {
        return studentsList.size();
    }

    @Override
    public int getColumnCount() {
        //Magic number is poor
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Student temp = studentsList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = temp.getName();
                break;
            case 1:
                value = temp.getSurname();
                break;
            case 2:
                value = temp.getPatronymic();
                break;
            case 3:
                value = temp.getBirthday();
                break;
            case 4:
                value = temp.getGroupID();
        }

        return value;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
