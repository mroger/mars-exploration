package org.nasa.exploration.api.service.instruction;

import org.nasa.exploration.model.ProbeAggregate;

public class RotateRightInstruction implements Instruction {
    private ProbeAggregate probeAggregate;

    public RotateRightInstruction(ProbeAggregate probeAggregate) {
        this.probeAggregate = probeAggregate;
    }

    @Override
    public void execute() {
        probeAggregate.rotateRight();
    }
}
