package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Student;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Модель данных представляющая список студентов
 * в виде таблицы. Для удобства пользователя, вместо
 * ID группы в модель введен столбец номер группы.
 * Используется для отображения на компоненте JTable.
 * Модель общается с БД запрашивая данные о студентах
 * и фильтруя по фамилии и группе.
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

    /**
     * Конструирование модели.
     * @param dataSource ссылка на хранилище данных.
     */
    public StudentsTableModel(DataSource dataSource) {
        data = dataSource;
        studentsList = data.getAllStudents();
    }

    /**
     * Метод обновляет модель, получая из базы список всех студентов.
     */
    public void updateModel() {
        studentsList = data.getAllStudents();
    }

    /**
     * Добавляет студента обращаясь к интерфейсу DataSource.
     * @param addingStudent ссылка на сущность, содержащую
     *                      данные о студенте, подлежащему
     *                      добавлению.
     */
    public void addStudent(Student addingStudent) {
        data.addStudent(addingStudent);
    }

    /**
     * Редактирование студента через интерфейс DataSource.
     * @param editingStudent ссылка на сущность, содержащую
     *                       данные о студенте, подлежащему
     *                       редактированию.
     */
    public void editStudent(Student editingStudent) {
        data.editStudent(editingStudent);
    }

    /**
     * Удаление студента через интерфейс DataSource.
     * @param deletingStudent ссылка на сущность,
     *                        содержащую данные о студенты,
     *                        подлежащему удалению.
     */
    public void deleteStudent(Student deletingStudent) {
        data.deleteStudent(deletingStudent);
    }

    /**
     * Фильтр списка студентов по фамилии и номеру группы.
     * @param surname строка, содержащая фамилию студента
     * @param groupNumber четырехзначное целое -- номер группы.
     */
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
