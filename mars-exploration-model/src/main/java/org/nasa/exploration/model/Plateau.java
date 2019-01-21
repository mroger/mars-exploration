package org.nasa.exploration.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

final class Plateau {

    private static final Logger LOGGER = LoggerFactory.getLogger(Plateau.class);

    private final int width;
    private final int height;

    /**
     * Constructed a Plateau where the probes will be positioned.
     *
     * @param width plateau width
     * @param height plateau height
     */
    Plateau(final int width, final int height) {
        LOGGER.info("Creating a plateau with width = {} and height = {}", width, height);

        if (width < 0) {
            LOGGER.error("Trying to create a plateau with negative width: {}", width);
            throw new IllegalArgumentException("Plateau width should not be a negative number");
        }
        if (height < 0) {
            LOGGER.error("Trying to create a plateau with negative height: {}", height);
            throw new IllegalArgumentException("Plateau height should not be a negative number");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Plateau width
     *
     * @return plateau width
     */
    int getWidth() {
        return width;
    }

    /**
     * Plateau height
     *
     * @return plateau height
     */
    int getHeight() {
        return height;
    }

    /**
     * Checks if the position passed as parameter is inside the plateau area
     *
     * @param position the position being tested
     * @return true if the point is inside the plateau, false otherwise
     */
    boolean contains(Position position) {
        return position.getX() <= width && position.getY() <= height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plateau plateau = (Plateau) o;
        return getWidth() == plateau.getWidth() &&
            getHeight() == plateau.getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight());
    }
}
