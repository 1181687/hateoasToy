package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;

public class LocationMapperTest {
    @Test
    public void testMapToEntity_LocationDTO() {
        //Arrange
        double latitude = 100;
        double altitude = 100;
        double longitude = 100;

        Location localização = new Location(latitude, longitude, altitude);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLongitude(longitude);
        locationDTO.setElevation(altitude);
        locationDTO.setLatitude(latitude);

        Location expectedResult = LocationMapper.mapToEntity(locationDTO);
        //Act
        LocationDTO outraCoisa = LocationMapper.mapToDTO(localização);
        Location result = LocationMapper.mapToEntity(outraCoisa);
        //Assert
    }
}
