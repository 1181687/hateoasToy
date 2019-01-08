package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeTest {

    @Test
    void TestaGetTipoSensor() {
        //Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        String expectedResult = "Temperatura";
        //Act
        String result = tipo0.getmType();
        //Assert
        assertEquals(result,expectedResult);
    }

    @Test
    void testaEqualsSameObject() {
        //Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo0);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsTrue() {
        //Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        SensorType tipo1 = new SensorType("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsFalseTiposDiferentes() {
        //Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        SensorType tipo1 = new SensorType("Humidade");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsFalse() {
        //Arrange
        String tipoSensor = "Temperatura";
        SensorType tipo0 = new SensorType(tipoSensor);
        //Act
        boolean result = tipo0.equals(tipoSensor);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsObjetosDiferentes() {
        //Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        // Instanciar Sensor
        LocalDateTime dataFuncionamento = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        //Act
        boolean result = tipo0.equals(s1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testarHashCode () {
        // Arrange
        SensorType tipo0 = new SensorType("Temperatura");
        int expectedResult = 1;
        // Act
        int result = tipo0.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

}