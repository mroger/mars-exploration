package org.nasa.exploration.model.exception;

public class PositionAlreadyTakenException extends RuntimeException {
    private String id;

    public PositionAlreadyTakenException(String id) {
        super("missioncontrol.probe.position-already-taken");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
