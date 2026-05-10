/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.linux.worker;

import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIBaseboard;
import io.github.eggy03.dmidecode.service.board.DMIBaseboardService;
import io.github.eggy03.nautilus.linux.common.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DMIBaseboardWorker extends SwingWorker<DMIBaseboard, Void> {

    private final List<JTextField> baseboardFields;
    private final JTextArea featureTextArea;

    @Override
    protected DMIBaseboard doInBackground() throws Exception {
        Optional<DMIBaseboard> optionalDMIBaseboard = new DMIBaseboardService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        if (optionalDMIBaseboard.isEmpty())
            log.info("No DMIBaseboard entry found");
        else
            log.info("DMIBaseboard entry found");

        return optionalDMIBaseboard.orElse(new ImmutableDMIBaseboard.Builder().build());
    }

    @Override
    protected void done() {

        try {
            populateFields(get());

        } catch (ExecutionException e) {
            log.error("Baseboard Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("Baseboard Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private void populateFields(DMIBaseboard dmiBaseboard) {

        baseboardFields.get(0).setText(dmiBaseboard.manufacturer());
        baseboardFields.get(1).setText(dmiBaseboard.productName());
        baseboardFields.get(2).setText(dmiBaseboard.version());
        baseboardFields.get(3).setText(dmiBaseboard.serialNumber());
        baseboardFields.get(4).setText(dmiBaseboard.assetTag());
        baseboardFields.get(5).setText(dmiBaseboard.locationInChassis());
        baseboardFields.get(6).setText(dmiBaseboard.chassisHandle());
        baseboardFields.get(7).setText(dmiBaseboard.type());

        List<String> features = dmiBaseboard.features();
        if (features != null) {
            featureTextArea.setText(
                    features.stream()
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(System.lineSeparator()))
            );
        }


    }
}
