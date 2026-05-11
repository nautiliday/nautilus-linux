/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.linux.worker.DMICacheWorker;
import io.github.eggy03.nautilus.linux.worker.DMIProcessorWorker;
import io.github.eggy03.nautilus.linux.worker.SystemUUIDWorker;
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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

@SuppressWarnings("java:S1192")
public class ProcessorPanelUI extends JPanel {

    // hardware id
    private final JTextField systemIdTextField = new JTextField();

    private final JButton copySystemIdButton = new JButton();

    // cpu primary
    private final JLabel cpuIdLabel = new JLabel("ID");
    private final JComboBox<String> cpuIdComboBox = new JComboBox<>();

    private final JLabel cpuVersionLabel = new JLabel("Version");
    private final JTextField cpuVersionTextField = new JTextField();

    private final JLabel coreLabel = new JLabel("Cores");
    private final JTextField coreTextField = new JTextField();

    private final JLabel threadLabel = new JLabel("Threads");
    private final JTextField threadTextField = new JTextField();

    private final JLabel currentClockLabel = new JLabel("Current Clock");
    private final JTextField currentClockTextField = new JTextField();

    private final JLabel cpuVoltageLabel = new JLabel("Voltage");
    private final JTextField cpuVoltageTextField = new JTextField();

    private final JLabel socketLabel = new JLabel("Socket");
    private final JTextField socketTextField = new JTextField();

    private final JLabel baseClockLabel = new JLabel("Base Clock");
    private final JTextField baseClockTextField = new JTextField();

    // cpu secondary
    private final JLabel cpuSignatureLabel = new JLabel("Signature");
    private final JTextField cpuSignatureTextField = new JTextField();

    private final JLabel familyLabel = new JLabel("Family");
    private final JTextField familyTextField = new JTextField();

    private final JLabel cpuManufacturerLabel = new JLabel("Manufacturer");
    private final JTextField cpuManufacturerTextField = new JTextField();

    private final JLabel serialNumberLabel = new JLabel("Serial");
    private final JTextField cpuSerialTextField = new JTextField();

    private final JLabel assetTagLabel = new JLabel("Asset Tag");
    private final JTextField assetTagTextField = new JTextField();

    private final JLabel partNumberLabel = new JLabel("Part Number");
    private final JTextField partNumberTextField = new JTextField();

    private final JLabel enabledCoresLabel = new JLabel("Enabled Cores");
    private final JTextField enabledCoresTextField = new JTextField();

    private final JLabel upgradeLabel = new JLabel("Upgrade");
    private final JTextField upgradeTextField = new JTextField();

    private final JLabel cpuStatusLabel = new JLabel("Status");
    private final JTextField cpuStatusTextField = new JTextField();

    // text areas
    private final JTextArea cpuCharsAndFlagsTextArea = new JTextArea();
    private final JTextArea cacheTextArea = new JTextArea();

