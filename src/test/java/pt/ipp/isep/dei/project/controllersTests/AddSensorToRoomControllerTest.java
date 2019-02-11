package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToRoomControllerTest {
    private AddSensorToRoomController controller;
    private GeographicalArea CampusDoIsep;
    private House houseEdificioB;

    @BeforeEach
    public void StartUp() {

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        this.CampusDoIsep = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.177748, -8.607745, 112);

        Address address = new Address("4200-072", houseLocation);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(CampusDoIsep);

    }

    @Test
    void testDisplayRoomsInTheHouse() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();


        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        //act
        String result = addSensorToRoomController.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void displayListOfSensorsType() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Type of sensor
        SensorType sensorType = new SensorType("Temperatura");


        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        String expectedResult = "1 - Sensor Type: Temperatura\n";

        // Act
        String result = addSensorToRoomController.displayListOfSensorsType();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void createAndAddSensorToTheList() {
        // Arrange

        // Sensor
        SensorType sensorType = new SensorType("Temperatura");
        String sensorName = "A123";

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        // Room list
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        addSensorToRoomController.getRoomByIndex(0);
        addSensorToRoomController.getSensorTypeByIndex(0);
        addSensorToRoomController.getLocationOfTheHouse();

        // Act
        boolean result = addSensorToRoomController.createAndAddSensorToTheList(sensorName);

        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyPositive() {
        // Arrange
        RoomList roomList = new RoomList();

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        // Act
        boolean result = addSensorToRoomController.isRoomListEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyNegative() {
        // Arrange
        RoomList roomList = new RoomList();

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);


        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        // Act
        boolean result = addSensorToRoomController.isRoomListEmpty();
        // Assert
        assertFalse(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyPositive() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        // Act
        boolean result = addSensorToRoomController.isSensorTypeListEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyNegative() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Tipo de sensor
        SensorType sensorType = new SensorType("Temperatura");

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, houseEdificioB);

        // Act
        boolean result = addSensorToRoomController.isSensorTypeListEmpty();
        // Assert
        assertFalse(result);
    }
}