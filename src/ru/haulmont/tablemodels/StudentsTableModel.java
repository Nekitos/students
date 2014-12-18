package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Student;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsTableModel extends AbstractTableModel {
    private static final String columnNames[] = {
            "ID студента",
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

    public void addStudent(Student addingStudent) {
        data.addStudent(addingStudent);
    }

    public void editStudent(Student editingStudent) {
        data.editStudent(editingStudent);
    }

    public void deleteStudent(Student deletedStudent) {
        data.deleteStudent(deletedStudent);
    }

    public void filter(String surname, int groupNumber) {
        studentsList = data.filter(surname, groupNumber);
    }

    @Override
    public int getRowCount() {
        return studentsList.size();
    }

    @Override
    public int getColumnCount() {
        //Magic number is poor
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Student temp = studentsList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = temp.getStudentID();
                break;
            case 1:
                value = temp.getName();
                break;
            case 2:
                value = temp.getSurname();
                break;
            case 3:
                value = temp.getPatronymic();
                break;
            case 4:
                String date = new SimpleDateFormat("dd.MM.yyyy").format(temp.getBirthday());
                value = date;
                break;
            case 5:
                value = temp.getGroupNumber();
                break;
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
