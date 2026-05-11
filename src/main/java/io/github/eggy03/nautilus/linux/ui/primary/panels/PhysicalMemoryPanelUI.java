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

@SuppressWarnings("java:S1192")
public class PhysicalMemoryPanelUI extends JPanel {

    // handles
    private final JLabel memoryModuleNumberLabel = new JLabel("Memory #");
    private final JComboBox<String> memoryLocatorComboBox = new JComboBox<>();

    private final JLabel setLabel = new JLabel("Set");
    private final JTextField setTextField = new JTextField();

    private final JLabel assetTagLabel = new JLabel("Asset Tag");
    private final JTextField assetTagTextField = new JTextField();

    private final JLabel serialNumberLabel = new JLabel("Serial Number");
    private final JTextField serialNumberTextField = new JTextField();

    private final JLabel partNumberLabel = new JLabel("Part Number");
    private final JTextField partNumberTextField = new JTextField();

    private final JLabel firmwareVersionLabel = new JLabel("Firmware Version");
    private final JTextField firmwareVersionTextField = new JTextField();

    // characteristics
    private final JLabel locatorLabel = new JLabel("Locator");
    private final JTextField locatorTextField = new JTextField();

    private final JLabel bankLocatorLabel = new JLabel("Bank Locator");
    private final JTextField bankLocatorTextField = new JTextField();

    private final JLabel formFactorLabel = new JLabel("Form Factor");
    private final JTextField formFactorTextField = new JTextField();

    private final JLabel typeLabel = new JLabel("Type");
    private final JTextField typeTextField = new JTextField();

    private final JLabel typeDetailLabel = new JLabel("Type Detail");
    private final JTextField typeDetailTextField = new JTextField();

    private final JLabel rankLabel = new JLabel("Rank");
    private final JTextField rankLabelTextField = new JTextField();

    // size
    private final JLabel sizeLabel = new JLabel("Size");
    private final JTextField sizeTextField = new JTextField();

    private final JLabel dataWidthLabel = new JLabel("Data Width");
    private final JTextField dataWidthTextField = new JTextField();

    private final JLabel totalWidthLabel = new JLabel("Total Width");
    private final JTextField totalWidthTextField = new JTextField();

    private final JLabel volatileSizeLabel = new JLabel("Volatile Size");
    private final JTextField volatileSizeTextField = new JTextField();

    private final JLabel nonVolatileSizeLabel = new JLabel("Non-Volatile Size");
    private final JTextField nonVolatileSizeTextField = new JTextField();

    private final JLabel cacheSizeLabel = new JLabel("Cache Size");
    private final JTextField cacheSizeTextField = new JTextField();

    private final JLabel logicalSizeLabel = new JLabel("Logical Size");
    private final JTextField logicalSizeTextField = new JTextField();

    // speed and voltage
    private final JLabel factorySpeedLabel = new JLabel("Factory Speed");
    private final JTextField factorySpeedTextField = new JTextField();

    private final JLabel configuredSpeedLabel = new JLabel("Configured Speed");
    private final JTextField configuredSpeedTextField = new JTextField();

    private final JLabel minimumVoltageLabel = new JLabel("Minimum Voltage");
    private final JTextField minimumVoltageTextField = new JTextField();

    private final JLabel maximumVoltageLabel = new JLabel("Maximum Voltage");
    private final JTextField maximumVoltageTextField = new JTextField();

    private final JLabel configuredVoltageLabel = new JLabel("Configured Voltage");
    private final JTextField configuredVoltageTextField = new JTextField();

    // manufacturer and technology
    private final JLabel manufacturerLabel = new JLabel("Manufacturer");
    private final JTextField manufacturerTextField = new JTextField();

    private final JLabel moduleManufacturerIdLabel = new JLabel("Module Manufacturer ID");
    private final JTextField moduleManufacturerIdTextField = new JTextField();

    private final JLabel moduleProductIdLabel = new JLabel("Module Product ID");
    private final JTextField moduleProductIdTextField = new JTextField();

    private final JLabel mscManufacturerIdLabel = new JLabel("MSC Manufacturer ID");
    private final JTextField mscManufacturerIdTextField = new JTextField();

    private final JLabel mscProductIdLabel = new JLabel("MSC Product ID");
    private final JTextField mscProductIdTextField = new JTextField();

