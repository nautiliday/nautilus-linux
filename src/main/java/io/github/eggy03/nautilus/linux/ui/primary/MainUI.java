/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.primary;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.linux.ui.primary.panels.BaseboardPanelUI;
import io.github.eggy03.nautilus.linux.ui.primary.panels.PhysicalMemoryPanelUI;
import io.github.eggy03.nautilus.linux.ui.primary.panels.ProcessorPanelUI;
import io.github.eggy03.nautilus.linux.ui.secondary.AboutUI;
import org.jspecify.annotations.NonNull;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public class MainUI extends JFrame {

    @NonNull
    public MainUI initUI() {

        setTitle("Nautilus");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 650);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/icons/icon_main.png")));

        setLayout(new BorderLayout(0, 0));

        return this;
    }

    @NonNull
    public MainUI initComponents() {

        add(createMenu(), BorderLayout.NORTH);
        add(createTabbedPane(), BorderLayout.CENTER);

        return this;
    }

    @NonNull
    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setIcon(new FlatSVGIcon(MainUI.class.getResource("/icons/general_icons/about.svg")));
        aboutMenuItem.addActionListener(event -> new AboutUI().setVisible(true));

        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        return menuBar;

    }

    @NonNull
    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

        tabbedPane.addTab(
                "CPU",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/CPU.svg")),
                new ProcessorPanelUI().initUI().initComponents().setWorkers(),
                null
        );

        tabbedPane.addTab(
                "Memory",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/RAM.svg")),
                new PhysicalMemoryPanelUI().initUI().initComponents().setWorkers(),
                null
        );

        tabbedPane.addTab(
                "Mainboard",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/MainBoard.svg")),
                new BaseboardPanelUI().initUI().initComponents().setWorkers(),
                null
        );

        return tabbedPane;
    }

}
