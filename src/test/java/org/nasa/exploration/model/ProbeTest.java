package org.nasa.exploration.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProbeTest {

    private static final Direction NORTH = Direction.N;
    private static final Direction WEST = Direction.W;
    private static final Direction EAST = Direction.E;
    private static final Direction SOUTH = Direction.S;

    @Test
    void shouldCreateProbe() {
        Position position = new Position(2, 2);
        Direction direction = SOUTH;
        Probe probe = new Probe(position, direction);

        assertThat(probe.getPosition()).isEqualTo(new Position(2, 2));
        assertThat(probe.getDirection()).isEqualTo(direction);
    }

    @Test
    void shouldRotateProbeLeft_whenFacingNorth() {
        Probe probe = createProbe(5, 5, NORTH);
        probe.rotateLeft();

        assertThat(probe.getDirection()).isEqualTo(WEST);
    }

    @Test
    void shouldRotateProbeLeft_whenFacingEast() {
        Probe probe = createProbe(5, 5, EAST);
        probe.rotateLeft();

        assertThat(probe.getDirection()).isEqualTo(NORTH);
    }

    @Test
    void shouldRotateProbeLeft_whenFacingSouth() {
        Probe probe = createProbe(5, 5, SOUTH);
        probe.rotateLeft();

        assertThat(probe.getDirection()).isEqualTo(EAST);
    }

    @Test
    void shouldRotateProbeLeft_whenFacingWest() {
        Probe probe = createProbe(5, 5, WEST);
        probe.rotateLeft();

        assertThat(probe.getDirection()).isEqualTo(SOUTH);
    }

    @Test
    void shouldRotateProbeRight_whenFacingNorth() {
        Probe probe = createProbe(5, 5, NORTH);
        probe.rotateRight();

        assertThat(probe.getDirection()).isEqualTo(EAST);
    }

    @Test
    void shouldRotateProbeRight_whenFacingEast() {
        Probe probe = createProbe(5, 5, EAST);
        probe.rotateRight();

        assertThat(probe.getDirection()).isEqualTo(SOUTH);
    }

    @Test
    void shouldRotateProbeRight_whenFacingSouth() {
        Probe probe = createProbe(5, 5, SOUTH);
        probe.rotateRight();

        assertThat(probe.getDirection()).isEqualTo(WEST);
    }

    @Test
    void shouldRotateProbeRight_whenFacingWest() {
        Probe probe = createProbe(5, 5, WEST);
        probe.rotateRight();

        assertThat(probe.getDirection()).isEqualTo(NORTH);
    }

    @Test
    void shouldMoveProbe_whenFacingNorth() {
        Probe probe = createProbe(5, 3, NORTH);
        probe.moveOneStep();

        assertThat(probe.getPosition()).isEqualTo(new Position(5, 4));
    }

    @Test
    void shouldMoveProbe_whenFacingEast() {
        Probe probe = createProbe(5, 3, EAST);
        probe.moveOneStep();

        assertThat(probe.getPosition()).isEqualTo(new Position(6, 3));
    }

    @Test
    void shouldMoveProbe_whenFacingSouth() {
        Probe probe = createProbe(5, 3, SOUTH);
        probe.moveOneStep();

        assertThat(probe.getPosition()).isEqualTo(new Position(5, 2));
    }

    @Test
    void shouldMoveProbe_whenFacingWest() {
        Probe probe = createProbe(5, 3, WEST);
        probe.moveOneStep();

        assertThat(probe.getPosition()).isEqualTo(new Position(4, 3));
    }

    private Probe createProbe(int x, int y, Direction d) {
        return new Probe(new Position(x, y), d);
    }
}
