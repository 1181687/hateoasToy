package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class GetNominalPowerRoomsDevicesControllerTest {

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        // Act
        boolean result = ctrl.checkIfGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange

        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        // Act
        boolean result = ctrl.checkIfGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmHouseGridsList().add(grid0);
        gridList.getmHouseGridsList().add(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        // Act
        String result = ctrl.listHouseGrids();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmHouseGridsList().add(grid0);
        gridList.getmHouseGridsList().add(grid1);
        int expectedResult = 2;

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        // Act
        int result = ctrl.getHouseGridListLength();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        int expectedResult = 0;

        // Act
        int result = ctrl.getHouseGridListLength();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomFromAPosition() {
        //arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);

        Room expectResult = room1;

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        //act
        Room result = ctrl.getChosenRoomInTheGrid(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        houseGrid.attachRoom(room);

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

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = ctrl.getContentOfDeviceListInRoomOfGrid(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        int expectResult = 2;
        //act
        int result = ctrl.getSizeOfListOfDevicesInARoom(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);


        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        //act
        boolean result = ctrl.checkIfRoomListIsEmpty();

        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        houseGrid.attachRoom(room);

        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        rList.addRoom(room1);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);
        //act
        boolean result = ctrl.checkIfRoomListIsEmpty();

        //assert
        assertFalse(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        // Act
        boolean result = ctrl.checkIfDeviceListIsEmpty(0);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        // Act
        boolean result = ctrl.checkIfDeviceListIsEmpty(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceFromPositionInList() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        Device expectedResult = dev1;

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        // Act
        Device result = ctrl.getDeviceFromPositionInList(0, 0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void TestDisplayRoomsAttachedToHouseGrid() {

        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        Dimensions dimensionsRoom1 = new Dimensions(5.2, 3.7, 8.5);
        Room room1 = new Room("Kid's room", 1, dimensionsRoom1);
        Dimensions dimensionsRoom2 = new Dimensions(5.2, 3.7, 8.5);
        Room room2 = new Room("Bathroom", 1, dimensionsRoom2);


        houseGrid.getRoomList().addRoom(room1);
        houseGrid.getRoomList().addRoom(room2);

        String expectedResult = "1- Name: Kid's room, House Floor: 1, Dimensions - Height: 5.2, Length: 3.7, Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimensions - Height: 5.2, Length: 3.7, Width: 8.5\n";

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        // Act
        String result = ctrl.getRoomsInTheHouseGrid();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);
        int expectResult = 2;
        //act
        int result = ctrl.getSizeOfRoomListInGrid();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);
        int expectResult = 0;

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);
        //act
        int result = ctrl.getSizeOfRoomListInGrid();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void getNominalPowerOfSelectedMeasurableObjects() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        ctrl.addAMeasurableObject(dev1);

        double expectedResult = 1;

        //Act

        double result = ctrl.getNominalPowerOfSelectedMeasurableObjects();

        //Assert
        assertEquals(expectedResult, result, 0.00001);
    }

    @Test
    void checkIfObjInList() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        ctrl.addAMeasurableObject(dev1);

        boolean result = ctrl.checkIfObjInList(dev1);

        //Assert

        assertTrue(result);
    }

    @Test
    void checkIfObjNotInList() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //housegrid
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        list.getmHouseGridsList().add(houseGrid);

        //initiate Room

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        //initiate Device2
        double luminousFlux2 = 10.0;
        double nominalPower2 = 1.0;
        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs1);

        room.addDevice(dev1);

        room.addDevice(dev2);

        GetNominalPowerRoomsDevicesController ctrl = new GetNominalPowerRoomsDevicesController(house);

        ctrl.getHouseGridbyPosition(0);

        ctrl.addAMeasurableObject(dev1);

        boolean result = ctrl.checkIfObjInList(dev2);

        //Assert

        assertFalse(result);
    }
}