package org.nasa.exploration.model;

import java.util.Arrays;

enum Direction {
    N {
        public Direction rotateLeft() {
            return W;
        }

        public Direction rotateRight() {
            return E;
        }

        public Position calculateNextPosition(int x, int y) {
            return new Position(x, y+1);
        }
    },
    S {
        public Direction rotateLeft() {
            return E;
        }

        public Direction rotateRight() {
            return W;
        }

        public Position calculateNextPosition(int x, int y) {
            return new Position(x, y-1);
        }
    },
    W {
        public Direction rotateLeft() {
            return S;
        }

        public Direction rotateRight() {
            return N;
        }

        public Position calculateNextPosition(int x, int y) {
            return new Position(x-1, y);
        }
    },
    E {
        public Direction rotateLeft() {
            return N;
        }

        public Direction rotateRight() {
            return S;
        }

        public Position calculateNextPosition(int x, int y) {
            return new Position(x+1, y);
        }
    };

    public static boolean isValid(String facingDirection) {
        return Arrays.asList("N", "S", "E", "W").contains(facingDirection);
    }

    public abstract Direction rotateLeft();

    public abstract Direction rotateRight();

    public abstract Position calculateNextPosition(int x, int y);
}
