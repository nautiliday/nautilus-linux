/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.primary.panels;

import io.github.eggy03.nautilus.linux.worker.DMIBIOSWorker;
import io.github.eggy03.nautilus.linux.worker.DMIBaseboardWorker;
import io.github.eggy03.nautilus.linux.worker.DMIPortConnectorWorker;
import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

@SuppressWarnings("java:S1192")
public class BaseboardPanelUI extends JPanel {

    // baseboard
    private final @NonNull JLabel manufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JTextField manufacturerTextField = new JTextField();

    private final @NonNull JLabel nameLabel = new JLabel("Name");
    private final @NonNull JTextField nameTextField = new JTextField();

    private final @NonNull JLabel versionLabel = new JLabel("Version");
    private final @NonNull JTextField baseboardVersionTextField = new JTextField();

    private final @NonNull JLabel serialLabel = new JLabel("Serial");
    private final @NonNull JTextField serialTextField = new JTextField();

    private final @NonNull JLabel assetTagLabel = new JLabel("Asset Tag");
    private final @NonNull JTextField assetTagTextField = new JTextField();

    private final @NonNull JLabel chassisLocationLabel = new JLabel("Location In Chassis");
    private final @NonNull JTextField chassisLocationTextField = new JTextField();

    private final @NonNull JLabel chassisHandleLabel = new JLabel("Chassis Handle");
    private final @NonNull JTextField chassisHandleTextField = new JTextField();

    private final @NonNull JLabel typeLabel = new JLabel("Type");
    private final @NonNull JTextField typeTextField = new JTextField();

    private final @NonNull JTextArea featureTextArea = new JTextArea();

    // port connector
    private final @NonNull JLabel portNumberLabel = new JLabel("Port #");
    private final @NonNull JComboBox<Integer> portNumberComboBox = new JComboBox<>();

    private final @NonNull JLabel erdLabel = new JLabel("ERD");
    private final @NonNull JTextField erdTextField = new JTextField();

    private final @NonNull JLabel irdLabel = new JLabel("IRD");
    private final @NonNull JTextField irdTextField = new JTextField();

    private final @NonNull JLabel ectLabel = new JLabel("ECT");
    private final @NonNull JTextField ectTextField = new JTextField();

    private final @NonNull JLabel ictLabel = new JLabel("ICT");
    private final @NonNull JTextField ictTextField = new JTextField();

    private final @NonNull JLabel portTypeLabel = new JLabel("Port Type");
    private final @NonNull JTextField portTypeTextField = new JTextField();

    // bios
    private final @NonNull JLabel biosNumberLabel = new JLabel("BIOS #");
    private final @NonNull JComboBox<Integer> biosNumberComboBox = new JComboBox<>();

    private final @NonNull JLabel vendorLabel = new JLabel("Vendor");
    private final @NonNull JTextField vendorTextField = new JTextField();

    private final @NonNull JLabel biosVersionLabel = new JLabel("Version");
    private final @NonNull JTextField versionTextField = new JTextField();

    private final @NonNull JLabel releaseDateLabel = new JLabel("Release Date");
    private final @NonNull JTextField releaseDateTextField = new JTextField();

    private final @NonNull JLabel addressLabel = new JLabel("Address");
    private final @NonNull JTextField addressTextField = new JTextField();

    private final @NonNull JLabel runtimeSizeLabel = new JLabel("Runtime Size");
    private final @NonNull JTextField runtimeSizeTextField = new JTextField();

    private final @NonNull JLabel romSizeLabel = new JLabel("ROM Size");
    private final @NonNull JTextField romSizeTextField = new JTextField();

    private final @NonNull JLabel biosRevisionLabel = new JLabel("BIOS Revision");
    private final @NonNull JTextField biosRevisionTextField = new JTextField();

    private final @NonNull JLabel firmwareRevisionLabel = new JLabel("Firmware Revision");
    private final @NonNull JTextField firmwareRevisionTextField = new JTextField();

    private final @NonNull JTextArea characteristicsTextArea = new JTextArea();

    @NonNull
    public BaseboardPanelUI initUI() {

        setLayout(new MigLayout("insets 0", "[grow][grow][grow]", "[grow]"));

        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Baseboard and Accessories", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        return this;
    }

    @NonNull
    public BaseboardPanelUI initComponents() {

        add(new JScrollPane(createBaseboardPanel()), "cell 0 0, grow");
        add(new JScrollPane(createBaseboardPortPanel()), "cell 1 0, grow");
        add(new JScrollPane(createBiosPanel()), "cell 2 0, grow");

        return this;
    }

    @NonNull
    private JPanel createBaseboardPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Baseboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[][][][][][][][][grow]"));

        panel.add(manufacturerLabel, "cell 0 0,alignx leading");
        panel.add(manufacturerTextField, "cell 1 0,growx");

