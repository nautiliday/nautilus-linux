/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.worker;

import io.github.eggy03.dmidecode.entity.board.DMIBIOS;
import io.github.eggy03.dmidecode.service.board.DMIBIOSService;
import io.github.eggy03.nautilus.linux.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
public class DMIBIOSWorker extends SwingWorker<Map<Integer, DMIBIOS>, Void> {

    private final JComboBox<Integer> biosNumberComboBox;
    private final List<JTextField> biosFields;
    private final JTextArea biosCharacteristicsTextArea;

    @Override
    protected Map<Integer, DMIBIOS> doInBackground() throws Exception {
        List<DMIBIOS> dmibiosList = new DMIBIOSService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} DMIBIOS entry(s)", dmibiosList.size());

        return IntStream.range(0, dmibiosList.size())
                .boxed()
                .collect(Collectors.toUnmodifiableMap(
                        index -> index + 1,
                        dmibiosList::get
                ));
    }

    @Override
    protected void done() {

        try {
            Map<Integer, DMIBIOS> biosMap = get();
            // populate the combo box with BIOS numbers
            biosMap.keySet().forEach(biosNumberComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(biosMap);
            // add a listener to the combo box to re-populate fields on new selection
            biosNumberComboBox.addActionListener(selectEvent -> populateFields(biosMap));
        } catch (ExecutionException e) {
            log.error("DMIBIOS Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("DMIBIOS Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private void populateFields(Map<Integer, DMIBIOS> mapList) {

        Integer selectedIndex = (Integer) biosNumberComboBox.getSelectedItem();

        DMIBIOS bios = mapList.get(selectedIndex);
        if (bios == null) return;

        biosFields.get(0).setText(bios.vendor());
        biosFields.get(1).setText(bios.version());
        biosFields.get(2).setText(bios.releaseDate());
        biosFields.get(3).setText(bios.address());
        biosFields.get(4).setText(bios.runtimeSize());
        biosFields.get(5).setText(bios.romSize());
        biosFields.get(6).setText(bios.biosRevision());
        biosFields.get(7).setText(bios.firmwareRevision());

        List<String> characteristics = bios.characteristics();

        if (characteristics != null && !characteristics.isEmpty()) {
            biosCharacteristicsTextArea.setText(characteristics.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(System.lineSeparator()))
            );
        }
    }
}
