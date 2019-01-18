package org.nasa.exploration.model;

import org.nasa.exploration.model.exception.PositionAlreadyTakenException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.nasa.exploration.model.Direction.isValid;

public class MissionControl {

    private final Map<UUID, ProbeAggregate> probesByUUID;
    private final Map<Position, ProbeAggregate> probesByPosition;
    private final Plateau plateau;

    public MissionControl(int width, int height) {
        if (width < 0) {
            throw new IllegalArgumentException("Plateau width should not be a negative number");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Plateau height should not be a negative number");
        }
        plateau = new Plateau(width, height);
        probesByUUID = new ConcurrentHashMap<>();
        probesByPosition = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param x
     * @param y
     * @param facingDirection
     * @return
     */
    public ProbeAggregate createProbe(int x, int y, String facingDirection) {
        validateProbeCreationParameters(x, y, facingDirection);

        final UUID id = UUID.randomUUID();
        final Position position = new Position(x, y);

        validateNewProbePosition(position);

        ProbeAggregate probeAggregate = new ProbeAggregate.ProbeAggregateBuilder(plateau)
            .id(id)
            .position(position)
            .direction(Direction.valueOf(facingDirection))
            .build();

        probesByUUID.put(id, probeAggregate);
        probesByPosition.put(position, probeAggregate);

        return probeAggregate;
    }

    public Optional<ProbeAggregate> getProbeAggregateById(String id) {
        if (isEmpty(id)) {
            throw new IllegalArgumentException("Probe id should not be null nor empty");
        }
        return Optional.ofNullable(probesByUUID.get(UUID.fromString(id)));
    }

    public int getPlateauWidth() {
        return plateau.getWidth();
    }

    public int getPlateauHeight() {
        return plateau.getHeight();
    }

    private void validateProbeCreationParameters(int x, int y, String facingDirection) {
        if (x < 0) {
            throw new IllegalArgumentException("X coordinate should not be a negative number");
        }
        if (y < 0) {
            throw new IllegalArgumentException("Y coordinate should not be a negative number");
        }
        if (isEmpty(facingDirection)) {
            throw new IllegalArgumentException("The facing direction should not be null nor empty");
        }
        if (!isValid(facingDirection)) {
            throw new IllegalArgumentException("The facing direction should be N, S, E or W");
        }
    }

    private void validateNewProbePosition(Position position) {
        final ProbeAggregate probe = probesByPosition.get(position);
        if (probe != null) {
            throw new PositionAlreadyTakenException("Position already taken by probe [" + probe.getId() + "]");
        }
    }
}
