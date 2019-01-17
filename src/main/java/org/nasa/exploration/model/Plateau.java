package org.nasa.exploration.model;

final class Plateau {

    private final int width;
    private final int height;

    public Plateau(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean contains(Position newPosition) {
        return newPosition.getX() <= width && newPosition.getY() <= height;
    }
}
