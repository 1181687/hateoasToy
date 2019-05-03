/*
package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetListOfSensorsAndDevicesRoomControllerTest {

    private GetListOfSensorsAndDevicesRoomController controller;
    private House house;
    private RoomList roomList;
    private Room room;

    @BeforeEach
    public void StartUp() {

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // Room 1
        String name = "room1";
        String description = "room";
        int houseFloor = 3;
        Dimension dimension = new Dimension(3, 3.5, 3.5);
        this.room = new Room(name, description, houseFloor, dimension);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.roomList = house.getRoomList();
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);


        this.controller = new GetListOfSensorsAndDevicesRoomController(house);
    }
    @Test
    public void getSensorsListContentOfARoomTest() {
        // Arrange

        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: sensor1\n" +
                        "2 - Name of the sensor: sensor2\n";

        // Act
        String result = this.controller.getSensorsListContent(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        this.house.addRoom(room);
        int position = 0;

        // Act
        boolean result = controller.isSensorListEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        GeoAreaSensor s0 = new GeoAreaSensor("A123", "sensor1", dataFuncionamento0, sensorType0, locS0, "C");

        room.addSensorToListOfSensorsInRoom(s0);
        house.addRoom(room);
        int position = 0;

        // Act
        boolean result = controller.isSensorListEmpty(position);

        // Assert
        assertFalse(result);
    }


    @Test
    public void getDeviceListContentTest() {
        // Arrange
        house.createDevice("Fridge", "Fridge1", room);

        house.createDevice("Lamp", "Lamp1", room);

        house.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Fridge1\n" + "2 - Name of the device: Lamp1\n";
        // Act
        String result = controller.getDeviceListContent(position);
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        house.addRoom(room);
        int position = 0;
        // Act
        boolean result = controller.isDeviceListEmpty(position);
        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Device dev1 = house.createDevice("Lamp", "Lamp1", room);

        house.addRoom(room);
        int position = 0;
        // Act
        boolean result = controller.isDeviceListEmpty(position);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        String name2 = "Living Room";
        String description = "room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room);
        house.addRoom(room2);
        int expectResult = 2;

        //act
        int result = controller.roomListSize();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        int expectResult = 0;

        //act
        int result = controller.roomListSize();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getRoomListTest() {
        // Arrange
        house.addRoom(room);
        RoomList expectedResult = this.roomList;

        // Act
        RoomList result = this.controller.getListOfRooms();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomListEmptyTest() {
        //arrange
        RoomList expectResult = this.roomList;

        //act
        RoomList result = this.controller.getListOfRooms();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("RoomTwo", "room", 1, dim1);
        this.roomList.addRoom(room);
        this.roomList.addRoom(room1);

        String expectedResult = "RoomTwo";
        int roomPos = 1;

        //Act
        String result = this.controller.getRoomNameByPosition(roomPos);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        String expectedResult = null;
        int roomPos = 0;

        //Act
        String result = this.controller.getRoomNameByPosition(roomPos);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = this.controller.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        String expectResult = "";

        //act
        String result = this.controller.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }
}
*/
