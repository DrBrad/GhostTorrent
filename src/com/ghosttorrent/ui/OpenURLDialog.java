package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.inter.Dialog;
import com.ghosttorrent.libs.ui.utils.Bundle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenURLDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        frame.setSize(480, 160);
        setContentView(R.layout.open_url_dialog);

        frame.setTitle("Open URL");
        frame.setMinimumSize(new Dimension(480, 160));
        frame.setMaximumSize(new Dimension(480, 160));


        getRoot().setLayout(new BoxLayout(getRoot(), BoxLayout.Y_AXIS));


        JLabel title = (JLabel) findViewById(R.id.dialog_title);
        title.setBorder(new EmptyBorder(5, 5, 0, 5));
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        //findViewById(R.id.dialog_url_parent).setMaximumSize(new Dimension(480, 60));
        GridBagLayout urlLayout = (GridBagLayout) findViewById(R.id.dialog_url_parent).getLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        urlLayout.setConstraints(findViewById(R.id.dialog_url_text), constraints);

        JTextField field = (JTextField) findViewById(R.id.dialog_url);
        field.setPreferredSize(new Dimension(300, 36));
        constraints.insets = new Insets(0, 0, 0, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridheight = 2;
        urlLayout.setConstraints(field, constraints);

        ((FlowLayout) findViewById(R.id.dialog_buttons).getLayout()).setAlignment(FlowLayout.RIGHT);

        ((JButton) findViewById(R.id.dialog_cancel)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                close();
            }
        });

        ((JButton) findViewById(R.id.dialog_open)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Bundle bundle = new Bundle();
                bundle.put("url", field.getText().toString().trim());
                closeWithIntent(bundle);
            }
        });
    }
}
