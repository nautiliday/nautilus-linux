/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.themes;

import com.formdev.flatlaf.FlatLightLaf;

public class LightGreenTheme extends FlatLightLaf {

	public static boolean setup() {
		return setup(new LightGreenTheme());
	}
	
	@Override
    public String getName() {
        return "LightGreenTheme";
    }

}
