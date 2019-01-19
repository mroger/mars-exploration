package org.nasa.exploration.model.exception;

public class PositionAlreadyTakenException extends RuntimeException {
    public PositionAlreadyTakenException(String message) {
        super(message);
    }
}
