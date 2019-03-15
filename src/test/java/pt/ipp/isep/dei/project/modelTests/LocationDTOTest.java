package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationDTOTest {
    private LocationDTO locationDTO;

    @BeforeEach
    public void StartUp() {
        this.locationDTO = new LocationDTO();

        locationDTO.setLatitude(100);
        locationDTO.setLongitude(200);
        locationDTO.setElevation(150);
    }

    @Test
    public void testGetLatitude_100() {
        //Arrange
        double expectedResult = 100;
        //Act
        double result = locationDTO.getLatitude();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetLatitude_200() {
        //Arrange
        locationDTO.setLatitude(200);
        double expectedResult = 200;
        //Act
        double result = locationDTO.getLatitude();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetLongitude_200() {
        //Arrange
        double expectedResult = 200;
        //Act
        double result = locationDTO.getLongitude();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetLongitude_100() {
        //Arrange
        locationDTO.setLongitude(100);
        double expectedResult = 100;
        //Act
        double result = locationDTO.getLongitude();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetElevation_150() {
        //Arrange
        double expectedResult = 150;
        //Act
        double result = locationDTO.getElevation();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetElevation_100() {
        //Arrange
        locationDTO.setElevation(100);
        double expectedResult = 100;
        //Act
        double result = locationDTO.getElevation();
        //Assert
        assertEquals(expectedResult, result);

    }

}
