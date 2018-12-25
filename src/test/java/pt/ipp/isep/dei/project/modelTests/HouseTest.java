package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

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
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(rList, gridlist, adr);

        boolean result = house.addRoomToHouse(null);
        assertFalse(result);
    }
}
