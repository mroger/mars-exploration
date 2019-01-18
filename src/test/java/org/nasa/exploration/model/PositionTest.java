package org.nasa.exploration.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    @Test
    void shouldCreatePosition() {
        Position position = new Position(20, 30);

        assertThat(position.getX()).isEqualTo(20);
        assertThat(position.getY()).isEqualTo(30);
    }

    @Test
    void shouldThrowException_whenXIsNegative() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class, () -> new Position(-10, 10)
        );

        assertEquals("X coordinate should not be a negative number", exception.getMessage());
    }

    @Test
    void shouldThrowException_whenYIsNegative() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class, () -> new Position(10, -20)
        );

        assertEquals("Y coordinate should not be a negative number", exception.getMessage());
    }
}
