package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.DetachRoomFromHouseGridController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DetachRoomFromHouseGridControllerTest {

    private DetachRoomFromHouseGridController ctrl;
    private House house;
    private Room r0;
    private Room r1;
    private HouseGrid mainGrid;


    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");


        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        //room0
        Dimension r0Dimension = new Dimension(2, 3, 4);
        this.r0 = new Room("Bedroom", "room", 3, r0Dimension);

        //room1
        Dimension r1Dimension = new Dimension(2, 3, 3);
        this.r1 = new Room("Living Room", "room", 2, r1Dimension);

        house.addRoom(r0);
        house.addRoom(r1);

        //housegrid
        mainGrid = new HouseGrid(new HouseGridId("Main Grid"));
        house.addGrid(mainGrid);
        mainGrid.addRoom(r0);
        mainGrid.addRoom(r1);

        RoomList roomList = house.getRoomList();

        this.ctrl = new DetachRoomFromHouseGridController(house, roomList);
    }

    @Test
    public void testsGetListContentMethod() {
        //Arrange
        String expectedResult = "1 - Name: Main Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testsGetListContentMethodMoreThanOneGrid() {
        //Arrange
        HouseGrid newGrid1 = new HouseGrid(new HouseGridId("Secondary Grid"));
        this.house.addGrid(newGrid1);

        String expectedResult = "1 - Name: Main Grid" + "\n" + "2 - Name: Secondary Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getHouseGridFromTheList() {
        //Arrange
        HouseGrid expectedResult = house.getHouseGridByPosition(0);
        //Act
        HouseGrid result = ctrl.getHouseGridFromTheList(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getListOfRooms() {
        //Arrange
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimension - Height: 2.0, Length: 3.0, Width: 3.0\n";
        //Act
        String result = ctrl.getRoomListContent();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomFromTheListOfRoomByAPosition() {
        //Arrange
        ctrl.getHouseGridFromTheList(0);
        Room expectedResult = r1;
        //Act
        Room result = ctrl.getRoomFromTheListOfRoomByAPosition(1);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void detachRoomFromGridList() {
        //Arrange

        ctrl.detachRoomFromGridList(mainGrid, r1);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n";

        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void detachRoomFromGridListRoomNotInTheListRemainsTheSame() {
        //Arrange

        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r2 = new Room("Bathroom", "room", 2, r1Dimension);

        HouseGrid newGrid1 = new HouseGrid(new HouseGridId("Secondary Grid"));
        newGrid1.addRoom(r2);
        ctrl.detachRoomFromGridList(mainGrid, r2);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimension - Height: 2.0, Length: 3.0, Width: 3.0\n";

        //Act
        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void detachRoomFromGridListRoomNotInTheListRemainsTheSameBooleanMethod() {
        //Arrange
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r2 = new Room("Bathroom", "room", 2, r1Dimension);

        HouseGrid newGrid1 = new HouseGrid(new HouseGridId("Secondary Grid"));
        newGrid1.addRoom(r2);

        //Act
        boolean result = ctrl.detachRoomFromGridList(mainGrid, r2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void detachRoomFromGridListBooleanMethod() {
        //Act
        boolean result = ctrl.detachRoomFromGridList(mainGrid, r1);
        //Assert
        assertTrue(result);
    }

    @Test
    public void getGridListSize() {
        //Arrange
        Dimension r1Dimension = new Dimension(2, 3, 3);

        Room r2 = new Room("Bathroom", "room", 2, r1Dimension);

        HouseGrid newGrid1 = new HouseGrid(new HouseGridId("Secondary Grid"));
        newGrid1.addRoom(r2);
        house.addGrid(newGrid1);

        int expectedResult = 2;
        //Act
        int result = ctrl.getGridListSize();
        //Assert
        assertEquals(result, expectedResult);
    }
}