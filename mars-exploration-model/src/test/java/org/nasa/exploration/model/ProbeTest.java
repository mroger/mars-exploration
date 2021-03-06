package org.nasa.exploration.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

import java.util.UUID;

class ProbeTest {

    private static final Direction NORTH = Direction.N;
    private static final Direction WEST = Direction.W;
    private static final Direction EAST = Direction.E;
    private static final Direction SOUTH = Direction.S;

    private static final UUID ANY_ID = UUID.randomUUID();

    private Plateau plateau;

    @BeforeEach
    void setUp() {
        plateau = new Plateau(10, 10);
    }

    @Nested
    class WhenNew {

        @Test
        void shouldThrowException_whenIdIsNull() {
            Position position = new Position(2, 2);
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Probe(null, position, SOUTH, plateau)
            );

            assertEquals("Id should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenPositionIsNull() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Probe(ANY_ID,null, SOUTH, plateau)
            );

            assertEquals("Position should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenDirectionIsNull() {
            Position position = new Position(2, 2);
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Probe(ANY_ID, position, null, plateau)
            );

            assertEquals("Direction should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenPlateauIsNull() {
            Position position = new Position(2, 2);
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Probe(ANY_ID, position, EAST, null)
            );

            assertEquals("Plateau should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenTryingToPutProbeOutsidePlateau() {
            Position position = new Position(10, 20);
            Throwable exception = assertThrows(
                PositionOutOfBoundsException.class, () -> new Probe(ANY_ID, position, EAST, plateau)
            );

            assertEquals("Trying to put the probe out of bounds", exception.getMessage());
        }

        @Test
        void shouldCreateProbe() {
            Position position = new Position(2, 2);
            Direction direction = SOUTH;
            UUID id = UUID.randomUUID();
            Probe probe = new Probe(id, position, direction, plateau);

            assertThat(probe.getPosition()).isEqualTo(new Position(2, 2));
            assertThat(probe.getDirection()).isEqualTo(direction);
        }
    }

    @Nested
    class WhenRotate {

        @Test
        void shouldRotateProbeLeft_whenFacingNorth() {
            Probe probe = createProbe(5, 5, NORTH, plateau);
            probe.rotateLeft();

            assertThat(probe.getDirection()).isEqualTo(WEST);
        }

        @Test
        void shouldRotateProbeLeft_whenFacingEast() {
            Probe probe = createProbe(7, 5, EAST, plateau);
            probe.rotateLeft();

            assertThat(probe.getDirection()).isEqualTo(NORTH);
        }

        @Test
        void shouldRotateProbeLeft_whenFacingSouth() {
            Probe probe = createProbe(9, 5, SOUTH, plateau);
            probe.rotateLeft();

            assertThat(probe.getDirection()).isEqualTo(EAST);
        }

        @Test
        void shouldRotateProbeLeft_whenFacingWest() {
            Probe probe = createProbe(2, 5, WEST, plateau);
            probe.rotateLeft();

            assertThat(probe.getDirection()).isEqualTo(SOUTH);
        }

        @Test
        void shouldRotateProbeRight_whenFacingNorth() {
            Probe probe = createProbe(5, 5, NORTH, plateau);
            probe.rotateRight();

            assertThat(probe.getDirection()).isEqualTo(EAST);
        }

        @Test
        void shouldRotateProbeRight_whenFacingEast() {
            Probe probe = createProbe(1, 5, EAST, plateau);
            probe.rotateRight();

            assertThat(probe.getDirection()).isEqualTo(SOUTH);
        }

        @Test
        void shouldRotateProbeRight_whenFacingSouth() {
            Probe probe = createProbe(5, 5, SOUTH, plateau);
            probe.rotateRight();

            assertThat(probe.getDirection()).isEqualTo(WEST);
        }

        @Test
        void shouldRotateProbeRight_whenFacingWest() {
            Probe probe = createProbe(5, 5, WEST, plateau);
            probe.rotateRight();

            assertThat(probe.getDirection()).isEqualTo(NORTH);
        }
    }

    @Nested
    class WhenMove {

        @Test
        void shouldMoveProbe_whenFacingNorth() {
            Probe probe = createProbe(5, 3, NORTH, plateau);
            probe.moveOneStep();

            assertThat(probe.getPosition()).isEqualTo(new Position(5, 4));
        }

        @Test
        void shouldMoveProbe_whenFacingEast() {
            Probe probe = createProbe(5, 3, EAST, plateau);
            probe.moveOneStep();

            assertThat(probe.getPosition()).isEqualTo(new Position(6, 3));
        }

        @Test
        void shouldMoveProbe_whenFacingSouth() {
            Probe probe = createProbe(5, 3, SOUTH, plateau);
            probe.moveOneStep();

            assertThat(probe.getPosition()).isEqualTo(new Position(5, 2));
        }

        @Test
        void shouldMoveProbe_whenFacingWest() {
            Probe probe = createProbe(5, 3, WEST, plateau);
            probe.moveOneStep();

            assertThat(probe.getPosition()).isEqualTo(new Position(4, 3));
        }

        @Test
        void shouldThrowException_whenFacingEastAndMovingOutOfXBound() {
            Probe probe = createProbe(10, 3, EAST, plateau);

            Throwable exception = assertThrows(
                PositionOutOfBoundsException.class, probe::moveOneStep
            );

            assertEquals("New position is out of bounds", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenFacingWestAndMovingOutOfXBound() {
            Probe probe = createProbe(0, 3, WEST, plateau);

            Throwable exception = assertThrows(
                IllegalArgumentException.class, probe::moveOneStep
            );

            assertEquals("X coordinate should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenFacingNorthAndMovingOutOfXBound() {
            Probe probe = createProbe(6, 10, NORTH, plateau);

            Throwable exception = assertThrows(
                PositionOutOfBoundsException.class, probe::moveOneStep
            );

            assertEquals("New position is out of bounds", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenFacingSouthAndMovingOutOfXBound() {
            Probe probe = createProbe(7, 0, SOUTH, plateau);

            Throwable exception = assertThrows(
                IllegalArgumentException.class, probe::moveOneStep
            );

            assertEquals("Y coordinate should not be a negative number", exception.getMessage());
        }
    }

    private Probe createProbe(int x, int y, Direction d, Plateau plateau) {
        return new Probe(ANY_ID, new Position(x, y), d, plateau);
    }
}
