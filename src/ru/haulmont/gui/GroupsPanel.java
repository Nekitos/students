package ru.haulmont.gui;

import ru.haulmont.controllers.GroupsController;
import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.tablemodels.GroupsTableModel;

import javax.swing.*;

/**
 * Created by nikita on 12/14/14.
 */
public class GroupsPanel extends JPanel {
    private JTable groupsListTable;
    private JScrollPane tablePane;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnRemove;
    private GroupsTableModel groupsTableModel;
    private GroupsController groupsController;
    private DataSource data;
    private GroupLayout layout;

    public GroupsPanel(DataSource data) {
        this.data = data;
        groupsListTable = new JTable();
        tablePane = new JScrollPane(groupsListTable);
        btnAdd = new JButton("Добавить");
        btnEdit = new JButton("Редактировать");
        btnRemove = new JButton("Удалить");
        groupsTableModel = new GroupsTableModel(data);
        groupsController = new GroupsController(groupsTableModel, groupsListTable);
        layout = new GroupLayout(this);

        groupsController.updateView();

        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(tablePane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnAdd)
                        .addComponent(btnEdit)
                        .addComponent(btnRemove)
                )
        );
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(tablePane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addComponent(btnEdit)
                        .addComponent(btnRemove)
                )
        );
    }
}
