package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RoomListTest {

    private House house;

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

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

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

    @Test
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
    }

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
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);

        LocalDateTime date1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", date1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Readings readings1 = new Readings(20.0, dateTimeDayMeasure1);
        Readings readings2 = new Readings(25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(readings1);
        s0.addReadingsToList(readings2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Readings readings3 = new Readings(20.0, dateTimeDayMeasure3);
        Readings readings4 = new Readings(30.0, dateTimeDayMeasure4);

        s1.addReadingsToList(readings3);
        s1.addReadingsToList(readings4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);


        double expectedResult = 30.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInRoomInGivenDay(name, sensorType0, dateTimeDayMeasure4.toLocalDate());


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
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);

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


        double expectedResult = -10.0;

        //Act
        double result = listOfRooms.getMaximumTemperatureInRoomInGivenDay(name, sensorType1, dateTimeDayMeasure3.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
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
    public void getSensorsListContentOfARoomTest () {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList list = new RoomList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("A456", dataFuncionamento1, sensorType1, locS1);

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
    public void checkIfSensorListIsEmptyTestTrue () {
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
    public void checkIfSensorListIsEmptyTestFalse () {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

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

   /* @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        ProgramList pgList = new ProgramList();
        FridgeSpecs specFridgeSpecs = new FridgeSpecs(100, 100, 100, 100);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(100, 100, pgList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(100, 100, pgList);
        Device dev1 = new Device("FridgeAriston", room1, specFridgeSpecs);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasherSpecs", room1, specDishWasherSpecs);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        ElectricWaterHeaterSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridgeSpecs);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasherSpecs);
        Device dev6 = new Device("ElectricWaterHeaterSpecs", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev3);
        expectedResult.add(dev4);
        expectedResult.add(dev5);
        expectedResult.add(dev6);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        List<Device> result = roomList.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);

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

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        roomList.addRoom(room);
        room.addDevice(dev1);

        int position = 0;

        // Act
        boolean result = roomList.isDeviceListEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        ProgramList pglist = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(100, 100, pglist);
        ElectricWaterHeaterSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridgeSpecs);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasherSpecs);
        Device dev6 = new Device("ElectricWaterHeaterSpecs", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        roomList.addRoom(room);
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
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // ElectricWaterHeaterSpecs Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgeratah V14", room0, fridge);
        room0.addDevice(device0);
        Device Device = new Device("Fridgeratah V15", room0, fridge);
        room0.addDevice(Device);
        Device device2 = new Device("Fridgeratah V16", room0, fridge);
        room0.addDevice(device2);
        Device device3 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device3);
        Device device4 = new Device("Bosh Tronic 4000", room1, electricWaterHeater);
        room1.addDevice(device4);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Fridgeratah V15, located in room: Kitchen\n" +
                        "3 - Device: Fridgeratah V16, located in room: Kitchen\n" +
                        "4 - Device: Bosh Tronic 3000, located in room: Laundry\n" +
                        "5 - Device: Bosh Tronic 4000, located in room: Laundry\n";

        // Act
        String result = roomList.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }*/
}
