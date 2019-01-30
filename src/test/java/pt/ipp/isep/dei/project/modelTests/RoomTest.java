package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        Fridge specFridge = new Fridge(100, 100, 100, 100);
        WashingMachine specWashing = new WashingMachine(100, 100, pgList);
        DishWasher specDishWasher = new DishWasher(100, 100, pgList);
        Device dev1 = new Device("FridgeAriston", room, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room, specWashing);
        Device dev3 = new Device("DishWasher", room, specDishWasher);

        room.addDevice(dev1);
        room.addDevice(dev2);
        room.addDevice(dev3);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);
        expectedResult.addDevice(dev2);
        expectedResult.addDevice(dev3);

        DeviceList result = room.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDevice() {
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        Fridge specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);

        room.addDevice(dev1);
        DeviceList result = room.getDeviceList();

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
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 0.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

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

   /* @Test
    public void testGetNominalPower() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Fridge specFridge = new Fridge(25, 50, 5000, 500);
        WashingMachine specWashing = new WashingMachine(400, 250);
        DishWasher specDishWasher = new DishWasher(400, 250);
        Device dev1 = new Device("FridgeAriston", room, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room, specWashing);
        Device dev3 = new Device("DishWasher", room, specDishWasher);

        room.addDevice(dev1);
        room.addDevice(dev2);
        room.addDevice(dev3);

        double expectedResult = 1000;

        // Act
        double result = room.getNominalPower();

        // Assert
        assertEquals(expectedResult, result);
    }*/

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
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

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
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        int expectResult = 2;
        //act
        int result = room.getDevicesListLength();
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
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room1, deviceSpecs1);

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
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room1, deviceSpecs1);

        room1.addDevice(dev1);

        room2.addDevice(dev1);

        Device expectedResult = dev1;

        //act
        Device result = room2.getDeviceList().getDeviceByPosition(0);

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
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

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
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = room.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void testGetDeviceTypeListContent() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);


        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- Dish Washer\n" +
                "4- Washing Machine\n" +
                "5- Electric Water Heater\n";
        //Act
        String result = room.getDeviceTypeListToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    ///testar a partir daqui

    @Test
    public void newElectricWaterHeater() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        Device expectedResult = new Device("Electric", room, electricWaterHeater1);
        String name = "Electric";

        Device result = devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        String name = "ELECTRIC2";

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio)
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
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Device2", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, washingMachine);

        Device result = devList.newWashingMachine(name, room, nominalPower, capacity, programList);

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
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Washing Machine Bosh", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);


        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newWashingMachine(name, room, nominalPower, capacity, programList)
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

        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower, programList);

        Device d2 = new Device("Device2", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, dishWasher);

        Device result = devList.newDishWasher(name, room, nominalPower, capacity, programList);

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

        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower, programList);

        Device d2 = new Device("Dish Washer Ariston", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newDishWasher(name, room, nominalPower, capacity, programList)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewLamp() {
        String name = "Lamp one";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;
        DeviceSpecs lamp = new Lamp(luminousFlux, nominalPower);
        ProgramList programList = new ProgramList();

        //Device d2 = new Device("Device2", room, lamp);
        DeviceList devList = room.getDeviceList();
        Room room2 = new Room("Room2", 2, dim);

        //  devList.addDevice(d2);

        Device expectedResult = new Device(name, room2, lamp);

        Device result = devList.newLamp(name, room, luminousFlux, nominalPower);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        String name = "Lamp one";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);

        Device d2 = new Device("LAMP ONE", room, lamp);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newLamp(name, room, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewFrigde() {
        String name = "Fridge Balay";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device("Device2", room, fridgeSpecs);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, fridgeSpecs);

        Device result = devList.newFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFridgeNegative() {
        String name = "Fridge Balay";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device("Fridge Balay", room, fridgeSpecs);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

}
