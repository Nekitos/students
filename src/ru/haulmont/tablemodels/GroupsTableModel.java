package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Group;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by nikita on 12/13/14.
 */
public class GroupsTableModel extends AbstractTableModel {
    private static final String columnNames[] = {
            "ID группы",
            "Номер группы",
            "Факультет"
    };

    private DataSource data;
    private List<Group> groupsList;

    public GroupsTableModel(DataSource dataSource) {
        data = dataSource;
        groupsList = data.getAllGroups();
    }

    public void updateModel() {
        groupsList = data.getAllGroups();
    }

    public void addGroup(Group addingGroup) {
        data.addGroup(addingGroup);
    }
    
    public void deleteGroup(Group deletingGroup) {
        data.deleteGroup(deletingGroup);
    }

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
