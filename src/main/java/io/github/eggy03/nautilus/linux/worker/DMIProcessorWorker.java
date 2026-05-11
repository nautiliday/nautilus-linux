/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.worker;

import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.service.processor.DMIProcessorService;
import io.github.eggy03.nautilus.linux.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DMIProcessorWorker extends SwingWorker<Map<String, DMIProcessor>, Void> {

    private final @NonNull JComboBox<String> cpuIdComboBox;
    private final @NonNull List<JTextField> cpuFields;
    private final @NonNull JTextArea cpuCharsAndFlagsTextArea;

    @Override
    protected @NonNull Map<String, DMIProcessor> doInBackground() {
        List<DMIProcessor> cpuList = new DMIProcessorService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} DMIProcessor entry(s)", cpuList.size());

        return cpuList.stream()
                .filter(Objects::nonNull)
                .filter(cpu -> Objects.nonNull(cpu.id()))
                .collect(Collectors.toUnmodifiableMap(
                        cpu -> Objects.requireNonNull(cpu.id()),
                        cpu -> cpu
                ));
    }

    @Override
    protected void done() {

        try {
            Map<String, DMIProcessor> cpuMap = get();
            // populate the combo box with cpu device id
            cpuMap.keySet().stream().sorted().forEach(cpuIdComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFieldsBasedOnCurrentCpuId(cpuMap);
            // add a listener to the combo box to re-populate fields on new selection
            cpuIdComboBox.addActionListener(selectEvent -> populateFieldsBasedOnCurrentCpuId(cpuMap));
        } catch (ExecutionException e) {
            log.error("CPU Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("CPU Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }

    }

    private void populateFieldsBasedOnCurrentCpuId(@NonNull Map<String, DMIProcessor> cpuMap) {
        String index = String.valueOf(cpuIdComboBox.getSelectedItem());

        DMIProcessor cpu = cpuMap.get(index);
        if (cpu == null) return;

        cpuFields.get(0).setText(String.valueOf(cpu.coreCount()));
        cpuFields.get(1).setText(String.valueOf(cpu.threadCount()));
        cpuFields.get(2).setText(cpu.currentSpeed());
        cpuFields.get(3).setText(cpu.version());
        cpuFields.get(4).setText(cpu.signature());
        cpuFields.get(5).setText(cpu.family());
        cpuFields.get(6).setText(cpu.manufacturer());
        cpuFields.get(7).setText(cpu.serialNumber());
        cpuFields.get(8).setText(cpu.assetTag());
        cpuFields.get(9).setText(cpu.partNumber());
        cpuFields.get(10).setText(String.valueOf(cpu.coreEnabled()));
        cpuFields.get(11).setText(cpu.upgrade());
        cpuFields.get(12).setText(cpu.status());
        cpuFields.get(13).setText(cpu.voltage());
        cpuFields.get(14).setText(cpu.socketDesignation());
        cpuFields.get(15).setText(cpu.maxSpeed());

        List<String> characteristics = cpu.characteristics();
        List<String> flags = cpu.flags();

        StringBuilder sb = new StringBuilder();

        sb.append("CPU Characteristics:").append(System.lineSeparator());
        if (characteristics != null) {
            characteristics.forEach(characteristic -> sb.append("• ")
                    .append(characteristic)
                    .append(System.lineSeparator()));
        }

        sb.append(System.lineSeparator()).append("CPU Flags:").append(System.lineSeparator());
        if (flags != null) {
            flags.forEach(flag -> sb.append("• ")
                    .append(flag)
                    .append(System.lineSeparator()));
        }

        cpuCharsAndFlagsTextArea.setText(sb.toString());

    }
}
