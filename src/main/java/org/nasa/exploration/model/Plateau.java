package org.nasa.exploration.model;

import java.util.Objects;

final class Plateau {

    private final int width;
    private final int height;

    Plateau(final int width, final int height) {
        if (width < 0) {
            throw new IllegalArgumentException("Plateau width should not be a negative number");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Plateau height should not be a negative number");
        }

        this.width = width;
        this.height = height;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    boolean contains(Position newPosition) {
        return newPosition.getX() <= width && newPosition.getY() <= height;
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
