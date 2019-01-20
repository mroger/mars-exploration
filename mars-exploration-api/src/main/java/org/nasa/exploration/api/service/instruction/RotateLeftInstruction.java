package org.nasa.exploration.api.service.instruction;

import org.nasa.exploration.model.ProbeAggregate;

public class RotateLeftInstruction implements Instruction {
    private ProbeAggregate probeAggregate;

    public RotateLeftInstruction(ProbeAggregate probeAggregate) {
        this.probeAggregate = probeAggregate;
    }

    @Override
    public void execute() {
        probeAggregate.rotateLeft();
    }
}
