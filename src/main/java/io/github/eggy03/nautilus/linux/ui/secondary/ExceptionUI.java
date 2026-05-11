/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.secondary;

import org.jspecify.annotations.NonNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class ExceptionUI extends JFrame {

    public ExceptionUI(@NonNull String errorName, @NonNull String errorMessage) {
        super("Crash Report Engine");
        initialize(errorName, errorMessage);
    }

    private void initialize(@NonNull String errorName, @NonNull String errorMessage) {

        setIconImage(Toolkit.getDefaultToolkit().getImage(ExceptionUI.class.getResource("/icons/icon_main.png")));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(490, 190);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(2, 1, 0, 0));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new TitledBorder(null, errorName, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.setLayout(new BorderLayout(0, 0));

        JTextArea exceptionArea = new JTextArea();
        exceptionArea.setLineWrap(true);
        exceptionArea.setEditable(false);
        exceptionArea.setText(errorMessage);

        JButton copyLog = new JButton("Copy Log");
        copyLog.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(exceptionArea.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        });

        contentPane.add(new JScrollPane(exceptionArea), BorderLayout.CENTER);
        contentPane.add(copyLog, BorderLayout.SOUTH);
        setContentPane(contentPane);
    }
}
