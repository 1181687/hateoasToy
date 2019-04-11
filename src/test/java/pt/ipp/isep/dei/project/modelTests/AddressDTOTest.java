package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.house.AddressDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressDTOTest {

    private AddressDTO addressDTO;
    private LocationDTO locationDTO;
    private GeographicalAreaDTO insertedGeoArea;

    @BeforeEach
    public void StartUp() {
        this.addressDTO = new AddressDTO();

        addressDTO.setCompleteAddress("Street A, number B, Town C, Country D");
        addressDTO.setLocation(locationDTO);
        addressDTO.setInsertedGeoArea(insertedGeoArea);
    }

    @Test
    public void testGetAddress_String() {
        //Arrange
        String expectedResult = "Street A, number B, Town C, Country D";
        //Act
        String result = addressDTO.getCompleteAddress();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAddress_newString() {
        //Arrange
        addressDTO.setCompleteAddress("new string");
        String expectedResult = "new string";
        //Act
        String result = addressDTO.getCompleteAddress();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetLocation_Location() {
        //Arrange
        LocationDTO expectedResult = locationDTO;
        //Act
        LocationDTO result = addressDTO.getLocation();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetLocation_newLocation() {
        //Arrange
        addressDTO.setLocation(locationDTO);
        LocationDTO expectedResult = locationDTO;
        //Act
        LocationDTO result = addressDTO.getLocation();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetInsertedGeoArea_GeoArea() {
        //Arrange
        GeographicalAreaDTO expectedResult = insertedGeoArea;
        //Act
        GeographicalAreaDTO result = addressDTO.getInsertedGeoArea();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetInsertedGeoArea_newInsertedGeoArea() {
        //Arrange
        addressDTO.setInsertedGeoArea(insertedGeoArea);
        GeographicalAreaDTO expectedResult = insertedGeoArea;
        //Act
        GeographicalAreaDTO result = addressDTO.getInsertedGeoArea();
        //Assert
        assertEquals(expectedResult, result);

    }
}
