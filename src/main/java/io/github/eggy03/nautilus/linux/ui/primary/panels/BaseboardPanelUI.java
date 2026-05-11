/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.primary.panels;

import io.github.eggy03.nautilus.linux.worker.DMIBIOSWorker;
import io.github.eggy03.nautilus.linux.worker.DMIBaseboardWorker;
import io.github.eggy03.nautilus.linux.worker.DMIPortConnectorWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.util.List;

public class BaseboardPanelUI extends JPanel {

    // baseboard
    private JTextField manufacturerTextField;
    private JTextField nameTextField;
    private JTextField baseboardVersionTextField;
    private JTextField serialTextField;
    private JTextField assetTagTextField;
    private JTextField chassisLocationTextField;
    private JTextField chassisHandleTextField;
    private JTextField typeTextField;
    private JTextArea featureTextArea;

    // port connector
    private JComboBox<Integer> portNumberComboBox;
    private JTextField erdTextField;
    private JTextField irdTextField;
    private JTextField ectTextField;
    private JTextField ictTextField;
    private JTextField portTypeTextField;

    // bios
    private JComboBox<Integer> biosNumberComboBox;
    private JTextField vendorTextField;
    private JTextField versionTextField;
    private JTextField releaseDateTextField;
    private JTextField addressTextField;
    private JTextField runtimeSizeTextField;
    private JTextField romSizeTextField;
    private JTextField biosRevisionTextField;
    private JTextField firmwareRevisionTextField;
    private JTextArea characteristicsTextArea;

    public BaseboardPanelUI() {
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Baseboard and Accessories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new GridLayout(1, 3, 0, 0));

        add(createBaseboardPanel());
        add(createBaseboardPortPanel());
        add(createBiosPanel());

