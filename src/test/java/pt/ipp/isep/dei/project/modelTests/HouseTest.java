package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HouseTest {

    @Test
    public void testAddRoomToHouse(){
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4,4,4);
        Room room = new Room("F5",1,dim);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(rList, gridlist, adr);

        boolean result = house.addRoomToHouse(room);
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse(){
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4,4,4);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(rList, gridlist, adr);

        boolean result = house.addRoomToHouse(null);
        assertFalse(result);
    }

    @Test
    void testGetLocationOfTheHouse () {
        // Arrange
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();

        House house = new House(roomList, houseGridList, address);

        Location expectedResult = local;
        // Act
        Location result = house.getLocationOfTheHouse();
        // Assert
        assertEquals(expectedResult, result);
    }

}
