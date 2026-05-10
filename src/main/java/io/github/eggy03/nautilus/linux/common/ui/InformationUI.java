/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.jetbrains.annotations.Nullable;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import net.miginfocom.swing.MigLayout;

public class InformationUI extends JFrame {

	/**
	 * Create the frame.
	 */
	public InformationUI(@Nullable String infoHeading, @Nullable String infoText) {
		
		setTitle("Info");
		setResizable(false);
		setBounds(100, 100, 350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformationUI.class.getResource("/icons/icon_main.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), infoHeading, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new MigLayout("insets 0", "[grow]", "[grow][grow]"));
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setIcon(new FlatSVGIcon(InformationUI.class.getResource("/icons/general_icons/notification_large.svg")));
		panel.add(iconLabel, "cell 0 0,grow");
		
		JLabel infoLabel = new JLabel();
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setText(infoText);
		panel.add(infoLabel, "cell 0 1,grow");
		
		setContentPane(panel);

	}

}
