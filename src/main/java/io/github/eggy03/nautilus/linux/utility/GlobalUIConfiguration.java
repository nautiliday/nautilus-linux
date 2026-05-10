/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.utility;

import com.formdev.flatlaf.FlatLaf;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Insets;

/**
 * Configures global Swing UI and Look-and-Feel (LAF) settings
 *
 * <p>
 * This class provides helpers for:
 * </p>
 *
 * <ul>
 *     <li>Applying the system look-and-feel</li>
 *     <li>Applying FlatLaf themes</li>
 *     <li>Configuring rounded UI components</li>
 *     <li>Configuring tab separator behavior</li>
 * </ul>
 *
 * <p>
 * All configuration changes are applied globally through
 * {@link UIManager}.
 * </p>
 *
 * <p>
 * If a look-and-feel cannot be applied, the failure is logged
 * and execution continues using the current Swing defaults.
 * </p>
 *
 */
@Slf4j
public class GlobalUIConfiguration {

    /**
     * Creates a new UI configuration using the host operating system's
     * default Swing look-and-feel.
     *
     * <p>
     * The configured LAF is retrieved using:
     * </p>
     *
     * <pre>
     * UIManager.getSystemLookAndFeelClassName()
     * </pre>
     *
     * <p>
     * If the system look-and-feel cannot be loaded or applied,
     * the failure is logged.
     * </p>
     */
    public @NonNull GlobalUIConfiguration applySystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            log.error("Could not set system LAF: {}", UIManager.getSystemLookAndFeelClassName(), e);
        }

        return this;
    }

    /**
     * Applies a custom Swing Look-and-Feel (LAF) using the provided
     * fully-qualified look-and-feel class name.
     *
     * <p>
     * The specified class must be available on the application's
     * classpath and must extend a valid Swing Look-and-Feel
     * implementation.
     * </p>
     *
     * <p>
     * If the look-and-feel cannot be loaded or applied,
     * the failure is logged and the current UI configuration
     * remains unchanged.
     * </p>
     *
     * @param themeClassName the fully-qualified class name of the
     *                       Swing Look-and-Feel implementation
     * @return the current instance for chaining
     */
    public @NonNull GlobalUIConfiguration applyCustomLookAndFeel(@NonNull String themeClassName) {
        try {
            UIManager.setLookAndFeel(themeClassName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            log.error("Could not set custom LAF: {}", themeClassName, e);
        }

        return this;
    }

    /**
     * Creates a new UI configuration using a FlatLaf-based theme.
     *
     * <p>
     * Custom FlatLaf defaults are resolved from the {@code themes}
     * resource directory.
     * </p>
     *
     * <p>
     * Example: For Maven
     * </p>
     *
     * <pre>
     * src/main/resources/themes/MyTheme.properties
     * </pre>
     *
     * <p>
     * If the specified look-and-feel cannot be applied,
     * the failure is logged.
     * </p>
     *
     * @param themeClassName        the fully-qualified FlatLaf
     *                              theme class name
     * @param themePropertyLocation the location of the theme properties
     *                              resource directory
     */
    public @NonNull GlobalUIConfiguration applyFlatLafLookAndFeel(@NonNull String themeClassName, @NonNull String themePropertyLocation) {
        FlatLaf.registerCustomDefaultsSource(themePropertyLocation); // for maven build, this points towards src/main/resources/themes

        try {
            UIManager.setLookAndFeel(themeClassName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            log.error("Could not set FlatLaf LAF: {}", themeClassName, e);
        }

        return this;
    }

    /**
     * Enables rounded rendering for several Swing components.
     *
     * <p>
     * This includes:
     * </p>
     *
     * <ul>
     *     <li>Buttons</li>
     *     <li>Text components</li>
     *     <li>Progress bars</li>
     *     <li>Scrollbars</li>
     * </ul>
     *
     * <p>
     * Scrollbar thumb and track insets are also configured
     * to improve spacing and appearance.
     * </p>
     *
     * @return the current instance for chaining
     */
    public @NonNull GlobalUIConfiguration enableRoundComponents() {
        UIManager.put("Button.arc", 999);
        UIManager.put("Component.arc", 999);
        UIManager.put("ProgressBar.arc", 999);
        UIManager.put("TextComponent.arc", 999);

        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));

        UIManager.put("ScrollBar.trackArc", 999);
        UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));

        return this;
    }

    /**
     * Enables or disables tab separators for Swing tabbed panes.
     *
     * <p>
     * This affects:
     * </p>
     *
     * <ul>
     *     <li>Visibility of tab separators</li>
     *     <li>Whether separators span the full tab height</li>
     * </ul>
     *
     * @param value {@code true} to enable separators,
     *              {@code false} to disable them
     */
    public @NonNull GlobalUIConfiguration enableTabSeparators(boolean value) {
        UIManager.put("TabbedPane.showTabSeparators", value);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", value);

        return this;

    }
}
