package org.nasa.exploration.model;

import java.util.UUID;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

final class Probe {

    private final UUID id;
    private Position position;
    private Direction direction;
    private final Plateau plateau;

    Probe(final UUID id, final Position position, final Direction direction, final Plateau plateau) {
        validateNonNullableParameters(id, position, direction, plateau);

        this.id = id;
        this.position = position;
        this.direction = direction;
        this.plateau = plateau;

        validatePosition(position);
    }

    String getId() {
        return id.toString();
    }

    Position getPosition() {
        return position;
    }

    Direction getDirection() {
        return direction;
    }

    void rotateLeft() {
        this.direction = direction.rotateLeft();
    }

    void rotateRight() {
        this.direction = direction.rotateRight();
    }

    void moveOneStep() {
        Position newPosition = direction.calculateNextPosition(position.getX(), position.getY());
        if (!plateau.contains(newPosition)) {
            throw new PositionOutOfBoundsException("New position is out of bounds");
        }
        this.position = newPosition;
    }

    private void validateNonNullableParameters(UUID id, Position position, Direction direction, Plateau plateau) {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("Position should not be null");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Direction should not be null");
        }
        if (plateau == null) {
            throw new IllegalArgumentException("Plateau should not be null");
        }
    }

    private void validatePosition(Position position) {
        if (!plateau.contains(position)) {
            throw new PositionOutOfBoundsException("New position is out of bounds");
        }
    }

    int getXPosition() {
        return position.getX();
    }

    int getYPosition() {
        return position.getY();
    }
}
