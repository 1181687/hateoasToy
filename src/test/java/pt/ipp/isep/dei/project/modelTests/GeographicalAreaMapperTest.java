package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaMapperTest {
    private GeographicalAreaDTO portoDTO;
    private GeographicalArea porto;

    /**
     * Method that initializes some attributes of this test class to simplify all tests.
     */
    @BeforeEach
    public void StartUp() {
        // Geo Area DTO
        portoDTO = GeographicalAreaMapper.newGeoAreaDTO();
        portoDTO.setId("Porto");
        portoDTO.setDescription("City of Porto");
        portoDTO.setType("City");
        portoDTO.setWidth(200);
        portoDTO.setLength(150);
        portoDTO.setLatitude(15);
        portoDTO.setLongitude(20);
        portoDTO.setElevation(10);

        // Geo Area
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(portoDTO.getType());
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId);
        Location location = new Location(portoDTO.getLatitude(), portoDTO.getLongitude(), portoDTO.getElevation());
        AreaShape shape = new AreaShape(portoDTO.getWidth(), portoDTO.getLength());
        porto = new GeographicalArea(portoDTO.getId(), portoDTO.getDescription(), city, location, shape);
    }

    /**
     * method that transforms a map to DTO
     */
    @Test
    public void testMapToDTO_mapToDTO_GeoAreaDTO() {
        // Arrange
        String id = "Nation";
        String description = "Porto";
        String geographicalAreaType = "City";
        double width = 200;
        double length = 150;
        double latitude = 15;
        double longitude = 20;
        double altitude = 10;

        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setId(id);
        geographicalAreaDTO.setDescription(description);
        geographicalAreaDTO.setType(geographicalAreaType);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setElevation(altitude);
        GeographicalArea expectedResult = GeographicalAreaMapper.mapToEntity(geographicalAreaDTO);

        //Act
        GeographicalAreaDTO anotherArea = GeographicalAreaMapper.newDTO();
        anotherArea.setId(id);
        anotherArea.setDescription(description);
        anotherArea.setType(geographicalAreaType);
        anotherArea.setWidth(width);
        anotherArea.setLength(length);
        anotherArea.setLatitude(latitude);
        anotherArea.setLongitude(longitude);
        anotherArea.setElevation(altitude);

        GeographicalArea result = GeographicalAreaMapper.mapToEntity(anotherArea);

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(width, result.getAreaShape().getWidth(), 0.00001);
        assertEquals(length, result.getAreaShape().getLength(), 0.00001);

    }

    /**
     * Test that tries to create a GeographicalArea based on a GeographicalAreaDTO, which results in a
     * new GeographicalArea with the information contained by the GeographicalAreaDTO.
     */
    @org.junit.jupiter.api.Test
    public void testMapToEntity_tryingToCreateBasedOnAGeoAreaDTO_ShouldReturnTrue() {
        // Act
        boolean result = GeographicalAreaMapper.mapToEntity(portoDTO).equals(porto);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to create a GeographicalArea based on a null Object, which results in a non creation.
     */
    @org.junit.jupiter.api.Test
    public void testMapToEntity_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        GeographicalArea result = GeographicalAreaMapper.mapToEntity(null);

        // Assert
        assertNull(result);
    }

    /**
     * Test that tries to create a ReadingDTO based on a Reading, which results in a new ReadingDTO with the information
     * contained by the Reading.
     */
    @org.junit.jupiter.api.Test
    public void testMapToDTO_tryingToCreateBasedOnAReading_ShouldReturnTrue() {
        // Act
        boolean result = GeographicalAreaMapper.mapToDTOwithSensors(porto).getId().equalsIgnoreCase(portoDTO.getId());

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to create a ReadingDTO based on a null Object, which results in a non creation.
     */
    @org.junit.jupiter.api.Test
    public void testMapToDTO_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        GeographicalAreaDTO result = GeographicalAreaMapper.mapToDTOwithSensors(null);

        // Assert
        assertNull(result);
    }
}
