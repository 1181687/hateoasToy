package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.SensorType;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DimensionTest {

    @Test
    void testhashCode() {
        //Arrange
        double height = 5.98;
        double length = 10.35;
        double width = 10.48;
        Dimension dim = new Dimension(height, length, width);

        int expectedResult = Objects.hash(height, length, width);

        // Act
        int result = dim.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testhashCodeTrue() {
        //Arrange
        double height = 5.98;
        double length = 10.35;
        double width = 10.48;

        int hash1 = Objects.hash(height, length, width);
        int hash2 = Objects.hash(height, length, width);

        // Act
        int result = hash1;
        int expectedResult = hash2;
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testhashCodeFalse() {
        //Arrange
        double height = 5;
        double length = 10;
        double width = 10;
        double width2 = 15;

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
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        //Act
        boolean result = dim.equals(dim);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsTrueAllDimensions() {
        //Arrange
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalseHeight() {
        //Arrange
        Dimension dim = new Dimension(2, 3.5, 3.5);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseLength() {
        //Arrange
        Dimension dim = new Dimension(3.5, 2, 3.5);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseWidth() {
        //Arrange
        Dimension dim = new Dimension(3.5, 3.5, 2);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        //Act
        boolean result = dim.equals(dim2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        SensorType tipo = new SensorType("humidade");

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
        Dimension dimension = new Dimension(height, length, width);

        dimension.setHeight(3.0);
        Double expectResult = 3.0;

        //act
        Double result = dimension.getHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }


    @Test
    public void getAndSetHeightOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimension dimension = new Dimension(height, length, width);

        dimension.setHeight(2.8);
        Double expectResult = 2.8;

        //act
        Double result = dimension.getHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimension dimension = new Dimension(height, length, width);

        dimension.setLength(2.6);
        Double expectResult = 2.6;

        //act
        Double result = dimension.getLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimension dimension = new Dimension(height, length, width);

        dimension.setLength(1.5);
        Double expectResult = 1.5;

        //act
        Double result = dimension.getLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimension dimension = new Dimension(height, length, width);

        dimension.setWidth(2.3);
        Double expectResult = 2.3;

        //act
        Double result = dimension.getWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimension dimension = new Dimension(height, length, width);

        dimension.setWidth(2.5);
        Double expectResult = 2.5;

        //act
        Double result = dimension.getWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void TestValidateHeightNeg() {

        double length = 40;
        double width = 20;
        double height = -20;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid height. Height should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateHeightZero() {

        double length = 40;
        double width = 20;
        double height = 0;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid height. Height should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateHeightDoubleNan() {
        double length = 40;
        double width = 20;
        double height = Double.NaN;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid height. Height should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateLengthNeg() {

        double length = -40;
        double width = 20;
        double height = 20;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid length. Length should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateLengthZero() {

        double length = 0;
        double width = 20;
        double height = 20;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid length. Length should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateLengthDoubleNan() {
        double height = 40;
        double width = 20;
        double length = Double.NaN;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid length. Length should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateWidthNeg() {

        double length = 40;
        double width = -20;
        double height = 20;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid width. Width should be greater than zero", exception.getMessage());
    }

    @Test
    public void TestValidateWidthZero() {

        double length = 40;
        double width = 0;
        double height = 20;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid width. Width should be greater than zero", exception.getMessage());
    }


    @Test
    public void TestValidateWidthDoubleNan() {
        double height = 40;
        double length = 20;
        double width = Double.NaN;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Dimension(height, length, width)
        );
        assertEquals("Please enter a valid width. Width should be greater than zero", exception.getMessage());
    }
}