package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;

import static org.junit.jupiter.api.Assertions.*;

public class LocationDTOTest {
    private LocationDTO locationDTO;

    @BeforeEach
    public void StartUp() {
        this.locationDTO = new LocationDTO();

        locationDTO.setLatitude(45);
        locationDTO.setLongitude(45);
        locationDTO.setElevation(150);
    }

    @Test
    public void testGetLatitude_100() {
        //Arrange
        double expectedResult = 45;
        //Act
        double result = locationDTO.getLatitude();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetLatitude_200() {
        //Arrange
        locationDTO.setLatitude(200);
        double expectedResult = 200;
        //Act
        double result = locationDTO.getLatitude();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testGetLongitude_200() {
        //Arrange
        double expectedResult = 45;
        //Act
        double result = locationDTO.getLongitude();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetLongitude_100() {
        //Arrange
        locationDTO.setLongitude(100);
        double expectedResult = 100;
        //Act
        double result = locationDTO.getLongitude();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testGetElevation_150() {
        //Arrange
        double expectedResult = 150;
        //Act
        double result = locationDTO.getElevation();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetElevation_100() {
        //Arrange
        locationDTO.setElevation(100);
        double expectedResult = 100;
        //Act
        double result = locationDTO.getElevation();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testEquals_Latitude_True() {
        //Arrange
        LocationDTO testLocation = locationDTO;
        //Act
        LocationDTO result = locationDTO;
        //Assert
        assertEquals(testLocation, result);
    }

    @Test
    public void testEquals_Latitude_False() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setElevation(150);
        testLocation.setLongitude(45);
        testLocation.setLatitude(50);
        //Act
        LocationDTO result = locationDTO;
        //Assert
        assertNotEquals(testLocation, result);
    }

    @Test
    public void testEquals_Longitude_False() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setElevation(150);
        testLocation.setLatitude(45);
        testLocation.setLongitude(35);
        //Act
        LocationDTO result = locationDTO;
        //Assert
        assertNotEquals(testLocation, result);
    }

    @Test
    public void testEquals_Elevation_False() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setLatitude(45);
        testLocation.setLongitude(45);
        testLocation.setElevation(250);
        //Act
        LocationDTO result = locationDTO;
        //Assert
        assertNotEquals(testLocation, result);
    }

    @Test
    public void testEquals_Location_False() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setLatitude(34);
        testLocation.setLongitude(23);
        testLocation.setElevation(100);
        //Act
        LocationDTO result = locationDTO;
        //Assert
        assertNotEquals(testLocation, result);
    }

    @Test
    public void testEquals_NotALocationObject_False() {
        //Arrange
        GeoAreaSensorDTO expectedResult = GeoAreaSensorMapper.newSensorDTO();
        //Act
        boolean result = locationDTO.equals(expectedResult);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testHashcode_Location_NotEquals() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setLatitude(45);
        testLocation.setLongitude(20);
        testLocation.setElevation(150);

        int expectedResult = testLocation.hashCode();
        //Act
        int result = locationDTO.hashCode();
        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testHashcode_Location_Equals() {
        //Arrange
        LocationDTO testLocation = LocationMapper.newLocationDTO();
        testLocation.setLatitude(45);
        testLocation.setLongitude(45);
        testLocation.setElevation(150);

        int expectedResult = testLocation.hashCode();
        //Act
        int result = locationDTO.hashCode();
        //Assert
        assertEquals(expectedResult, result);
    }

}
