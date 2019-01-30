package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentAndMaxTempRoomControllerTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);

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

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);
        //act
        String result = ctrl.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testLengthOfRoomList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);

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
        SensorType sensorType0 = new SensorType("Temperatura");
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

        s0.addMeasurementToList(readings01);
        s0.addMeasurementToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(readings11);
        s1.addMeasurementToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(26, dataHoraDaMedicao22);

        s2.addMeasurementToList(readings21);
        s2.addMeasurementToList(readings22);

        room1.addSensorToListOfSensorsInRoom(s0);
        room1.addSensorToListOfSensorsInRoom(s1);
        room1.addSensorToListOfSensorsInRoom(s2);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType0);

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

        s2.addMeasurementToList(readings21);
        s2.addMeasurementToList(readings22);

        Readings expectedResult = null;

        room1.addSensorToListOfSensorsInRoom(s2);

        SensorType sensorType0 = new SensorType("Temperature");
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType0);

        //Act
        Readings result = ctrl.getLatestMeasurementByRoomName("room1");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameOfTheChosenRoomInSpecificPos() {

        //Arrange
        RoomList rList = new RoomList();
        Dimension dim0 = new Dimension(3, 3.5, 3.5);
        Dimension dim1 = new Dimension(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoom(room0);
        rList.addRoom(room1);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType0 = new SensorType("Temperature");
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType0);

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
        RoomList rList = new RoomList();
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("City");
        AreaShape rectangleArea = new AreaShape(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType0 = new SensorType("Temperature");
        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType0);

        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = ctrl.getRoomNameByPos(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumTemperatureOfARoomInAGivenDay() {
        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape rectangleArea = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, rectangleArea);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

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

        s0.addMeasurementToList(readings1);
        s0.addMeasurementToList(readings2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Readings readings3 = new Readings(-10.0, dateTimeDayMeasure3);
        Readings readings4 = new Readings(-15.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(readings3);
        s1.addMeasurementToList(readings4);

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
        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape rectangleArea = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, rectangleArea);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");

        GetCurrentAndMaxTempRoomController ctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);
        SensorType expectedResult = sensorType;

        //Act
        SensorType result = ctrl.getType();

        //Assert
        assertEquals(expectedResult, result);
    }

}