package org.nasa.exploration.api.service.instruction;

import org.nasa.exploration.model.ProbeAggregate;

public class MoveInstruction implements Instruction {

    private ProbeAggregate probeAggregate;

    public MoveInstruction(ProbeAggregate probeAggregate) {
        this.probeAggregate = probeAggregate;
    }

    @Override
    public void execute() {
        probeAggregate.move();
    }
}
