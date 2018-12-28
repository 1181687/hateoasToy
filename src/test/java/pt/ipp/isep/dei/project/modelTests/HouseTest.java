package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {
    @Test
    public void testAddRoomToHouse() {
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4, 4, 4);
        SensorList list = new SensorList();
        Room room = new Room("F5", 1, dim, list);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        boolean result = house.addRoomToHouse(room);
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse() {
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        boolean result = house.addRoomToHouse(null);
        assertFalse(result);
    }

    @Test
    public void testGetLocationOfTheHouse() {
        // Arrange
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        Location expectedResult = local;
        // Act
        Location result = house.getLocationOfTheHouse();
        // Assert
        assertEquals(expectedResult, result);
    }

}
