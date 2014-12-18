package ru.haulmont.gui;

import ru.haulmont.daoclasses.entities.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikita on 12/15/14.
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
    private JFormattedTextField ftfFaculty;
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
        ftfGroupNumber = new JFormattedTextField();
        ftfFaculty = new JFormattedTextField();
        layout = new GroupLayout(getContentPane());
        choosedButton = BTN_CLOSE;
        addOkListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group = new Group();
                group.setFaculty(ftfFaculty.getText().trim());
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
                group.setFaculty(ftfFaculty.getText().trim());
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
                .addComponent(ftfFaculty)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblGroupNumber, 20, 20, 20)
                .addComponent(ftfGroupNumber, 20, 20, 20)
                .addComponent(lblFaculty, 20, 20, 20)
                .addComponent(ftfFaculty, 20, 20, 20)
                .addGap(50)
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnOK)
                        .addComponent(btnCancel)
                )
        );
        pack();
    }

    public int showAddingDialog(String title) {
        choosedButton = BTN_CLOSE;
        setTitle(title);
        btnOK.addActionListener(addOkListener);
        btnCancel.addActionListener(addCancelListener);
        setVisible(true);

        return choosedButton;
    }

    public int showEditingDialog(String title, Group editingGroup) {
        group = editingGroup;
        choosedButton = BTN_CLOSE;
        ftfGroupNumber.setText(Integer.toString(editingGroup.getGroupNumber()));
        ftfFaculty.setText(editingGroup.getFaculty());
        setTitle(title);
        btnOK.addActionListener(editOkListener);
        btnCancel.addActionListener(editCancelListener);
        setVisible(true);

        return choosedButton;
    }

    public Group getAddingGroup() {
        return group;
    }
}
