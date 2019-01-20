package org.nasa.exploration.api.model;

import org.nasa.exploration.api.validation.ValidateStringOptions;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProbeCreationRequest {

    @NotNull(message = "missioncontrol.probe.required.x")
    @Min(value=0, message = "missioncontrol.probe.minx")
    private Integer x;
    @NotNull(message = "missioncontrol.probe.required.y")
    @Min(value=0, message = "missioncontrol.probe.miny")
    private Integer y;
    @NotNull(message = "missioncontrol.probe.required.direction")
    @ValidateStringOptions(enumClass = Direction.class, message = "missioncontrol.probe.invalid.direction")
    private String direction;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