    private final JLabel memoryTechnologyLabel = new JLabel("Memory Technology");
    private final JTextField memoryTechnologyTextField = new JTextField();

    private final JLabel memoryOperatingModeCapabilityLabel = new JLabel("Memory Operating Mode Capability");
    private final JTextField memoryOperatingModeCapabilityTextField = new JTextField();

    // extra
    private final JLabel arrayHandleLabel = new JLabel("Array Handle");
    private final JTextField arrayHandleTextField = new JTextField();

    private final JLabel errorHandleLabel = new JLabel("Error Handle");
    private final JTextField errorHandleTextField = new JTextField();


    public PhysicalMemoryPanelUI initUI() {

        setLayout(new MigLayout("insets 0", "[grow][grow][grow]", "[grow][grow]"));
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        return this;
    }

    public PhysicalMemoryPanelUI initComponents() {

        add(new JScrollPane(createHandlePanel()), "cell 0 0, grow");
        add(new JScrollPane(createCharacteristicsPanel()), "cell 1 0, grow");
        add(new JScrollPane(createSizePanel()), "cell 2 0, grow");
        add(new JScrollPane(createSpeedAndVoltagePanel()), "cell 0 1, grow");
        add(new JScrollPane(createManufacturerPanel()), "cell 1 1, grow");
        add(new JScrollPane(createExtraPanel()), "cell 2 1, grow");

        return this;
    }

    private JPanel createHandlePanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Handles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        panel.add(memoryModuleNumberLabel, "cell 0 0,alignx leading");
        panel.add(memoryLocatorComboBox, "cell 1 0,growx");

        panel.add(setLabel, "cell 0 1,alignx leading");
        panel.add(setTextField, "cell 1 1,growx");

        panel.add(assetTagLabel, "cell 0 2,alignx leading");
        panel.add(assetTagTextField, "cell 1 2,growx");

        panel.add(serialNumberLabel, "cell 0 3,alignx leading");
        panel.add(serialNumberTextField, "cell 1 3,growx");

        panel.add(partNumberLabel, "cell 0 4,alignx leading");
        panel.add(partNumberTextField, "cell 1 4,growx");

        panel.add(firmwareVersionLabel, "cell 0 5,alignx leading");
        panel.add(firmwareVersionTextField, "cell 1 5,growx");

        setTextField.setEditable(false);
        assetTagTextField.setEditable(false);
        serialNumberTextField.setEditable(false);
        partNumberTextField.setEditable(false);
        firmwareVersionTextField.setEditable(false);