        setWorkers();
    }

    public JPanel getPanel() {
        return this;
    }

    private JScrollPane createBaseboardPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Baseboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[][][][][][][][][grow]"));

        JLabel manufacturerLabel = new JLabel("Manufacturer");
        panel.add(manufacturerLabel, "cell 0 0,alignx leading");

        manufacturerTextField = new JTextField();
        manufacturerTextField.setEditable(false);
        panel.add(manufacturerTextField, "cell 1 0,growx");

        JLabel nameLabel = new JLabel("Name");
        panel.add(nameLabel, "cell 0 1,alignx leading");

        nameTextField = new JTextField();
        nameTextField.setEditable(false);
        panel.add(nameTextField, "cell 1 1,growx");

        JLabel versionLabel = new JLabel("Version");
        panel.add(versionLabel, "cell 0 2,alignx leading");

        baseboardVersionTextField = new JTextField();
        baseboardVersionTextField.setEditable(false);
        panel.add(baseboardVersionTextField, "cell 1 2,growx");

        JLabel serialLabel = new JLabel("Serial");
        panel.add(serialLabel, "cell 0 3,alignx leading");

        serialTextField = new JTextField();
        serialTextField.setEditable(false);
        panel.add(serialTextField, "cell 1 3,growx");

        JLabel assetTagLabel = new JLabel("Asset Tag");
        panel.add(assetTagLabel, "cell 0 4,alignx leading");

        assetTagTextField = new JTextField();
        assetTagTextField.setEditable(false);
        panel.add(assetTagTextField, "cell 1 4,growx");

        JLabel chassisLocationLabel = new JLabel("Location In Chassis");
        panel.add(chassisLocationLabel, "cell 0 5,alignx leading");

        chassisLocationTextField = new JTextField();
        chassisLocationTextField.setEditable(false);
        panel.add(chassisLocationTextField, "cell 1 5,growx");

        JLabel chassisHandleLabel = new JLabel("Chassis Handle");
        panel.add(chassisHandleLabel, "cell 0 6,alignx leading");

        chassisHandleTextField = new JTextField();
        chassisHandleTextField.setEditable(false);
        panel.add(chassisHandleTextField, "cell 1 6,growx");

        JLabel typeLabel = new JLabel("Type");
        panel.add(typeLabel, "cell 0 7,alignx leading");

        typeTextField = new JTextField();
        typeTextField.setEditable(false);
        panel.add(typeTextField, "cell 1 7,growx");

        JPanel featurePanel = new JPanel();
        featurePanel.setBorder(new TitledBorder(null, "Features", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        featurePanel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow]"));
        panel.add(featurePanel, "cell 0 8 2 1,grow");

        featureTextArea = new JTextArea();
        featureTextArea.setEditable(false);
        featurePanel.add(featureTextArea, "cell 0 0 2 1,grow");

        return new JScrollPane(panel);
    }

    private JScrollPane createBaseboardPortPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Baseboard Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        JLabel portNumberLabel = new JLabel("Port #");
        panel.add(portNumberLabel, "cell 0 0,alignx leading");

        portNumberComboBox = new JComboBox<>();
        panel.add(portNumberComboBox, "cell 1 0,growx");

        JLabel erdLabel = new JLabel("ERD");
        erdLabel.setToolTipText("External Reference Designator");
        panel.add(erdLabel, "cell 0 1,alignx leading");

        erdTextField = new JTextField();
        erdTextField.setEditable(false);
        panel.add(erdTextField, "cell 1 1,growx");

        JLabel irdLabel = new JLabel("IRD");
        irdLabel.setToolTipText("Internal Reference Designator");
        panel.add(irdLabel, "cell 0 2,alignx leading");

        irdTextField = new JTextField();
        irdTextField.setEditable(false);
        panel.add(irdTextField, "cell 1 2,growx");

        JLabel ectLabel = new JLabel("ECT");
        ectLabel.setToolTipText("External Connector Type");
        panel.add(ectLabel, "cell 0 3,alignx leading");

        ectTextField = new JTextField();
        ectTextField.setEditable(false);
        panel.add(ectTextField, "cell 1 3,growx");

        JLabel ictLabel = new JLabel("ICT");
        ictLabel.setToolTipText("Internal Connector Type");
        panel.add(ictLabel, "cell 0 4,alignx leading");

        ictTextField = new JTextField();
        ictTextField.setEditable(false);
        panel.add(ictTextField, "cell 1 4,growx");

        JLabel portTypeLabel = new JLabel("Port Type");
        panel.add(portTypeLabel, "cell 0 5,alignx leading");

        portTypeTextField = new JTextField();
        portTypeTextField.setEditable(false);
        panel.add(portTypeTextField, "cell 1 5,growx");

        return new JScrollPane(panel);
    }

    private JScrollPane createBiosPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "BIOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[][][][][][][][][][grow]"));

        JLabel biosNumberLabel = new JLabel("BIOS #");
        panel.add(biosNumberLabel, "cell 0 0,alignx leading");

        biosNumberComboBox = new JComboBox<>();
        panel.add(biosNumberComboBox, "cell 1 0,growx");

        JLabel vendorLabel = new JLabel("Vendor");
        panel.add(vendorLabel, "cell 0 1,alignx leading");

        vendorTextField = new JTextField();
        vendorTextField.setEditable(false);
        panel.add(vendorTextField, "cell 1 1,growx");

        JLabel versionLabel = new JLabel("Version");
        panel.add(versionLabel, "cell 0 2,alignx leading");

        versionTextField = new JTextField();
        versionTextField.setEditable(false);
        panel.add(versionTextField, "cell 1 2,growx");

        JLabel releaseDateLabel = new JLabel("Release Date");
        panel.add(releaseDateLabel, "cell 0 3,alignx leading");

        releaseDateTextField = new JTextField();
        releaseDateTextField.setEditable(false);
        panel.add(releaseDateTextField, "cell 1 3,growx");

        JLabel addressLabel = new JLabel("Address");
        panel.add(addressLabel, "cell 0 4,alignx leading");

        addressTextField = new JTextField();
        addressTextField.setEditable(false);
        panel.add(addressTextField, "cell 1 4,growx");

        JLabel runtimeSizeLabel = new JLabel("Runtime Size");
        panel.add(runtimeSizeLabel, "cell 0 5,alignx leading");

        runtimeSizeTextField = new JTextField();
        runtimeSizeTextField.setEditable(false);
        panel.add(runtimeSizeTextField, "cell 1 5,growx");

        JLabel romSizeLabel = new JLabel("ROM Size");
        panel.add(romSizeLabel, "cell 0 6,alignx leading");

        romSizeTextField = new JTextField();
        romSizeTextField.setEditable(false);
        panel.add(romSizeTextField, "cell 1 6,growx");

        JLabel biosRevisionLabel = new JLabel("BIOS Revision");
        panel.add(biosRevisionLabel, "cell 0 7,alignx leading");

        biosRevisionTextField = new JTextField();
        biosRevisionTextField.setEditable(false);
        panel.add(biosRevisionTextField, "cell 1 7,growx");

        JLabel firmwareRevisionLabel = new JLabel("Firmware Revision");
        panel.add(firmwareRevisionLabel, "cell 0 8,alignx leading");

        firmwareRevisionTextField = new JTextField();
        firmwareRevisionTextField.setEditable(false);
        panel.add(firmwareRevisionTextField, "cell 1 8,growx");

        JPanel characteristicsPanel = new JPanel();
        characteristicsPanel.setBorder(new TitledBorder(null, "Characteristics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        characteristicsPanel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow]"));
        panel.add(characteristicsPanel, "cell 0 9 2 1,grow");

        characteristicsTextArea = new JTextArea();
        characteristicsTextArea.setEditable(false);
        characteristicsPanel.add(characteristicsTextArea, "cell 0 0 2 1,grow");

        return new JScrollPane(panel);
    }

    private void setWorkers() {

        List<JTextField> baseboardTextFields = List.of(manufacturerTextField, nameTextField, baseboardVersionTextField,
                serialTextField, assetTagTextField, chassisLocationTextField, chassisHandleTextField,
                typeTextField
        );

        List<JTextField> portConnectorTextFields = List.of(erdTextField, irdTextField, ectTextField, ictTextField,
                portTypeTextField
        );

        List<JTextField> biosTextFields = List.of(vendorTextField, versionTextField, releaseDateTextField,
                addressTextField, runtimeSizeTextField, romSizeTextField,
                biosRevisionTextField, firmwareRevisionTextField
        );

        new DMIBaseboardWorker(baseboardTextFields, featureTextArea).execute();
        new DMIPortConnectorWorker(portNumberComboBox, portConnectorTextFields).execute();
        new DMIBIOSWorker(biosNumberComboBox, biosTextFields, characteristicsTextArea).execute();
    }

}
