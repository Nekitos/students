package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Group;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Модель содержащая в себе таблицу групп студентов.
 * Использутеся для отображения на компоненте JTable.
 * Модель общается с БД запрашивая данные о группах.
 */
public class GroupsTableModel extends AbstractTableModel {
    private static final String columnNames[] = {
            "ID группы",
            "Номер группы",
            "Факультет"
    };

    private DataSource data;
    private List<Group> groupsList;

    /**
     * Конструирование модели.
     * @param dataSource ссылка на хранилище данных
     */
    public GroupsTableModel(DataSource dataSource) {
        data = dataSource;
        groupsList = data.getAllGroups();
    }

    /**
     * Метод обновляет данные в модели.
     */
    public void updateModel() {
        groupsList = data.getAllGroups();
    }

    /**
     * Добавление группы в базу через интерфейс DataSource.
     * @param addingGroup ссылка на сущность, инкапсулирующую данные о группе.
     */
    public void addGroup(Group addingGroup) {
        data.addGroup(addingGroup);
    }

    /**
     * Удаление группы из базы через интерфейс DataSource.
     * @param deletingGroup ссылка на сущность, инкапсулирующую данные о группе, подлежащая удалению.
     */
    public void deleteGroup(Group deletingGroup) {
        data.deleteGroup(deletingGroup);
    }

    /**
     * Редактирование группы через интерфейс DataSource.
     * @param editingGroup ссылка на сущность, инкапсулирующую данные о группе, подлежащая редактированию
     */
    public void editGroup(Group editingGroup) {
        data.editGroup(editingGroup);
    }

    @Override
    public int getRowCount() {
        return groupsList.size();
    }

    @Override
    public int getColumnCount() {
        //Magic number is poor
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Group temp = groupsList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = temp.getGroupID();
                break;
            case 1:
                value = temp.getGroupNumber();
                break;
            case 2:
                value = temp.getFaculty();
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
