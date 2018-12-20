package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.TipoSensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DimensionsTest {

    @Test
    void testhashCode() {
        //Arrange
        Dimensions dim = new Dimensions(3.5,3.5,3.5);
        int expectedResult = 1;
        // Act
        int result = dim.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        Dimensions dim = new Dimensions(3.5,3.5,3.5);
        Dimensions dim2 = new Dimensions(3.5,3.5,3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        //Arrange
        Dimensions dim = new Dimensions(2,3.5,3.5);
        Dimensions dim2 = new Dimensions(3.5,3.5,3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        TipoSensor tipo = new TipoSensor("humidade");

        //Act
        boolean result = dim.equals(tipo);
        //Assert
        assertFalse(result);
    }
}
