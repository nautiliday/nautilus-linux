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
import org.jspecify.annotations.NonNull;

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
    private final @NonNull JTextField systemIdTextField = new JTextField();

    private final @NonNull JButton copySystemIdButton = new JButton();

    // cpu primary
    private final @NonNull JLabel cpuIdLabel = new JLabel("ID");
    private final @NonNull JComboBox<String> cpuIdComboBox = new JComboBox<>();

    private final @NonNull JLabel cpuVersionLabel = new JLabel("Version");
    private final @NonNull JTextField cpuVersionTextField = new JTextField();

    private final @NonNull JLabel coreLabel = new JLabel("Cores");
    private final @NonNull JTextField coreTextField = new JTextField();

    private final @NonNull JLabel threadLabel = new JLabel("Threads");
    private final @NonNull JTextField threadTextField = new JTextField();

    private final @NonNull JLabel currentClockLabel = new JLabel("Current Clock");
    private final @NonNull JTextField currentClockTextField = new JTextField();

    private final @NonNull JLabel cpuVoltageLabel = new JLabel("Voltage");
    private final @NonNull JTextField cpuVoltageTextField = new JTextField();

    private final @NonNull JLabel socketLabel = new JLabel("Socket");
    private final @NonNull JTextField socketTextField = new JTextField();

    private final @NonNull JLabel baseClockLabel = new JLabel("Base Clock");
    private final @NonNull JTextField baseClockTextField = new JTextField();

    // cpu secondary
    private final @NonNull JLabel cpuSignatureLabel = new JLabel("Signature");
    private final @NonNull JTextField cpuSignatureTextField = new JTextField();

    private final @NonNull JLabel familyLabel = new JLabel("Family");
    private final @NonNull JTextField familyTextField = new JTextField();

    private final @NonNull JLabel cpuManufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JTextField cpuManufacturerTextField = new JTextField();

    private final @NonNull JLabel serialNumberLabel = new JLabel("Serial");
    private final @NonNull JTextField cpuSerialTextField = new JTextField();

    private final @NonNull JLabel assetTagLabel = new JLabel("Asset Tag");
    private final @NonNull JTextField assetTagTextField = new JTextField();

    private final @NonNull JLabel partNumberLabel = new JLabel("Part Number");
    private final @NonNull JTextField partNumberTextField = new JTextField();

    private final @NonNull JLabel enabledCoresLabel = new JLabel("Enabled Cores");
    private final @NonNull JTextField enabledCoresTextField = new JTextField();

    private final @NonNull JLabel upgradeLabel = new JLabel("Upgrade");
    private final @NonNull JTextField upgradeTextField = new JTextField();

    private final @NonNull JLabel cpuStatusLabel = new JLabel("Status");
    private final @NonNull JTextField cpuStatusTextField = new JTextField();

    // text areas
    private final @NonNull JTextArea cpuCharsAndFlagsTextArea = new JTextArea();
    private final @NonNull JTextArea cacheTextArea = new JTextArea();

    @NonNull
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

    @NonNull
    public ProcessorPanelUI initComponents() {

        add(new JScrollPane(createHardwareIdPanel()), "cell 0 0 2 1,grow");
        add(new JScrollPane(createPrimaryCpuPanel()), "cell 0 1 2 1,grow");
        add(new JScrollPane(createSecondaryCpuPanel()), "cell 0 2 2 1,grow");
        add(createCpuFlagPanel(), "cell 0 3 1 1,grow");
        add(createCpuCachePanel(), "cell 1 3 1 1,grow");

        return this;
    }

    @NonNull
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

    @NonNull
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

    @NonNull
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

    @NonNull
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

    @NonNull
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

    @NonNull
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