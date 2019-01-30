package pt.ipp.isep.dei.project.controllers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDeviceFromRoomControllerTest {

    @Test
    public void testGetDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        String expectResult = "";

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.deviceListIsEmpty();

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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);
        room.addDevice(dev1);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.deviceListIsEmpty();

        // Assert
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);

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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void deleteDeviceTrue() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        int position = 0;
        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);
        room.addDevice(dev1);
        deviceList.addDevice(dev1);
        controller.getRoomByPosition(position);

        // act
        boolean result = controller.deleteDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deleteDeviceFalse() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;
        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        // act
        boolean result = controller.deleteDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        String expectedResult = "There are no devices in the device list.";

        // act
        String result = controller.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameByPosition() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        int position = 0;

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        String expectedResult = "Lamp1";
        deviceList.addDevice(dev1);
        house.addRoom(room);
        controller.getRoomByPosition(position);

        // act
        String result = controller.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        int position = 0;
        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        house.addRoom(room);
        room.addDevice(dev1);
        room.addDevice(dev2);
        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);
        controller.getRoomByPosition(position);

        int expectResult = 2;
        //act
        int result = controller.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        //act
        boolean result = controller.roomListIsEmpty();
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        rList.addRoom(room1);
        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        //act
        boolean result = controller.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
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

        DeleteDeviceFromRoomController controller = new DeleteDeviceFromRoomController(house);
        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";

        int position = 0;
        rList.addRoom(room);
        room.addDevice(dev);
        room.addDevice(dev1);
        rList.addRoom(room);
        rList.addRoom(room);
        house.addRoom(room);
        controller.getRoomByPosition(position);
        // Act
        String result = controller.getDevicesInTheRoom();

        // Assert
        assertEquals(expectedResult, result);
    }
}