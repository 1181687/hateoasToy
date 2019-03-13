package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeographicalAreaDTOTest {

    private GeographicalAreaDTO portoCity;
    private Sensor temperatureSensor;
    private SensorType temperature;

    @BeforeEach
    public void StartUp() {

        // Geographical Areas
        portoCity = new GeographicalAreaDTO();
        portoCity.setId("S001");
        portoCity.setSensorName("Sensor1");
        portoCity.setGeographicalAreaType("Temperature");

        // Sensors
        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        temperatureSensor = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");
    }

    @Test
    public void getIdTest_getId_S001() {
        // arrange
        String expectedResult = "S001";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setId_changeId_S002() {
        // arrange
        portoCity.setId("S002");
        String expectedResult = "S002";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorNameTest_getSensorName_Sensor1() {
        // arrange
        String expectedResult = "Sensor1";
        // act
        String result = portoCity.getSensorName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setSensorNameTest_changeSensorName_Sensor2() {
        // arrange
        portoCity.setSensorName("Sensor2");
        String expectedResult = "Sensor2";
        // act
        String result = portoCity.getSensorName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getGeographicalAreaTypeTest_getGeoAreaType_Temperature() {
        // arrange
        String expectedResult = "Temperature";
        // act
        String result = portoCity.getGeographicalAreaType();
        // assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void setGeographicalAreaTypeTest_newGeoAreaType_NewGeoArea() {
        // arrange
        portoCity.setGeographicalAreaType("New Geo Area");
        String expectedResult = "New Geo Area";
        // act
        String result = portoCity.getGeographicalAreaType();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAndGetWidthTest_getWidthAndChangeWidth_10 () {
        // arrange
        double expectedResult = 10.0;
        // act
       portoCity.setWidth(10.0);
       double result = portoCity.getWidth();
        // assert
        assertEquals(expectedResult, result);
    }

    /*
    @Test
    public void setAndGetLengthTest_getLengthAndChangeLength_15 () {
        // arrange
        double expectedResult = 15.0;

        // act
        portoCity.setLength(15.0);
        double result = portoCity.getLength();

        // assert
        assertEquals(expectedResult, result);
    }
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

    @Test
    public void setAndGetAltitudeTest_getAltitudeAndChangeAltitude_30() {
        // arrange
        double expectedResult = 30.0;

        // act
        portoCity.setAltitude(30.0);
        double result = portoCity.getAltitude();

        // assert
        assertEquals(expectedResult, result);
    }
}

