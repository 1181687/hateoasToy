package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetDayWithHighestTemperatureAmplitudeControllerTest {

    private static final String CONFIG_PROPERTIES = "Configuration.properties";
    private GeographicalArea northernRegion;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private Sensor temperatureSensor;
    private Sensor temperatureSensor1;
    private SensorType temperature;
    private Location location2;
    private House house;
    private GetDayWithHighestTemperatureAmplitudeController controller;

    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeographicalAreaType region = new GeographicalAreaType("Region");
        GeographicalAreaType district = new GeographicalAreaType("District");
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100, location);
        northernRegion = new GeographicalArea("Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40, location1);
        portoDistrict = new GeographicalArea("Porto District", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        this.location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10, location2);
        portoCity = new GeographicalArea("Porto City", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(portoCity);


        // Sensors
        temperature = new SensorType("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new Sensor("A123", startDate, temperature, sensorLocation);
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("B123", startDate1, temperature, sensorLocation1);

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        controller = new GetDayWithHighestTemperatureAmplitudeController(house);

    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added, included a negative value
     * expected result {2018-12-04=20, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    void getDailyAmplitudeInterval() {

        // Extra Reading
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(-5, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(15, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);


        // Map
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), 20.0);

        //Act
        Map<LocalDate, Double> result = controller.getDailyAmplitudeInIntervalInHouseArea(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 12/04/2018 has only a DoubleNan values, so the amplitude in that day will be DoubleNan.
     * expected result {2018-12-04=NaN, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    void getDailyAmplitudeInterval_doubleNanValuesFor4_12_2018() {

        // Extra Reading
        double value = Double.NaN;
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(value, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(value, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), value);

        //Act
        Map<LocalDate, Double> result = controller.getDailyAmplitudeInIntervalInHouseArea(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyAmplitudeInterval_emptySensor_emptyMap() {

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 12, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 14, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = controller.getDailyAmplitudeInIntervalInHouseArea(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 4/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 20.
     */
    @Test
    void getHighestDailyAmplitude_4_12_2018_amplitude20() {

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), 20.0);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time2.toLocalDate(), 20.0);

        //Act
        Map<LocalDate, Double> result = controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is a doubleNan value for the amplitude in 4/12/2018
     * 2/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 7.
     */
    @Test
    void getHighestDailyAmplitude_doubleNanValuesIn4_12_2018_highestAmplitude7() {

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        double value = Double.NaN;
        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), value);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);

        //Act
        Map<LocalDate, Double> result = controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * the Map is empty
     * expected a empty Map.
     */
    @Test
    void getHighestDailyAmplitude_emptyMap_emptyMap() {

        // Maps
        Map<LocalDate, Double> dailyAmplitudesEmpty = new HashMap<>();

        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudesEmpty);

        //Assert
        assertEquals(expectedResult, result);
    }
}
