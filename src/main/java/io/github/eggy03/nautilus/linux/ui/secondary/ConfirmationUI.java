/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.secondary;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

@Getter
public class ConfirmationUI extends JFrame {

    //allows for implementation of your own action listeners
    private JButton btnYes;
    private JButton btnNo;

    public ConfirmationUI(@NonNull String heading, @NonNull String question) {
        super(heading);
        initialize(question);
    }

    private void initialize(@NonNull String question) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmationUI.class.getResource("/icons/icon_main.png")));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 140);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Select an option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 264, 79);
        getContentPane().add(panel);
        panel.setLayout(null);

        btnYes = new JButton("Yes");
        btnYes.setBounds(68, 51, 54, 17);
        panel.add(btnYes);

        btnNo = new JButton("No");
        btnNo.setBounds(152, 51, 54, 17);
        panel.add(btnNo);

        JLabel questionLabel = new JLabel(question);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBounds(10, 18, 244, 24);
        panel.add(questionLabel);
    }

}

