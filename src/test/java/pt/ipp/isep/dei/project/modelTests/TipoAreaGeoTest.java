package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.TipoAreaGeo;
import pt.ipp.isep.dei.project.model.TipoSensor;

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

    @Test
    void testarGetmTipoAreaGeo() {
        //Arrange
        String TipoAreaGeo = "Rua";
        TipoAreaGeo tipo1 = new TipoAreaGeo(TipoAreaGeo);
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
        TipoAreaGeo tipo1 = new TipoAreaGeo(TipoAreaGeo1);

        String TipoAreaGeo2 = "Rua";
        TipoAreaGeo tipo2 = new TipoAreaGeo(TipoAreaGeo2);
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
        TipoAreaGeo tipo1 = new TipoAreaGeo(TipoAreaGeo1);

        String TipoAreaGeo2 = "Cidade";
        TipoAreaGeo tipo2 = new TipoAreaGeo(TipoAreaGeo2);
        boolean expectedResult= false;

        //Act
        boolean result = tipo1.umTipoAreaGeoEIgualAOutra("Cidade");
        //Assert
        assertEquals(expectedResult, result);
    }
}