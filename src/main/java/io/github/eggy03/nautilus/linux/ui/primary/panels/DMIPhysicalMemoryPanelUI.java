/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.primary.panels;

import io.github.eggy03.nautilus.linux.worker.DMIPhysicalMemoryWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.util.List;

public class DMIPhysicalMemoryPanelUI extends JPanel {

    private JComboBox<String> memoryLocatorComboBox;
    private JTextField setTextField;
    private JTextField assetTagTextField;
    private JTextField serialNumberTextField;
    private JTextField partNumberTextField;
    private JTextField firmwareVersionTextField;
    private JTextField locatorTextField;
    private JTextField bankLocatorTextField;
    private JTextField formFactorTextField;
    private JTextField typeTextField;
    private JTextField typeDetailTextField;
    private JTextField rankLabelTextField;
    private JTextField sizeTextField;
    private JTextField dataWidthTextField;
    private JTextField totalWidthTextField;
    private JTextField volatileSizeTextField;
    private JTextField nonVolatileSizeTextField;
    private JTextField cacheSizeTextField;
    private JTextField logicalSizeTextField;
    private JTextField factorySpeedTextField;
    private JTextField configuredSpeedTextField;
    private JTextField minimumVoltageTextField;
    private JTextField maximumVoltageTextField;
    private JTextField configuredVoltageTextField;
    private JTextField manufacturerTextField;
    private JTextField moduleManufacturerIdTextField;
    private JTextField moduleProductIdTextField;
    private JTextField mscManufacturerIdTextField;
    private JTextField mscProductIdTextField;
    private JTextField memoryTechnologyTextField;
    private JTextField memoryOperatingModeCapabilityTextField;
    private JTextField errorHandleTextField;
    private JTextField arrayHandleTextField;

    /**
     * Create the panel.
     */
    public DMIPhysicalMemoryPanelUI() {
        setUI();
        setWorker();
    }

    public JPanel getPanel() {
        return this;
    }

    private void setUI() {

        setLayout(new GridLayout(1, 0, 0, 0));

        // add panel
        JPanel memoryPanel = new JPanel();
        memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        memoryPanel.setLayout(new GridLayout(2, 3, 0, 0));

        add(memoryPanel);

        // add sub-panels
        memoryPanel.add(createHandlePanel());
        memoryPanel.add(createCharacteristicsPanel());
        memoryPanel.add(createSizePanel());
        memoryPanel.add(createSpeedAndVoltagePanel());
        memoryPanel.add(createManufacturerPanel());
        memoryPanel.add(createExtraPanel());

    }

    private JScrollPane createHandlePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Handles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        JLabel memoryModuleNumberLabel = new JLabel("Memory #");
        panel.add(memoryModuleNumberLabel, "cell 0 0,alignx leading");

        memoryLocatorComboBox = new JComboBox<>();
        panel.add(memoryLocatorComboBox, "cell 1 0,growx");

        JLabel setLabel = new JLabel("Set");
        panel.add(setLabel, "cell 0 1,alignx leading");

        setTextField = new JTextField();
        setTextField.setEditable(false);
        panel.add(setTextField, "cell 1 1,growx");

        JLabel assetTagLabel = new JLabel("Asset Tag");
        panel.add(assetTagLabel, "cell 0 2,alignx leading");

        assetTagTextField = new JTextField();
        assetTagTextField.setEditable(false);
        panel.add(assetTagTextField, "cell 1 2,growx");

        JLabel serialNumberLabel = new JLabel("Serial Number");
        panel.add(serialNumberLabel, "cell 0 3,alignx leading");

        serialNumberTextField = new JTextField();
        serialNumberTextField.setEditable(false);
        panel.add(serialNumberTextField, "cell 1 3,growx");

        JLabel partNumberLabel = new JLabel("Part Number");
        panel.add(partNumberLabel, "cell 0 4,alignx leading");

        partNumberTextField = new JTextField();
        partNumberTextField.setEditable(false);
        panel.add(partNumberTextField, "cell 1 4,growx");

        JLabel firmwareVersionLabel = new JLabel("Firmware Version");
        panel.add(firmwareVersionLabel, "cell 0 5,alignx leading");

        firmwareVersionTextField = new JTextField();
        firmwareVersionTextField.setEditable(false);
        panel.add(firmwareVersionTextField, "cell 1 5,growx");

