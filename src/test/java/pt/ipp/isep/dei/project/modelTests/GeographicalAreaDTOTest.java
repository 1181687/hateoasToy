package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeographicalAreaDTOTest {

    private GeographicalAreaDTO portoCity;
    private SensorDTO temperatureSensor;
    private SensorType temperature;

    @BeforeEach
    public void StartUp() {
        // Geographical Areas
        portoCity = new GeographicalAreaDTO();
        portoCity.setId("S001");
        portoCity.setDescription("Sensor1");
        portoCity.setType("City");

        // Sensors
        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        Sensor sensor = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");
        temperatureSensor = SensorMapper.mapToDTO(sensor);
        portoCity.addSensor(temperatureSensor);
    }

    /**
     * get method for ID, ID S001
     */
    @Test
    public void testGetIdTest_getId_S001() {
        // arrange
        String expectedResult = "S001";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for ID, changes ID to S002
     */
    @Test
    public void testsetId_changeId_S002() {
        // arrange
        portoCity.setId("S002");
        String expectedResult = "S002";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for Sensor, Sensor1
     */
    @Test
    public void getSensorNameTest_getSensorName_Sensor1() {
        // arrange
        String expectedResult = "Sensor1";
        // act
        String result = portoCity.getDescription();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for sensor, changes name to Sensor2
     */
    @Test
    public void setSensorNameTest_changeSensorName_Sensor2() {
        // arrange
        portoCity.setDescription("Sensor2");
        String expectedResult = "Sensor2";
        // act
        String result = portoCity.getDescription();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for I, ID S001
     */
    @Test
    public void getGeographicalAreaTypeTest_getGeoAreaType_City() {
        // arrange
        String expectedResult = "City";
        // act
        String result = portoCity.getType();
        // assert
        assertEquals(expectedResult, result);

    }

    /**
     * set method for geographicalareatype, changes type to newGeoArea
     */
    @Test
    public void setGeographicalAreaTypeTest_newGeoAreaType_NewGeoArea() {
        // arrange
        portoCity.setType("New Geo Area");
        String expectedResult = "New Geo Area";
        // act
        String result = portoCity.getType();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set and get methods for width changes width to 10.0
     */
    @Test
    public void setAndGetWidthTest_getWidthAndChangeWidth_10() {
        // arrange
        double expectedResult = 10.0;
        // act
        portoCity.setWidth(10.0);
        double result = portoCity.getWidth();
        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set and get methods for length changes length to 15.0
     */
    @Test
    public void setAndGetLengthTest_getLengthAndChangeLength_15() {
        // arrange
        double expectedResult = 15.0;

        // act
        portoCity.setLength(15.0);
        double result = portoCity.getLength();

        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set and get methods for latitude changes latitude to 20.0
     */
    @Test
    public void setAndGetLatitudeTest_getLatitudeAndChangeLatitude_20() {
        // arrange
        double expectedResult = 20.0;

        // act
        portoCity.setLatitude(20.0);
        double result = portoCity.getLatitude();

        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set and get methods for longitude changes longitude to 25.0
     */
    @Test
    public void setAndGetLongitudeTest_getLongitudeAndChangeLongitude_25() {
        // arrange
        double expectedResult = 25.0;

        // act
        portoCity.setLongitude(25.0);
        double result = portoCity.getLongitude();

        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * set and get methods for altitude changes altitude to 30.0
     */
    @Test
    public void setAndGetAltitudeTest_getAltitudeAndChangeAltitude_30() {
        // arrange
        double expectedResult = 30.0;

        // act
        portoCity.setElevation(30.0);
        double result = portoCity.getElevation();

        // assert
        assertEquals(expectedResult, result);
    }

    /**
     * get methods for sensor, returns temperature Sensor
     */
    @Test
    public void getSensors_get_temperatureSensor() {
        //arrange
        List<SensorDTO> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensor);

        //act
        List<SensorDTO> result = portoCity.getSensors();


        //assert
        assertEquals(expectedResult, result);
    }
}

