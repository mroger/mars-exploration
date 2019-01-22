package org.nasa.exploration.api.service.instruction;

import org.nasa.exploration.api.exception.InvalidInstructionException;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;

public class InstructionFactory {

    public static Instruction makeInstruction(ProbeAggregate probeAggregate, String instruction,
            MissionControl missionControl) {
        switch (instruction) {
            case "M": {
                return new MoveInstruction(probeAggregate, missionControl);
            }
            case "R": {
                return new RotateRightInstruction(probeAggregate);
            }
            case "L": {
                return new RotateLeftInstruction(probeAggregate);
            }
            default:
                throw new InvalidInstructionException(instruction);
        }
    }
}
