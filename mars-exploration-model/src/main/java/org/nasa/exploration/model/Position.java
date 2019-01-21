package org.nasa.exploration.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

final class Position {

    private static final Logger LOGGER = LoggerFactory.getLogger(Plateau.class);

    private final int x;
    private final int y;

    /**
     * Construct a position object
     *
     * @param x x-coordinate of the position
     * @param y y-coordinate of the position
     */
    Position(final int x, final int y) {
        if (x < 0) {
            LOGGER.error("Trying to build a position with negative x coordinate: {}", x);
            throw new IllegalArgumentException("X coordinate should not be a negative number");
        }
        if (y < 0) {
            LOGGER.error("Trying to build a position with negative y coordinate: {}", y);
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

    /**
     * The x-coordinate of the position
     *
     * @return the x-coordinate of the position
     */
    int getX() {
        return x;
    }

    /**
     * The y-coordinate of the position
     *
     * @return the y-coordinate of the position
     */
    int getY() {
        return y;
    }
}
