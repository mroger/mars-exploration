package org.nasa.exploration.api.exception;

public class ProbesNotFoundException extends RuntimeException {

    public ProbesNotFoundException() {
        super("missioncontrol.probes.not-found");
    }
}
