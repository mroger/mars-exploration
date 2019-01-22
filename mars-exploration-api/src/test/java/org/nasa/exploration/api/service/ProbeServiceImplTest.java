package org.nasa.exploration.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.model.MissionControl;
import org.nasa.exploration.model.ProbeAggregate;

@ExtendWith(MockitoExtension.class)
public class ProbeServiceImplTest {

    private static final int PLATEAU_WIDTH = 10;
    private static final int PLATEAU_HEIGHT = 15;

    @Mock
    private MissionControl missionControl;

    @InjectMocks
    private ProbeServiceImpl service;

    @BeforeEach
    void setUp() {
        Mockito.when(missionControl.createProbe(5, 7, "W")).thenReturn(
            createProbeAggregate(5, 7, "W"));
    }

    @Test
    void shouldCreateProbe() {
        ProbeCreationRequest request = createRequest(5, 7, "W");

        final ProbeCreationResponse probe = service.createProbe(request);

        assertThat(probe.getId()).isNotNull();
        assertThat(probe.getX()).isEqualTo(5);
        assertThat(probe.getY()).isEqualTo(7);
        assertThat(probe.getDirection()).isEqualTo("W");
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
}
