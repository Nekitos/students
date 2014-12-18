package ru.haulmont.gui;

import ru.haulmont.controllers.StudentsController;
import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.entities.Student;
import ru.haulmont.tablemodels.StudentsTableModel;
import ru.haulmont.util.DateFormat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nikita on 12/13/14.
 */
public class StudentsPanel extends JPanel {
    private static final String QUESTION_MESSAGE_STRING = "Вы действительно хотите удалить выбранного студента?";
    private static final String QUESTION_MESSAGE_TITLE = "Внимание!";
    private static final String ERROR_GROUP_NOT_FOUND = "Такой группы не существует!";
    private static final String FILTER_ERROR_TITLE = "Внимание!";
    private static final String FILTER_ERROR_STRING = "Поля фильтра или одно из его полей не заданы";

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnRemove;
    private JButton btnSetFilter;
    private JButton btnResetFilter;
    private JTable studentsListTable;
    private JLabel lblSurname;
    private JLabel lblGroupNum;
    private JFormattedTextField ftfGroupNumber;
    private JTextField tfSurname;
    private JScrollPane tablePane;
    private StudentsController studentsController;
    private StudentsTableModel tableModel;
    private GroupLayout layout;
    private GroupLayout filterLayout;
    private TitledBorder filterGroup;
    private JPanel filterPanel;
    private EditStudentDialog editDialog;
    private SimpleDateFormat simpleDateFormat;

    public StudentsPanel(final DataSource data, JFrame owner) {
        tableModel = new StudentsTableModel(data);
        studentsListTable = new JTable();
        studentsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePane = new JScrollPane(studentsListTable);
        studentsController = new StudentsController(tableModel, studentsListTable);
        btnAdd = new JButton("Добавить");
        btnEdit = new JButton("Редактировать");
        btnRemove = new JButton("Удалить");
        btnSetFilter = new JButton("Фильтровать");
        btnResetFilter = new JButton("Сброс фильтра");
        lblSurname = new JLabel("Фамилия студента");
        lblGroupNum = new JLabel("Номер группы");
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            ftfGroupNumber = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tfSurname = new JTextField();
        filterPanel = new JPanel();
        layout = new GroupLayout(this);
        filterLayout = new GroupLayout(filterPanel);
        filterGroup = new TitledBorder("Фильтр");
        editDialog = new EditStudentDialog(owner);

        studentsController.updateModel();
        studentsController.updateView();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editDialog.showAddingDialog("Добавление студента") == editDialog.BTN_OK) {
                    Student newStudent = editDialog.getAddingStudent();
                    long result = data.getGroupIDByGroupNumber(newStudent.getGroupNumber());

                    if (result == -1) {
                        JOptionPane.showMessageDialog(StudentsPanel.this, ERROR_GROUP_NOT_FOUND, QUESTION_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    newStudent.setGroupID(result);
                    studentsController.addStudent(newStudent);
                    studentsController.updateModel();
                    studentsController.updateView();
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentsListTable.getSelectedRow();
                if (selectedRow == -1)
                    return;

                Student editingStudent = new Student();
                editingStudent.setStudentID((Long)studentsListTable.getValueAt(selectedRow, 0));
                editingStudent.setName((String)studentsListTable.getValueAt(selectedRow, 1));
                editingStudent.setSurname((String)studentsListTable.getValueAt(selectedRow, 2));
                editingStudent.setPatronymic((String)studentsListTable.getValueAt(selectedRow, 3));
                Date date = null;
                try {
                    date = DateFormat.fromString((String)studentsListTable.getValueAt(selectedRow, 4), simpleDateFormat);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                editingStudent.setBirthday(date);
                editingStudent.setGroupNumber((Integer)studentsListTable.getValueAt(selectedRow, 5));
                if (editDialog.showEditingDialog("Редактирование студента", editingStudent) == editDialog.BTN_OK) {
                    long result = data.getGroupIDByGroupNumber(editingStudent.getGroupNumber());

                    if (result == -1) {
                        JOptionPane.showMessageDialog(  StudentsPanel.this,
                                                        ERROR_GROUP_NOT_FOUND,
                                                        QUESTION_MESSAGE_TITLE,
                                                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    editingStudent.setGroupID(result);
                    studentsController.editStudent(editingStudent);
                    studentsController.updateModel();
                    studentsController.updateView();
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
                                                            QUESTION_MESSAGE_STRING,
                                                            QUESTION_MESSAGE_TITLE,
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.OK_OPTION) {
                    Student deletedStudent = new Student();
                    long ID = (Long)studentsListTable.getValueAt(selectedRow, 0);
                    deletedStudent.setStudentID(ID);
                    studentsController.deleteStudent(deletedStudent);
                    studentsController.updateModel();
                    studentsController.updateView();
                }
            }
        });
        btnSetFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfSurname.getText().trim().equals("") || ftfGroupNumber.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(StudentsPanel.this, FILTER_ERROR_STRING, FILTER_ERROR_TITLE, JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int groupNumber = Integer.parseInt(ftfGroupNumber.getText().trim());
                String surname = tfSurname.getText();
                studentsController.setFilter(surname, groupNumber);
                studentsController.updateView();
            }
        });
        btnResetFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsController.updateModel();
                studentsController.updateView();
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
                                .addComponent(ftfGroupNumber)
                )
                .addGap(100)
                .addGroup(filterLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(btnSetFilter)
                                .addComponent(btnResetFilter)
                )
        );
        filterLayout.setVerticalGroup(filterLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(filterLayout.createSequentialGroup()
                                        .addComponent(lblSurname, 20, 20, 20)
                                        .addComponent(tfSurname, 20, 20, 20)
                                        .addComponent(lblGroupNum, 20, 20, 20)
                                        .addComponent(ftfGroupNumber, 20, 20, 20)
                        )
                        .addGroup(filterLayout.createSequentialGroup()
                                        .addComponent(btnSetFilter)
                                        .addGap(20)
                                        .addComponent(btnResetFilter)
                        )
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
