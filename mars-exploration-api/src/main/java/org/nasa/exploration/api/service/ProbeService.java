package org.nasa.exploration.api.service;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.model.ProbeResponse;

import java.util.List;

public interface ProbeService {
    ProbeCreationResponse createProbe(ProbeCreationRequest request);

    List<ProbeResponse> findAllProbes();

    ProbeResponse findProbeById(String id);
}
