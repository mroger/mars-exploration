package org.nasa.exploration.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nasa.exploration.model.exception.PositionAlreadyTakenException;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;

class MissionControlTest {

    @Nested
    class WhenNew {
        @Test
        void shouldThrowException_whenPlateauWidthIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new MissionControl(-10, 10)
            );

            assertEquals("Plateau width should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenPlateauHeightIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new MissionControl(10, -10)
            );

            assertEquals("Plateau height should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldCreateMissionControl() {
            MissionControl missionControl = new MissionControl(10, 20);

            assertThat(missionControl.getPlateauWidth()).isEqualTo(10);
            assertThat(missionControl.getPlateauHeight()).isEqualTo(20);
        }
    }

    @Nested
    class WhenCreateProbe {
        private MissionControl missionControl;

        @BeforeEach
        void setUp() {
            missionControl = new MissionControl(10, 10);
        }

        @Test
        void shouldCreateProbeAggregate() {
            ProbeAggregate probeAggregate = missionControl.createProbe(5, 3, "E");

            assertThat(probeAggregate.getXPosition()).isEqualTo(5);
            assertThat(probeAggregate.getYPosition()).isEqualTo(3);
            assertThat(probeAggregate.getDirection()).isEqualTo("E");
        }

        @Test
        void shouldThrowException_whenXCoordinateIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.createProbe(-5, 3, "E")
            );

            assertEquals("X coordinate should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenYCoordinateIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.createProbe(5, -3, "E")
            );

            assertEquals("Y coordinate should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenFacingDirectionIsEmpty() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.createProbe(5, 3, "")
            );

            assertEquals("The facing direction should not be null nor empty", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenFacingDirectionIsInvalid() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.createProbe(5, 3, "A")
            );

            assertEquals("The facing direction should be N, S, E or W", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenProbeIsAlreadyInThePosition() {
            final ProbeAggregate probe = missionControl.createProbe(5, 3, "E");
            Throwable exception = assertThrows(
                PositionAlreadyTakenException.class, () -> missionControl.createProbe(5, 3, "W")
            );

            assertEquals("Position already taken by probe [" + probe.getId() + "]", exception.getMessage());
        }

        @Test
        void shouldGetProbeAggregateById() {
            ProbeAggregate newProbeAggregate = missionControl.createProbe(5, 3, "E");
            String id = newProbeAggregate.getId();

            Optional<ProbeAggregate> retrievedProbeAggregate = missionControl.getProbeAggregateById(id);
            assertTrue(retrievedProbeAggregate.isPresent());
        }

        @Test
        void shouldThrowException_whenGettingProbeByNullId() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.getProbeAggregateById(null)
            );

            assertEquals("Probe id should not be null nor empty", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenGettingProbeByEmptyId() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> missionControl.getProbeAggregateById("")
            );

            assertEquals("Probe id should not be null nor empty", exception.getMessage());
        }
    }
}
