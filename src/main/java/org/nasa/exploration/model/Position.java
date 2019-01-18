package org.nasa.exploration.model;

import java.util.Objects;

final class Position {

    private final int x;
    private final int y;

    Position(final int x, final int y) {
        if (x < 0) {
            throw new IllegalArgumentException("X coordinate should not be a negative number");
        }
        if (y < 0) {
            throw new IllegalArgumentException("Y coordinate should not be a negative number");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x &&
            y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
