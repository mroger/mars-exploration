package org.nasa.exploration.api.exception;

public class ProbeNotFoundByPositionException extends RuntimeException {
    private final int x;
    private final int y;

    public ProbeNotFoundByPositionException(int x, int y) {
        super("missioncontrol.probe.position.not-found");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
