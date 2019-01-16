package org.nasa.exploration.model;

class Probe {

    private Position position;
    private Direction direction;

    Probe(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
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
        this.position = direction.moveOneStep(position.getX(), position.getY());
    }
}
