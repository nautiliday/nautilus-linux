/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.common.utilities;

import lombok.experimental.UtilityClass;

import javax.swing.UIManager;
import java.awt.Insets;

// custom UI manager configurations that allow slight changes to the LAF
// Applies on all LAFs
// Invoke this class only AFTER you've used UIManager.setLookAndFeel() and BEFORE you have laid out your components
@UtilityClass
public class UIManagerConfigurations {

	public static void enableRoundComponents() {
		UIManager.put( "Button.arc", 999 );
		UIManager.put( "Component.arc", 999 );
		UIManager.put( "ProgressBar.arc", 999 );
		UIManager.put( "TextComponent.arc", 999 );
		
		UIManager.put( "ScrollBar.thumbArc", 999 );
		UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
		
		UIManager.put( "ScrollBar.trackArc", 999 );
		UIManager.put( "ScrollBar.thumbArc", 999 );
		UIManager.put( "ScrollBar.trackInsets", new Insets( 2, 4, 2, 4 ) );
		UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
	}
	
	public static void enableTabSeparators(boolean value) {
		UIManager.put( "TabbedPane.showTabSeparators", value );
		UIManager.put( "TabbedPane.tabSeparatorsFullHeight", value );
	}
}
