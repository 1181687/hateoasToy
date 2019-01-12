package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RoomListTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";

        //act
        String result = rList.displayRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();

        String expectResult = "";

        //act
        String result = rList.displayRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        RoomList rList = new RoomList();
        //act
        boolean result = rList.checkIfRoomListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        rList.addRoom(room1);
        //act
        boolean result = rList.checkIfRoomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int expectResult = 2;
        //act
        int result = rList.listSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        RoomList rList = new RoomList();

        int expectResult = 0;
        //act
        int result = rList.listSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testNewRoom() {
        //arrange
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        Room room2 = list.newRoom("Room1", 2, 3.5, 6.5, 7.5);

        //act
        boolean result = room1.equals(room2);

        //assert
        assertEquals(true, result);
    }

    @Test
    public void getDisplayOfTheChosenRoomTest() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimensions - Height: 2.0\n4 - Dimensions - Length: 2.0\n5 - Dimensions - Width: 2.0\n";

        //act
        String result = rList.displayOfTheChosenRoom(0);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomNameInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 1;
        String nameChange = "Living Room";
        rList.changeRoomName(chosenRoomPositionInList, nameChange);

        String expectedResult = "1 - Name: Living Room\n2 - House Floor: 1\n3 - Dimensions - Height: 2.6\n4 - Dimensions - Length: 2.8\n5 - Dimensions - Width: 2.1\n";

        //act
        String result = rList.displayOfTheChosenRoom(chosenRoomPositionInList);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setHouseFloorInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int houseFloorChange = 3;
        rList.setRoomFloor(chosenRoomPositionInList, houseFloorChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 3\n3 - Dimensions - Height: 2.0\n4 - Dimensions - Length: 2.0\n5 - Dimensions - Width: 2.0\n";

        //act
        String result = rList.displayOfTheChosenRoom(chosenRoomPositionInList);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomHeightInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 3;
        double heightChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, heightChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimensions - Height: 3.0\n4 - Dimensions - Length: 2.0\n5 - Dimensions - Width: 2.0\n";

        //act
        String result = rList.displayOfTheChosenRoom(chosenRoomPositionInList);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomLengthInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 4;
        double lengthChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, lengthChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimensions - Height: 2.0\n4 - Dimensions - Length: 3.0\n5 - Dimensions - Width: 2.0\n";

        //act
        String result = rList.displayOfTheChosenRoom(chosenRoomPositionInList);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomWidthInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);

        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 5;
        double widthChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, widthChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimensions - Height: 2.0\n4 - Dimensions - Length: 2.0\n5 - Dimensions - Width: 3.0\n";

        //act
        String result = rList.displayOfTheChosenRoom(chosenRoomPositionInList);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddRoomToRoomList() {
        //Arrange
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("RoomOne", 2, dim);
        //Act
        boolean result = list.addRoom(room);
        //assert
        assertTrue(result);
    }

    @Test
    void testAddRoomToRoomListFalse() {
        //Arrange
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("RoomOne", 2, dim);
        list.addRoom(room);
        //Act
        boolean result = list.addRoom(room);
        //assert
        assertFalse(result);
    }

    @Test
    public void testAddRoomToRoomListFalseNull() {
        //Arrange
        RoomList rList = new RoomList();
        //Act
        boolean result = rList.addRoom(null);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGetRoomByNameListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        Dimensions dim0 = new Dimensions(3, 3.5, 3.5);
        Dimensions dim1 = new Dimensions(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoom(room0);
        rList.addRoom(room1);

        Room expectedResult = room1;
        String roomName = "RoomTwo";
        //Act
        Room result = rList.getRoomByName(roomName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomByNameListOfRoomsEmpty() {
        //Arrange
        RoomList rList = new RoomList();

        Room expectedResult = null;
        String roomName = "RoomOne";
        //Act
        Room result = rList.getRoomByName(roomName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        Dimensions dim0 = new Dimensions(3, 3.5, 3.5);
        Dimensions dim1 = new Dimensions(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoom(room0);
        rList.addRoom(room1);

        String expectedResult = "RoomTwo";
        int roomPos = 1;
        //Act
        String result = rList.getNameOfTheChosenRoomInSpecificPosition(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = rList.getNameOfTheChosenRoomInSpecificPosition(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumTemperatureInARoomInAGivenDay() {
        //Arrange


        String name = "Master Bedroom";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimensions dimensions = new Dimensions(height, length, width);
        Room room1 = new Room(name, houseFloor, dimensions);

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

        Measurement measurement1 = new Measurement(20.0, dateTimeDayMeasure1);
        Measurement measurement2 = new Measurement(25.0, dateTimeDayMeasure2);

        s0.addMeasurementToList(measurement1);
        s0.addMeasurementToList(measurement2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Measurement measurement3 = new Measurement(20.0, dateTimeDayMeasure3);
        Measurement measurement4 = new Measurement(30.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(measurement3);
        s1.addMeasurementToList(measurement4);

        room1.getSensorList().addSensorToTheListOfSensors(s0);
        room1.getSensorList().addSensorToTheListOfSensors(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);


        double expectedResult = 30.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInARoomInAGivenDay(name, sensorType0, dateTimeDayMeasure4.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void getMaximumTemperatureInARoomInAGivenDayNegativeTemperatures() {
        //Arrange


        String name = "Master Bedroom";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimensions dimensions = new Dimensions(height, length, width);
        Room room1 = new Room(name, houseFloor, dimensions);

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

        Measurement measurement1 = new Measurement(-20.0, dateTimeDayMeasure1);
        Measurement measurement2 = new Measurement(-25.0, dateTimeDayMeasure2);

        s0.addMeasurementToList(measurement1);
        s0.addMeasurementToList(measurement2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Measurement measurement3 = new Measurement(-10.0, dateTimeDayMeasure3);
        Measurement measurement4 = new Measurement(-15.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(measurement3);
        s1.addMeasurementToList(measurement4);

        room1.getSensorList().addSensorToTheListOfSensors(s0);
        room1.getSensorList().addSensorToTheListOfSensors(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);


        double expectedResult = -10.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInARoomInAGivenDay(name, sensorType1, dateTimeDayMeasure3.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        String nameToCheck = "Room one";
        String name = "ROOM ONE";
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);

        boolean expectedResult = true;

        boolean result = list.checkIfNameAlreadyExists(nameToCheck);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room1, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing, 300);
        Device dev3 = new Device("DishWasher", room1, specDishWasher, 400);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater();
        Device dev4 = new Device("FridgeSiemens", room2, specFridge, 300);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher, 400);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater, 25);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1, dev2, dev3, dev4, dev5, dev6));
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        List<Device> result = roomList.getAllDevicesList();

        assertEquals(expectedResult, result);
    }
}
