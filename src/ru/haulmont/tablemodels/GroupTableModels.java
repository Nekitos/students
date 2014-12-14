package ru.haulmont.tablemodels;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.entities.Group;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by nikita on 12/13/14.
 */
public class GroupTableModels extends AbstractTableModel {
    private static final String columnNames[] = {
            "Group number",
            "Faculty"
    };

    private DataSource data;
    private List<Group> groupsList;

    public GroupTableModels(DataSource dataSource) {
        data = dataSource;
        groupsList = data.getAllGroups();
    }

    public void updateModel() {
        groupsList = data.getAllGroups();
    }

    @Override
    public int getRowCount() {
        return groupsList.size();
    }

    @Override
    public int getColumnCount() {
        //Magic number is poor
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return groupsList.get(columnIndex);
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
