package org.nasa.exploration.api.exception;

public class ProbeCollisionException extends RuntimeException {
    private String probeId;

    public ProbeCollisionException(String message, String probeId) {
        super(message);
        this.probeId = probeId;
    }

    public String getProbeId() {
        return probeId;
    }
}