        panel.add(nameLabel, "cell 0 1,alignx leading");
        panel.add(nameTextField, "cell 1 1,growx");

        panel.add(versionLabel, "cell 0 2,alignx leading");
        panel.add(baseboardVersionTextField, "cell 1 2,growx");

        panel.add(serialLabel, "cell 0 3,alignx leading");
        panel.add(serialTextField, "cell 1 3,growx");

        panel.add(assetTagLabel, "cell 0 4,alignx leading");
        panel.add(assetTagTextField, "cell 1 4,growx");

        panel.add(chassisLocationLabel, "cell 0 5,alignx leading");
        panel.add(chassisLocationTextField, "cell 1 5,growx");

        panel.add(chassisHandleLabel, "cell 0 6,alignx leading");
        panel.add(chassisHandleTextField, "cell 1 6,growx");

        panel.add(typeLabel, "cell 0 7,alignx leading");

        panel.add(typeTextField, "cell 1 7,growx");
        panel.add(featureTextArea, "cell 0 8 2 1,grow");

        manufacturerTextField.setEditable(false);
        nameTextField.setEditable(false);
        baseboardVersionTextField.setEditable(false);
        serialTextField.setEditable(false);
        assetTagTextField.setEditable(false);
        chassisLocationTextField.setEditable(false);
        chassisHandleTextField.setEditable(false);
        typeTextField.setEditable(false);
        featureTextArea.setEditable(false);

        return panel;
    }

    @NonNull
    private JPanel createBaseboardPortPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Baseboard Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        panel.add(portNumberLabel, "cell 0 0,alignx leading");
        panel.add(portNumberComboBox, "cell 1 0,growx");

        panel.add(erdLabel, "cell 0 1,alignx leading");
        panel.add(erdTextField, "cell 1 1,growx");

        panel.add(irdLabel, "cell 0 2,alignx leading");
        panel.add(irdTextField, "cell 1 2,growx");

        panel.add(ectLabel, "cell 0 3,alignx leading");
        panel.add(ectTextField, "cell 1 3,growx");

        panel.add(ictLabel, "cell 0 4,alignx leading");
        panel.add(ictTextField, "cell 1 4,growx");

        panel.add(portTypeLabel, "cell 0 5,alignx leading");
        panel.add(portTypeTextField, "cell 1 5,growx");

        erdLabel.setToolTipText("External Reference Designator");
        irdLabel.setToolTipText("Internal Reference Designator");
        ectLabel.setToolTipText("External Connector Type");
        ictLabel.setToolTipText("Internal Connector Type");

        erdTextField.setEditable(false);
        irdTextField.setEditable(false);
        ectTextField.setEditable(false);
        ictTextField.setEditable(false);
        portTypeTextField.setEditable(false);

        return panel;
    }

    @NonNull
    private JPanel createBiosPanel() {

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "BIOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(new MigLayout("insets 0", "[grow][grow]", "[][][][][][][][][][grow]"));

        panel.add(biosNumberLabel, "cell 0 0,alignx leading");
        panel.add(biosNumberComboBox, "cell 1 0,growx");

        panel.add(vendorLabel, "cell 0 1,alignx leading");
        panel.add(vendorTextField, "cell 1 1,growx");

        panel.add(biosVersionLabel, "cell 0 2,alignx leading");
        panel.add(versionTextField, "cell 1 2,growx");

        panel.add(releaseDateLabel, "cell 0 3,alignx leading");
        panel.add(releaseDateTextField, "cell 1 3,growx");

        panel.add(addressLabel, "cell 0 4,alignx leading");
        panel.add(addressTextField, "cell 1 4,growx");

        panel.add(runtimeSizeLabel, "cell 0 5,alignx leading");
        panel.add(runtimeSizeTextField, "cell 1 5,growx");

        panel.add(romSizeLabel, "cell 0 6,alignx leading");
        panel.add(romSizeTextField, "cell 1 6,growx");

        panel.add(biosRevisionLabel, "cell 0 7,alignx leading");
        panel.add(biosRevisionTextField, "cell 1 7,growx");

        panel.add(firmwareRevisionLabel, "cell 0 8,alignx leading");
        panel.add(firmwareRevisionTextField, "cell 1 8,growx");

        panel.add(new JScrollPane(characteristicsTextArea), "cell 0 9 2 1,grow");

        vendorTextField.setEditable(false);
        versionTextField.setEditable(false);
        releaseDateTextField.setEditable(false);
        addressTextField.setEditable(false);
        runtimeSizeTextField.setEditable(false);
        romSizeTextField.setEditable(false);
        biosRevisionTextField.setEditable(false);
        firmwareRevisionTextField.setEditable(false);
        characteristicsTextArea.setEditable(false);

        characteristicsTextArea.setRows(10);
        characteristicsTextArea.setColumns(5);

        return panel;
    }

    @NonNull
    public BaseboardPanelUI setWorkers() {

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

        return this;
    }

}
