/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.utility;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

@UtilityClass
public class OSDetection {

    public static boolean isLinux() {
        return SystemUtils.IS_OS_LINUX;
    }

    public static String getCurrentOS() {
        return System.getProperty("os.name");
    }

}
