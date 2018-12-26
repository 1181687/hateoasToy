package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.TipoSensor;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DimensionsTest {

    @Test
    void testhashCode() {
        //Arrange
        double height = 5;
        double length = 10;
        double width = 10;
        double width2 = 15;
        Dimensions dim = new Dimensions(height, length, width);
        Dimensions dim2 = new Dimensions(height, length, width2);

        int hash1 = Objects.hash(height, length, width);
        int hash2 = Objects.hash(height, length, width2);

        // Act
        int result = hash1;
        int expectedResult = hash2;
        // Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrueSameObj() {
        //Arrange
        Dimensions dim = new Dimensions(3.5,3.5,3.5);
        //Act
        boolean result = dim.equals(dim);
        //Assert
        assertTrue(result);
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

    @Test
    public void getAndSetHeightOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmHeight(3.0);
        Double expectResult = 3.0;

        //act
        Double result = dimensions.getmHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }


    @Test
    public void getAndSetHeightOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmHeight(2.8);
        Double expectResult = 2.8;

        //act
        Double result = dimensions.getmHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmLength(2.6);
        Double expectResult = 2.6;

        //act
        Double result = dimensions.getmLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmLength(1.5);
        Double expectResult = 1.5;

        //act
        Double result = dimensions.getmLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmWidth(2.3);
        Double expectResult = 2.3;

        //act
        Double result = dimensions.getmWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmWidth(2.5);
        Double expectResult = 2.5;

        //act
        Double result = dimensions.getmWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }
}