/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.worker;

import io.github.eggy03.dmidecode.entity.processor.DMICache;
import io.github.eggy03.dmidecode.service.processor.DMICacheService;
import io.github.eggy03.nautilus.linux.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
public class DMICacheWorker extends SwingWorker<List<DMICache>, Void> {

    private final @NonNull JTextArea cacheTextArea;

    @Override
    protected @NonNull List<DMICache> doInBackground() {
        List<DMICache> dmiCacheList = new DMICacheService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} DMICache entry(s)", dmiCacheList.size());

        return dmiCacheList;
    }

    @Override
    protected void done() {
        try {
            List<DMICache> dmiCacheList = get();

            StringBuilder sb = new StringBuilder();
            sb.append("Cache Characteristics:").append("\n");

            dmiCacheList.forEach(cache -> sb
                    .append("\nSocket Designation: ").append(cache.socketDesignation())
                    .append("\nConfiguration: ").append(cache.configuration())
                    .append("\nOperational Mode: ").append(cache.operationalMode())
                    .append("\nLocation: ").append(cache.location())
                    .append("\nInstalled Size: ").append(cache.installedSize())
                    .append("\nMaximum Size: ").append(cache.maximumSize())
                    .append("\nSupported SRAM Types: ").append(cache.supportedSramTypes())
                    .append("\nInstalled SRAM Type: ").append(cache.installedSramType())
                    .append("\nSpeed: ").append(cache.speed())
                    .append("\nError Correction Type: ").append(cache.errorCorrectionType())
                    .append("\nSystem Type: ").append(cache.systemType())
                    .append("\nAssociativity: ").append(cache.associativity()).append("\n")
            );

            cacheTextArea.setText(sb.toString());
        } catch (ExecutionException e) {
            log.error("CPU Cache Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("CPU Cache Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
