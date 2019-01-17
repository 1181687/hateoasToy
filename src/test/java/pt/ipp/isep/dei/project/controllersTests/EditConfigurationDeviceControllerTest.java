package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class EditConfigurationDeviceControllerTest {

    @Test
    public void testGetDisplayRoomListTest() {
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
        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Length: 1.5, Width: 1.3\n";
        int position = 0;
        controller.getRoomByPosition(position);

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmptyTest() {
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
    public void testGetDevicesInTheRoomTest() {
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
    void testGetDeviceAttributesToString() {
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
                "3 - Location: Room\n";
        // act
        String result = controller.getDeviceAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetNameAlreadyInListFalse() {
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

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        Device dev2 = new Device("Lamp2", room, deviceSpecs1);
        room.addDevice(dev1);
        room.addDevice(dev2);

        int position = 0;
        house.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        Throwable exception = assertThrows(RuntimeException.class, () -> dev1.setName("Lamp1"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }

    @Test
    public void testSetNameTrue() {
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

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        room.addDevice(dev1);

        int position = 0;
        house.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        // Act
        boolean result = controller.setDeviceName("Lamp10");

        // Assert
        assertTrue(result);
    }

    /* @Test
    void testGetSpecsAttributesToString() {
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

        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        room.addDevice(device);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Luminous Flux: 10.0\n" +
                "2 - Nominal Power: 1.0\n";

        // act
        String result = controller.getSpecsAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    } */

    @Test
    void testSetLocation() {
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

        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);
        Room room2 = new Room("Bedroom", 1, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        room.addDevice(device);
        house.addRoom(room);
        house.addRoom(room2);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(1);

        // act
        boolean result = controller.setLocation();

        // assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        // initiate House
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        //act
        boolean result = controller.checkIfRoomListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        // initiate House
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

        rList.addRoom(room1);
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        //act
        boolean result = controller.checkIfRoomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
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

        int expectResult = 2;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
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
        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddDeviceTrue() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        room.addDevice(dev1);
        house.addRoom(room);
        int position = 0;
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        // act
        int expectedResult = 1;

        devList.addDevice(dev1);
        int result = controller.getDeviceListLength();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);
        int position = 0;
        controller.getRoomByPosition(position);
        // act
        int expectedResult = 0;

        int result = controller.getDeviceListLength();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.checkIfDeviceListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);
        room.addDevice(dev1);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.checkIfDeviceListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfAttributesInDeviceSpecs() {
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

        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        room.addDevice(device);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        int expectedResult = 2;

        // act
        int result = controller.getNumberOfAttributesInDeviceSpecs();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeviceSpecs() {
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

        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        int coldWaterTempPosition = 5;
        // Device Instantiation
        Device device = new Device("Electric Water Heater", room, electricWaterHeater);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        room.addDevice(device);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(0);

        // Act
        boolean result = controller.setDeviceSpecs(coldWaterTempPosition, 30);

        // Assert
        assertTrue(result);
    }
}