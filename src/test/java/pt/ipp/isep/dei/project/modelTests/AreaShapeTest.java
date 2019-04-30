package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class AreaShapeTest {

    @Test
    public void testhashCode() {
        //Arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);

        int expectedResult = Objects.hash();

        // Act
        int result = area.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsSame() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);

        //act
        boolean result = area1.equals(area1);

        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsTrueAllAttributes() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        AreaShape area2 = new AreaShape(10, 10);

        //act
        boolean result = area1.equals(area2);
        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsFalseAllAttributes() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(10, 10);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationAllAttributes() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseAreaShapeAllAttributes() {
        //arrange
        Location local1 = new Location(40, -8, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(10, 9);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationLatitude() {
        //arrange
        Location local1 = new Location(41.1496, -8, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationLongitude() {
        //arrange
        Location local1 = new Location(40, -9, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationAltitude() {
        //arrange
        Location local1 = new Location(40, -8, 97);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationWidth() {
        //arrange
        Location local1 = new Location(40, -8, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationWidthLatitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationWidthLongitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(41, -9, 95);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationWidthAltitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(41, -8, 97);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationLengthAltitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(41, -8, 97);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(11, 9);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationLengthLongitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(41, -9, 95);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(11, 9);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocationLengthLatitude() {
        //arrange
        Location local1 = new Location(41, -8, 95);
        Location local2 = new Location(40, -8, 95);
        AreaShape area1 = new AreaShape(11, 8);
        AreaShape area2 = new AreaShape(11, 9);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testEqualsFalseDiferentObject() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(12, 8);
        String id = "1";
        String description = "room";
        int housefloor = 2;
        Dimension dim = new Dimension(4, 10.5, 7.5);
        Room room = new Room(id, description, housefloor, dim);

        //act
        boolean result = area1.equals(room);

        //assert
        assertFalse(result);
    }


    @Test
    public void testGetWidth() {
        // Arrange
        Location location0 = new Location(29, 20, 65);
        Location locationRectangleArea = new Location(40, 20, 65);
        AreaShape area = new AreaShape(20, 40);

        double expectedResult = 20;

        double result = area.getWidth();
        assertEquals(expectedResult, result, 0.0001);

    }


    @Test
    public void testGetLength() {
        // Arrange
        Location location0 = new Location(29, 20, 65);
        Location locationRectangleArea = new Location(40, 20, 65);
        AreaShape area = new AreaShape(20, 40);

        double expectedResult = 40;

        double result = area.getLength();
        assertEquals(expectedResult, result, 0.0001);

    }
}