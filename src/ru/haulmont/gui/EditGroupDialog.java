package ru.haulmont.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nikita on 12/15/14.
 */
public class EditGroupDialog extends JDialog {
    private JButton btnOk;
    private JButton btnCancel;
    private GroupLayout layout;
    private JLabel lblGroupNumber;
    private JLabel lblFaculty;
    private JFormattedTextField ftfGroupNumber;
    private JFormattedTextField ftfFaculty;

    public EditGroupDialog(JFrame owner, String title) {
        super(owner, title, true);
        btnOk = new JButton("Ок");
        btnCancel = new JButton("Отмена");
        lblGroupNumber = new JLabel("Номер группы");
        lblFaculty = new JLabel("Факультет");
        ftfGroupNumber = new JFormattedTextField();
        ftfFaculty = new JFormattedTextField();
        layout = new GroupLayout(getContentPane());

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
                        .addComponent(btnOk)
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
                        .addComponent(btnOk)
                        .addComponent(btnCancel)
                )
        );
        pack();
    }
}
