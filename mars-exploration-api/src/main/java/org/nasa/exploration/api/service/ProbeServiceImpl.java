package org.nasa.exploration.api.service;

import org.nasa.exploration.api.exception.ProbeNotFoundByIdException;
import org.nasa.exploration.api.exception.ProbeNotFoundByPositionException;
import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.model.ProbeResponse;
import org.nasa.exploration.api.service.instruction.Instruction;
import org.nasa.exploration.api.service.instruction.InstructionFactory;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProbeServiceImpl implements ProbeService {

    private MissionControl missionControl;

    public ProbeServiceImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
    }

    @Override
    public ProbeCreationResponse createProbe(ProbeCreationRequest request) {
        final ProbeAggregate probe =
            missionControl.createProbe(request.getX(), request.getY(), request.getDirection());
        return ProbeCreationResponse.fromModel(probe);
    }

    @Override
    public List<ProbeResponse> findAllProbes() {
        return missionControl.getRegisteredProbeAggregates().stream()
            .map(ProbeResponse::fromModel)
            .collect(Collectors.toList());
    }

    @Override
    public ProbeResponse findProbeById(String id) {
        final Optional<ProbeAggregate> probeAggregate = missionControl.getProbeAggregateById(id);
        if (!probeAggregate.isPresent()) {
            throw new ProbeNotFoundByIdException(id);
        }
        return ProbeResponse.fromModel(probeAggregate.get());
    }

    @Override
    public ProbeResponse findProbeByPosition(int x, int y) {
        final Optional<ProbeAggregate> probeAggregate = missionControl.getProbeAggregateByPosition(x, y);
        if (!probeAggregate.isPresent()) {
            throw new ProbeNotFoundByPositionException(x, y);
        }
        return ProbeResponse.fromModel(probeAggregate.get());
    }

    @Override
    public ProbeResponse processInstruction(String id, String instruction) {
        final Optional<ProbeAggregate> probeAggregate = missionControl.getProbeAggregateById(id);
        if (!probeAggregate.isPresent()) {
            throw new ProbeNotFoundByIdException(id);
        }
        ProbeAggregate probeAggregateGet = probeAggregate.get();

        Instruction instructionImpl = InstructionFactory.makeInstruction(probeAggregateGet, instruction);
        instructionImpl.execute();

        return ProbeResponse.fromModel(probeAggregate.get());
    }
}
