package org.nasa.exploration.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ProbeAggregate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProbeAggregate.class);

    private Probe probe;

    private ProbeAggregate(ProbeAggregateBuilder builder) {
        this.probe = new Probe(builder.id, builder.position, builder.direction, builder.plateau);
    }

    /**
     * Builds an ProbeAggregate
     */
    public static class ProbeAggregateBuilder {

        private UUID id;

        private Plateau plateau;

        private Direction direction;

        private Position position;
        public ProbeAggregateBuilder(final Plateau plateau) {
            this.plateau = plateau;
        }
        public ProbeAggregateBuilder(final int width, final int height) {
            this.plateau = new Plateau(width, height);
        }
        public ProbeAggregateBuilder id(UUID id) {
            this.id = id;
            return this;
        }
        public ProbeAggregateBuilder direction(String direction) {
            this.direction = Direction.valueOf(direction);
            return this;
        }
        public ProbeAggregateBuilder position(final int x, final int y) {
            this.position = new Position(x, y);
            return this;
        }
        public ProbeAggregate build() {
            return new ProbeAggregate(this);
        }

    }
    /**
     * Returns the probe id
     *
     * @return the probe id
     */
    public String getId() {
        return probe.getId();
    }

    /**
     * Returns the x-coordinate of the probe position
     *
     * @return the x-coordinate of the probe position
     */
    public int getXPosition() {
        return probe.getXPosition();
    }

    /**
     * Returns the y-coordinate of the probe position
     *
     * @return the y-coordinate of the probe position
     */
    public int getYPosition() {
        return probe.getYPosition();
    }

    /**
     * Returns the direction of the probe as one of the characters:
     * N, S, E or W
     *
     * @return the direction of the probe as one character
     */
    public String getDirection() {
        return probe.getDirection().toString();
    }

    /**
     * Rotates the probe left
     */
    public void rotateLeft() {
        LOGGER.info("Rotating probe left");
        this.probe.rotateLeft();
    }

    /**
     * Rotates the probe right
     */
    public void rotateRight() {
        LOGGER.info("Rotating probe right");
        this.probe.rotateRight();
    }

    /**
     * Moves the probe one step to its facing direction
     */
    public void move() {
        LOGGER.info("Moving probe");
        this.probe.moveOneStep();
    }

    /**
     * Returns the new position for the probe when it moves
     *
     * @return the next position assumed by the probe
     */
    public Position calculateNextPosition() {
        return this.probe.calculateNextPosition();
    }
}
