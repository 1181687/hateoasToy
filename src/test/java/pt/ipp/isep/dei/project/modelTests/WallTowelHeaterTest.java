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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {
    private House house;
    private Room kitchen;
    private Room bathroom;
    private Device wallTowerHeater;


    @BeforeEach
    public void StartUp() {
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
        wallTowerHeater = house.createDevice("Wall Towel Heater", deviceName, this.bathroom);
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
    }

    @Test
    void getName() {
    }

    @Test
    void getSpecs() {
    }

    @Test
    void setLocation() {
    }

    @Test
    void getIsActive() {
    }

    @Test
    void getDeactivationDate() {
    }

    @Test
    void setDeactivateDevice() {
    }

    @Test
    void setName() {
    }

    @Test
    void getReadings() {
    }
}