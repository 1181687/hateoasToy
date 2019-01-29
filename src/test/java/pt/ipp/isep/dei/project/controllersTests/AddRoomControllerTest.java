package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddRoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddRoomControllerTest {

    @Test
    public void testAddRoomToHouseFalse() {
        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        AddRoomController ctr = new AddRoomController(house);

        boolean result = ctr.addRoomToHouse();
        assertFalse(result);
    }

    @Test
    public void testAddRoomToHouse() {
        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        AddRoomController ctr = new AddRoomController(house);

        ctr.newRoom(4, 4, 4, "F5", 1);

        boolean result = ctr.addRoomToHouse();

        assertTrue(result);
    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        String nameToCheck = "Room one";
        String name = "ROOM ONE";
        RoomList list = new RoomList();
        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address adress = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(list, listHG, adress, geo);
        AddRoomController ctrl = new AddRoomController(house);

        boolean expectedResult = true;

        boolean result = ctrl.isNameExistant(nameToCheck);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfNameAlreadyExistsFalse() {
        String nameToCheck = "Room one";
        String name = "ROOM two";
        RoomList list = new RoomList();
        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address adress = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(list, listHG, adress, geo);
        AddRoomController ctrl = new AddRoomController(house);

        boolean expectedResult = false;

        boolean result = ctrl.isNameExistant(nameToCheck);

        assertEquals(expectedResult, result);
    }
}
