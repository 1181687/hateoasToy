package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeographicalAreaMappingTest {

    @Test
    public void testMapToDTO_GeoAreaDTO() {
        //Arrange
        String geoAreaName = "Nação";
        String description = "Porto";
        String geographicalAreaType = "Cidade";
        double width = 200;
        double lenght = 150;
        double latitude = 15;
        double longitude = 20;
        double altitude = 10;
        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setId(geoAreaName);
        geographicalAreaDTO.setName(description);
        geographicalAreaDTO.setType(geographicalAreaType);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(lenght);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setAltitude(altitude);
        GeographicalArea expectedResult = GeographicalAreaMapping.mapToEntity(geographicalAreaDTO);

        //Act
        GeographicalAreaDTO outraCoisa = GeographicalAreaMapping.mapToDTO(geoAreaName, description, geographicalAreaType, width, lenght, latitude, longitude, altitude);
        GeographicalArea result = GeographicalAreaMapping.mapToEntity(outraCoisa);
        //Assert
        assertEquals(expectedResult, result);

    }
}
