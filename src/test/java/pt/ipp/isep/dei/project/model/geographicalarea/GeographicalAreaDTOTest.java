package pt.ipp.isep.dei.project.model.geographicalarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
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
        portoCity.setName("Sensor1");
        portoCity.setType("Temperature");

        // Sensors
        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        temperatureSensor = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");
    }

    @Test
    public void getId() {
        // arrange
        String expectedResult = "S001";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setId() {
        // arrange
        portoCity.setId("S002");
        String expectedResult = "S002";
        // act
        String result = portoCity.getId();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorName() {
        // arrange
        String expectedResult = "Sensor1";
        // act
        String result = portoCity.getName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setSensorName() {
        // arrange
        portoCity.setName("Sensor2");
        String expectedResult = "Sensor2";
        // act
        String result = portoCity.getName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getGeographicalAreaType() {
        // arrange
        String expectedResult = "Temperature";
        // act
        String result = portoCity.getType();
        // assert
        assertEquals(expectedResult, result);

    }

    /*
    @Test
    public void setType() {


    }

    @Test
    public void getWidth() {


    }

    @Test
    public void setWidth() {
    }

    @Test
    public void getLenght() {


    }

    @Test
    public void setLenght() {
    }

    @Test
    public void getLatitude() {


    }

    @Test
    public void setLatitude() {


    }

    @Test
    public void getLongitude() {


    }

    @Test
    public void setLongitude() {


    }

    @Test
    public void getAltitude() {


    }

    @Test
    public void setAltitude() {


    }

    @Test
    public void getSensors() {


    }

    @Test
    public void addSensor() {


    }
    */
}