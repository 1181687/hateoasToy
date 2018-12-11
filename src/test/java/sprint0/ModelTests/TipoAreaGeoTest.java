package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.TipoAreaGeo;
import sprint0.Model.TipoSensor;

import static org.junit.jupiter.api.Assertions.*;

class TipoAreaGeoTest {

    @Test
    void testehashCode() {
        //Arrange
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        int expectedResult = 1;
        // Act
        int result = tipo.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaEqualsTrue() {
        //Arrange
        TipoAreaGeo tipo0 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsFalseTiposDiferentes() {
        //Arrange
        TipoAreaGeo tipo0 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo1 = new TipoAreaGeo("Rua");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsFalse() {
        //Arrange
        String TipoAreaGeo = "Cidade";
        TipoAreaGeo tipo0 = new TipoAreaGeo(TipoAreaGeo);
        TipoSensor tipo1 = new TipoSensor("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }
}