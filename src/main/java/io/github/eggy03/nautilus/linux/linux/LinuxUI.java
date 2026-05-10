/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.linux;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import io.github.eggy03.nautilus.linux.common.ui.AboutUI;
import io.github.eggy03.nautilus.linux.common.ui.ThemeManagerUI;
import io.github.eggy03.nautilus.linux.linux.panels.DMIBaseboardPanelUI;
import io.github.eggy03.nautilus.linux.linux.panels.DMIPhysicalMemoryPanelUI;
import io.github.eggy03.nautilus.linux.linux.panels.DMIProcessorPanelUI;

public class LinuxUI extends JFrame {

	/**
	 * Create the frame.
	 */
	public LinuxUI() {

		setTitle("Nautilus");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 650);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LinuxUI.class.getResource("/icons/icon_main.png")));
        
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(createMenu(), BorderLayout.NORTH);
		contentPane.add(createTabbedPane(), BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}
	
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setIcon(new FlatSVGIcon(LinuxUI.class.getResource("/icons/general_icons/about.svg")));
		aboutMenuItem.addActionListener(event -> new AboutUI().setVisible(true));
		helpMenu.add(aboutMenuItem);
		
		JMenu appearanceMenu = new JMenu("Appearance");
		menuBar.add(appearanceMenu);

		JMenuItem themeMenuItem = new JMenuItem("Theme");
		themeMenuItem.setIcon(new FlatSVGIcon(LinuxUI.class.getResource("/icons/general_icons/theme.svg")));
		themeMenuItem.addActionListener(event -> new ThemeManagerUI().setVisible(true));
		appearanceMenu.add(themeMenuItem);
		
		return menuBar;
		
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		
		tabbedPane.addTab("CPU", new FlatSVGIcon(LinuxUI.class.getResource("/icons/tab_icons_material_green/CPU.svg")), new DMIProcessorPanelUI().getPanel(), null);
		tabbedPane.addTab("Memory", new FlatSVGIcon(LinuxUI.class.getResource("/icons/tab_icons_material_green/RAM.svg")), new DMIPhysicalMemoryPanelUI().getPanel(), null);
		tabbedPane.addTab("Mainboard", new FlatSVGIcon(LinuxUI.class.getResource("/icons/tab_icons_material_green/MainBoard.svg")), new DMIBaseboardPanelUI().getPanel(), null);
		
		return tabbedPane;
	}

}
