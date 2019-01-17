package pt.ipp.isep.dei.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    void isSameDouble() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.1;

        // act
        boolean result = Utils.isSameDouble(value1, value2);

        // assert
        assertTrue(result);
    }

    @Test
    void isSameDoubleFalse() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.0;

        // act
        boolean result = Utils.isSameDouble(value1, value2);

        // assert
        assertFalse(result);
    }
}