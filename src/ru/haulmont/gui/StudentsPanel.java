package ru.haulmont.gui;

import ru.haulmont.controllers.StudentsController;
import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsPanel extends JPanel {
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnRemove;
    private JButton btnSetFilter;
    private JTable studentsListTable;
    private JLabel lblSurname;
    private JLabel lblGroupNum;
    private JComboBox<String> cbGroupNum;
    private JTextField tfSurname;
    private JScrollPane tablePane;
    private StudentsController studentsController;
    private StudentsTableModel tableModel;
    private GroupLayout layout;
    private GroupLayout filterLayout;
    private TitledBorder filterGroup;
    private JPanel filterPanel;

    public StudentsPanel(DataSource data) {
        tableModel = new StudentsTableModel(data);
        studentsListTable = new JTable();
        tablePane = new JScrollPane(studentsListTable);
        studentsController = new StudentsController(tableModel, studentsListTable);
        btnAdd = new JButton("Добавить");
        btnEdit = new JButton("Редактировать");
        btnRemove = new JButton("Удалить");
        btnSetFilter = new JButton("Фильтровать");
        lblSurname = new JLabel("Фамилия студента");
        lblGroupNum = new JLabel("Номер группы");
        cbGroupNum = new JComboBox<String>();
        tfSurname = new JFormattedTextField();
        filterPanel = new JPanel();
        layout = new GroupLayout(this);
        filterLayout = new GroupLayout(filterPanel);
        filterGroup = new TitledBorder("Фильтр");

        studentsController.updateView();

        filterPanel.setBorder(filterGroup);
        filterPanel.setLayout(filterLayout);
        filterLayout.setAutoCreateGaps(true);
        filterLayout.setAutoCreateContainerGaps(true);
        filterLayout.setHorizontalGroup(filterLayout.createSequentialGroup()
                .addGroup(filterLayout.createParallelGroup()
                        .addComponent(lblSurname)
                        .addComponent(tfSurname)
                        .addComponent(lblGroupNum)
                        .addComponent(cbGroupNum)
                )
                .addGap(100)
                .addComponent(btnSetFilter)
        );
        filterLayout.setVerticalGroup(filterLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(filterLayout.createSequentialGroup()
                        .addComponent(lblSurname, 20, 20, 20)
                        .addComponent(tfSurname, 20, 20, 20)
                        .addComponent(lblGroupNum, 20, 20, 20)
                        .addComponent(cbGroupNum, 20, 20, 20)
                )
                .addComponent(btnSetFilter)
        );

        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addComponent(tablePane)
                .addGroup(layout.createParallelGroup()
                                .addComponent(btnAdd)
                                .addComponent(btnEdit)
                                .addComponent(btnRemove)
                )
                        .addComponent(filterPanel)
        );
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(tablePane)
                .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addComponent(btnEdit)
                                .addComponent(btnRemove)
                )
                        .addComponent(filterPanel)
        );
    }

}
