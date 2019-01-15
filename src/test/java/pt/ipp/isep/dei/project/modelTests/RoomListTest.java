package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

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
        String result = rList.getChosenRoomContent(0);
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
        String result = rList.getChosenRoomContent(chosenRoomPositionInList);
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
        String result = rList.getChosenRoomContent(chosenRoomPositionInList);
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
        String result = rList.getChosenRoomContent(chosenRoomPositionInList);
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
        String result = rList.getChosenRoomContent(chosenRoomPositionInList);
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
        String result = rList.getChosenRoomContent(chosenRoomPositionInList);
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
    public void getSensorsListContentOfARoomTest () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
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

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);

        list.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                "2 - Name of the sensor: A456\n";
        // Act
        String result = list.getSensorListContentOfARoom(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        roomList.addRoom(room);

        int position = 0;
        // Act
        boolean result = roomList.checkIfSensorListIsEmpty(position);

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

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        roomList.addRoom(room);
        room.addSensorToTheListOfSensorsInTheRoom(s0);

        int position = 0;
        // Act
        boolean result = roomList.checkIfSensorListIsEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        //DeviceSpecs specFridge = new Fridge();
        Fridge specFridge = new Fridge();
        WashingMachine specWashing = new WashingMachine();
        DishWasher specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater();
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);
        expectedResult.addDevice(dev2);
        expectedResult.addDevice(dev3);
        expectedResult.addDevice(dev4);
        expectedResult.addDevice(dev5);
        expectedResult.addDevice(dev6);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        DeviceList result = roomList.getAllDevicesList();

        assertEquals(expectedResult, result);
    }


    @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange

        //initiate Room
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);

        roomList.addRoom(room);
        house.addRoom(room);


        int position = 0;

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = roomList.getDeviceListContentOfARoom(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        roomList.addRoom(room);

        house.addRoom(room);


        int position = 0;

        // Act
        boolean result = roomList.checkIfDeviceListIsEmpty(position);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        roomList.addRoom(room);
        room.addDevice(dev1);

        int position = 0;

        // Act
        boolean result = roomList.checkIfDeviceListIsEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        DishWasher dishWasher = new DishWasher();
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater();
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridge);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        roomList.addRoom(room);
        roomList.addRoom(room2);

        // Act
        boolean result = roomList.checkIfThereAreNoDevices();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesTrue() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        roomList.addRoom(room);

        // Act
        boolean result = roomList.checkIfThereAreNoDevices();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0);
        double hotWaterTemp1 = 60;
        double maximumVolume1 = 200;
        double nominalPower1 = 110;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, nominalPower1);

        // Device Instantiation
        Device device0 = new Device("Electric Water Heater", room, electricWaterHeater0);
        Device device1 = new Device("Electric Water Heater", room, electricWaterHeater1);

        room.addDevice(device0);
        room.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        roomList.setColdWaterTempAndVolumeOfWaterToHeat(30, 100);

        double expectedResult = 5233.5;

        // Act
        double result = roomList.getEnergyConsumptionInADayOfAllDevicesOfAType("Electric Water Heater");

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}
