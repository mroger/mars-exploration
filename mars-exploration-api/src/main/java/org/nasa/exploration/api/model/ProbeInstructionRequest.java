package org.nasa.exploration.api.model;

import org.nasa.exploration.api.validation.ValidateStringOptions;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProbeInstructionRequest {

    @NotNull(message = "missioncontrol.probe.required.id")
    private String id;
    @NotNull(message = "missioncontrol.probe.required.instruction")
    @ValidateStringOptions(enumClass = Instruction.class, message = "missioncontrol.probe.invalid.instruction")
    private String instruction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
