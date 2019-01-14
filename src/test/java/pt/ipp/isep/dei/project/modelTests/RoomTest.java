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
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(name1, houseFloor1, dimensions1);

        String expectResult = "Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0";

        //act
        String result = room.getRoomContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void testHashCode() {
        //Arrange
        String name = "roomOne";
        int housefloor = 2;
        Dimensions dim = new Dimensions(4, 10.5, 7.5);
        Room room = new Room(name, housefloor, dim);

        int expectedResult = Objects.hash(name);

        // Act
        int result = room.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }
    
    @Test
    void testhashCodeNotEquals() {
        //Arrange
        String name = "roomOne";
        String name2 = "roomtwo";
        int housefloor = 2;
        Dimensions dim = new Dimensions(4, 10.5, 7.5);

        // Act
        int hash1 = Objects.hash(name, housefloor, dim);
        int hash2 = Objects.hash(name2, housefloor, dim);
        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Dimensions dim2 = new Dimensions(3.5, 3.5, 3.5);
        Room room2 = new Room("Room", 2, dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        //Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Dimensions dim2 = new Dimensions(3.5, 3.5, 3.5);
        Room room2 = new Room("RoomTwo", 2, dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
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

        Dimensions dim = new Dimensions(3, 3.5, 3.5);

        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.addSensorToTheListOfSensorsInTheRoom(s1);

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

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        sensorList.addSensorToTheListOfSensors(s0);
        List<Sensor> expectedResult = sensorList.getmSensorList();
        //Act
        List<Sensor> result = room.getSensorList().getmSensorList();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testValidateNameNull() {
        String name = null;
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameEmpty() {
        String name = "  ";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateDimensions() {
        String name = "Room 1";
        Dimensions dim = null;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, 2, dim)
        );
        assertEquals("Dimensions should not be null", exception.getMessage());
    }

    @Test
    public void getSensorsListContentTest () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
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

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);

        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = room.getSensorListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.checkIfSensorListIsEmpty();

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

        room.addSensorToTheListOfSensorsInTheRoom(s0);

        // Act
        boolean result = room.checkIfSensorListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetDeviceList() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        Fridge specFridge = new Fridge();
        WashingMachine specWashing = new WashingMachine();
        DishWasher specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room, specWashing, 300);
        Device dev3 = new Device("DishWasher", room, specDishWasher, 400);

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
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        Fridge specFridge = new Fridge();
        Device dev1 = new Device("FridgeAriston", room, specFridge, 300);

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
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity);
        double nominalPower = 100.5;
        Device dev = new Device("Fridge1", room, deviceSpecs, nominalPower);


        double luminousFlux = 10.0;
        double energyConsumption1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux);
        double nominalPower1 = 0.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);

        room.addDevice(dev);
        room.addDevice(dev1);


        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = room.getDeviceListContent();

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = room.checkIfDeviceListIsEmpty();

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double energyConsumption1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux);
        double nominalPower1 = 1.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);

        room.addDevice(dev1);

        // Act
        boolean result = room.checkIfDeviceListIsEmpty();

        // Assert
        assertFalse(result);
    }
}
