package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    void testhashCode() {
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
                "1 - Name: A123\n" +
                        "2 - Name: A456\n";
        // Act
        String result = room.getSensorsListContent();

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

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room, specWashing, 300);
        Device dev3 = new Device("DishWasher", room, specDishWasher, 400);

        room.addDevice(dev1);
        room.addDevice(dev2);
        room.addDevice(dev3);
        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1, dev2, dev3));

        List<Device> result = room.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDevice() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room, specFridge, 300);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1));

        room.addDevice(dev1);
        List<Device> result = room.getDeviceList();

        assertEquals(expectedResult, result);
    }
}
