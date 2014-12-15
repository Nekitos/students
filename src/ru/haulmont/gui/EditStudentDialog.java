package ru.haulmont.gui;

import ru.haulmont.entities.Student;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nikita on 12/14/14.
 */
public class EditStudentDialog extends JDialog {
    public static final int BTN_OK = 0;
    public static final int BTN_CANCEL = 1;
    public static final int CLOSE_WINDOW = 2;

    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblPatronymic;
    private JLabel lblBirthday;
    private JLabel lblGroup;
    private JFormattedTextField ftfName;
    private JFormattedTextField ftfSurname;
    private JFormattedTextField ftfPatronymic;
    private JFormattedTextField ftfBirthday;
    private JComboBox<String> cbGroups;
    private GroupLayout layout;
    private JButton btnOK;
    private JButton btnCancel;
    private int choosedButton;
    private Student student;

    public EditStudentDialog(JFrame owner, String title) {
        super(owner, title, true);
        lblName = new JLabel("Имя");
        lblSurname = new JLabel("Фамилия");
        lblPatronymic = new JLabel("Отчество");
        lblBirthday = new JLabel("Дата рождения");
        lblGroup = new JLabel("Номер группы");
        ftfName = new JFormattedTextField();
        ftfSurname = new JFormattedTextField();
        ftfPatronymic = new JFormattedTextField();
        ftfBirthday = new JFormattedTextField();
        cbGroups = new JComboBox<String>();
        btnOK = new JButton("Ок");
        btnCancel = new JButton("Отмена");
        layout = new GroupLayout(getContentPane());

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
                                        .addComponent(cbGroups)
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
                                        .addComponent(cbGroups, 20, 20, 20)
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
}
