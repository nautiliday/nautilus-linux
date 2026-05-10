/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux;

import com.formdev.flatlaf.FlatLaf;
import io.github.eggy03.nautilus.linux.ui.primary.MainUI;
import io.github.eggy03.nautilus.linux.ui.secondary.ExceptionUI;
import io.github.eggy03.nautilus.linux.ui.themes.StandardDarkTheme;
import io.github.eggy03.nautilus.linux.utility.OSDetection;
import io.github.eggy03.nautilus.linux.utility.UIManagerConfigurations;
import io.github.eggy03.theme.manager.ThemeManager;
import lombok.extern.slf4j.Slf4j;

import java.awt.EventQueue;

@Slf4j
public class Start {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        ThemeManager themeManager = new ThemeManager(Start.class);

        log.info("Detected OS: {}", OSDetection.getCurrentOS());
        FlatLaf.registerCustomDefaultsSource("themes"); // for maven build, this points towards src/main/resources/themes

        EventQueue.invokeLater(() -> {
            themeManager.loadAndApplyRegisteredThemeOrFallback(StandardDarkTheme.class.getName());
            themeManager.loadAndApplyColorFilter();

            UIManagerConfigurations.enableRoundComponents();
            UIManagerConfigurations.enableTabSeparators(true);

            launchUIBasedOnOS();
        });
    }

    private static void launchUIBasedOnOS() {
        if (OSDetection.isLinux()) {
            new MainUI().setVisible(true);
        } else {
            new ExceptionUI("Unsupported OS", OSDetection.getCurrentOS() + " is not supported");
        }
    }
}
