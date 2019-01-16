package pt.ipp.isep.dei.project.controllers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditConfigurationDeviceControllerTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        house.addRoom(room1);
        house.addRoom(room2);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";
        int position = 0;
        controller.getRoomByPosition(position);

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        String expectResult = "";

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetRoomName() {
        //Arrange
        RoomList rList = new RoomList();
        Dimensions dim0 = new Dimensions(4, 4, 4);
        Room room0 = new Room("RoomOne", 1, dim0);
        Dimensions dim1 = new Dimensions(4, 4, 4);
        Room room1 = new Room("RoomTwo", 1, dim1);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        rList.addRoom(room0);
        rList.addRoom(room1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        String expectedResult = "RoomTwo";
        int roomPos = 1;
        //Act
        String result = controller.getRoomName(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomNameEmpty() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = controller.getRoomName(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDevicesInTheRoomTest() {
        // Arrange
        // initiate House
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        double luminousFlux = 10.0;
        double nominalPower1 = 0.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);
        house.addRoom(room);
        int option = 0;

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        controller.getRoomByPosition(option);
        // Act
        String result = controller.getDevicesInTheRoom();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceAttributesToString() {
        // initiate House
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        Fridge deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        int position = 0;
        room.addDevice(dev);
        house.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Name: Fridge1" + "\n" +
                "2 - Device Specifications\n" +
                "3 - Location: pt.ipp.isep.dei.project.model.Room@26f51a\n";
        // act
        String result = controller.getDeviceAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setDeviceName() {


    }

    @Test
    void setDeviceSpecs() {


    }

    @Test
    void getSpecsAttributesToString() {


    }

    @Test
    void setLocation() {


    }

    @Test
    void checkIfRoomListIsEmpty() {


    }

    @Test
    void roomListSize() {


    }

    @Test
    void getDeviceListLength() {


    }

    @Test
    void checkIfDeviceListIsEmpty() {


    }

    @Test
    void getNewRoom() {


    }

    @Test
    void getNumberOfAttributesInDeviceSpecs() {


    }
}