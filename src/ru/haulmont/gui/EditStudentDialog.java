package ru.haulmont.gui;

import ru.haulmont.daoclasses.entities.Student;
import ru.haulmont.util.DateFormat;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Данный класс предназначен для ввода данных о студенте.
 * В зависимости от операции данное диалоговое окно может служить,
 * как для добавления студента, так и редактирования уже
 * существующего студента.
 */
public class EditStudentDialog extends JDialog {
    public static final int BTN_OK = 0;
    public static final int BTN_CANCEL = 1;
    public static final int BTN_CLOSE = 3;

    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblPatronymic;
    private JLabel lblBirthday;
    private JLabel lblGroup;
    private JFormattedTextField ftfName;
    private JFormattedTextField ftfSurname;
    private JFormattedTextField ftfPatronymic;
    private JFormattedTextField ftfBirthday;
    private JFormattedTextField ftfGroups;
    private GroupLayout layout;
    private JButton btnOK;
    private JButton btnCancel;
    private int choosedButton;
    private Student student;
    private ActionListener editOkListener;
    private ActionListener editCancelListener;
    private ActionListener addOkListener;
    private ActionListener addCancelListener;
    private SimpleDateFormat simpleDateFormat;

    public EditStudentDialog(JFrame owner) {
        super(owner, true);
        lblName = new JLabel("Имя");
        lblSurname = new JLabel("Фамилия");
        lblPatronymic = new JLabel("Отчество");
        lblBirthday = new JLabel("Дата рождения");
        lblGroup = new JLabel("Номер группы");
        ftfName = new JFormattedTextField();
        ftfSurname = new JFormattedTextField();
        ftfPatronymic = new JFormattedTextField();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            ftfBirthday = new JFormattedTextField(new MaskFormatter("##.##.####"));
            ftfGroups = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        btnOK = new JButton("Ок");
        btnCancel = new JButton("Отмена");
        layout = new GroupLayout(getContentPane());
        choosedButton = BTN_CLOSE;
        editOkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student = new Student();
                student.setName(ftfName.getText());
                student.setSurname(ftfSurname.getText());
                student.setPatronymic(ftfPatronymic.getText());
                Date date = null;
                try {
                    date = DateFormat.fromString(ftfBirthday.getText().trim(), simpleDateFormat);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                student.setBirthday(date);
                student.setGroupNumber(Integer.parseInt(ftfGroups.getText().trim()));
                choosedButton = BTN_OK;
                setVisible(false);
            }
        };
        editCancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosedButton = BTN_CANCEL;
                setVisible(false);
            }
        };
        addOkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student = new Student();
                student.setName(ftfName.getText().trim());
                student.setSurname(ftfSurname.getText().trim());
                student.setPatronymic(ftfPatronymic.getText().trim());
                Date date = null;
                try {
                    date = DateFormat.fromString(ftfBirthday.getText().trim(), simpleDateFormat);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                student.setBirthday(date);
                student.setGroupNumber(Integer.parseInt(ftfGroups.getText().trim()));
                choosedButton = BTN_OK;
                setVisible(false);
            }
        };
        addCancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosedButton = BTN_CANCEL;
                setVisible(false);
            }
        };

        setPreferredSize(new Dimension(500, 300));
        setResizable(false);

        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                        .addComponent(lblName)
                                        .addComponent(ftfName)
                                        .addComponent(lblSurname)
                                        .addComponent(ftfSurname)
                                        .addComponent(lblPatronymic)
                                        .addComponent(ftfPatronymic)
                        )
                        .addGroup(layout.createParallelGroup()
                                        .addComponent(lblBirthday)
                                        .addComponent(ftfBirthday)
                                        .addComponent(lblGroup)
                                        .addComponent(ftfGroups)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOK)
                                .addComponent(btnCancel)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSurname, 20, 20, 20)
                                        .addComponent(ftfSurname, 20, 20, 20)
                                        .addComponent(lblName, 20, 20, 20)
                                        .addComponent(ftfName, 20, 20, 20)
                                        .addComponent(lblPatronymic, 20, 20, 20)
                                        .addComponent(ftfPatronymic, 20, 20, 20)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblBirthday, 20, 20, 20)
                                        .addComponent(ftfBirthday, 20, 20, 20)
                                        .addComponent(lblGroup, 20, 20, 20)
                                        .addComponent(ftfGroups, 20, 20, 20)
                                )
                        )
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(btnOK)
                                .addComponent(btnCancel)
                        )
        );
        pack();
    }

    /**
     * Отобразить диалоговое окно добавления студента.
     * @param title заголовок окна ввода
     * @return код нажатой кнопки
     */
    public int showAddingDialog(String title) {
        choosedButton = BTN_CLOSE;
        setTitle(title);
        btnOK.addActionListener(addOkListener);
        btnCancel.addActionListener(addCancelListener);
        setVisible(true);

        return choosedButton;
    }

    /**
     * Отобразить диалоговое окно редактирования студента.
     * @param title
     * @param editingStudent
     * @return
     */
    public int showEditingDialog(String title, Student editingStudent) {
        choosedButton = BTN_CLOSE;
        setTitle(title);
        ftfName.setText(editingStudent.getName());
        ftfSurname.setText(editingStudent.getSurname());
        ftfPatronymic.setText(editingStudent.getPatronymic());
        ftfBirthday.setText(simpleDateFormat.format(editingStudent.getBirthday()));
        ftfGroups.setText(Integer.toString(editingStudent.getGroupNumber()));
        btnOK.addActionListener(editOkListener);
        btnCancel.addActionListener(editCancelListener);
        setVisible(true);
        //Если были произведены изменения и нажата кнопка Ок
        if (choosedButton == BTN_OK) {
            editingStudent.setName(student.getName());
            editingStudent.setSurname(student.getSurname());
            editingStudent.setPatronymic(student.getPatronymic());
            editingStudent.setBirthday(student.getBirthday());
            editingStudent.setGroupNumber(student.getGroupNumber());
        }

        return choosedButton;
    }

    /**
     * Метод возвращает добавленного студента.
     * Необходимо вызвать этот метод после того, как
     * данные были введены и нажата кнопка Ок.
     * @return возвращает сущность инкапсулирующую данные о студенте.
     */
    public Student getAddingStudent() {
        return student;
    }
}
