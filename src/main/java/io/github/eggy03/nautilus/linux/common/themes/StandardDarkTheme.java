/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.themes;

import com.formdev.flatlaf.FlatDarkLaf;

public class StandardDarkTheme extends FlatDarkLaf {

	public static final String NAME = "StandardDarkTheme";

	public static boolean setup() {
		return setup( new StandardDarkTheme() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, StandardDarkTheme.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
	
}
