package org.nasa.exploration.model;

import java.util.UUID;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

final class Probe {

    private final UUID id;
    private Position position;
    private Direction direction;
    private final Plateau plateau;

    /**
     * Constructs a new probe
     *
     * @param id id of the probe
     * @param position position of the probe
     * @param direction the facing direction of the probe
     * @param plateau the plateau to which the probe position must conform
     */
    Probe(final UUID id, final Position position, final Direction direction, final Plateau plateau) {
        validateNonNullableParameters(id, position, direction, plateau);

        this.id = id;
        this.position = position;
        this.direction = direction;
        this.plateau = plateau;

        validatePosition(position);
    }

    /**
     * The probe id as string
     *
     * @return the probe id as string
     */
    String getId() {
        return id.toString();
    }

    /**
     * The probe position
     *
     * @return the probe position
     */
    Position getPosition() {
        return position;
    }

    /**
     * The probe direction
     *
     * @return the probe direction
     */
    Direction getDirection() {
        return direction;
    }

    /**
     * Rotates the probe to left
     */
    void rotateLeft() {
        this.direction = direction.rotateLeft();
    }

    /**
     * Rotates the probe to the right
     */
    void rotateRight() {
        this.direction = direction.rotateRight();
    }

    /**
     * Move the probe one step to the direction it's facing
     */
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

    /**
     * Returns the x-coordinate of the probe position
     *
     * @return the x-coordinate of the probe position
     */
    int getXPosition() {
        return position.getX();
    }

    /**
     * Returns the y-coordinate of the probe position
     *
     * @return the y-coordinate of the probe position
     */
    int getYPosition() {
        return position.getY();
    }
}
