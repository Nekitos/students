package ru.haulmont.gui;

import ru.haulmont.controllers.GroupsController;
import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Group;
import ru.haulmont.tablemodels.GroupsTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikita on 12/14/14.
 */
public class GroupsPanel extends JPanel {
    private static final String QUESTION_MESSAGE_STRING = "Вы действительно хотите удалить выбранную группу?";
    private static final String QUESTION_MESSAGE_TITLE = "Внимание";

    private JTable groupsListTable;
    private JScrollPane tablePane;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnRemove;
    private GroupsTableModel groupsTableModel;
    private GroupsController groupsController;
    private DataSource data;
    private GroupLayout layout;
    private EditGroupDialog editGroupDialog;

    public GroupsPanel(DataSource data, JFrame owner) {
        this.data = data;
        groupsListTable = new JTable();
        groupsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePane = new JScrollPane(groupsListTable);
        btnAdd = new JButton("Добавить");
        btnEdit = new JButton("Редактировать");
        btnRemove = new JButton("Удалить");
        groupsTableModel = new GroupsTableModel(data);
        groupsController = new GroupsController(groupsTableModel, groupsListTable);
        layout = new GroupLayout(this);
        editGroupDialog = new EditGroupDialog(owner);

        groupsController.updateView();

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groupsListTable.getSelectedRow();
                if (selectedRow == -1)
                    return;
                int result = JOptionPane.showConfirmDialog( GroupsPanel.this,
                                                            QUESTION_MESSAGE_STRING,
                                                            QUESTION_MESSAGE_TITLE,
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Group deletingGroup = new Group();
                    long ID = (Long) groupsListTable.getValueAt(selectedRow, 0);
                    deletingGroup.setGroupID(ID);
                    groupsController.deleteGroup(deletingGroup);
                    groupsController.updateView();
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groupsListTable.getSelectedRow();
                if (selectedRow == -1)
                    return;
                Group editingGroup = new Group();
                editingGroup.setGroupID((Long) groupsListTable.getValueAt(selectedRow, 0));
                editingGroup.setGroupNumber((Integer) groupsListTable.getValueAt(selectedRow, 1));
                editingGroup.setFaculty((String) groupsListTable.getValueAt(selectedRow, 2));
                int result = editGroupDialog.showEditingDialog("Редактирование группы", editingGroup);
                if (result == editGroupDialog.BTN_OK) {
                    groupsController.editGroup(editingGroup);
                    groupsController.updateView();
                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = editGroupDialog.showAddingDialog("Добавление группы");
                if (result == editGroupDialog.BTN_OK) {
                    groupsController.addGroup(editGroupDialog.getAddingGroup());
                    groupsController.updateView();
                }
            }
        });

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
