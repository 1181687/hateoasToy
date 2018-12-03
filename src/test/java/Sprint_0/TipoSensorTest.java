package Sprint_0;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TipoSensorTest {

    @Test
    void TestaGetTipoSensor() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        String expectedResult = "Temperatura";
        //Act
        String result = tipo0.getmTipo();
        //Assert
        assertEquals(result,expectedResult);
    }

    @Test
    void testaEqualsSameObject() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo0);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsTrue() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        TipoSensor tipo1 = new TipoSensor("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsFalseTiposDiferentes() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        TipoSensor tipo1 = new TipoSensor("Humidade");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsFalse() {
        //Arrange
        String tipoSensor = "Temperatura";
        TipoSensor tipo0 = new TipoSensor(tipoSensor);
        //Act
        boolean result = tipo0.equals(tipoSensor);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsObjetosDiferentes() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        // Instanciar Sensor
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        //Act
        boolean result = tipo0.equals(s1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testarHashCode () {
        // Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        int expectedResult = 1;
        // Act
        int result = tipo0.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

}