package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfARoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GetNominalPowerOfARoomControllerTest {

    @Test
    void testGetListOfRooms() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("r1", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("r2", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        String expectedResult = "1- Name: r1, House Floor: 1, Dimensions - Height: 4.0, Length: 4.0, Width: 4.0\n" +
                "2- Name: r2, House Floor: 1, Dimensions - Height: 4.0, Length: 4.0, Width: 4.0\n";

        //Act

        String result = ctrl.getListOfRooms();

        //Assert
        assertEquals(result, expectedResult);
    }


    @Test
    void testGetListOfRoomsEmpty() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        String expectedResult = "";
        //Act

        String result = ctrl.getListOfRooms();

        //Assert
        assertEquals(result, expectedResult);
    }

   /* @Test
    void getNominalPower() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("F5", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("F5", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        ctrl.getRoom(0);

        Fridge fridge1 = new Fridge(25, 50, 5000, 110);
        DishWasher dishWasher1 = new DishWasher(400, 110);

        Device d1 = new Device("Fridge1", room1, fridge1);

        Device d2 = new Device("Dish Washer1", room1, dishWasher1);


        room1.addDevice(d1);

        room1.addDevice(d2);

        double expectedResult = 220;

        //Act
        double result = ctrl.getNominalPower();

        //Assert

        assertEquals(result, expectedResult);
    }*/

   /* @Test
    void getNominalPowerNoDevices() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("F5", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("F5", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        ctrl.getRoom(0);

        Fridge fridge1 = new Fridge(20, 20, 50, 400);
        DishWasher dishWasher1 = new DishWasher(50, 250);

        Device d1 = new Device("Fridge1", room1, fridge1);

        Device d2 = new Device("Dish Washer1", room1, dishWasher1);

        room2.addDevice(d1);
        room2.addDevice(d2);

        double expectedResult = 0;

        //Act
        double result = ctrl.getNominalPower();

        //Assert

        assertEquals(expectedResult, result);
    }*/


    @Test
    void testGetRoomListLength() {
        //Arrange
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        int expectedResult = 2;

        //Act
        int result = ctrl.getRoomListLength();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testifDeviceListIsEmpty() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Act
        boolean result = ctrl.checkIfDeviceListIsEmpty(1);

        //Assert
        assertTrue(result);
    }

    /*@Test
    public void testifDeviceListIsEmptyWithDevices() {
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        GetNominalPowerOfARoomController ctrl = new GetNominalPowerOfARoomController(house);

        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimensions dim2 = new Dimensions(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        Fridge fridge1 = new Fridge(20, 20, 50, 400);
        DishWasher dishWasher1 = new DishWasher(50, 250);

        Device d1 = new Device("Fridge", room1, fridge1);

        Device d2 = new Device("Dish Washer", room1, dishWasher1);

        room2.getmDeviceList().getmDeviceList().add(d1);

        room2.getmDeviceList().getmDeviceList().add(d2);

        //Act
        boolean result = ctrl.checkIfDeviceListIsEmpty(1);

        //Assert
        assertFalse(result);
    }*/
}