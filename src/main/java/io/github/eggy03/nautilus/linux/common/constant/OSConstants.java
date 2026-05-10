/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.constant;

import org.apache.commons.lang3.SystemUtils;

public enum OSConstants {

    WINDOWS,
    MAC,
    LINUX,
    UNKNOWN;

    private static boolean isWindows() {

        if(SystemUtils.IS_OS_WINDOWS) {
            return SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8 ||
                    SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11;
        }
        return false;
    }

    private static boolean isLinux() {
        return SystemUtils.IS_OS_LINUX ||
                SystemUtils.IS_OS_OPEN_BSD || SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_NET_BSD ||
                SystemUtils.IS_OS_UNIX; // i have no clue if BSD/UNIX is supported by dmi-decode
    }

    private static boolean isMac() {
        return SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX; // fine tune this later when MAC support is implemented
    }

    public static OSConstants detectOs() {
        if(isWindows()) {
            return WINDOWS;
        } else if (isLinux()) {
            return LINUX;
        } else if (isMac()) {
            return MAC;
        } else {
            return UNKNOWN;
        }
    }

    public static String getCurrentOS() {
        return System.getProperty("os.name");
    }

}
