/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.utility;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ColorFilter {

    NONE(""),
    OLIVE_GREEN("#78A75A"),
    PINK("#F3687C"),
    LIGHT_YELLOW("#FFCC4D"),
    DARK_YELLOW("#E0B84D");

    private final String hexValue;
}
