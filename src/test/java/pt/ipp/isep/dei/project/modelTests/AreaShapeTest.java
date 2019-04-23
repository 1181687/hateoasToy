package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class AreaShapeTest {

    @Test
    public void testhashCode() {
        //Arrange
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
        AreaShape area1 = new AreaShape(10, 10);

        //act
        boolean result = area1.equals(area1);

        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsTrueAllAttributes() {
        //arrange
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
        AreaShape area1 = new AreaShape(12, 8);
        AreaShape area2 = new AreaShape(10, 10);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseAreaShapeAllAttributes() {
        //arrange
        AreaShape area1 = new AreaShape(10, 9);
        AreaShape area2 = new AreaShape(12, 8);

        //act
        boolean result = area1.equals(area2);

        //assert
        assertFalse(result);
    }


    @Test
    public void testarEqualsFalseLocationWidth() {
        //arrange
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
        AreaShape area1 = new AreaShape(12, 8);
        String id = "1";
        String description = "room";
        int housefloor = 2;
        Dimension dim = new Dimension(4, 10.5, 7.5);
        RoomId roomId = new RoomId(id);
        Room room = new Room(roomId, description, housefloor, dim);

        //act
        boolean result = area1.equals(room);

        //assert
        assertFalse(result);
    }
}