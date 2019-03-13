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
        AreaShape areaShape2 = new AreaShape(10, 10, sensorLocation);
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
        String result = portoCity.getSensorName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setSensorName() {
        // arrange
        portoCity.setSensorName("Sensor2");
        String expectedResult = "Sensor2";
        // act
        String result = portoCity.getSensorName();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getGeographicalAreaType() {
        // arrange
        String expectedResult = "Temperature";
        // act
        String result = portoCity.getGeographicalAreaType();
        // assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void setGeographicalAreaType() {
        // arrange
        portoCity.setGeographicalAreaType("New Geo Area");
        String expectedResult = "New Geo Area";
        // act
        String result = portoCity.getGeographicalAreaType();
        // assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void setWidth() {
        portoCity.setWidth(14.0);
        double expectedResult = 14.0;
        // act
        double result = portoCity.getWidth();
        // assert
        assertEquals(expectedResult, result);
    }
}