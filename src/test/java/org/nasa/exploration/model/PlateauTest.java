package org.nasa.exploration.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlateauTest {

    @Nested
    class WhenNew {
        @Test
        void shouldThrowException_whenWidthIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Plateau(-10, 10)
            );

            assertEquals("Plateau width should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldThrowException_whenHeightIsNegative() {
            Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Plateau(10, -10)
            );

            assertEquals("Plateau height should not be a negative number", exception.getMessage());
        }

        @Test
        void shouldCreate() {
            final Plateau plateau = new Plateau(10, 20);

            assertEquals(10, plateau.getWidth());
            assertEquals(20, plateau.getHeight());
        }
    }

    @Test
    void shouldContainPosition_whenInsidePlateau() {
        final Plateau plateau = new Plateau(10, 20);
        assertTrue(plateau.contains(new Position(5, 10)));
    }

    @Test
    void shouldContainPosition_whenOverExtremeNorth() {
        final Plateau plateau = new Plateau(10, 20);
        assertTrue(plateau.contains(new Position(5, 20)));
    }

    @Test
    void shouldContainPosition_whenOverExtremeEast() {
        final Plateau plateau = new Plateau(10, 20);
        assertTrue(plateau.contains(new Position(10, 10)));
    }

    @Test
    void shouldContainPosition_whenOverExtremeSouth() {
        final Plateau plateau = new Plateau(10, 20);
        assertTrue(plateau.contains(new Position(5, 0)));
    }

    @Test
    void shouldContainPosition_whenOverExtremeWest() {
        final Plateau plateau = new Plateau(10, 20);
        assertTrue(plateau.contains(new Position(0, 10)));
    }

    @Test
    void shouldNotContainPosition() {
        final Plateau plateau = new Plateau(10, 20);
        assertFalse(plateau.contains(new Position(8, 21)));
    }
}
