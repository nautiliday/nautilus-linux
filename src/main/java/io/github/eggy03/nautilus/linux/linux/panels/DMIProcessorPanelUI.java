/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.linux.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.linux.linux.worker.DMICacheWorker;
import io.github.eggy03.nautilus.linux.linux.worker.DMIProcessorWorker;
import io.github.eggy03.nautilus.linux.linux.worker.SystemUUIDWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class DMIProcessorPanelUI extends JPanel {

	// hwid
	private JTextField systemIdTextField;

	// cpu
    private JComboBox<String> cpuIdComboBox;
	private JTextField coreTextField;
	private JTextField threadTextField;
	private JTextField currentClockTextField;
	private JTextField cpuVersionTextField;
	private JTextField cpuSignatureTextField;
	private JTextField familyTextField;
	private JTextField cpuManufacturerTextField;
	private JTextField cpuSerialTextField;
	private JTextField assetTagTextField;
	private JTextField partNumberTextField;
	private JTextField enabledCoresTextField;
	private JTextField upgradeTextField;
	private JTextField cpuStatusTextField;
	private JTextField cpuVoltageTextField;
	private JTextField socketTextField;
	private JTextField baseClockTextField;
	
	private JTextArea cpuCharsAndFlagsTextArea;
    private JTextArea cacheTextArea;
    
	
	public JPanel getPanel() {
		return this;
	}

	/**
	 * Create the panel.
	 */
	public DMIProcessorPanelUI() {
		
		setLayout(new BorderLayout(0, 0));
		
		//set UI
		add(createHardwareIdPanel(), BorderLayout.NORTH);
		add(createCpuPanel(), BorderLayout.CENTER);
        // execute workers
		setWorkers();

	}
	
	private JPanel createHardwareIdPanel() {
		
		JPanel systemIdPanel = new JPanel();
		
		systemIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "System UUID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		systemIdPanel.setLayout(new MigLayout("insets 0", "[grow][]", "[]"));

		systemIdTextField = new JTextField();
		systemIdTextField.setEditable(false);
		
		JButton copySystemId = new JButton();
		copySystemId.setIcon(new FlatSVGIcon(DMIProcessorPanelUI.class.getResource("/icons/general_icons/copy.svg")));
		copySystemId.addActionListener(copyAction -> {
			StringSelection textToCopy = new StringSelection(systemIdTextField.getText());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textToCopy, null);
		});
		
		systemIdPanel.add(systemIdTextField, "cell 0 0,grow");
		systemIdPanel.add(copySystemId, "cell 1 0,alignx center,aligny center");
		
		return systemIdPanel;

	}
	
	private JPanel createCpuPanel() {
		
		// create the main cpu panel
		JPanel cpuPanel = new JPanel();
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cpuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		// PRIMARY INFO PANEL
		JPanel primaryInfoPanel = new JPanel();
		primaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Primary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		primaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][]"));
		
		JLabel cpuIdLabel = new JLabel("ID");
		primaryInfoPanel.add(cpuIdLabel, "cell 0 0,alignx leading,aligny center");

        cpuIdComboBox = new JComboBox<>();
		primaryInfoPanel.add(cpuIdComboBox, "cell 1 0,growx,aligny center");
		
		JLabel cpuVersionLabel = new JLabel("Version");
		primaryInfoPanel.add(cpuVersionLabel, "cell 2 0,alignx leading,aligny center");
		
		cpuVersionTextField = new JTextField();
		cpuVersionTextField.setEditable(false);
		primaryInfoPanel.add(cpuVersionTextField, "cell 3 0 3 1,growx,aligny center");
		
		JLabel coreLabel = new JLabel("Cores");
		primaryInfoPanel.add(coreLabel, "cell 0 1,alignx leading,aligny center");
		
		coreTextField = new JTextField();
		coreTextField.setEditable(false);
		primaryInfoPanel.add(coreTextField, "cell 1 1,grow");
		
		JLabel threadLabel = new JLabel("Threads");
		primaryInfoPanel.add(threadLabel, "cell 2 1,alignx leading,aligny center");
		
		threadTextField = new JTextField();
		threadTextField.setEditable(false);
		primaryInfoPanel.add(threadTextField, "cell 3 1,grow");
		
		JLabel currentClockLabel = new JLabel("Current Clock");
		primaryInfoPanel.add(currentClockLabel, "cell 4 1,alignx leading,aligny center");
		
		currentClockTextField = new JTextField();
		currentClockTextField.setEditable(false);
		primaryInfoPanel.add(currentClockTextField, "cell 5 1,grow");
		
		JLabel cpuVoltageLabel = new JLabel("Voltage");
		primaryInfoPanel.add(cpuVoltageLabel, "cell 0 2,alignx leading,aligny center");
		
		cpuVoltageTextField = new JTextField();
		cpuVoltageTextField.setEditable(false);
		primaryInfoPanel.add(cpuVoltageTextField, "cell 1 2,grow");
		
		JLabel socketLabel = new JLabel("Socket");
		primaryInfoPanel.add(socketLabel, "cell 2 2,alignx leading,aligny center");
		
		socketTextField = new JTextField();
		socketTextField.setEditable(false);
		primaryInfoPanel.add(socketTextField, "cell 3 2,grow");
		
		JLabel baseClockLabel = new JLabel("Base Clock");
		primaryInfoPanel.add(baseClockLabel, "cell 4 2,alignx leading,aligny center");
		
		baseClockTextField = new JTextField();
		baseClockTextField.setEditable(false);
		primaryInfoPanel.add(baseClockTextField, "cell 5 2,grow");
		
		// SECONDARY INFO PANEL
		JPanel secondaryInfoPanel = new JPanel();
		secondaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Secondary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		secondaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][]"));
		
		JLabel cpuSignatureLabel = new JLabel("Signature");
		secondaryInfoPanel.add(cpuSignatureLabel, "cell 0 0,alignx leading,aligny center");
		
		cpuSignatureTextField = new JTextField();
		cpuSignatureTextField.setEditable(false);
		secondaryInfoPanel.add(cpuSignatureTextField, "cell 1 0,growx,aligny center");
		
		JLabel familyLabel = new JLabel("Family");
		secondaryInfoPanel.add(familyLabel, "cell 2 0,alignx leading,aligny center");
		
		familyTextField = new JTextField();
		familyTextField.setEditable(false);
		secondaryInfoPanel.add(familyTextField, "cell 3 0,growx,aligny center");
		
		JLabel cpuManufacturerLabel = new JLabel("Manufacturer");
		secondaryInfoPanel.add(cpuManufacturerLabel, "cell 4 0,alignx leading,aligny center");
		
		cpuManufacturerTextField = new JTextField();
		cpuManufacturerTextField.setEditable(false);
		secondaryInfoPanel.add(cpuManufacturerTextField, "cell 5 0,growx,aligny center");
		
		JLabel serialNumberLabel = new JLabel("Serial");
		secondaryInfoPanel.add(serialNumberLabel, "cell 0 1,alignx leading,aligny center");
		
		cpuSerialTextField = new JTextField();
		cpuSerialTextField.setEditable(false);
		secondaryInfoPanel.add(cpuSerialTextField, "cell 1 1,growx,aligny center");
		
		JLabel assetTagLabel = new JLabel("Asset Tag");
		secondaryInfoPanel.add(assetTagLabel, "cell 2 1,alignx leading,aligny center");
		
		assetTagTextField = new JTextField();
		assetTagTextField.setEditable(false);
		secondaryInfoPanel.add(assetTagTextField, "cell 3 1,growx,aligny center");
		
		JLabel partNumberLabel = new JLabel("Part Number");
		secondaryInfoPanel.add(partNumberLabel, "cell 4 1,alignx leading,aligny center");
		
		partNumberTextField = new JTextField();
		partNumberTextField.setEditable(false);
		secondaryInfoPanel.add(partNumberTextField, "cell 5 1,growx,aligny center");
		
		JLabel enabledCoresLabel = new JLabel("Enabled Cores");
		secondaryInfoPanel.add(enabledCoresLabel, "cell 0 2,alignx leading,aligny center");
		
		enabledCoresTextField = new JTextField();
		enabledCoresTextField.setEditable(false);
		secondaryInfoPanel.add(enabledCoresTextField, "cell 1 2,growx,aligny center");
		
		JLabel upgradeLabel = new JLabel("Upgrade");
		secondaryInfoPanel.add(upgradeLabel, "cell 2 2,alignx leading,aligny center");
		
		upgradeTextField = new JTextField();
		upgradeTextField.setEditable(false);
		secondaryInfoPanel.add(upgradeTextField, "cell 3 2,growx,aligny center");
		
		JLabel cpuStatusLabel = new JLabel("Status");
		secondaryInfoPanel.add(cpuStatusLabel, "cell 4 2,alignx leading,aligny center");
		
		cpuStatusTextField = new JTextField();
		cpuStatusTextField.setEditable(false);
		secondaryInfoPanel.add(cpuStatusTextField, "cell 5 2,growx,aligny center");
		
		// TERTIARY INFO PANEL
		JPanel tertiaryInfoPanel = new JPanel();
		tertiaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tertiary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tertiaryInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		// chars and flags panel
		JPanel cpuCharsAndFlagsPanel = new JPanel();
		cpuCharsAndFlagsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Characteristics and Flags", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cpuCharsAndFlagsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		cpuCharsAndFlagsTextArea = new JTextArea();
		cpuCharsAndFlagsTextArea.setEditable(false);
		cpuCharsAndFlagsPanel.add(new JScrollPane(cpuCharsAndFlagsTextArea));
		
		// cache panel
		JPanel cacheInfoPanel = new JPanel();
		cacheInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Cache Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cacheInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		cacheTextArea = new JTextArea();
		cacheTextArea.setEditable(false);
		cacheInfoPanel.add(new JScrollPane(cacheTextArea));
		
		tertiaryInfoPanel.add(cpuCharsAndFlagsPanel);
		tertiaryInfoPanel.add(cacheInfoPanel);
		
		// add sub panels to the main panel
		cpuPanel.add(new JScrollPane(primaryInfoPanel));
		cpuPanel.add(new JScrollPane(secondaryInfoPanel));
		cpuPanel.add(tertiaryInfoPanel);
		
		return cpuPanel;
	}

	private void setWorkers() {

		List<JTextField> textFields = List.of(coreTextField, threadTextField, currentClockTextField, cpuVersionTextField,
				cpuSignatureTextField, familyTextField, cpuManufacturerTextField, cpuSerialTextField,
				assetTagTextField, partNumberTextField, enabledCoresTextField, upgradeTextField,
				cpuStatusTextField, cpuVoltageTextField, socketTextField, baseClockTextField
		);

		new DMIProcessorWorker(cpuIdComboBox, textFields, cpuCharsAndFlagsTextArea).execute();
		new DMICacheWorker(cacheTextArea).execute();

		new SystemUUIDWorker(systemIdTextField).execute();

	}


}
