package ru.haulmont.gui;

import ru.haulmont.daoclasses.entities.Group;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * Данный класс предназначен для ввода данных о группе.
 * В зависимости от операции данное диалоговое окно может служить,
 * как для добавления группы, так и редактирования уже
 * существующей группы.
 */
public class EditGroupDialog extends JDialog {
    public static final int BTN_OK = 0;
    public static final int BTN_CANCEL = 1;
    public static final int BTN_CLOSE = 3;

    private JButton btnOK;
    private JButton btnCancel;
    private GroupLayout layout;
    private JLabel lblGroupNumber;
    private JLabel lblFaculty;
    private JFormattedTextField ftfGroupNumber;
    private JTextField tfFaculty;
    private int choosedButton;
    private Group group;
    private ActionListener addOkListener;
    private ActionListener addCancelListener;
    private ActionListener editOkListener;
    private ActionListener editCancelListener;

    public EditGroupDialog(JFrame owner) {
        super(owner, true);
        btnOK = new JButton("Ок");
        btnCancel = new JButton("Отмена");
        lblGroupNumber = new JLabel("Номер группы");
        lblFaculty = new JLabel("Факультет");
        //Формат для ввода группы
        try {
            ftfGroupNumber = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tfFaculty = new JTextField();
        layout = new GroupLayout(getContentPane());
        //По умолчанию нажата кнопка закрыть
        choosedButton = BTN_CLOSE;
        addOkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group = new Group();
                group.setFaculty(tfFaculty.getText().trim());
                Integer groupNum = Integer.parseInt(ftfGroupNumber.getText().trim());
                group.setGroupNumber(groupNum);
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
        editCancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosedButton = BTN_CANCEL;
                setVisible(false);
            }
        };
        editOkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group.setFaculty(tfFaculty.getText().trim());
                Integer groupNum = Integer.parseInt(ftfGroupNumber.getText().trim());
                group.setGroupNumber(groupNum);
                choosedButton = BTN_OK;
                setVisible(false);
            }
        };

        setPreferredSize(new Dimension(300, 200));
        setLayout(layout);
        setResizable(false);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblGroupNumber)
                .addComponent(ftfGroupNumber)
                .addComponent(lblFaculty)
                .addComponent(tfFaculty)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblGroupNumber, 20, 20, 20)
                .addComponent(ftfGroupNumber, 20, 20, 20)
                .addComponent(lblFaculty, 20, 20, 20)
                .addComponent(tfFaculty, 20, 20, 20)
                .addGap(50)
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                )
        );
        pack();
    }

    /**
     * Метод отображает диалоговое окно добавления группы
     * @param title заголовок окна
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
     * Метод отображает диалоговое окно редактирования группы
     * @param title заголовок окна
     * @param editingGroup группа подлежащая изменению
     * @return код нажатой кнопки
     */
    public int showEditingDialog(String title, Group editingGroup) {
        group = editingGroup;
        choosedButton = BTN_CLOSE;
        ftfGroupNumber.setText(Integer.toString(editingGroup.getGroupNumber()));
        tfFaculty.setText(editingGroup.getFaculty());
        setTitle(title);
        btnOK.addActionListener(editOkListener);
        btnCancel.addActionListener(editCancelListener);
        setVisible(true);

        return choosedButton;
    }

    /**
     * Метод возвращает добавленную группу.
     * Необходимо вызвать этот метод после того, как данные были введены
     * и нажата кнопка Ок.
     * @return ссылку на сущность инкапсулирующую данные о группе
     */
    public Group getAddingGroup() {
        return group;
    }
}
