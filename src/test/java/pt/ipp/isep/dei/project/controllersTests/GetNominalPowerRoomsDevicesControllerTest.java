package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetNominalPowerRoomsDevicesControllerTest {
    private GetNominalPowerRoomsDevicesController controller;
    private House houseEdificioB;
    private HouseGrid grid;
    private HouseGrid gridTwo;
    private Room roomOne;
    private Room roomTwo;
    //private Device fridge;
    //private Device lamp;


    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(insertedGeoArea);

        //Rooms
        Dimension dimensionRoom1 = new Dimension(5.2, 3.7, 8.5);
        roomOne = new Room("Kid's room", 1, dimensionRoom1);
        Dimension dimensionRoom2 = new Dimension(5.2, 3.7, 8.5);
        roomTwo = new Room("Bathroom", 1, dimensionRoom2);

        houseEdificioB.addRoom(roomOne);
        houseEdificioB.addRoom(roomTwo);

        //grids
        grid = new HouseGrid("Grid");
        gridTwo = new HouseGrid("Grid2");

        this.controller = new GetNominalPowerRoomsDevicesController(houseEdificioB);

    }


    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Act
        boolean result = controller.isGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        houseEdificioB.addGrid(grid);

        // Act
        boolean result = controller.isGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        //New Grids
        houseEdificioB.addGrid(grid);
        houseEdificioB.addGrid(gridTwo);

        String expectedResult = "1 - Name: Grid\n2 - Name: Grid2\n";

        // Act
        String result = controller.getHouseGridsListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        //New Grids
        houseEdificioB.addGrid(grid);
        houseEdificioB.addGrid(gridTwo);

        int expectedResult = 2;

        // Act
        int result = controller.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        int expectedResult = 0;

        // Act
        int result = controller.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomFromAPosition() {
        //Arrange
        //Adding Grids
        houseEdificioB.addGrid(grid);
        houseEdificioB.addGrid(gridTwo);

        //Rooms
        houseEdificioB.addRoom(roomOne);
        houseEdificioB.addRoom(roomTwo);

        //Rooms of Grid
        grid.attachRoom(roomOne);
        grid.attachRoom(roomTwo);

        controller.getHouseGridByPosition(0);

        Room expectResult = roomOne;

        //act
        Room result = controller.getRoomOfHouseGridByPosition(0);

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        //Arrange
        //Adding Grids
        houseEdificioB.addGrid(grid);

        grid.attachRoom(roomOne);

        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);

        //Device - Lamp
        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("Lamp1", roomOne);


        controller.getHouseGridByPosition(0);

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        //Act
        String result = controller.getDeviceListToString(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        houseEdificioB.addGrid(grid);

        grid.attachRoom(roomOne);

        //Device - Lamp
        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("Lamp1", roomOne);

        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);


        controller.getHouseGridByPosition(0);

        int expectResult = 2;
        //act
        int result = controller.getDeviceListSize(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        //house
        houseEdificioB.addGrid(grid);

        controller.getHouseGridByPosition(0);

        //act
        boolean result = controller.roomListOfHouseGridIsEmpty();

        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        houseEdificioB.addGrid(grid);

        grid.attachRoom(roomOne);

        controller.getHouseGridByPosition(0);
        //act
        boolean result = controller.roomListOfHouseGridIsEmpty();

        //assert
        assertFalse(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        controller.getHouseGridByPosition(0);

        // Act
        boolean result = controller.deviceListIsEmpty(0);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);

        controller.getHouseGridByPosition(0);

        // Act
        boolean result = controller.deviceListIsEmpty(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceFromPositionInList() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);

        controller.getHouseGridByPosition(0);


        Device expectedResult = fridge;

        // Act
        Device result = controller.getDeviceListByPosition(0, 0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void TestDisplayRoomsAttachedToHouseGrid() {

        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);
        grid.attachRoom(roomTwo);


        String expectedResult = "1- Name: Kid's room, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n";


        controller.getHouseGridByPosition(0);

        // Act
        String result = controller.getRoomListInHouseGridToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);
        grid.attachRoom(roomTwo);

        controller.getHouseGridByPosition(0);
        int expectResult = 2;
        //act
        int result = controller.getRoomListInHouseGridSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        // Arrange
        houseEdificioB.addGrid(grid);
        int expectResult = 0;

        controller.getHouseGridByPosition(0);
        //act
        int result = controller.getRoomListInHouseGridSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void getNominalPowerOfSelectedMeasurableObjects() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);


        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);
        fridge.setAttributesDevType("Nominal Power", 1);


        controller.getHouseGridByPosition(0);

        controller.addMeasurable(fridge);

        double expectedResult = 1;

        //Act

        double result = controller.getNominalPowerOfMeasurableObjects();

        //Assert
        assertEquals(expectedResult, result, 0.00001);
    }

    @Test
    void checkIfObjInList() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        //Device - Fridge
        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("Fridge1", roomOne);


        controller.getHouseGridByPosition(0);

        controller.addMeasurable(fridge);

        boolean result = controller.isMeasurableInList(fridge);

        //Assert

        assertTrue(result);
    }

    @Test
    void checkIfObjNotInList() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        //initiate Device
        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", roomOne);

        //initiate Device2
        Device dev2 = lampType.createDevice("Lamp2", roomOne);


        controller.getHouseGridByPosition(0);

        controller.addMeasurable(dev1);

        boolean result = controller.isMeasurableInList(dev2);

        //Assert

        assertFalse(result);
    }

    @Test
    void testGetListToString() {
        // Arrange
        houseEdificioB.addGrid(grid);
        grid.attachRoom(roomOne);

        //initiate Devices
        FridgeType fridgeType = new FridgeType();
        Device dev1 = fridgeType.createDevice("FridgeAriston", roomOne);


        MeasurableList mList = new MeasurableList();
        mList.addMeasurable(dev1);
        mList.addMeasurable(roomTwo);

        controller.getHouseGridByPosition(0);

        controller.addMeasurable(dev1);
        controller.addMeasurable(roomTwo);

        String expectedResult = "Device: FridgeAriston, located in room: Kid's room\n" +
                "Room: Bathroom\n";

        // act
        String result = controller.getListToString();

        // assert
        assertEquals(expectedResult, result);
    }
}