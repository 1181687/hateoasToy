package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.walltowelheater.WallTowelHeaterType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WallTowelHeaterTest {
    private static final String WALL_TOWEL_HEATER_TYPE = "WallTowelHeater";
    private static final String NOMINAL_POWER = "Nominal Power";
    private House house;
    private Room kitchen;
    private Room bathroom;
    private Device wallTowerHeater;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;


    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        Address address = new Address(null, null, null);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", "room", 1, dim);
        bathroom = new Room("Bathroom", "room", 1, dim);
        this.house.addRoom(kitchen);
        this.house.addRoom(bathroom);

        //Device
        String deviceName = "Towel Warmer XPT0";

        wallTowerHeater = house.createDevice(WALL_TOWEL_HEATER_TYPE, deviceName, this.bathroom);
        wallTowerHeater.setAttributesDevType(NOMINAL_POWER, 90.0);
        wallTowerHeater.setAttributesDevType("Time", 120.0);

        // Readings
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        wallTowerHeater.addReadingsToTheList(reading0);
        wallTowerHeater.addReadingsToTheList(reading1);
        wallTowerHeater.addReadingsToTheList(reading2);
    }

    @Test
    public void getLocation() {
        //Arrange
        Room expectedResult = this.bathroom;
        //Act
        Room result = this.wallTowerHeater.getLocation();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getName() {
        //Arrange
        String expectedResult = "Towel Warmer XPT0";
        //Act
        String result = this.wallTowerHeater.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setLocation_WithRoomDifferentFromLocation_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setLocation(this.kitchen);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setLocation_WithRoomEqualToLocation_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setLocation(this.bathroom);
        //Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        WallTowelHeaterType type = new WallTowelHeaterType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(kitchen);


        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActive_WithActiveDevice_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    public void getIsActive_WithDeactivatedDevice_ShouldReturnFalse() {
        //Arrange
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        boolean result = this.wallTowerHeater.getIsActive();
        //Assert
        assertFalse(result);
    }

    @Test
    public void getDeactivationDate() {
        //Arrange
        LocalDateTime expectedResult = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        LocalDateTime result = this.wallTowerHeater.getDeactivationDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setDeactivateDevice_WithActiveDevice_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setDeactivateDevice();
        //Assert
        assertTrue(result);
    }

    @Test
    public void setDeactivateDevice_WithDeviceThatIsAlreadyDeactivated_ShouldReturnFalse() {
        //Arrange
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        boolean result = this.wallTowerHeater.setDeactivateDevice();
        //Assert
        assertFalse(result);
    }

    @Test
    public void setName_WithDifferentName_ShouldReturnTrue() {
        //Arrange
        String newName = "TowelHeater2000";
        //Act
        boolean result = this.wallTowerHeater.setName(newName);
        //Assert
        assertTrue(result);
    }

        @Test
        public void setName_WithSameName_ShouldReturnFalse() {
            //Arrange
            String newName = "Towel Warmer XPT0";
            //Act
            Throwable exception = assertThrows(RuntimeException.class, () -> this.wallTowerHeater.setName(newName));
            //Assert
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

        @Test
        public void setName_RoomAlreadyHaveDeviceWithSameName_ShouldReturnFalse() {
            //Arrange
            String deviceName = "WarmyTowel";

            Device wallTowerHeater1 = house.createDevice(WALL_TOWEL_HEATER_TYPE, deviceName, this.bathroom);

            String newName = "WarmyTowel";
            //Act
            Throwable exception = assertThrows(RuntimeException.class, () -> this.wallTowerHeater.setName(newName));
            //Assert
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

    @Test
    public void getReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(this.reading0);
        expectedResult.add(this.reading1);
        expectedResult.add(this.reading2);
        //Act
        List<Reading> result = this.wallTowerHeater.getReadings();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDevSpecsAttributesToString() {
        //Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n";
        //Act
        String result = this.wallTowerHeater.getDevSpecsAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToString() {
        //Arrange
        String expectedResult = "1 - Name: Towel Warmer XPT0\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Bathroom\n";
        //Act
        String result = this.wallTowerHeater.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevType_WithNewValueDifferentFromOldOne_ShouldReturnTrue() {
        //Arrange
        double newValue = 20.0;
        //Act
        boolean result = this.wallTowerHeater.setAttributesDevType(NOMINAL_POWER, newValue);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setAttributesDevType_WithNewValueEqualToOldOne_ShouldReturnFalse() {
        //Arrange
        double newValue = 90.0;
        //Act
        boolean result = this.wallTowerHeater.setAttributesDevType(NOMINAL_POWER, newValue);
        //Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributes() {
        //Arrange
        int expectedResult = 1;
        //Act
        int result = this.wallTowerHeater.getNumberOfSpecsAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToString() {
        //Arrange
        String expectedResult = "Device: Towel Warmer XPT0, located in room: Bathroom\n";
        //Act
        String result = this.wallTowerHeater.getNameToString();
        //Assert
        assertEquals(expectedResult, result);
    }
}