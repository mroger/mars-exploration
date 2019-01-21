package org.nasa.exploration.api.exception;

public class ProbeNotFoundByIdException extends RuntimeException {
    private String id;

    public ProbeNotFoundByIdException(String id) {
        super("missioncontrol.probe.id.not-found");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
