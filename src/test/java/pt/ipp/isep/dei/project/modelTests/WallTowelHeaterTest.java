package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {
    private static final String WALL_TOWEL_HEATER_TYPE = "WallTowelHeater";
    private House house;
    private Room kitchen;
    private Room bathroom;
    private Device wallTowerHeater;


    @BeforeEach
    private void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        bathroom = new Room("Bathroom", 1, dim);
        this.house.addRoom(kitchen);
        this.house.addRoom(bathroom);

        //Device
        String deviceName = "Towel Warmer XPT0";

        wallTowerHeater = house.createDevice(WALL_TOWEL_HEATER_TYPE, deviceName, this.bathroom);
        wallTowerHeater.setAttributesDevType("Nominal Power", 90.0);
        wallTowerHeater.setAttributesDevType("Time", 120.0);

        // Readings
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);
        wallTowerHeater.addReadingsToTheList(reading0);
        wallTowerHeater.addReadingsToTheList(reading1);
        wallTowerHeater.addReadingsToTheList(reading2);
    }

    @Test
    void getLocation() {
        //Arrange
        Room expectedResult= this.bathroom;
        //Act
        Room result = this.wallTowerHeater.getLocation();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getName() {
        //Arrange
        String expectedResult = "Towel Warmer XPT0";
        //Act
        String result= this.wallTowerHeater.getName();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setLocation_WithRoomDifferentFromLocation_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setLocation(this.kitchen);
        //Assert
        assertTrue(result);
    }

    @Test
    void setLocation_WithRoomEqualToLocation_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setLocation(this.bathroom);
        //Assert
        assertFalse(result);
    }

    @Test
    void getIsActive_WithActiveDevice_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    void getIsActive_WithDeactivatedDevice_ShouldReturnFalse() {
        //Arrange
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        boolean result = this.wallTowerHeater.getIsActive();
        //Assert
        assertFalse(result);
    }

    @Test
    void getDeactivationDate() {
        //Arrange
        LocalDateTime expectedResult = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        LocalDateTime result = this.wallTowerHeater.getDeactivationDate();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setDeactivateDevice_WithActiveDevice_ShouldReturnTrue() {
        //Act
        boolean result = this.wallTowerHeater.setDeactivateDevice();
        //Assert
        assertTrue(result);
    }

    @Test
    void setDeactivateDevice_WithDeviceThatIsAlreadyDeactivated_ShouldReturnFalse() {
        //Arrange
        this.wallTowerHeater.setDeactivateDevice();
        //Act
        boolean result = this.wallTowerHeater.setDeactivateDevice();
        //Assert
        assertFalse(result);
    }

    @Test
    void setName_WithDifferentName_ShouldReturnTrue() {
        //Arrange
        String newName = "TowelHeater2000";
        //Act
        boolean result = this.wallTowerHeater.setName(newName);
        //Assert
        assertTrue(result);
    }

    @Test
    void setName_WithSameName_ShouldReturnFalse() {
        //Arrange
        String newName = "Towel Warmer XPT0";
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () -> this.wallTowerHeater.setName(newName));
        //Assert
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    void setName_RoomAlreadyHaveDeviceWithSameName_ShouldReturnFalse() {
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
    void getReadings() {
    }
}