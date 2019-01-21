package org.nasa.exploration.model;

import org.nasa.exploration.model.exception.PositionAlreadyTakenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.nasa.exploration.model.Direction.isValid;

public class MissionControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MissionControl.class);

    private final Map<UUID, ProbeAggregate> probesByUUID;
    private final Map<Position, ProbeAggregate> probesByPosition;
    private final Plateau plateau;

    /**
     * Mission control class that holds probes created and put over a plateau
     *
     * @param width Width of the plateau
     * @param height Height of the plateau
     */
    public MissionControl(int width, int height) {
        if (width < 0) {
            LOGGER.error("Trying to create a plateau with negative width: {}", width);
            throw new IllegalArgumentException("Plateau width should not be a negative number");
        }
        if (height < 0) {
            LOGGER.error("Trying to create a plateau with negative height: {}", height);
            throw new IllegalArgumentException("Plateau height should not be a negative number");
        }
        plateau = new Plateau(width, height);
        probesByUUID = new ConcurrentHashMap<>();
        probesByPosition = new ConcurrentHashMap<>();

        LOGGER.info("Creating MissionControl with area {}x{}", width, height);
    }

    /**
     * Creates a probe and holds it in maps by UUID and by position
     *
     * @param x x-coordinate of the probe
     * @param y y-coordinate of the probe
     * @param facingDirection facing direction of the probe
     * @return a new ProbeAggregate
     */
    public ProbeAggregate createProbe(int x, int y, String facingDirection) {
        LOGGER.info("Adding probe at ({},{}) facing {}", x, y, facingDirection);

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

        LOGGER.info("There are {} probes on the plateau", probesByUUID.size());

        return probeAggregate;
    }

    /**
     * Searches a probe in the map by its id
     *
     * @param id id of the probe
     * @return a probe if its id exists in the map
     */
    public Optional<ProbeAggregate> getProbeAggregateById(String id) {
        if (isEmpty(id)) {
            throw new IllegalArgumentException("Probe id should not be null nor empty");
        }
        return Optional.ofNullable(probesByUUID.get(UUID.fromString(id)));
    }

    /**
     * Plateau width
     *
     * @return plateau width
     */
    public int getPlateauWidth() {
        return plateau.getWidth();
    }

    /**
     * Plateau height
     *
     * @return plateau height
     */
    public int getPlateauHeight() {
        return plateau.getHeight();
    }

    /**
     * Returns all created and registered probes
     *
     * @return List of created and registered probes
     */
    public List<ProbeAggregate> getRegisteredProbeAggregates() {
        return new ArrayList<>(probesByUUID.values());
    }

    /**
     * Searches and returns a probe by its position
     *
     * @param x x coordinate of the probe
     * @param y y coordinate of the probe
     * @return a probe if it exists in the given position
     */
    public Optional<ProbeAggregate> getProbeAggregateByPosition(int x, int y) {
        return Optional.ofNullable(probesByPosition.get(new Position(x, y)));
    }

    private void validateProbeCreationParameters(int x, int y, String facingDirection) {
        if (x < 0) {
            LOGGER.error("Trying to create probe with negative x coordinate: {}", x);
            throw new IllegalArgumentException("X coordinate should not be a negative number");
        }
        if (y < 0) {
            LOGGER.error("Trying to create probe with negative y coordinate: {}", y);
            throw new IllegalArgumentException("Y coordinate should not be a negative number");
        }
        if (isEmpty(facingDirection)) {
            LOGGER.error("Trying to create probe with null or empty direction");
            throw new IllegalArgumentException("The facing direction should not be null nor empty");
        }
        if (!isValid(facingDirection)) {
            LOGGER.error("Trying to create probe with invalid direction: {}", facingDirection);
            throw new IllegalArgumentException("The facing direction should be N, S, E or W");
        }
    }

    private void validateNewProbePosition(Position position) {
        final ProbeAggregate probe = probesByPosition.get(position);
        if (probe != null) {
            LOGGER.warn("Position already taken by probe [{}]", probe.getId());
            throw new PositionAlreadyTakenException("Position already taken by probe [" + probe.getId() + "]");
        }
    }
}
