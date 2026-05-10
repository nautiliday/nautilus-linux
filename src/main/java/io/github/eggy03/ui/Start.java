/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.ui;

import com.formdev.flatlaf.FlatLaf;
import io.github.eggy03.theme.manager.ThemeManager;
import io.github.eggy03.ui.common.constant.OSConstants;
import io.github.eggy03.ui.common.themes.StandardDarkTheme;
import io.github.eggy03.ui.common.ui.ExceptionUI;
import io.github.eggy03.ui.common.utilities.UIManagerConfigurations;
import io.github.eggy03.ui.linux.LinuxUI;
import lombok.extern.slf4j.Slf4j;

import java.awt.EventQueue;
import java.util.Objects;

@Slf4j
public class Start {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        ThemeManager themeManager = new ThemeManager(Start.class);

        log.info("Detected OS: {}", OSConstants.getCurrentOS());
        FlatLaf.registerCustomDefaultsSource("themes"); // for maven build, this points towards src/main/resources/themes

        EventQueue.invokeLater(()-> {
            themeManager.loadAndApplyRegisteredThemeOrFallback(StandardDarkTheme.class.getName());
            themeManager.loadAndApplyColorFilter();

            UIManagerConfigurations.enableRoundComponents();
            UIManagerConfigurations.enableTabSeparators(true);
            
            launchUIBasedOnOS();
        });
    }

    private static void launchUIBasedOnOS() {
        if (Objects.requireNonNull(OSConstants.detectOs()) == OSConstants.LINUX) {
            new LinuxUI().setVisible(true);
        } else {
            new ExceptionUI("Unsupported OS", OSConstants.getCurrentOS() + " is not supported");
        }
    }
}
