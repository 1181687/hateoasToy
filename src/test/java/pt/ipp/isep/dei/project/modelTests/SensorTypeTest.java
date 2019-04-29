package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SensorTypeTest {

    @Test
    public void Test_getSensorType() {
        //

        String typeId = "Temperature";
        SensorTypeId sensorTypeId = new SensorTypeId(typeId);

        String expectedResult = "Temperature";
        //Act
        String result = sensorTypeId.getSensorTypeId();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testEqualsSameObject() {
        //Arrange
        String typeId = "Temperature";
        SensorTypeId sensorTypeId = new SensorTypeId(typeId);
        //Act
        boolean result = sensorTypeId.equals(sensorTypeId);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testEqualsTrue() {
        //Arrange

        String typeId = "Temperature";
        SensorTypeId sensorTypeId = new SensorTypeId(typeId);
        String typeId1 = "Temperature";
        SensorTypeId sensorTypeId1 = new SensorTypeId(typeId1);

        //Act
        boolean result = sensorTypeId.equals(sensorTypeId1);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testEqualsFalseDifferentTypes() {
        //Arrange
        String typeId = "Temperature";
        SensorTypeId sensorTypeId = new SensorTypeId(typeId);
        String typeId1 = "Rainfall";
        SensorTypeId sensorTypeId1 = new SensorTypeId(typeId1);
        //Act
        boolean result = sensorTypeId.equals(sensorTypeId1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testEqualsFalse() {
        //Arrange

        String typeId = "Temperature";
        SensorTypeId sensorTypeId = new SensorTypeId(typeId);
        String typeId1 = "Temperature";
        SensorTypeId sensorTypeId1 = new SensorTypeId(typeId1);

        //Act
        boolean result = typeId.equals(sensorTypeId1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testEqualsDifferentObjects() {
        //Arrange

        String typeId = "Temperature";
        SensorTypeId anotherSensorTypeId = new SensorTypeId(typeId);

        // Instanciar sensor
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        String typeId1 = "Temperature";
        SensorTypeId sensorTypeId1 = new SensorTypeId(typeId1);

        Location locS1 = new Location(123, 345, 50);
        String sensorName = "A123";
        String sensorId = "123";
        SensorId sensorId1 = new SensorId(sensorId);
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, sensorName, startingDate, sensorTypeId1, locS1, "l/m2");
        //Act
        boolean result = anotherSensorTypeId.equals(s1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Arrange
        String typeId1 = "Temperature";
        SensorTypeId sensorTypeId1 = new SensorTypeId(typeId1);

        int expectedResult = 1;
        // Act
        int result = sensorTypeId1.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }
}