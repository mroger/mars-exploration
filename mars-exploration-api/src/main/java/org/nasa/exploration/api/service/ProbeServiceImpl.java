package org.nasa.exploration.api.service;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.service.instruction.Instruction;
import org.nasa.exploration.api.service.instruction.InstructionFactory;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProbeServiceImpl implements ProbeService {

    private MissionControl missionControl;

    public ProbeServiceImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
    }

    @Override
    public ProbeAggregate createProbe(ProbeCreationRequest request) {
        return missionControl.createProbe(request.getX(), request.getY(), request.getDirection());
    }

    @Override
    public List<ProbeAggregate> findAllProbes() {
        return missionControl.getRegisteredProbeAggregates();
    }

    @Override
    public Optional<ProbeAggregate> findProbeById(String id) {
        return missionControl.getProbeAggregateById(id);
    }

    @Override
    public Optional<ProbeAggregate> findProbeByPosition(int x, int y) {
        return missionControl.getProbeAggregateByPosition(x, y);
    }

    @Override
    public Optional<ProbeAggregate> processInstruction(String id, String instruction) {
        final Optional<ProbeAggregate> probeAggregate = missionControl.getProbeAggregateById(id);
        if (!probeAggregate.isPresent()) {
            return Optional.empty();
        }
        ProbeAggregate probeAggregateGet = probeAggregate.get();

        Instruction instructionImpl = InstructionFactory.makeInstruction(probeAggregateGet, instruction, missionControl);
        instructionImpl.execute();

        return probeAggregate;
    }
}
