package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderTest {

    @Test
    public void readJSONFileToList() {
        // arrange
        // geo area ISEP
        String id = "ISEP";
        String description = "Campus do ISEP";
        String type = "Urban area";
        double width = 0.261;
        double length = 0.249;
        double latitude = 41.178553;
        double longitude = -8.608035;
        double altitude = 111;

        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setId(id);
        geographicalAreaDTO.setName(description);
        geographicalAreaDTO.setType(type);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setAltitude(altitude);

        GeographicalArea expectedResult = GeographicalAreaMapping.mapToEntity(geographicalAreaDTO);

        GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapping.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);
        GeographicalArea result = GeographicalAreaMapping.mapToEntity(geoAreaDTO);

        // assert
        assertEquals(expectedResult, result);
    }
}