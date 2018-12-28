package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.SensorType;

import static org.junit.jupiter.api.Assertions.*;

class GeoAreaTypeTest {

    @Test
    void testehashCode() {
        //Arrange
        GeoAreaType tipo = new GeoAreaType("Cidade");
        int expectedResult = 1;
        // Act
        int result = tipo.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaEqualsTrue() {
        //Arrange
        GeoAreaType tipo0 = new GeoAreaType("Cidade");
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaEqualsFalseTiposDiferentes() {
        //Arrange
        GeoAreaType tipo0 = new GeoAreaType("Cidade");
        GeoAreaType tipo1 = new GeoAreaType("Rua");
        //Act
        boolean result = tipo0.equals(tipo1);
        //Assert
        assertFalse(result);
    }

    @Test
    void testaEqualsFalse() {
        //Arrange
        String TipoAreaGeo = "Cidade";
        GeoAreaType tipo0 = new GeoAreaType(TipoAreaGeo);
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
        GeoAreaType tipo1 = new GeoAreaType(TipoAreaGeo);
        String expectedResult= "Rua";
        //Act
        String result = tipo1.getNomeDoTipoAreaGeo();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarUmTipoAreaGeoEIgualAOutraTrue() {
        //Arrange
        String TipoAreaGeo1 = "Rua";
        GeoAreaType tipo1 = new GeoAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "Rua";
        GeoAreaType tipo2 = new GeoAreaType(TipoAreaGeo2);
        boolean expectedResult= true;

        //Act
        boolean result = tipo1.umTipoAreaGeoEIgualAOutra("Rua");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarUmTipoAreaGeoEIgualAOutraFalse() {
        //Arrange
        String TipoAreaGeo1 = "Rua";
        GeoAreaType tipo1 = new GeoAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "Cidade";
        GeoAreaType tipo2 = new GeoAreaType(TipoAreaGeo2);
        boolean expectedResult= false;

        //Act
        boolean result = tipo1.umTipoAreaGeoEIgualAOutra("Cidade");
        //Assert
        assertEquals(expectedResult, result);
    }
}