package org.nasa.exploration.api.model;

import java.util.List;

public class ProbeListResponse {

    private List<ProbeResponse> probeResponses;

    public ProbeListResponse(List<ProbeResponse> probeResponses) {
        this.probeResponses = probeResponses;
    }

    public List<ProbeResponse> getProbes() {
        return probeResponses;
    }

    public int getSize() {
        if (probeResponses == null) {
            return 0;
        }
        return probeResponses.size();
    }
}
