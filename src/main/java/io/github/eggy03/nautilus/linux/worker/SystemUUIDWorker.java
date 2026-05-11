/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.linux.worker;

import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.service.system.DMISystemService;
import io.github.eggy03.nautilus.linux.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
public class SystemUUIDWorker extends SwingWorker<Optional<DMISystem>, Void> {

    private final @NonNull JTextField uuidField;

    @Override
    protected @NonNull Optional<DMISystem> doInBackground() throws Exception {
        return new DMISystemService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
    }

    @Override
    protected void done() {
        try {
            Optional<DMISystem> system = get();
            system.ifPresent(sys -> uuidField.setText(sys.uuid()));
        } catch (ExecutionException e) {
            log.error("System Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("System Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
