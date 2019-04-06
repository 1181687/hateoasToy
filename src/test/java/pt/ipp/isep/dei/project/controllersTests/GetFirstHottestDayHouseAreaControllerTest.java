package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.getfirsthottestdayhouseareacontroller.GetFirstHottestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetFirstHottestDayHouseAreaControllerTest {
    private static final String CONFIG_PROPERTIES = "Configuration.properties";
    private GeographicalArea northernRegion;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private Sensor temperatureSensor;
    private Sensor temperatureSensor1;
    private SensorType temperature;
    private Location location2;
    private House house;
    private GetFirstHottestDayHouseAreaController controller;

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
        temperatureSensor = new Sensor("S01", "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("S01", "B123", startDate1, temperature, sensorLocation1, "l/m2");

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

        controller = new GetFirstHottestDayHouseAreaController(house);
    }

    /**
     * There are 2 temperature sensors in Geographical area portocity
     * expected that isSensorListEmpty method returns false;
     **/
    @Test
    public void isSensorListEmpty_False() {
        // Arrange

        // Act
        boolean result = this.controller.isSensorListOfATypeEmpty();

        // Assert
        assertFalse(result);
    }

    /**
     * There are no temperature sensors in Geographical area "newGeoArea"
     * expected that isSensorListEmpty method returns true;
     **/
    @Test
    public void isSensorListEmptyTest_True() {
        // Arrange
        GeographicalAreaType street = new GeographicalAreaType("Street");

        // Geographical Area

        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(10, 10, location);
        GeographicalArea newGeoArea = new GeographicalArea("PortoStreet", "Porto Street", street, location, areaShape);
        // House

        house.setInsertedGeoArea(newGeoArea);

        // Act
        boolean result = this.controller.isSensorListOfATypeEmpty();

        // Assert
        assertTrue(result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * there are 5 measurements in that period (reading2, reading3, reading4, reading5 and reading6)
     * expected to return true;
     **/
    @Test
    public void checkNearestSensorReadingsExistenceBetweenDates_True() {
        // Arrange
        LocalDate initialDate = LocalDate.of(2018, 12, 2);
        LocalDate finalDate = LocalDate.of(2018, 12, 6);

        // Act
        boolean result = this.controller.checkNearestSensorReadingsExistenceBetweenDates(initialDate, finalDate);

        // Assert
        assertTrue(result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * there are no measurements in that period
     * expected to return false;
     **/
    @Test
    public void checkNearestSensorReadingsExistenceBetweenDates_False() {
        // Arrange
        LocalDate initialDate = LocalDate.of(2018, 12, 7);
        LocalDate finalDate = LocalDate.of(2018, 12, 10);

        // Act
        boolean result = this.controller.checkNearestSensorReadingsExistenceBetweenDates(initialDate, finalDate);

        // Assert
        assertFalse(result);
    }


    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * after calculating the first highest reading, it should be turned into a readingDTO
     * expected to return ReadingDTO;
     **/
    @Test
    public void getFirstHighestReadingHouseArea_ReadingDTO() {
        //Arrange
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        double valueReading3 = 25;
        ReadingDTO expectedResultDTO = ReadingMapper.newReadingDTO();
        expectedResultDTO.setValue(valueReading3);
        expectedResultDTO.setDateTime(readingDate3);
        Reading expectedResult = ReadingMapper.mapToEntity(expectedResultDTO);

        //Act
        ReadingDTO resultDTO = controller.getFirstHighestReadingHouseArea(startDate, endDate);
        Reading result = ReadingMapper.mapToEntity(resultDTO);
        //Assert
        assertEquals(expectedResult, result);
    }
}