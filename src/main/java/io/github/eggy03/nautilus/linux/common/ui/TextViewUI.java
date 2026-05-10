/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.ui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextViewUI extends JFrame {

	
	public TextViewUI(@Nullable String windowTitle, @Nullable String contents) {
		buildUI(windowTitle, contents);
	}

	public TextViewUI(@Nullable String windowTitle, @NotNull InputStream contents) {
		try {
			String content = IOUtils.toString(contents, StandardCharsets.UTF_8);
			buildUI(windowTitle, content);
		} catch (IOException e) {
			new ExceptionUI("Resource Not Found", e.getMessage()).setVisible(true);
			log.error("Resource could not be opened", e);
		}
	}

	private void buildUI(@Nullable String windowTitle, @Nullable String contents) {

		setTitle(windowTitle);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TextViewUI.class.getResource("/icons/icon_main.png")));

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));

		JEditorPane contentEditorPane = new JEditorPane();
		contentEditorPane.setEditable(false);
		contentEditorPane.setContentType("text/plain");
		contentEditorPane.setText(contents);
		
		contentPane.add(new JScrollPane(contentEditorPane));

		setContentPane(contentPane);
	}

}
