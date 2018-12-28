package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US105Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class US105ControllerTest {

    @Test
    public void testAddRoomToHouseFalse(){
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4,4,4);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        US105Controller ctr= new US105Controller(house);

        boolean result = ctr.addRoomToHouse();
        assertFalse(result);
    }

    @Test
    public void testAddRoomToHouse(){
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4,4,4);
        SensorList list = new SensorList();
        Room room = new Room("F5", 1, dim, list);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        US105Controller ctr= new US105Controller(house);

        ctr.newRoom(4,4,4,"F5",1);

        boolean result = ctr.addRoomToHouse();

        assertTrue(result);
    }
}
