package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.getlastcoldestdayhouseareacontroller.GetLastColdestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetLastColdestDayHouseAreaControllerTest {

    private static final String CONFIG_PROPERTIES = "Configuration.properties";
    private GeographicalArea northernRegion;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private GeoAreaSensor temperatureSensor;
    private GeoAreaSensor temperatureSensor1;
    private SensorType temperature;
    private Location location2;
    private House house;
    private GetLastColdestDayHouseAreaController controller;

    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeographicalAreaType region = new GeographicalAreaType("Region");
        GeographicalAreaType district = new GeographicalAreaType("District");
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100, location);
        northernRegion = new GeographicalArea("North", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40, location1);
        portoDistrict = new GeographicalArea("Porto District", "Porto District", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        this.location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10, location2);
        portoCity = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        temperature = new SensorType("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new GeoAreaSensor("S01", "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new GeoAreaSensor("S01", "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 2, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 3, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        LocalDateTime readingDate4 = LocalDateTime.of(2018, 12, 4, 05, 24, 00);
        LocalDateTime readingDate5 = LocalDateTime.of(2018, 12, 5, 05, 24, 00);
        LocalDateTime readingDate6 = LocalDateTime.of(2018, 12, 4, 04, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        Reading reading4 = new Reading(20, readingDate4);
        Reading reading5 = new Reading(23, readingDate5);
        Reading reading6 = new Reading(19, readingDate6);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);

        //Add sensors to SensorList

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        controller = new GetLastColdestDayHouseAreaController(house);
    }

    @Test
    public void hasReadingsBetweenDates_WithNoReadingsInInterval_ShouldReturnFalse() {
        //Arrange
        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 2);
        //Act
        boolean result = controller.hasReadingsBetweenDates(startDate, endDate);
        //Assert
        assertFalse(result);
    }

    @Test
    public void hasReadingsBetweenDates_WithReadingsInInterval_ShouldReturnTrue() {
        //Arrange
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 5);
        //Act
        boolean result = controller.hasReadingsBetweenDates(startDate, endDate);
        //Assert
        assertTrue(result);
    }

    @Test
    public void getLastLowestMaximumReading_WithOneLowestMaxReadingInInterval_ShouldReturnLastLowestMaxReadingDTO() {
        //Arrange
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        LocalDateTime readingDate4 = LocalDateTime.of(2018, 12, 4, 05, 24, 00);
        double value = 20;
        ReadingDTO expectedResultDTO = ReadingMapper.newReadingDTO();
        expectedResultDTO.setValue(value);
        expectedResultDTO.setDateTime(readingDate4);
        Reading expectedResult = ReadingMapper.mapToEntity(expectedResultDTO);

        //Act
        ReadingDTO resultDTO = controller.getLastLowestMaximumReading(startDate, endDate);
        Reading result = ReadingMapper.mapToEntity(resultDTO);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestMaximumReading_WithTwoEqualLowestMaxReadingInInterval_ShouldReturnLastOne() {
        //Arrange
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 6, 04, 24, 00);
        Reading reading = new Reading(20, readingDate);
        temperatureSensor1.addReadingsToList(reading);

        double value = 20;
        ReadingDTO expectedResultDTO = ReadingMapper.newReadingDTO();
        expectedResultDTO.setValue(value);
        expectedResultDTO.setDateTime(readingDate);
        Reading expectedResult = ReadingMapper.mapToEntity(expectedResultDTO);

        //Act
        ReadingDTO resultDTO = controller.getLastLowestMaximumReading(startDate, endDate);
        Reading result = ReadingMapper.mapToEntity(resultDTO);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void hasSensorsOfGivenTypeInGeoArea_withSensorsOfGivenTypeInArea_ShouldReturnTrue() {
        //Act
        boolean result = this.controller.hasSensorsOfGivenTypeInGeoArea();
        //Assert
        assertTrue(result);
    }

    @Test
    public void hasSensorsOfGivenTypeInGeoArea_withoutSensorsOfGivenTypeInArea_ShouldReturnFalse() {
        //Arrange
        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas

        Location location = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10, location);
        GeographicalArea porto = new GeographicalArea("Porto", "Porto City", city, location, areaShape2);

        // New house in geographical area without sensors
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        House house2 = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, porto);
        house2.setAddress(address);

        GetLastColdestDayHouseAreaController controller2 = new GetLastColdestDayHouseAreaController(house2);
        //Act
        boolean result = controller2.hasSensorsOfGivenTypeInGeoArea();
        //Assert
        assertFalse(result);
    }
}
