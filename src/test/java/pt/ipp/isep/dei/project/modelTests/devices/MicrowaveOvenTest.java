package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.devices.microwaveoven.MicrowaveOvenType;
import pt.ipp.isep.dei.project.model.house.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MicrowaveOvenTest {
    private Room kitchen;
    private Room laundry;
    private Device microwaveOven;
    private Map<LocalDateTime, Double> map;
    private DeviceReading reading0;
    private DeviceReading reading1;
    private DeviceReading reading2;


    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        RoomId kitchenId = new RoomId("Kitchen");
        RoomId laundryId = new RoomId("Laundry");
        kitchen = new Room(kitchenId, "room", 1, dim);
        laundry = new Room(laundryId, "room", 1, dim);


        // devices
        MicrowaveOvenType microwaveOvenType = new MicrowaveOvenType();
        Device dummyMicrowave = microwaveOvenType.createDevice("Becken BMW2329");
        microwaveOven = microwaveOvenType.createDevice("Becken BMW2328");
        microwaveOven.setLocation(kitchen);
        dummyMicrowave.setLocation(kitchen);
        microwaveOven.setAttributesDevType("Time", 1);
        microwaveOven.setAttributesDevType("Nominal Power", 1200);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new DeviceReading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new DeviceReading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new DeviceReading(7, time2);
        microwaveOven.addReadingsToTheList(reading0);
        microwaveOven.addReadingsToTheList(reading1);
        microwaveOven.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time0, 3.0);
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void testGetName() {
        // Arrange
        String expectedResult = "Becken BMW2328";

        // Act
        String result = microwaveOven.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> microwaveOven.setName("Becken BMW2328"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> microwaveOven.setName("Becken BMW2329"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = microwaveOven.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = microwaveOven.setName("Bosch 700 Series");

        // Assert
        assertTrue(result);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = microwaveOven.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setLocationFalseTest() {
        // Act
        boolean result = microwaveOven.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = microwaveOven.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        MicrowaveOvenType type = new MicrowaveOvenType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    public void getNominalPowerTest() {
        //Arrange
        double expectedResult = 1200.0;

        //Act
        double result = microwaveOven.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<DeviceReading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<DeviceReading> result = microwaveOven.getReadings();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = microwaveOven.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalseTest() {
        // arrange
        microwaveOven.setDeactivateDevice();

        // Act
        boolean result = microwaveOven.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        microwaveOven.setDeactivateDevice();
        // act
        LocalDateTime result = microwaveOven.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(microwaveOven.getName());

        // Act
        int result = microwaveOven.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = microwaveOven.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetDeactivateDevice_true() {
        boolean expectedResult = true;
        boolean result = microwaveOven.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeactivateDevice_false() {
        boolean expectedResult = false;
        microwaveOven.setDeactivateDevice();
        boolean result = microwaveOven.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

}