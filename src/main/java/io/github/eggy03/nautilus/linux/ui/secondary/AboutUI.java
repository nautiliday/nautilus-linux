/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.secondary;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.linux.constant.Version;
import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class AboutUI extends JFrame {

    @NonNull
    private static final String VERSION_AND_AUTHOR =
            "Version: " + Version.SEMVER + Version.SUFFIX
                    + System.lineSeparator()
                    + "Developer: " + Version.DEVELOPER
                    + System.lineSeparator()
                    + "License: " + Version.LICENSE;

    @NonNull
    private static final String ATTRIBUTION = """	
            <b>Open-Source Licenses</b><br>
            This application includes the following third-party open-source libraries and frameworks:
            <ul>
              <li>dmidecode4j</li>
              <li>Apache Commons Lang</li>
              <li>Apache Commons IO</li>
              <li>FlatLaf</li>
              <li>JSVG</li>
              <li>SLF4J</li>
              <li>Tinylog</li>
              <li>Project Lombok</li>
              <li>MigLayout Layout Manager for Swing</li>
            </ul>
            Full license texts are included with this distribution and are also
            available online on the project's GitHub page.<br><br>
            
            <b>Source Code</b><br>
            The source code for this application is available on GitHub.<br><br>
            
            <b>Icons</b><br>
            This application uses icons from Google Material Design Icons,
            licensed under the Apache License, Version 2.0.<br><br>
            """;

    public AboutUI() {
        setTitle("About Nautilus");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(AboutUI.class.getResource("/icons/icon_main.png")));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new MigLayout("insets 0", "[grow]", "[][][grow]"));

        contentPane.add(createMenu(), "cell 0 0, growx");
        contentPane.add(createVersionPanel(), "cell 0 1, growx");
        contentPane.add(createAttributionPanel(), "cell 0 2, growx");

        setContentPane(contentPane);
    }

    @NonNull
    private JPanel createVersionPanel() {

        JPanel versionPanel = new JPanel();
        versionPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Version Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        versionPanel.setLayout(new MigLayout("insets 0", "[grow][]", "[]"));

        JTextArea versionTextArea = new JTextArea();
        versionTextArea.setText(VERSION_AND_AUTHOR);
        versionTextArea.setEditable(false);
        versionPanel.add(versionTextArea, "cell 0 0,growx");

        JLabel appLogoLabel = new JLabel("");
        appLogoLabel.setIcon(new FlatSVGIcon(AboutUI.class.getResource("/icons/icon_main.svg")));
        versionPanel.add(appLogoLabel, "cell 1 0,alignx center");

        return versionPanel;
    }

    @NonNull
    private JPanel createAttributionPanel() {

        JPanel attributionPanel = new JPanel();
        attributionPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Attribution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        attributionPanel.setLayout(new GridLayout(1, 1, 0, 0));

        JEditorPane attributionEditorPane = new JEditorPane();
        attributionEditorPane.setEditable(false);
        attributionEditorPane.setContentType("text/html");
        attributionEditorPane.setText(ATTRIBUTION);

        attributionPanel.add(new JScrollPane(attributionEditorPane));

        return attributionPanel;
    }

    @NonNull
    private JMenuBar createMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu attributionMenu = new JMenu("More Info");
        menuBar.add(attributionMenu);

        JMenuItem visitGithub = new JMenuItem("Visit Nautilus GitHub");
        visitGithub.addActionListener(action -> {
            try {
                Desktop.getDesktop().browse(URI.create(Version.GITHUB_PAGE));
            } catch (IOException | UnsupportedOperationException e) {
                new ExceptionUI("Could not open browser", e.getMessage());
            }
        });
        attributionMenu.add(visitGithub);

        JMenuItem privacyPolicy = new JMenuItem("Privacy Policy");
        privacyPolicy.addActionListener(action -> {
            InputStream stream = Objects.requireNonNull(AboutUI.class.getResourceAsStream("/PrivacyPolicy.txt"), "Privacy Policy Stream must not be null");
            new TextViewUI("Privacy Policy", stream).setVisible(true);
        });
        attributionMenu.add(privacyPolicy);

        JMenuItem applicationLicense = new JMenuItem("License");
        applicationLicense.addActionListener(action -> {
            InputStream stream = Objects.requireNonNull(AboutUI.class.getResourceAsStream("/ApplicationLicense.txt"), "Application License Stream must not be null");
            new TextViewUI("Application License", stream).setVisible(true);
        });
        attributionMenu.add(applicationLicense);

        JMenuItem openSourceLicenses = new JMenuItem("Open Source Licenses");
        openSourceLicenses.addActionListener(action -> {
            InputStream stream = Objects.requireNonNull(AboutUI.class.getResourceAsStream("/OpenSourceLicenses.txt"), "License Stream must not be null");
            new TextViewUI("Third Party Software Licenses", stream).setVisible(true);
        });
        attributionMenu.add(openSourceLicenses);

        return menuBar;
    }

}
