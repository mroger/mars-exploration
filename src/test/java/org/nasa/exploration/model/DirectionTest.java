package org.nasa.exploration.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Nested
    class WhenFacingNorth {
        @Test
        void shouldBeFacingWest_whenRotateLeft() {
            Direction newDirection = Direction.N.rotateLeft();
            assertThat(newDirection).isEqualTo(Direction.W);
        }

        @Test
        void shouldBeFacingEast_whenRotateRight() {
            Direction newDirection = Direction.N.rotateRight();
            assertThat(newDirection).isEqualTo(Direction.E);
        }

        @Test
        void shouldMoveNorth() {
            Position newPosition = Direction.N.calculateNextPosition(10, 15);

            assertThat(newPosition).isEqualTo(new Position(10, 16));
        }
    }

    @Nested
    class WhenFacingEast {
        @Test
        void shouldBeFacingNorth_whenRotateLeft() {
            Direction newDirection = Direction.E.rotateLeft();
            assertThat(newDirection).isEqualTo(Direction.N);
        }

        @Test
        void shouldBeFacingSouth_whenRotateRight() {
            Direction newDirection = Direction.E.rotateRight();
            assertThat(newDirection).isEqualTo(Direction.S);
        }

        @Test
        void shouldMoveEast() {
            Position newPosition = Direction.E.calculateNextPosition(10, 15);

            assertThat(newPosition).isEqualTo(new Position(11, 15));
        }
    }

    @Nested
    class WhenFacingSouth {
        @Test
        void shouldBeFacingEast_whenRotateLeft() {
            Direction newDirection = Direction.S.rotateLeft();
            assertThat(newDirection).isEqualTo(Direction.E);
        }

        @Test
        void shouldBeFacingWest_whenRotateRight() {
            Direction newDirection = Direction.S.rotateRight();
            assertThat(newDirection).isEqualTo(Direction.W);
        }

        @Test
        void shouldMoveSouth() {
            Position newPosition = Direction.S.calculateNextPosition(10, 15);

            assertThat(newPosition).isEqualTo(new Position(10, 14));
        }
    }

    @Nested
    class WhenFacingWest {
        @Test
        void shouldBeFacingSouth_whenRotateLeft() {
            Direction newDirection = Direction.W.rotateLeft();
            assertThat(newDirection).isEqualTo(Direction.S);
        }

        @Test
        void shouldBeFacingNorth_whenRotateRight() {
            Direction newDirection = Direction.W.rotateRight();
            assertThat(newDirection).isEqualTo(Direction.N);
        }

        @Test
        void shouldMoveWest() {
            Position newPosition = Direction.W.calculateNextPosition(10, 15);

            assertThat(newPosition).isEqualTo(new Position(9, 15));
        }
    }
}
