package org.nasa.exploration.model;

import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

final class Probe {

    private Position position;
    private Direction direction;
    private Plateau plateau;

    Probe(Position position, Direction direction, Plateau plateau) {
        if (position == null) {
            throw new IllegalArgumentException("Position should not be null");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Direction should not be null");
        }
        if (plateau == null) {
            throw new IllegalArgumentException("Plateau should not be null");
        }

        //TODO Validar que a posição esta dentro do plateau

        this.position = position;
        this.direction = direction;
        this.plateau = plateau;
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
        if (plateau.contains(newPosition)) {
            this.position = newPosition;
        } else {
            throw new PositionOutOfBoundsException("New position is out of bounds");
        }
    }
}
