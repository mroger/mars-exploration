package org.nasa.exploration.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nasa.exploration.api.exception.ProbeNotFoundByIdException;
import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProbeServiceImplTest {

    private static final int PLATEAU_WIDTH = 10;
    private static final int PLATEAU_HEIGHT = 15;

    private static final String PROBE_ID = "12345";

    private static final String INSTRUCTION_M = "M";
    private static final String INSTRUCTION_R = "R";
    private static final String INSTRUCTION_L = "L";

    private static final String DIRECTION_N = "N";
    private static final String DIRECTION_E = "E";
    private static final String DIRECTION_S = "S";
    private static final String DIRECTION_W = "W";

    @Mock
    private MissionControl missionControl;

    @InjectMocks
    private ProbeServiceImpl service;

    @Test
    void shouldCreateProbe() {
        when(missionControl.createProbe(5, 7, "W")).thenReturn(
            createProbeAggregate(5, 7, "W"));

        ProbeCreationRequest request = createRequest(5, 7, "W");

        final ProbeAggregate probe = service.createProbe(request);

        assertThat(probe.getId()).isNotNull();
        assertThat(probe.getXPosition()).isEqualTo(5);
        assertThat(probe.getYPosition()).isEqualTo(7);
        assertThat(probe.getDirection()).isEqualTo("W");
    }

    @Test
    void shouldThrowProbeNotFoundByIdException() {
        when(missionControl.getProbeAggregateById(PROBE_ID)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(
            ProbeNotFoundByIdException.class, () -> service.processInstruction(PROBE_ID, INSTRUCTION_M)
        );

        assertEquals("missioncontrol.probe.id.not-found", exception.getMessage());
    }

    @Test
    void shouldRotateProbeLeft() {
        when(missionControl.getProbeAggregateById(PROBE_ID)).thenReturn(
            createOptionalProbeAggregate(5, 7, "W"));

        final Optional<ProbeAggregate> probeAggregate = service.processInstruction(PROBE_ID, INSTRUCTION_L);

        assertThat(probeAggregate).isPresent();
        assertThat(probeAggregate.get().getDirection()).isEqualTo(DIRECTION_S);
    }

    @Test
    void shouldRotateProbeRight() {
        when(missionControl.getProbeAggregateById(PROBE_ID)).thenReturn(
            createOptionalProbeAggregate(5, 7, "W"));

        final Optional<ProbeAggregate> probeAggregate = service.processInstruction(PROBE_ID, INSTRUCTION_R);

        assertThat(probeAggregate).isPresent();
        assertThat(probeAggregate.get().getDirection()).isEqualTo(DIRECTION_N);
    }

    @Test
    void shouldMoveProbe() {
        when(missionControl.getProbeAggregateById(PROBE_ID)).thenReturn(
            createOptionalProbeAggregate(5, 7, "W"));

        final Optional<ProbeAggregate> probeAggregate = service.processInstruction(PROBE_ID, INSTRUCTION_M);

        assertThat(probeAggregate).isPresent();
        assertThat(probeAggregate.get().getDirection()).isEqualTo(DIRECTION_W);
        assertThat(probeAggregate.get().getXPosition()).isEqualTo(4);
        assertThat(probeAggregate.get().getYPosition()).isEqualTo(7);
    }

    private ProbeCreationRequest createRequest(int x, int y, String direction) {
        ProbeCreationRequest request = new ProbeCreationRequest();
        request.setX(x);
        request.setY(y);
        request.setDirection(direction);
        return request;
    }

    private ProbeAggregate createProbeAggregate(int x, int y, String direction) {
        return new ProbeAggregate.ProbeAggregateBuilder(PLATEAU_WIDTH, PLATEAU_HEIGHT)
            .id(UUID.randomUUID())
            .position(x, y)
            .direction(direction)
            .build();
    }

    private Optional<ProbeAggregate> createOptionalProbeAggregate(int x, int y, String direction) {
        return Optional.of(createProbeAggregate(x, y, direction));
    }
}