        return panel;
    }

    private JPanel createCharacteristicsPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Characteristics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        panel.add(locatorLabel, "cell 0 0,alignx leading");
        panel.add(locatorTextField, "cell 1 0,growx");

        panel.add(bankLocatorLabel, "cell 0 1,alignx leading");
        panel.add(bankLocatorTextField, "cell 1 1,growx");

        panel.add(formFactorLabel, "cell 0 2,alignx leading");
        panel.add(formFactorTextField, "cell 1 2,growx");

        panel.add(typeLabel, "cell 0 3,alignx leading");
        panel.add(typeTextField, "cell 1 3,growx");

        panel.add(typeDetailLabel, "cell 0 4,alignx leading");
        panel.add(typeDetailTextField, "cell 1 4,growx");

        panel.add(rankLabel, "cell 0 5,alignx leading");
        panel.add(rankLabelTextField, "cell 1 5,growx");

        locatorTextField.setEditable(false);
        bankLocatorTextField.setEditable(false);
        formFactorTextField.setEditable(false);
        typeTextField.setEditable(false);
        typeDetailTextField.setEditable(false);
        rankLabelTextField.setEditable(false);

        return panel;

    }

    private JPanel createSizePanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Size", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][]"));

        panel.add(sizeLabel, "cell 0 0,alignx leading");
        panel.add(sizeTextField, "cell 1 0,growx");

        panel.add(dataWidthLabel, "cell 0 1,alignx leading");
        panel.add(dataWidthTextField, "cell 1 1,growx");

        panel.add(totalWidthLabel, "cell 0 2,alignx leading");
        panel.add(totalWidthTextField, "cell 1 2,growx");

        panel.add(volatileSizeLabel, "cell 0 3,alignx leading");
        panel.add(volatileSizeTextField, "cell 1 3,growx");

        panel.add(nonVolatileSizeLabel, "cell 0 4,alignx leading");
        panel.add(nonVolatileSizeTextField, "cell 1 4,growx");

        panel.add(cacheSizeLabel, "cell 0 5,alignx leading");
        panel.add(cacheSizeTextField, "cell 1 5,growx");

        panel.add(logicalSizeLabel, "cell 0 6,alignx leading");
        panel.add(logicalSizeTextField, "cell 1 6,growx");

        sizeTextField.setEditable(false);
        dataWidthTextField.setEditable(false);
        totalWidthTextField.setEditable(false);
        volatileSizeTextField.setEditable(false);
        nonVolatileSizeTextField.setEditable(false);
        cacheSizeTextField.setEditable(false);
        logicalSizeTextField.setEditable(false);

        return panel;

    }

    private JPanel createSpeedAndVoltagePanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Speed And Voltage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][]"));

        panel.add(factorySpeedLabel, "cell 0 0,alignx leading");
        panel.add(factorySpeedTextField, "cell 1 0,growx");

        panel.add(configuredSpeedLabel, "cell 0 1,alignx leading");
        panel.add(configuredSpeedTextField, "cell 1 1,growx");

        panel.add(minimumVoltageLabel, "cell 0 2,alignx leading");
        panel.add(minimumVoltageTextField, "cell 1 2,growx");

        panel.add(maximumVoltageLabel, "cell 0 3,alignx leading");
        panel.add(maximumVoltageTextField, "cell 1 3,growx");

        panel.add(configuredVoltageLabel, "cell 0 4,alignx leading");
        panel.add(configuredVoltageTextField, "cell 1 4,growx");

        factorySpeedTextField.setEditable(false);
        configuredSpeedTextField.setEditable(false);
        minimumVoltageTextField.setEditable(false);
        maximumVoltageTextField.setEditable(false);
        configuredVoltageTextField.setEditable(false);

        return panel;

    }

    private JPanel createManufacturerPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Manufacturer And Technology", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][]"));

        panel.add(manufacturerLabel, "cell 0 0,alignx leading");
        panel.add(manufacturerTextField, "cell 1 0,growx");

        panel.add(moduleManufacturerIdLabel, "cell 0 1,alignx leading");
        panel.add(moduleManufacturerIdTextField, "cell 1 1,growx");

        panel.add(moduleProductIdLabel, "cell 0 2,alignx leading");
        panel.add(moduleProductIdTextField, "cell 1 2,growx");

        panel.add(mscManufacturerIdLabel, "cell 0 3,alignx leading");
        panel.add(mscManufacturerIdTextField, "cell 1 3,growx");

        panel.add(mscProductIdLabel, "cell 0 4,alignx leading");
        panel.add(mscProductIdTextField, "cell 1 4,growx");

        panel.add(memoryTechnologyLabel, "cell 0 5,alignx leading");
        panel.add(memoryTechnologyTextField, "cell 1 5,growx");

        panel.add(memoryOperatingModeCapabilityLabel, "cell 0 6,alignx leading");
        panel.add(memoryOperatingModeCapabilityTextField, "cell 1 6,growx");

        mscManufacturerIdLabel.setToolTipText("Memory Sub-System Controller Manufacturer ID");
        mscProductIdLabel.setToolTipText("Memory Sub-System Controller Product ID");

        manufacturerTextField.setEditable(false);
        moduleManufacturerIdTextField.setEditable(false);
        moduleProductIdTextField.setEditable(false);
        mscManufacturerIdTextField.setEditable(false);
        mscProductIdTextField.setEditable(false);
        memoryTechnologyTextField.setEditable(false);
        memoryOperatingModeCapabilityTextField.setEditable(false);

        return panel;
    }

    private JPanel createExtraPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Extra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][]"));

        panel.add(arrayHandleLabel, "cell 0 0,alignx leading");
        panel.add(arrayHandleTextField, "cell 1 0,growx");

        panel.add(errorHandleLabel, "cell 0 1,alignx leading");
        panel.add(errorHandleTextField, "cell 1 1,growx");

        return panel;
    }


    public PhysicalMemoryPanelUI setWorkers() {

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

        return this;
    }

}
