package org.nasa.exploration.api.service;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;
import org.springframework.stereotype.Service;

@Service
public class MarsExplorationServiceImpl implements MarsExplorationService {

    private MissionControl missionControl;

    public MarsExplorationServiceImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
    }

    @Override
    public ProbeCreationResponse createProbe(ProbeCreationRequest request) {
        final ProbeAggregate probe =
            missionControl.createProbe(request.getX(), request.getY(), request.getDirection());
        return ProbeCreationResponse.fromModel(probe);
    }
}
