package ru.haulmont.gui;

import ru.haulmont.controllers.StudentsController;
import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.entities.Student;
import ru.haulmont.tablemodels.StudentsTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsPanel extends JPanel {
    private static final String QUESTION_MESSAGE_STRING = "Вы действительно хотите удалить выбранного студента?";
    private static final String QUESTION_MESSAGE_TITLE = "Внимание!";

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
    private EditStudentDialog editDialog;

    public StudentsPanel(DataSource data, JFrame owner) {
        tableModel = new StudentsTableModel(data);
        studentsListTable = new JTable();
        studentsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        editDialog = new EditStudentDialog(owner);

        studentsController.updateView();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editDialog.showAddingDialog("Добавление студента") == editDialog.BTN_OK) {
                    Student temp = editDialog.getAddingStudent();
                    System.out.println(temp.getStudentID());
                    System.out.println(temp.getName());
                    System.out.println(temp.getSurname());
                    System.out.println(temp.getPatronymic());
                    System.out.println(temp.getBirthday());
                    System.out.println(temp.getGroupID());
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentsListTable.getSelectedRow();
                if (selectedRow == -1)
                    return;

                int result = JOptionPane.showConfirmDialog( StudentsPanel.this,
                                                            QUESTION_MESSAGE_TITLE,
                                                            QUESTION_MESSAGE_STRING,
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.OK_OPTION) {
                    Student deletedStudent = new Student();
                    long ID = (Long)studentsListTable.getValueAt(selectedRow, 0);
                    deletedStudent.setStudentID(ID);
                    studentsController.deleteStudent(deletedStudent);
                    studentsController.updateView();
                }
            }
        });

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
