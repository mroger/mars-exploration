package org.nasa.exploration.model;

public enum Direction {
    N {
        public Direction rotateLeft() {
            return W;
        }

        public Direction rotateRight() {
            return E;
        }

        public Position moveOneStep(int x, int y) {
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

        public Position moveOneStep(int x, int y) {
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

        public Position moveOneStep(int x, int y) {
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

        public Position moveOneStep(int x, int y) {
            return new Position(x+1, y);
        }
    };

    public abstract Direction rotateLeft();

    public abstract Direction rotateRight();

    public abstract Position moveOneStep(int x, int y);
}