        return new JScrollPane(panel);
    }

    private JScrollPane createCharacteristicsPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Characteristics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        JLabel locatorLabel = new JLabel("Locator");
        panel.add(locatorLabel, "cell 0 0,alignx leading");

        locatorTextField = new JTextField();
        locatorTextField.setEditable(false);
        panel.add(locatorTextField, "cell 1 0,growx");

        JLabel bankLocatorLabel = new JLabel("Bank Locator");
        panel.add(bankLocatorLabel, "cell 0 1,alignx leading");

        bankLocatorTextField = new JTextField();
        bankLocatorTextField.setEditable(false);
        panel.add(bankLocatorTextField, "cell 1 1,growx");

        JLabel formFactorLabel = new JLabel("Form Factor");
        panel.add(formFactorLabel, "cell 0 2,alignx leading");

        formFactorTextField = new JTextField();
        formFactorTextField.setEditable(false);
        panel.add(formFactorTextField, "cell 1 2,growx");

        JLabel typeLabel = new JLabel("Type");
        panel.add(typeLabel, "cell 0 3,alignx leading");

        typeTextField = new JTextField();
        typeTextField.setEditable(false);
        panel.add(typeTextField, "cell 1 3,growx");

        JLabel typeDetailLabel = new JLabel("Type Detail");
        panel.add(typeDetailLabel, "cell 0 4,alignx leading");

        typeDetailTextField = new JTextField();
        typeDetailTextField.setEditable(false);
        panel.add(typeDetailTextField, "cell 1 4,growx");

        JLabel rankLabel = new JLabel("Rank");
        panel.add(rankLabel, "cell 0 5,alignx leading");

        rankLabelTextField = new JTextField();
        rankLabelTextField.setEditable(false);
        panel.add(rankLabelTextField, "cell 1 5,growx");

        return new JScrollPane(panel);

    }

    private JScrollPane createSizePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Size", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][]"));

        JLabel sizeLabel = new JLabel("Size");
        panel.add(sizeLabel, "cell 0 0,alignx leading");

        sizeTextField = new JTextField();
        sizeTextField.setEditable(false);
        panel.add(sizeTextField, "cell 1 0,growx");

        JLabel dataWidthLabel = new JLabel("Data Width");
        panel.add(dataWidthLabel, "cell 0 1,alignx leading");

        dataWidthTextField = new JTextField();
        dataWidthTextField.setEditable(false);
        panel.add(dataWidthTextField, "cell 1 1,growx");

        JLabel totalWidthLabel = new JLabel("Total Width");
        panel.add(totalWidthLabel, "cell 0 2,alignx leading");

        totalWidthTextField = new JTextField();
        totalWidthTextField.setEditable(false);
        panel.add(totalWidthTextField, "cell 1 2,growx");

        JLabel volatileSizeLabel = new JLabel("Volatile Size");
        panel.add(volatileSizeLabel, "cell 0 3,alignx leading");

        volatileSizeTextField = new JTextField();
        volatileSizeTextField.setEditable(false);
        panel.add(volatileSizeTextField, "cell 1 3,growx");

        JLabel nonVolatileSizeLabel = new JLabel("Non-Volatile Size");
        panel.add(nonVolatileSizeLabel, "cell 0 4,alignx leading");

        nonVolatileSizeTextField = new JTextField();
        nonVolatileSizeTextField.setEditable(false);
        panel.add(nonVolatileSizeTextField, "cell 1 4,growx");

        JLabel cacheSizeLabel = new JLabel("Cache Size");
        panel.add(cacheSizeLabel, "cell 0 5,alignx leading");

        cacheSizeTextField = new JTextField();
        cacheSizeTextField.setEditable(false);
        panel.add(cacheSizeTextField, "cell 1 5,growx");

        JLabel logicalSizeLabel = new JLabel("Logical Size");
        panel.add(logicalSizeLabel, "cell 0 6,alignx leading");
        logicalSizeTextField = new JTextField();

        logicalSizeTextField.setEditable(false);
        panel.add(logicalSizeTextField, "cell 1 6,growx");

        return new JScrollPane(panel);

    }

    private JScrollPane createSpeedAndVoltagePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Speed And Voltage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][]"));

        JLabel factorySpeedLabel = new JLabel("Factory Speed");
        panel.add(factorySpeedLabel, "cell 0 0,alignx leading");

        factorySpeedTextField = new JTextField();
        factorySpeedTextField.setEditable(false);
        panel.add(factorySpeedTextField, "cell 1 0,growx");

        JLabel configuredSpeedLabel = new JLabel("Configured Speed");
        panel.add(configuredSpeedLabel, "cell 0 1,alignx leading");

        configuredSpeedTextField = new JTextField();
        configuredSpeedTextField.setEditable(false);
        panel.add(configuredSpeedTextField, "cell 1 1,growx");

        JLabel minimumVoltageLabel = new JLabel("Minimum Volage");
        panel.add(minimumVoltageLabel, "cell 0 2,alignx leading");

        minimumVoltageTextField = new JTextField();
        minimumVoltageTextField.setEditable(false);
        panel.add(minimumVoltageTextField, "cell 1 2,growx");

        JLabel maximumVoltageLabel = new JLabel("Maximum Voltage");
        panel.add(maximumVoltageLabel, "cell 0 3,alignx leading");

        maximumVoltageTextField = new JTextField();
        maximumVoltageTextField.setEditable(false);
        panel.add(maximumVoltageTextField, "cell 1 3,growx");

        JLabel configuredVoltageLabel = new JLabel("Configured Voltage");
        panel.add(configuredVoltageLabel, "cell 0 4,alignx leading");

        configuredVoltageTextField = new JTextField();
        configuredVoltageTextField.setEditable(false);
        panel.add(configuredVoltageTextField, "cell 1 4,growx");

        return new JScrollPane(panel);

    }

    private JScrollPane createManufacturerPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Manufacturer And Technology", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][]"));

        JLabel manufacturerLabel = new JLabel("Manufacturer");
        panel.add(manufacturerLabel, "cell 0 0,alignx leading");

        manufacturerTextField = new JTextField();
        manufacturerTextField.setEditable(false);
        panel.add(manufacturerTextField, "cell 1 0,growx");

        JLabel moduleManufacturerIdLabel = new JLabel("Module Manufacturer ID");
        panel.add(moduleManufacturerIdLabel, "cell 0 1,alignx leading");

        moduleManufacturerIdTextField = new JTextField();
        moduleManufacturerIdTextField.setEditable(false);
        panel.add(moduleManufacturerIdTextField, "cell 1 1,growx");

        JLabel moduleProductIdLabel = new JLabel("Module Product ID");
        panel.add(moduleProductIdLabel, "cell 0 2,alignx leading");

        moduleProductIdTextField = new JTextField();
        moduleProductIdTextField.setEditable(false);
        panel.add(moduleProductIdTextField, "cell 1 2,growx");

        JLabel mscManufacturerIdLabel = new JLabel("MSC Manufacturer ID");
        mscManufacturerIdLabel.setToolTipText("Memory Sub-System Controller Manufacturer ID");
        panel.add(mscManufacturerIdLabel, "cell 0 3,alignx leading");

        mscManufacturerIdTextField = new JTextField();
        mscManufacturerIdTextField.setEditable(false);
        panel.add(mscManufacturerIdTextField, "cell 1 3,growx");

        JLabel mscProductIdLabel = new JLabel("MSC Product ID");
        mscProductIdLabel.setToolTipText("Memory Sub-System Controller Product ID");
        panel.add(mscProductIdLabel, "cell 0 4,alignx leading");

        mscProductIdTextField = new JTextField();
        mscProductIdTextField.setEditable(false);
        panel.add(mscProductIdTextField, "cell 1 4,growx");

        JLabel memoryTechnologyLabel = new JLabel("Memory Technology");
        panel.add(memoryTechnologyLabel, "cell 0 5,alignx leading");

        memoryTechnologyTextField = new JTextField();
        memoryTechnologyTextField.setEditable(false);
        panel.add(memoryTechnologyTextField, "cell 1 5,growx");

        JLabel memoryOperatingModeCapabilityLabel = new JLabel("Memory Operating Mode Capability");
        panel.add(memoryOperatingModeCapabilityLabel, "cell 0 6,alignx leading");

        memoryOperatingModeCapabilityTextField = new JTextField();
        memoryOperatingModeCapabilityTextField.setEditable(false);
        panel.add(memoryOperatingModeCapabilityTextField, "cell 1 6,growx");

        return new JScrollPane(panel);
    }

    private JScrollPane createExtraPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Extra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][]"));

        JLabel arrayHandleLabel = new JLabel("Array Handle");
        panel.add(arrayHandleLabel, "cell 0 0,alignx leading");

        arrayHandleTextField = new JTextField();
        arrayHandleTextField.setEditable(false);
        panel.add(arrayHandleTextField, "cell 1 0,growx");

        JLabel errorHandleLabel = new JLabel("Error Handle");
        panel.add(errorHandleLabel, "cell 0 1,alignx leading");

        errorHandleTextField = new JTextField();
        errorHandleTextField.setEditable(false);
        panel.add(errorHandleTextField, "cell 1 1,growx");

        return new JScrollPane(panel);
    }


    private void setWorker() {

        List<JTextField> textFields = List.of(setTextField, assetTagTextField, serialNumberTextField,
                partNumberTextField, firmwareVersionTextField, locatorTextField,
                bankLocatorTextField, formFactorTextField, typeTextField, typeDetailTextField,
                rankLabelTextField, sizeTextField, dataWidthTextField, totalWidthTextField,
                volatileSizeTextField, nonVolatileSizeTextField, cacheSizeTextField, logicalSizeTextField,
                factorySpeedTextField, configuredSpeedTextField, minimumVoltageTextField, maximumVoltageTextField,
                configuredVoltageTextField, manufacturerTextField, moduleManufacturerIdTextField, moduleProductIdTextField,
                mscManufacturerIdTextField, mscProductIdTextField, memoryTechnologyTextField,
                memoryOperatingModeCapabilityTextField, errorHandleTextField, arrayHandleTextField
        );

        new DMIPhysicalMemoryWorker(memoryLocatorComboBox, textFields).execute();
    }

}