    public ProcessorPanelUI initUI() {

        setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Processor",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        setLayout(new MigLayout(
                "insets 0",
                "[grow][grow]",
                "[grow][grow][grow][grow]"
        ));

        return this;
    }

    public ProcessorPanelUI initComponents() {

        add(createHardwareIdPanel(), "cell 0 0 2 1,grow");
        add(createPrimaryCpuPanel(), "cell 0 1 2 1,grow");
        add(createSecondaryCpuPanel(), "cell 0 2 2 1,grow");
        add(createCpuFlagPanel(), "cell 0 3 1 1,grow");
        add(createCpuCachePanel(), "cell 1 3 1 1,grow");

        return this;
    }

    private JPanel createHardwareIdPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "System UUID",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        panel.setLayout(new MigLayout("insets 0", "[grow][]", "[]"));

        systemIdTextField.setEditable(false);

        copySystemIdButton.setIcon(
                new FlatSVGIcon(
                        ProcessorPanelUI.class.getResource(
                                "/icons/general_icons/copy.svg"
                        )
                )
        );

        copySystemIdButton.addActionListener(copyAction -> {

            StringSelection textToCopy =
                    new StringSelection(systemIdTextField.getText());

            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(textToCopy, null);
        });

        panel.add(systemIdTextField, "cell 0 0,grow");
        panel.add(copySystemIdButton, "cell 1 0,align center");

        return panel;
    }

    private JPanel createPrimaryCpuPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Primary Information",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        panel.setLayout(new MigLayout(
                "insets 0",
                "[][grow][][grow][][grow]",
                "[][][]"
        ));

        panel.add(cpuIdLabel, "cell 0 0,alignx leading");
        panel.add(cpuIdComboBox, "cell 1 0,growx");

        panel.add(cpuVersionLabel, "cell 2 0,alignx leading");
        panel.add(cpuVersionTextField, "cell 3 0 3 1,growx");

        panel.add(coreLabel, "cell 0 1,alignx leading");
        panel.add(coreTextField, "cell 1 1,growx");

        panel.add(threadLabel, "cell 2 1,alignx leading");
        panel.add(threadTextField, "cell 3 1,growx");

        panel.add(currentClockLabel, "cell 4 1,alignx leading");
        panel.add(currentClockTextField, "cell 5 1,growx");

        panel.add(cpuVoltageLabel, "cell 0 2,alignx leading");
        panel.add(cpuVoltageTextField, "cell 1 2,growx");

        panel.add(socketLabel, "cell 2 2,alignx leading");
        panel.add(socketTextField, "cell 3 2,growx");

        panel.add(baseClockLabel, "cell 4 2,alignx leading");
        panel.add(baseClockTextField, "cell 5 2,growx");

        cpuVersionTextField.setEditable(false);
        coreTextField.setEditable(false);
        threadTextField.setEditable(false);
        currentClockTextField.setEditable(false);
        cpuVoltageTextField.setEditable(false);
        socketTextField.setEditable(false);
        baseClockTextField.setEditable(false);

        return panel;
    }

    private JPanel createSecondaryCpuPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Secondary Information",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        panel.setLayout(new MigLayout(
                "insets 0",
                "[][grow][][grow][][grow]",
                "[][][]"
        ));

        panel.add(cpuSignatureLabel, "cell 0 0,alignx leading");
        panel.add(cpuSignatureTextField, "cell 1 0,growx");

        panel.add(familyLabel, "cell 2 0,alignx leading");
        panel.add(familyTextField, "cell 3 0,growx");

        panel.add(cpuManufacturerLabel, "cell 4 0,alignx leading");
        panel.add(cpuManufacturerTextField, "cell 5 0,growx");

        panel.add(serialNumberLabel, "cell 0 1,alignx leading");
        panel.add(cpuSerialTextField, "cell 1 1,growx");

        panel.add(assetTagLabel, "cell 2 1,alignx leading");
        panel.add(assetTagTextField, "cell 3 1,growx");

        panel.add(partNumberLabel, "cell 4 1,alignx leading");
        panel.add(partNumberTextField, "cell 5 1,growx");

        panel.add(enabledCoresLabel, "cell 0 2,alignx leading");
        panel.add(enabledCoresTextField, "cell 1 2,growx");

        panel.add(upgradeLabel, "cell 2 2,alignx leading");
        panel.add(upgradeTextField, "cell 3 2,growx");

        panel.add(cpuStatusLabel, "cell 4 2,alignx leading");
        panel.add(cpuStatusTextField, "cell 5 2,growx");

        cpuSignatureTextField.setEditable(false);
        familyTextField.setEditable(false);
        cpuManufacturerTextField.setEditable(false);
        cpuSerialTextField.setEditable(false);
        assetTagTextField.setEditable(false);
        partNumberTextField.setEditable(false);
        enabledCoresTextField.setEditable(false);
        upgradeTextField.setEditable(false);
        cpuStatusTextField.setEditable(false);

        return panel;
    }

    private JPanel createCpuFlagPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Characteristics and Flags",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        panel.setLayout(new GridLayout(1, 1, 0, 0));

        cpuCharsAndFlagsTextArea.setEditable(false);
        cpuCharsAndFlagsTextArea.setRows(10);
        cpuCharsAndFlagsTextArea.setColumns(5);

        panel.add(new JScrollPane(cpuCharsAndFlagsTextArea));

        return panel;
    }

    private JPanel createCpuCachePanel() {

        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder(
                new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Cache Info",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                null
        ));

        panel.setLayout(new GridLayout(1, 1, 0, 0));

        cacheTextArea.setEditable(false);
        cacheTextArea.setRows(10);
        cacheTextArea.setColumns(5);

        panel.add(new JScrollPane(cacheTextArea));

        return panel;
    }

    public ProcessorPanelUI setWorkers() {

        List<JTextField> textFields = List.of(
                coreTextField,
                threadTextField,
                currentClockTextField,
                cpuVersionTextField,
                cpuSignatureTextField,
                familyTextField,
                cpuManufacturerTextField,
                cpuSerialTextField,
                assetTagTextField,
                partNumberTextField,
                enabledCoresTextField,
                upgradeTextField,
                cpuStatusTextField,
                cpuVoltageTextField,
                socketTextField,
                baseClockTextField
        );

        new DMIProcessorWorker(
                cpuIdComboBox,
                textFields,
                cpuCharsAndFlagsTextArea
        ).execute();

        new DMICacheWorker(cacheTextArea).execute();
        new SystemUUIDWorker(systemIdTextField).execute();

        return this;
    }
}