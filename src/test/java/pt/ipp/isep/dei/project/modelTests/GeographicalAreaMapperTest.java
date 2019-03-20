package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeographicalAreaMapperTest {
    /**
     * method that transforms a map to DTO
     */
    @Test
    public void testMapToDTO_mapToDTO_GeoAreaDTO() {
        //Arrange
        String geoAreaName = "Nation";
        String description = "Porto";
        String geographicalAreaType = "City";
        double width = 200;
        double length = 150;
        double latitude = 15;
        double longitude = 20;
        double altitude = 10;

        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setId(geoAreaName);
        geographicalAreaDTO.setName(description);
        geographicalAreaDTO.setType(geographicalAreaType);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setAltitude(altitude);
        GeographicalArea expectedResult = GeographicalAreaMapper.mapToEntity(geographicalAreaDTO);

        //Act
        GeographicalAreaDTO anotherArea = GeographicalAreaMapper.mapToDTO(geoAreaName, description, geographicalAreaType, width, length, latitude, longitude, altitude);
        GeographicalArea result = GeographicalAreaMapper.mapToEntity(anotherArea);

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(width, result.getAreaShape().getWidth());
        assertEquals(length, result.getAreaShape().getLength());
    }
}
