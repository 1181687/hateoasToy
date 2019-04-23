package pt.ipp.isep.dei.project.modelTests.devices;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.devices.kettle.KettleType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class KettleTest {

    private Room kitchen;
    private Room laundry;
    private Device kettle1;
    private Device kettle2;
    private Map<LocalDateTime, Double> map;
    private DeviceReading reading0;
    private DeviceReading reading1;
    private DeviceReading reading2;

    /**
     * This method pretends to initialize some attributes of this test class to simplifying all tests.
     */
    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        RoomId kitchenId = new RoomId("Kitchen");
        RoomId laundryId = new RoomId("Laundry");
        kitchen = new Room(kitchenId, "room", 1, dim);
        laundry = new Room(laundryId, "room", 1, dim);

        // devices
        KettleType kettleType = new KettleType();
        kettle1 = kettleType.createDevice("Kettle 1");
        kettle1.setAttributesDevType("Nominal Power", 2000);
        kettle2 = kettleType.createDevice("Kettle 2");
        kettle2.setAttributesDevType("Nominal Power", 3000);
        kettle1.setLocation(kitchen);
        kettle2.setLocation(kitchen);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new DeviceReading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new DeviceReading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new DeviceReading(7, time2);
        kettle2.addReadingsToTheList(reading0);
        kettle2.addReadingsToTheList(reading1);
        kettle2.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    /**
     * Test the setLocation method with another location,
     * so the result should be true.
     */
    @Test
    public void testSetLocation_AnotherLocation_True() {
        //Arrange
        Room location = laundry;
        boolean expectedResult = true;

        //Act
        boolean result = kettle1.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setLocation method with the same location,
     * so the result should be false, as the method doesn't allow that.
     */
    @Test
    public void testSetLocation_SameLocation_False() {
        //Arrange
        Room location = kitchen;
        boolean expectedResult = false;

        //Act
        boolean result = kettle1.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setName method with a new name for the device,
     * so the result should be true.
     */
    @Test
    public void testSetName_NewName_True() {
        //Arrange
        String name = "Nome novo";
        boolean expectedResult = true;

        //Act
        boolean result = kettle1.setName(name);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        KettleType type = new KettleType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    public void testHashCode_voidReturn() {
        // Arrange
        int expectedResult = Objects.hash(kettle1.getName());

        // Act
        int result = kettle1.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setName method with the same name for the device,
     * so the result should be false, as the method doesn't allow that.
     */
    @Test
    public void testSetName_SameName_False() {
        //Arrange
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () -> kettle1.setName("Kettle 1"));

        //Assert
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    /**
     * Test the setName method with an already existent name for the device,
     * so the result should be false, as the method doesn't allow that.
     */
    @Test
    public void testSetName_ExistentName_False() {
        //Arrange
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () -> kettle1.setName("Kettle 2"));

        //Assert
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    /**
     * Tests if the method getLocation returns the location of the device kettle1.
     * The location should be the kitchen.
     */
    @Test
    public void testGetLocation_DeviceLocation() {
        //Arrange
        Room expectedResult = kitchen;
        //Act
        Room result = kettle1.getLocation();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Tests if the method getId returns the name of the device kettle1.
     * The location should be the "Kettle 1".
     */
    @Test
    public void testGetName_DeviceName() {
        //Arrange
        String expectedResult = "Kettle 1";
        //Act
        String result = kettle1.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetReadings_ListOfReadingsFromKettle2() {
        //Arrange
        List<DeviceReading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<DeviceReading> result = kettle2.getReadings();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the equals method as to know if an object is equal to another.
     * This test is comparing the same name with some Case Letters.
     * The result should be false.
     */
    @Test
    public void testEquals_SameNameWithDifferentCase_False() {
        // Arrange
        boolean expectedResult = true;
        String name = "kettle 1";
        KettleType kettleType = new KettleType();
        Device kettle = kettleType.createDevice(name);

        // Act
        boolean result = kettle1.equals(kettle);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the equals method as to know if an object is equal to another.
     * This test is comparing two different objects.
     * The result should be false.
     */
    @Test
    public void testEquals_DifferentObject_False() {
        // Arrange
        boolean expectedResult = false;
        Object object = new Object();

        // Act
        boolean result = kettle1.equals(object);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setDeactivateDevice as to know if the device is deactive.
     */
    @Test
    public void testGetDeactivationDate_False() {
        //Arrange
        kettle1.setDeactivateDevice();
        boolean expectedResult = false;

        //Act
        boolean result = kettle1.setDeactivateDevice();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the equals method as to know if an object is equal to another.
     * This test is comparing the same object.
     * The result should be true.
     */
    @Test
    public void testEquals_SameObject_True() {
        // Arrange
        boolean expectedResult = true;

        // Act
        boolean result = kettle1.equals(kettle1);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeactivationDate_DateOfDeactivation() {
        //Arrange
        LocalDateTime expectedResult = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        kettle1.setDeactivateDevice();
        //Act
        LocalDateTime result = kettle1.getDeactivationDate();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test the setDeactivateDevice as to know if the device is active.
     */
    @Test
    public void testSetDeactivationDate_True() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean result = kettle1.setDeactivateDevice();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method getIsActive when the device is active.
     * So the test should return True.
     */
    @Test
    public void testGetIsActive_True() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean result = kettle1.getIsActive();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Configuration
    static class Config {
    }

    /**
     * Test the method getIsActive when the device is deactive.
     * So the test should return False.
     */
    @Test
    public void testGetIsActive_False() {
        //Arrange
        kettle1.setDeactivateDevice();
        boolean expectedResult = false;

        //Act
        boolean result = kettle1.getIsActive();

        //Assert
        assertEquals(expectedResult, result);
    }
}
