package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaTypeTest {

    @Test
    public void testehashCode() {
        //Arrange
        GeographicalAreaType type = new GeographicalAreaType("City");
        int expectedResult = 1;
        // Act
        int result = type.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testaEqualsTrue() {
        //Arrange
        GeographicalAreaType type0 = new GeographicalAreaType("City");
        GeographicalAreaType type1 = new GeographicalAreaType("City");
        //Act
        boolean result = type0.equals(type1);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testaEqualsFalseTiposDiferentes() {
        //Arrange
        GeographicalAreaType type0 = new GeographicalAreaType("City");
        GeographicalAreaType type1 = new GeographicalAreaType("Street");
        //Act
        boolean result = type0.equals(type1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testaEqualsFalse() {
        //Arrange
        String geoAreaType = "City";
        GeographicalAreaType type0 = new GeographicalAreaType(geoAreaType);
        SensorTypeId type1 = new SensorTypeId("Temperature");
        //Act
        boolean result = type0.getStringOfTypeOfGeoArea().equals(type1.getSensorTypeId());
        //Assert
        assertFalse(result);
    }

    @Test
    public void testarGetmTipoAreaGeo() {
        //Arrange
        String TipoAreaGeo = "Street";
        GeographicalAreaType type1 = new GeographicalAreaType(TipoAreaGeo);
        String expectedResult = "Street";
        //Act
        String result = type1.getStringOfTypeOfGeoArea();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUmTipoAreaGeoEIgualAOutraTrue() {
        //Arrange
        String TipoAreaGeo1 = "Street";
        GeographicalAreaType type1 = new GeographicalAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "Street";
        GeographicalAreaType type2 = new GeographicalAreaType(TipoAreaGeo2);
        boolean expectedResult = true;

        //Act
        boolean result = type1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("Street");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUmTipoAreaGeoEIgualAOutraFalse() {
        //Arrange
        String TipoAreaGeo1 = "Street";
        GeographicalAreaType type1 = new GeographicalAreaType(TipoAreaGeo1);

        String TipoAreaGeo2 = "City";
        GeographicalAreaType type2 = new GeographicalAreaType(TipoAreaGeo2);
        boolean expectedResult = false;

        //Act
        boolean result = type1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("City");
        //Assert
        assertEquals(expectedResult, result);
    }
}