package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaTypeTest {

    @Test
    public void testehashCode() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(geoAreaTypeId);
        int expectedResult = 1;
        // Act
        int result = type.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testaEqualsTrue() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType type0 = new GeographicalAreaType(geoAreaTypeId);
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId);
        //Act
        boolean result = type0.equals(type1);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testaEqualsFalseTiposDiferentes() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("City");
        GeographicalAreaType type0 = new GeographicalAreaType(geoAreaTypeId1);
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("Street");
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId2);
        //Act
        boolean result = type0.equals(type1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testaEqualsFalse() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType type0 = new GeographicalAreaType(geoAreaTypeId3);
        SensorTypeId type1 = new SensorTypeId("Temperature");
        //Act
        boolean result = type0.getStringOfTypeOfGeoArea().equals(type1.getSensorTypeId());
        //Assert
        assertFalse(result);
    }

    @Test
    public void testarGetmTipoAreaGeo() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("Street");
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId3);
        String expectedResult = "Street";
        //Act
        String result = type1.getStringOfTypeOfGeoArea();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUmTipoAreaGeoEIgualAOutraTrue() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("Street");
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId3);

        GeoAreaTypeId geoAreaTypeId4 = new GeoAreaTypeId("Street");
        GeographicalAreaType type2 = new GeographicalAreaType(geoAreaTypeId3);
        boolean expectedResult = true;

        //Act
        boolean result = type1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("Street");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUmTipoAreaGeoEIgualAOutraFalse() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("Street");
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId3);

        GeoAreaTypeId geoAreaTypeId4 = new GeoAreaTypeId("City");
        GeographicalAreaType type2 = new GeographicalAreaType(geoAreaTypeId4);
        boolean expectedResult = false;

        //Act
        boolean result = type1.checkIfOneTypeOfGeoAreaIsEqualToAnotherType("City");
        //Assert
        assertEquals(expectedResult, result);
    }
}