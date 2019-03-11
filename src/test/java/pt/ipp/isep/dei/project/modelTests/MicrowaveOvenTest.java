package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class MicrowaveOvenTest {
    private Room kitchen;
    private Room laundry;
    private Device microwaveOven;
    private House house;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;


    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);


        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", 1, dim);
        this.laundry = new Room("Laundry", 1, dim);

        house.addRoom(kitchen);
        house.addRoom(laundry);


        // devices
        house.createDevice("MicrowaveOven", "Becken BMW2329", kitchen);
        microwaveOven = house.createDevice("MicrowaveOven", "Becken BMW2328", kitchen);
        microwaveOven.setAttributesDevType("Time", 1);
        microwaveOven.setAttributesDevType("Nominal Power", 1200);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
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
    public void testGetLocation() {
    }

    @Test
    public void testSetLocation() {
    }

    @Test
    public void testGetSpecs() {
    }

    @Test
    public void testGetReadings() {
    }

    @Test
    public void testGetIsActive() {
    }

    @Test
    public void testGetDeactivationDate() {
    }

    @Test
    public void testSetDeactivateDevice() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

}