package org.nasa.exploration.api.service;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;

public interface MarsExplorationService {
    ProbeCreationResponse createProbe(ProbeCreationRequest request);
}
