package org.nasa.exploration.api.service;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.model.ProbeAggregate;

import java.util.List;
import java.util.Optional;

public interface ProbeService {
    ProbeAggregate createProbe(ProbeCreationRequest request);

    List<ProbeAggregate> findAllProbes();

    Optional<ProbeAggregate> findProbeById(String id);

    Optional<ProbeAggregate> findProbeByPosition(int x, int y);

    Optional<ProbeAggregate> processInstruction(String id, String instruction);
}
