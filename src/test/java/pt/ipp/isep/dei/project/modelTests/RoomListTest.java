package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoomListTest {

    private House house;
    private static final String FRIDGE_TYPE = "Fridge";
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";
    private static final String DISHWASHER_TYPE = "DishWasher";
    private static final String LAMP_TYPE = "Lamp";
    private static final String WASHING_MACHINE_TYPE = "WashingMachine";


    @Before
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);
    }

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

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = rList.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();
        String expectResult = "";

        //act
        String result = rList.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        RoomList rList = new RoomList();
        //act
        boolean result = rList.isEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        RoomList rList = new RoomList();
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        rList.addRoom(room1);

        //act
        boolean result = rList.isEmpty();

        //assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
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

        int expectResult = 2;

        //act
        int result = rList.getLength();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        RoomList rList = new RoomList();

        int expectResult = 0;

        //act
        int result = rList.getLength();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testNewRoom() {
        //arrange
        RoomList list = new RoomList();
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        Room room2 = list.newRoom("Room1", 2, 3.5, 6.5, 7.5);

        //act
        boolean result = room1.equals(room2);

        //assert
        assertEquals(true, result);
    }

    /*@Test
    public void testNewRoomFalseDuplicatedName() {
        //arrange
        RoomList list = new RoomList();
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        list.addRoom(room1);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                list.newRoom("ROOM1", 3, 3.5, 6.5, 7.5)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }*/

    @Test
    public void getDisplayOfTheChosenRoomTest() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimension - Height: 2.0\n4 - Dimension - Length: 2.0\n5 - Dimension - Width: 2.0\n";

        //act
        String result = rList.getChosenRoomToString(0);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomNameInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 1;
        String nameChange = "Living Room";
        rList.changeRoomName(chosenRoomPositionInList, nameChange);

        String expectedResult = "1 - Name: Living Room\n2 - House Floor: 1\n3 - Dimension - Height: 2.6\n4 - Dimension - Length: 2.8\n5 - Dimension - Width: 2.1\n";

        //act
        String result = rList.getChosenRoomToString(chosenRoomPositionInList);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setHouseFloorInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int houseFloorChange = 3;
        rList.setRoomFloor(chosenRoomPositionInList, houseFloorChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 3\n3 - Dimension - Height: 2.0\n4 - Dimension - Length: 2.0\n5 - Dimension - Width: 2.0\n";

        //act
        String result = rList.getChosenRoomToString(chosenRoomPositionInList);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomHeightInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 3;
        double heightChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, heightChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimension - Height: 3.0\n4 - Dimension - Length: 2.0\n5 - Dimension - Width: 2.0\n";

        //act
        String result = rList.getChosenRoomToString(chosenRoomPositionInList);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomLengthInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 4;
        double lengthChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, lengthChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimension - Height: 2.0\n4 - Dimension - Length: 3.0\n5 - Dimension - Width: 2.0\n";

        //act
        String result = rList.getChosenRoomToString(chosenRoomPositionInList);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setRoomWidthInList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);

        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Bathroom";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2.6, 2.8, 2.1);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        int chosenRoomPositionInList = 0;
        int positionOfTheChosenFeature = 5;
        double widthChange = 3.0;
        rList.setRoomDimensions(chosenRoomPositionInList, positionOfTheChosenFeature, widthChange);

        String expectedResult = "1 - Name: Kitchen\n2 - House Floor: 0\n3 - Dimension - Height: 2.0\n4 - Dimension - Length: 2.0\n5 - Dimension - Width: 3.0\n";

        //act
        String result = rList.getChosenRoomToString(chosenRoomPositionInList);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddRoomToRoomList() {
        //Arrange
        RoomList list = new RoomList();
        Dimension dim = new Dimension(3, 3.5, 3.5);
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
        Dimension dim = new Dimension(3, 3.5, 3.5);
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
        Dimension dim0 = new Dimension(3, 3.5, 3.5);
        Dimension dim1 = new Dimension(3, 3.5, 3.5);
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
        Dimension dim0 = new Dimension(3, 3.5, 3.5);
        Dimension dim1 = new Dimension(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoom(room0);
        rList.addRoom(room1);

        String expectedResult = "RoomTwo";
        int roomPos = 1;

        //Act
        String result = rList.getRoomNameByPosition(roomPos);

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
        String result = rList.getRoomNameByPosition(roomPos);

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
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);

        LocalDateTime date0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("123", "A123", date0, sensorType0, locS0, "l/m2");

        LocalDateTime date1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("1241254", "B123", date1, sensorType1, locS1, "l/m2");

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading1 = new Reading(20.0, dateTimeDayMeasure1);
        Reading reading2 = new Reading(25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(reading1);
        s0.addReadingsToList(reading2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Reading reading3 = new Reading(20.0, dateTimeDayMeasure3);
        Reading reading4 = new Reading(30.0, dateTimeDayMeasure4);

        s1.addReadingsToList(reading3);
        s1.addReadingsToList(reading4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);

        double expectedResult = 30.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInRoomInGivenDay(name, sensorType0, dateTimeDayMeasure4.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void getMaximumTemperatureInARoomInAGivenDayNegativeTemperatures() {
        //Arrange
        String name = "Master Bedroom";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);

        LocalDateTime date0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("124124", "A123", date0, sensorType0, locS0, "l/m2");

        LocalDateTime date1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("64", "B123", date1, sensorType1, locS1, "l/m2");

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading1 = new Reading(-20.0, dateTimeDayMeasure1);
        Reading reading2 = new Reading(-25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(reading1);
        s0.addReadingsToList(reading2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Reading reading3 = new Reading(-10.0, dateTimeDayMeasure3);
        Reading reading4 = new Reading(-15.0, dateTimeDayMeasure4);

        s1.addReadingsToList(reading3);
        s1.addReadingsToList(reading4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);

        double expectedResult = -10.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInRoomInGivenDay(name, sensorType1, dateTimeDayMeasure3.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        String nameToCheck = "Room one";
        String name = "ROOM ONE";
        RoomList list = new RoomList();
        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);

        boolean expectedResult = true;

        boolean result = list.isNameExistant(nameToCheck);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorsListContentOfARoomTest() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList list = new RoomList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("F421", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("54", "A456", dataFuncionamento1, sensorType1, locS1, "l/m2");

        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);

        list.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = list.getSensorListContentOfRoom(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        roomList.addRoom(room);

        int position = 0;
        // Act
        boolean result = roomList.isSensorListEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("4124", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        roomList.addRoom(room);
        room.addSensorToListOfSensorsInRoom(s0);

        int position = 0;

        // Act
        boolean result = roomList.isSensorListEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        String nameFridge = "FridgeAriston";
        Device fridge1 = house.createDevice(FRIDGE_TYPE, nameFridge, room1);

        String nameWashingMachine = "WashingMachineBosch";
        Device washingMachine1 = house.createDevice(WASHING_MACHINE_TYPE, nameWashingMachine, room1);

        String nameDishWasher = "DishWasherAriston";
        Device dishwasher1 = house.createDevice(DISHWASHER_TYPE, nameDishWasher, room1);

        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        String nameFridge1 = "FridgeSiemens";
        Device fridge2 = house.createDevice(FRIDGE_TYPE, nameFridge1, room2);

        String nameDishWasher1 = "DishWasherTeka";
        Device dishwasher2 = house.createDevice(DISHWASHER_TYPE, nameDishWasher1, room2);

        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(fridge1);
        expectedResult.add(washingMachine1);
        expectedResult.add(dishwasher1);
        expectedResult.add(fridge2);
        expectedResult.add(dishwasher2);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        List<Device> result = roomList.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        String nameFridge1 = "Fridge1";
        house.createDevice(FRIDGE_TYPE, nameFridge1, room);

        String nameLamp1 = "Lamp1";
        house.createDevice(LAMP_TYPE, nameLamp1, room);

        house.addRoom(room);

        int position = 0;

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";

        // Act
        String result = house.getRoomList().getDeviceListContentByPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        int position = 0;

        // Act
        boolean result = house.getRoomList().isDeviceListEmpty(position);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        String nameFridge1 = "Fridge1";
        house.createDevice(FRIDGE_TYPE, nameFridge1, room);

        roomList.addRoom(room);

        int position = 0;

        // Act
        boolean result = roomList.isDeviceListEmpty(position);

        // Assert
        assertFalse(result);
    }


    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange
        String name1 = "Room";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room(name1, 2, dim);
        RoomList roomList = new RoomList();

        String nameFridge = "FridgeAriston";
        house.createDevice(FRIDGE_TYPE, nameFridge, room1);

        String nameWashingMachine = "WashingMachineBosch";
        house.createDevice(WASHING_MACHINE_TYPE, nameWashingMachine, room1);

        String nameDishWasher = "DishWasherAriston";
        house.createDevice(DISHWASHER_TYPE, nameDishWasher, room1);

        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        String nameFridge1 = "FridgeSiemens";
        house.createDevice(FRIDGE_TYPE, nameFridge1, room2);

        String nameDishWasher1 = "DishWasherTeka";
        house.createDevice(DISHWASHER_TYPE, nameDishWasher1, room2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Act
        boolean result = roomList.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testCheckIfThereAreNoDevicesTrue() {
        // Arrange
        RoomList roomList = new RoomList();

        // Act
        boolean result = roomList.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetRoomListToString() {
        //Arrange
        RoomList rList = new RoomList();
        Dimension dim0 = new Dimension(3, 3.5, 3.5);
        Dimension dim1 = new Dimension(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoom(room0);
        rList.addRoom(room1);

        String expectedResult = "1 - Name: RoomOne\n" +
                "2 - Name: RoomTwo\n";

        //Act
        String result = rList.getRoomListToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomFromAPosition() {
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

        Room expectResult = room1;

        //act
        Room result = rList.getRoomFromPosition(0);

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        Dimension dim = new Dimension(3, 5, 6);
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        String nameFridge = "Fridgeratah V14";
        house.createDevice(FRIDGE_TYPE, nameFridge, room0);

        String nameFridge1 = "Fridgeratah V15";
        house.createDevice(FRIDGE_TYPE, nameFridge1, room0);

        String nameFridge2 = "Fridgeratah V16";
        house.createDevice(FRIDGE_TYPE, nameFridge2, room0);

        String nameElectricWaterHeater = "Bosch Tronic 3000";
        house.createDevice(ELECTRIC_W_H_TYPE, nameElectricWaterHeater, room1);

        String nameElectricWaterHeater2 = "Bosch Tronic 4000";
        house.createDevice(ELECTRIC_W_H_TYPE, nameElectricWaterHeater2, room1);

        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Fridgeratah V15, located in room: Kitchen\n" +
                        "3 - Device: Fridgeratah V16, located in room: Kitchen\n" +
                        "4 - Device: Bosch Tronic 3000, located in room: Laundry\n" +
                        "5 - Device: Bosch Tronic 4000, located in room: Laundry\n";

        // Act
        String result = roomList.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }
}

