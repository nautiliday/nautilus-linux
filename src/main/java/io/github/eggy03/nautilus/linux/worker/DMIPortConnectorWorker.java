/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.worker;

import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.service.board.DMIPortConnectorInformationService;
import io.github.eggy03.nautilus.linux.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
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
public class DMIPortConnectorWorker extends SwingWorker<Map<Integer, DMIPortConnectorInformation>, Void> {

    private final @NonNull JComboBox<Integer> portNumberComboBox;
    private final @NonNull List<JTextField> portFields;

    @Override
    protected @NonNull Map<Integer, DMIPortConnectorInformation> doInBackground() throws Exception {
        List<DMIPortConnectorInformation> portList = new DMIPortConnectorInformationService()
                .get(TerminalConstant.TIMEOUT_SIXTY_SECONDS)
                .stream()
                .filter(Objects::nonNull)
                .toList();

        log.info("Found {} DMIPortConnector entry(s)", portList.size());

        return IntStream.range(0, portList.size())
                .boxed()
                .collect(Collectors.toUnmodifiableMap(
                        index -> index + 1,
                        portList::get
                ));
    }

    @Override
    protected void done() {

        try {
            Map<Integer, DMIPortConnectorInformation> portMap = get();
            // populate the combo box with port numbers
            portMap.keySet().stream().sorted().forEach(portNumberComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(portMap);
            // add a listener to the combo box to re-populate fields on new selection
            portNumberComboBox.addActionListener(selectEvent -> populateFields(portMap));
        } catch (ExecutionException e) {
            log.error("DMIPortConnectorInformation Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("DMIPortConnectorInformation Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private void populateFields(@NonNull Map<Integer, DMIPortConnectorInformation> portMap) {

        Integer index = (Integer) portNumberComboBox.getSelectedItem();

        DMIPortConnectorInformation port = portMap.get(index);
        if (port == null) return;

        portFields.get(0).setText(port.externalReferenceDesignator());
        portFields.get(1).setText(port.internalReferenceDesignator());
        portFields.get(2).setText(port.externalConnectorType());
        portFields.get(3).setText(port.internalConnectorType());
        portFields.get(4).setText(port.portType());

    }
}
