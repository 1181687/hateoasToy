package pt.ipp.isep.dei.project.modelTests;

import org.junit.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import static org.junit.Assert.*;

public class GeographicalAreaTypeTest {

    @Test
    void testehashCode() {
        //Arrange
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        int expectedResult = 1;
        // Act
        int result = tipo.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaEqualsTrue() {
        //Arrange
        GeographicalAreaType tipo0 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsFalseTiposDiferentes() {
        //Arrange
        GeographicalAreaType tipo0 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType("Rua");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsFalse() {
        //Arrange
        String TipoAreaGeo = "Cidade";
        GeographicalAreaType tipo0 = new GeographicalAreaType(TipoAreaGeo);
        SensorType tipo1 = new SensorType("Temperatura");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testarGetmTipoAreaGeo() {
        //Arrange
        String TipoAreaGeo = "Rua";
        GeographicalAreaType tipo1 = new GeographicalAreaType(TipoAreaGeo);
        String expectedResult= "Rua";
        //Act
        String result = tipo1.getStringOfTypeOfGeoArea();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarUmTipoAreaGeoEIgualAOutraTrue() {
        //Arrange
        String TipoAreaGeo1 = "Rua";
        GeographicalAreaType tipo1 = new GeographicalAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "Rua";
        GeographicalAreaType tipo2 = new GeographicalAreaType(TipoAreaGeo2);
        boolean expectedResult= true;

        //Act
        boolean result = tipo1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("Rua");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarUmTipoAreaGeoEIgualAOutraFalse() {
        //Arrange
        String TipoAreaGeo1 = "Rua";
        GeographicalAreaType tipo1 = new GeographicalAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "Cidade";
        GeographicalAreaType tipo2 = new GeographicalAreaType(TipoAreaGeo2);
        boolean expectedResult= false;

        //Act
        boolean result = tipo1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("Cidade");
        //Assert
        assertEquals(expectedResult, result);
    }
}