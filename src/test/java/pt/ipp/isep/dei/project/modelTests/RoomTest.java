package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void getDisplayRoomTest() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(name1, houseFloor1, dimension1);

        String expectResult = "Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0";

        //act
        String result = room.getRoomToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void testHashCode() {
        //Arrange
        String name = "roomOne";
        int housefloor = 2;
        Dimension dim = new Dimension(4, 10.5, 7.5);
        Room room = new Room(name, housefloor, dim);

        int expectedResult = Objects.hash(name);

        // Act
        int result = room.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testHashCodeNotEquals() {
        //Arrange
        String name = "roomOne";
        String name2 = "roomtwo";
        int housefloor = 2;
        Dimension dim = new Dimension(4, 10.5, 7.5);

        // Act
        int hash1 = Objects.hash(name, housefloor, dim);
        int hash2 = Objects.hash(name2, housefloor, dim);
        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room("ROOM", 2, dim);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        Room room2 = new Room("Room", 2, dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Dimension dim2 = new Dimension(3.5, 3.5, 3.5);
        Room room2 = new Room("RoomTwo", 2, dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        SensorType tipo = new SensorType("humidade");

        //Act
        boolean result = room.equals(tipo);
        //Assert
        assertFalse(result);
    }

    @Test
    void testAddSensorToTheListOfSensorsInTheRoom() {
        // Arrange
        LocalDateTime dataFuncionamento = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);

        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Dimension dim = new Dimension(3, 3.5, 3.5);

        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.addSensorToListOfSensorsInRoom(s1);

        // Assert
        assertTrue(result);
    }

    @Test
    void getSensorList() {
        //Arrange
        SensorList sensorList = new SensorList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        room.addSensorToListOfSensorsInRoom(s0);
        sensorList.addSensor(s0);
        List<Sensor> expectedResult = sensorList.getSensorList();
        //Act
        List<Sensor> result = room.getSensorList().getSensorList();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testValidateNameNull() {
        String name = null;
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameEmpty() {
        String name = "  ";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateDimensions() {
        String name = "Room 1";
        Dimension dim = null;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Dimension should not be null", exception.getMessage());
    }

    @Test
    public void getSensorsListContentTest() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        SensorList sensorList = new SensorList();

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

        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = room.getSensorListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.isSensorListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        room.addSensorToListOfSensorsInRoom(s0);

        // Act
        boolean result = room.isSensorListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetDeviceList() {
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        ProgramList pgList = new ProgramList();
        FridgeSpecs specFridgeSpecs = new FridgeSpecs(100, 100, 100, 100);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(100, 100, pgList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(100, 100, pgList);
        Device1 dev1 = new Device1("FridgeAriston", room, specFridgeSpecs);
        Device1 dev2 = new Device1("WashingMachineBosh", room, specWashing);
        Device1 dev3 = new Device1("DishWasherSpecs", room, specDishWasherSpecs);

        room.addDevice(dev1);
        room.addDevice(dev2);
        room.addDevice(dev3);

        List<Device1> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev3);

        List<Device1> result = room.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDevice() {
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        FridgeSpecs specFridgeSpecs = new FridgeSpecs(100, 100, 100, 100);
        Device1 dev1 = new Device1("FridgeAriston", room, specFridgeSpecs);

        List<Device1> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);

        room.addDevice(dev1);
        List<Device1> result = room.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device1 dev = new Device1("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 0.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);


        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = room.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNominalPower() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        ProgramList programList = new ProgramList();
        FridgeSpecs specFridge = new FridgeSpecs(25, 50, 5000, 500);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(400, 250, programList);
        DishWasherSpecs specDishWasher = new DishWasherSpecs(400, 250, programList);
        Device1 dev1 = new Device1("FridgeAriston", room, specFridge);
        Device1 dev2 = new Device1("WashingMachineBosh", room, specWashing);
        Device1 dev3 = new Device1("DishWasherSpecs", room, specDishWasher);

        room.addDevice(dev1);
        room.addDevice(dev2);
        room.addDevice(dev3);

        double expectedResult = 1000;

        // Act
        double result = room.getNominalPower();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // Act
        boolean result = room.isDeviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device1 dev2 = new Device1("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        int expectResult = 2;
        //act
        int result = room.getDeviceList().size();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddDeviceDevicePointer() {
        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room(name, 2, dim);

        String name2 = "Bedroom";
        Dimension dim2 = new Dimension(3, 3.5, 3.5);
        Room room2 = new Room(name2, 2, dim2);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room1, deviceSpecs1);

        room1.addDevice(dev1);

        room2.addDevice(dev1);

        Room expectedResult = room2;

        //act
        Room result = dev1.getLocation();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceDeviceListPointer() {
        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room(name, 2, dim);

        String name2 = "Bedroom";
        Dimension dim2 = new Dimension(3, 3.5, 3.5);
        Room room2 = new Room(name2, 2, dim2);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room1, deviceSpecs1);

        room1.addDevice(dev1);

        room2.addDevice(dev1);

        Device1 expectedResult = dev1;

        //act
        Device1 result = room2.getDeviceList().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDevices() {
        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        String name1 = "Bedroom";
        Room room1 = new Room(name1, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        //Act
        boolean result = room1.addDevice(dev1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddDevicesFalse() {
        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        //Act
        boolean result = room.addDevice(dev1);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testGetNameToString() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room1", 2, dim);

        String expectedResult =
                "Room: Room1\n";


        // Act
        String result = room.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriod() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgerator", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = room.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoFullPeriods() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgerator", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = room.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithoutFullPeriods() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgerator", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = room.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriodDeviceListEmpty() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Kitchen", 1, dim);

        double expectedResult = 0.0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = room.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void newElectricWaterHeater() {
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        String name = "Electric";
        Device1 expectedResult = room.newElectricWaterHeater(name, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Device1 result = room.getDeviceByPosition(0);

        assertEquals(expectedResult, result);

    }

    @Test
    public void newElectricWaterHeaterNegative() {
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device1 d2 = new Device1("Electric2", room, electricWaterHeater1);

        room.addDevice(d2);
        String name = "ELECTRIC2";

        Throwable exception = assertThrows(RuntimeException.class, () ->
                room.newElectricWaterHeater(name, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }

    @Test
    public void testNewWashingMachine() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();


        Device1 expectedResult = room.newWashingMachine(name, nominalPower, capacity, programList);

        Device1 result = room.getDeviceByPosition(0);


        assertEquals(expectedResult, result);

    }

    @Test
    public void testNewWashingMachineNegative() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();
        DeviceSpecs washingMachine = new WashingMachineSpecs(capacity, nominalPower, programList);

        Device1 d2 = new Device1("Washing Machine Bosh", room, washingMachine);
        room.addDevice(d2);


        Throwable exception = assertThrows(RuntimeException.class, () ->
                room.newWashingMachine(name, nominalPower, capacity, programList)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewDishWasher() {
        String name = "Dish Washer Ariston";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        ProgramList programList = new ProgramList();


        Device1 expectedResult = room.newDishWasher(name, nominalPower, capacity, programList);

        Device1 result = room.getDeviceByPosition(0);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testNewDishWasherNegative() {
        String name = "Dish Washer Ariston";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        ProgramList programList = new ProgramList();

        DeviceSpecs dishWasher = new DishWasherSpecs(capacity, nominalPower, programList);

        Device1 d2 = new Device1("Dish Washer Ariston", room, dishWasher);
        room.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                room.newDishWasher(name, nominalPower, capacity, programList)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewLamp() {
        String name = "LampSpecs one";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;
        DeviceSpecs lamp = new LampSpecs(luminousFlux, nominalPower);
        ProgramList programList = new ProgramList();

        Device1 d2 = new Device1("Device2", room, lamp);
        Room room2 = new Room("Room2", 2, dim);

        room.addDevice(d2);

        Device1 expectedResult = new Device1(name, room2, lamp);

        Device1 result = room.newLamp(name, luminousFlux, nominalPower);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        String name = "LAMP ONE";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs lamp = new LampSpecs(capacity, nominalPower);

        Device1 d2 = new Device1("LAMP ONE", room, lamp);
        room.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                room.newLamp(name, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewFrigde() {
        String name = "FridgeSpecs Balay";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;

        Device1 expectedResult = room.newFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        Device1 result = room.getDeviceByPosition(0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFridgeNegative() {
        String name = "FridgeSpecs Balay";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device1 d2 = new Device1("FridgeSpecs Balay", room, fridgeSpecs);
        room.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                room.newFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }


    @Test
    public void deleteDeviceTrue() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = room.deleteDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deleteDeviceFalse() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        // act
        boolean result = room.deleteDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;

        String expectedResult = "There are no devices in the device list.";

        // act
        String result = room.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameByPosition() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        int position = 0;

        room.addDevice(dev1);
        String expectedResult = "Lamp1";

        // act
        String result = room.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void deactivationDeviceTrue() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = room.deactivateDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivationDeviceFalse() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        // act
        boolean result = room.deactivateDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getActiveDeviceListToString_Active() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device1 dev2 = new Device1("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        String expectedResult =
                "1 - Name of the device: Lamp1 - ACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        // Act
        String result = room.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_OneDeviceDeactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device1 dev2 = new Device1("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);
        dev1.setDeactivateDevice();

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        // Act
        String result = room.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_Deactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device1 dev2 = new Device1("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);
        dev1.setDeactivateDevice();
        dev2.setDeactivateDevice();

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - DEACTIVATED\n";
        // Act
        String result = room.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDataSeries() {
        ///Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room = new Room("Room", 2, dimension);

        DeviceSpecs deviceSpecs = new LampSpecs(25, 20);
        Device1 lamp = new Device1("LampSpecs", room, deviceSpecs);

        DeviceSpecs specsFridge = new FridgeSpecs(12, 15, 25, 12);
        Device1 fridge = new Device1("FridgeSpecs", room, specsFridge);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        Map<LocalDateTime, Double> expectedResult = new TreeMap<>();
        expectedResult.put(time0, 6.0);
        expectedResult.put(time1, 10.0);
        expectedResult.put(time2, 14.0);

        //Act
        Map<LocalDateTime, Double> result = room.getDataSeries(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

}
