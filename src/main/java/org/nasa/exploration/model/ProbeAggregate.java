package org.nasa.exploration.model;

import java.util.UUID;

public class ProbeAggregate {

    private Probe probe;

    private ProbeAggregate(ProbeAggregateBuilder builder) {
        this.probe = new Probe(builder.id, builder.position, builder.direction, builder.plateau);
    }

    public static class ProbeAggregateBuilder {
        private UUID id;
        private Plateau plateau;
        private Direction direction;
        private Position position;

        public ProbeAggregateBuilder(final Plateau plateau) {
            this.plateau = plateau;
        }

        public ProbeAggregateBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ProbeAggregateBuilder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public ProbeAggregateBuilder position(Position position) {
            this.position = position;
            return this;
        }

        public ProbeAggregate build() {
            return new ProbeAggregate(this);
        }
    }

    public String getId() {
        return probe.getId();
    }

    public int getXPosition() {
        return probe.getXPosition();
    }

    public int getYPosition() {
        return probe.getYPosition();
    }

    public String getDirection() {
        return probe.getDirection().toString();
    }
}
