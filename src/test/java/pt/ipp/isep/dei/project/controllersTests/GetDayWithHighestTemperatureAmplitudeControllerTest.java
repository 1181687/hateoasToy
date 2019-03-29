package pt.ipp.isep.dei.project.controllersTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.controllers.getDayWithHighestTemperatureAmplitudeController.GetDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
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


    @Autowired
    private GeoAreaRepository geoAreaRepository;

    @Before
    public void StartUp() {
        GeoAreaService.getInstance().setGeoAreaRepository(geoAreaRepository);

        // Geographical Area Types
        GeographicalAreaType region = new GeographicalAreaType("Region");
        GeographicalAreaType district = new GeographicalAreaType("District");
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100, location);
        northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40, location1);
        portoDistrict = new GeographicalArea("Distrito do Porto", "Porto District", district, location1, areaShape1);
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
        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new Sensor("S01", "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("S01", "B123", startDate1, temperature, sensorLocation1, "l/m2");

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
     * beforeach has some readings, extra ones where added
     * dailyAmplitudes_2/12/2018 = 7.0;
     * dailyAmplitudes_3/12/2018 = 6.0;
     * dailyAmplitudes_4/12/2018 = 20.0;
     * 4/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 20.
     * expected result: "The highest temperature amplitude for the chosen period is 20.0 Celsius and was registered on:\n" +
     *                 "2018-12-04\n"
     */
    @Test
    public void getHighestDailyAmplitude_4_12_2018_amplitude20() {

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

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "The highest temperature amplitude for the chosen period is 20.0 Celsius and was registered on:\n" +
                "2018-12-04\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * dailyAmplitudes_2/12/2018 = 7.0;
     * dailyAmplitudes_3/12/2018 = 6.0;
     * dailyAmplitudes_4/12/2018 = doubleNan;
     * there is a doubleNan value for the amplitude in 4/12/2018
     * 02/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 7.
     */
    @Test
    public void getHighestDailyAmplitude_doubleNanValuesIn4_12_2018_highestAmplitude7_2_12_2018() {

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
        Reading reading7 = new Reading(15, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "The highest temperature amplitude for the chosen period is 7.0 Celsius and was registered on:\n" +
                "2018-12-02\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is only one reading per day
     * 2/12/2018 = 22.0;
     * 3/12/2018 = 25.0;
     * 4/12/2018 = 13.0;
     * expected result is: "There are not enough values to calculate the amplitude."
     */
    @Test
    public void getHighestDailyAmplitude_onlyOneValuePerDay_ThereAreNotEnoughValuesToCalculateTheAmplitude() {

        // Extra Reading
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(13, time2);
        temperatureSensor1.addReadingsToList(reading6);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "There are not enough values to calculate the amplitude.\n";

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is only one reading per day, and in one day is doubleNan
     * 2/12/2018 = 22.0;
     * 3/12/2018 = 25.0;
     * 4/12/2018 = DoubleNan;
     * expected result is: "There are not enough values to calculate the amplitude."
     */
    @Test
    public void getHighestDailyAmplitude_onlyOneValuePerDayWithOneBeingDoubleNan_ThereAreNotEnoughValuesToCalculateTheAmplitude() {

        double valueNan = Double.NaN;
        // Extra Reading
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(valueNan, time2);
        temperatureSensor1.addReadingsToList(reading6);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "There are not enough values to calculate the amplitude.\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is only one reading per day, and in one day is doubleNan
     * 4/12/2018 = DoubleNan;
     * 12/12/2018 = DoubleNan;
     * 13/12/2018 = DoubleNan;
     * expected result is: "There's no registers for this period."
     */
    @Test
    public void getHighestDailyAmplitude_onlyValuesDoubleNan_TheresNoRegisterForThisPeriod() {

        double valueNan = Double.NaN;
        // Extra Reading
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(valueNan, time2);
        temperatureSensor1.addReadingsToList(reading6);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 12, 06, 20, 00);
        Reading reading7 = new Reading(valueNan, time3);
        temperatureSensor1.addReadingsToList(reading7);
        LocalDateTime time4 = LocalDateTime.of(2018, 12, 13, 06, 20, 00);
        Reading reading8 = new Reading(valueNan, time4);
        temperatureSensor1.addReadingsToList(reading8);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 4, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 12, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "There's no registers for this period.\n";

        //Assert
        assertEquals(expectedResult, result);
    }



    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * there aren't measurements in that period
     * expected a message "There's no registers for this period.\n"
     **/
    @Test
    public void getHighestDailyAmplitude_noMeasurements() {

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 01, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 02, 4, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "There's no registers for this period.\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * there aren't sensors in that period
     * expected a message "There's no registers for this period.\n"
     **/
    @Test
    public void getHighestDailyAmplitude_noSensor() {

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2010, 01, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2010, 02, 4, 23, 59, 00);

        //Act
        controller.getDayWithHighestTemperatureAmplitude(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        String result = controller.displayResults();
        String expectedResult = "There's no registers for this period.\n";

        //Assert
        assertEquals(expectedResult, result);
    }

}
