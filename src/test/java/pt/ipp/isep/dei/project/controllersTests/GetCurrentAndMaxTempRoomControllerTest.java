package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentAndMaxTempRoomControllerTest {
    private GetCurrentAndMaxTempRoomController ctrl;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

        SensorType sensorType = new SensorType("Temperature");

        ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);
    }

    @Test
    public void getDisplayRoomListTest() {
        //arrange
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

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = ctrl.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmpty() {
        //arrange
        RoomList rList = new RoomList();

        String expectResult = "";

        //act
        String result = ctrl.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testLengthOfRoomList() {
        //arrange
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

        int expectResult = 2;
        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetLatestTemperatureRoom() {
        //arrange
        Dimension dimension = new Dimension(300, 600, 600);
        Room room1 = new Room("room1", 1, dimension);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType0, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(25, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(26, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        room1.addSensorToListOfSensorsInRoom(s0);
        room1.addSensorToListOfSensorsInRoom(s1);
        room1.addSensorToListOfSensorsInRoom(s2);

        house.addRoom(room1);

        Readings expectedResult = readings02;

        //Act
        Readings result = ctrl.getLatestMeasurementByRoomName("room1");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLatestTemperatureRoomNull() {
        //arrange
        Dimension dimension = new Dimension(300, 600, 600);
        Room room1 = new Room("room1", 1, dimension);

        //Sensor
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidity");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Readings
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(26, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        Readings expectedResult = null;

        room1.addSensorToListOfSensorsInRoom(s2);

        //Act
        Readings result = ctrl.getLatestMeasurementByRoomName("room1");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameOfTheChosenRoomInSpecificPos() {
        //Arrange
        Dimension dim0 = new Dimension(3, 3.5, 3.5);
        Dimension dim1 = new Dimension(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        house.addRoom(room0);
        house.addRoom(room1);

        String expectedResult = "RoomTwo";
        int roomPos = 1;
        //Act
        String result = ctrl.getRoomNameByPos(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = ctrl.getRoomNameByPos(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumTemperatureOfARoomInAGivenDay() {
        String name = "Master Bedroom";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);

        house.addRoom(room1);

        LocalDateTime date0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);

        LocalDateTime date1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", date1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Readings readings1 = new Readings(-20.0, dateTimeDayMeasure1);
        Readings readings2 = new Readings(-25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(readings1);
        s0.addReadingsToList(readings2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Readings readings3 = new Readings(-10.0, dateTimeDayMeasure3);
        Readings readings4 = new Readings(-15.0, dateTimeDayMeasure4);

        s1.addReadingsToList(readings3);
        s1.addReadingsToList(readings4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);

        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType0);

        double expectedResult = -10.0;

        //Act
        double result = ctrl.getMaximumTemperatureOfRoomInGivenDay(name, sensorType0, dateTimeDayMeasure3.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetmType() {
        //Arrange
        SensorType sensorType = new SensorType("Temperature");

        SensorType expectedResult = sensorType;

        //Act
        SensorType result = ctrl.getType();

        //Assert
        assertEquals(expectedResult, result);
    }
}