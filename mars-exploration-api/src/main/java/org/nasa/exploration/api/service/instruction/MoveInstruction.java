package org.nasa.exploration.api.service.instruction;

import org.nasa.exploration.api.exception.ProbeCollisionException;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;

public class MoveInstruction implements Instruction {

    private ProbeAggregate probeAggregate;
    private MissionControl missionControl;

    public MoveInstruction(ProbeAggregate probeAggregate, MissionControl missionControl) {
        this.probeAggregate = probeAggregate;
        this.missionControl = missionControl;
    }

    @Override
    public void execute() {
        if (missionControl.probeMoveCausesCollision(probeAggregate)) {
            throw new ProbeCollisionException("missioncontrol.probe.collision", probeAggregate.getId());
        }
        probeAggregate.move();
    }
}
