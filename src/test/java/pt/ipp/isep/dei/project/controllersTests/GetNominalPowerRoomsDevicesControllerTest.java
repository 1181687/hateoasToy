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
}

    /*@Test
    void getHouseGridListLength() {
    }

    @Test
    void getHouseGridbyPosition() {
    }

    @Test
    void getChosenRoomInTheGrid() {
    }

    @Test
    void getContentOfDeviceListInRoomOfGrid() {
    }

    @Test
    void getSizeOfListOfDevicesInARoom() {
    }

    @Test
    void checkIfRoomListIsEmpty() {
    }

    @Test
    void checkIfDeviceListIsEmpty() {
    }

    @Test
    void getDeviceFromPositionInList() {
    }

    @Test
    void getNominalPowerOfSelectedMeasurableObjects() {
    }

    @Test
    void addAMeasurableObject() {
    }

    @Test
    void getRoomsInTheHouseGrid() {
    }

    @Test
    void getSizeOfRoomListInGrid() {
    }

    @Test
    void checkIfObjInList() {
    }*/