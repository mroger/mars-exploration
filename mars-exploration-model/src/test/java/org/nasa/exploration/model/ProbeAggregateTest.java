package org.nasa.exploration.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbeAggregateTest {

    private static final Plateau PLATEAU = new Plateau(20, 30);

    private ProbeAggregate.ProbeAggregateBuilder aggregateBuilder;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        aggregateBuilder = new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
            .id(id)
            .position(10, 15);
    }

    @Nested
    class WhenNew {
        @Test
        void shouldCreateProbe() {
            UUID id = UUID.randomUUID();
            final ProbeAggregate probeAggregate = new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
                .id(id)
                .position(10, 15)
                .direction("S")
                .build();

            assertThat(probeAggregate.getId()).isEqualTo(id.toString());
            assertThat(probeAggregate.getXPosition()).isEqualTo(10);
            assertThat(probeAggregate.getYPosition()).isEqualTo(15);
            assertThat(probeAggregate.getDirection()).isEqualTo(Direction.S.toString());
        }

        @Test
        void shouldThrowException_whenPositioningProbeOutsidePlateau() {
            Throwable exception = assertThrows(
                PositionOutOfBoundsException.class, () -> new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
                    .id(UUID.randomUUID())
                    .position(25, 35)
                    .direction("S")
                    .build()
            );

            assertEquals("Trying to put the probe out of bounds", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenMissingPlateauParameter() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new ProbeAggregate.ProbeAggregateBuilder(null)
                    .id(UUID.randomUUID())
                    .position(10, 15)
                    .direction("S")
                    .build()
            );

            assertEquals("Plateau should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenMissingIdParameter() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
                    .position(10, 15)
                    .direction("S")
                    .build()
            );

            assertEquals("Id should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenMissingPositionParameter() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
                    .id(UUID.randomUUID())
                    .direction("S")
                    .build()
            );

            assertEquals("Position should not be null", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenMissingDirectionParameter() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new ProbeAggregate.ProbeAggregateBuilder(PLATEAU)
                    .id(UUID.randomUUID())
                    .position(10, 15)
                    .build()
            );

            assertEquals("Direction should not be null", exception.getMessage());
        }
    }

    @Nested
    class WhenRotate {
        @Test
        void shouldRotateProbeLeft_whenFacingNorth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("N")
                .build();
            probeAggregate.rotateLeft();

            assertThat(probeAggregate.getDirection()).isEqualTo("W");
        }

        @Test
        void shouldRotateProbeLeft_whenFacingEast() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("E")
                .build();
            probeAggregate.rotateLeft();

            assertThat(probeAggregate.getDirection()).isEqualTo("N");
        }

        @Test
        void shouldRotateProbeLeft_whenFacingSouth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("S")
                .build();
            probeAggregate.rotateLeft();

            assertThat(probeAggregate.getDirection()).isEqualTo("E");
        }

        @Test
        void shouldRotateProbeLeft_whenFacingWest() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("W")
                .build();
            probeAggregate.rotateLeft();

            assertThat(probeAggregate.getDirection()).isEqualTo("S");
        }

        @Test
        void shouldRotateProbeRight_whenFacingNorth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("N")
                .build();
            probeAggregate.rotateRight();

            assertThat(probeAggregate.getDirection()).isEqualTo("E");
        }

        @Test
        void shouldRotateProbeRight_whenFacingEast() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("E")
                .build();
            probeAggregate.rotateRight();

            assertThat(probeAggregate.getDirection()).isEqualTo("S");
        }

        @Test
        void shouldRotateProbeRight_whenFacingSouth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("S")
                .build();
            probeAggregate.rotateRight();

            assertThat(probeAggregate.getDirection()).isEqualTo("W");
        }

        @Test
        void shouldRotateProbeRight_whenFacingWest() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("W")
                .build();
            probeAggregate.rotateRight();

            assertThat(probeAggregate.getDirection()).isEqualTo("N");
        }
    }

    @Nested
    class WhenMove {
        @Test
        void shouldMoveProve_whenFacingNorth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("N")
                .build();
            probeAggregate.move();

            assertThat(probeAggregate.getXPosition()).isEqualTo(10);
            assertThat(probeAggregate.getYPosition()).isEqualTo(16);
        }

        @Test
        void shouldMoveProve_whenFacingEast() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("E")
                .build();
            probeAggregate.move();

            assertThat(probeAggregate.getXPosition()).isEqualTo(11);
            assertThat(probeAggregate.getYPosition()).isEqualTo(15);
        }

        @Test
        void shouldMoveProve_whenFacingSouth() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("S")
                .build();
            probeAggregate.move();

            assertThat(probeAggregate.getXPosition()).isEqualTo(10);
            assertThat(probeAggregate.getYPosition()).isEqualTo(14);
        }

        @Test
        void shouldMoveProve_whenFacingWest() {
            final ProbeAggregate probeAggregate = aggregateBuilder
                .direction("W")
                .build();
            probeAggregate.move();

            assertThat(probeAggregate.getXPosition()).isEqualTo(9);
            assertThat(probeAggregate.getYPosition()).isEqualTo(15);
        }

        @Test
        void shouldThrowException_whenMovingProbeOutsidePlateau() {
            UUID id = UUID.randomUUID();
            final ProbeAggregate probeAggregate = new ProbeAggregate
                .ProbeAggregateBuilder(new Plateau(20, 30))
                .id(id)
                .position(10, 30)
                .direction("N")
                .build();

            Throwable exception = assertThrows(
                PositionOutOfBoundsException.class, probeAggregate::move
            );

            assertEquals("New position is out of bounds", exception.getMessage());
        }
    }
}
