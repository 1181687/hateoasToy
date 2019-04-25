package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.ConfigureHouseLocationController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ConfigureHouseLocationControllerTest {


    @Mock
    private HouseService houseService;

    private ConfigureHouseLocationController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ConfigureHouseLocationController(houseService);
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnTrue() {

        when(this.houseService.isGeoAreaRepositoryEmpty()).thenReturn(true);

        boolean result = controller.isGeoAreaRepositoryEmpty();

        assertTrue(result);
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnFalse() {

        when(this.houseService.isGeoAreaRepositoryEmpty()).thenReturn(false);

        boolean result = controller.isGeoAreaRepositoryEmpty();

        assertFalse(result);
    }

    @Test
    public void getGeoAreaList() {

        // GeoArea
        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        Location location = new Location(latitude, longitude, elevation);
        String geoAreaTypeId = "geoAreaTypeId";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId(geoAreaTypeId);
        GeoAreaId geoAreaId1 = new GeoAreaId(geoAreaId, location, geoAreaTypeId1);
        String description = "City";
        double width = 12;
        double length = 12;
        AreaShape areaShape = new AreaShape(width, length);
        GeographicalArea geographicalArea = new GeographicalArea(geoAreaId1, description, areaShape);

        // GeoArea list
        List<GeographicalArea> geoAreaList = new ArrayList<>();
        geoAreaList.add(geographicalArea);

        when(this.houseService.getAllGeoAreas()).thenReturn(geoAreaList);


        // Geo Area DTO list
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geographicalArea);
        geographicalAreaDTOList.add(geographicalAreaDTO);

        List<GeographicalAreaDTO> result = this.controller.getGeoAreaList();

        GeographicalAreaDTO geoAreaDTO = geographicalAreaDTOList.get(0);
        GeographicalAreaDTO geoAreaResult = result.get(0);


        //assert
        assertEquals(geoAreaDTO.getId(), geoAreaResult.getId());
        assertEquals(geoAreaDTO.getDescription(), geoAreaResult.getDescription());
        assertEquals(geoAreaDTO.getType(), geoAreaResult.getType());
        assertEquals(geoAreaDTO.getElevation(), geoAreaResult.getElevation());
        assertEquals(geoAreaDTO.getLatitude(), geoAreaResult.getLatitude());
        assertEquals(geoAreaDTO.getLongitude(), geoAreaResult.getLongitude());
        assertEquals(geoAreaDTO.getLength(), geoAreaResult.getLength());
        assertEquals(geoAreaDTO.getWidth(), geoAreaResult.getWidth());
    }
}
   /* @Test
    public void configureHouseLocation () {

        //Arrange

        // Geographical Area
        double latitude = 40;
        double longitude = 50;
        double elevation= 100;
        Location location = new Location (latitude, longitude, elevation);
        double width = 10;
        double length = 10;
        AreaShape areaShape = new AreaShape (width,length);
        String geoAreaTypeId = "Urban Area";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId(geoAreaTypeId);
        String geoAreaId = "ISEP";
        GeoAreaId geoAreaId1 = new GeoAreaId (geoAreaId, location, geoAreaTypeId1);
        String description = "Campus do ISEP";
        GeographicalArea geoArea = new GeographicalArea(geoAreaId1,description, areaShape);

        //Address
        String completeAddress = "Awesome Street";
        double latitude1 = 40.5;
        double longitude1 = 50.5;
        double elevation1= 100.0;
        Location local = new Location(latitude1, longitude1, elevation1);
        Address address = new Address(completeAddress, local, geoArea);


        when(this.houseService.getAddress()).thenReturn(address);

        AddressDTO addressDTO = AddressMapper.newAddressDTO();
        addressDTO.setCompleteAddress(completeAddress);
        addressDTO.getLocation().setElevation(elevation1);
        addressDTO.getLocation().setLatitude(latitude1);
        addressDTO.getLocation().setLongitude(longitude1);
        addressDTO.getInsertedGeoArea().setId(geoAreaId);
        addressDTO.getInsertedGeoArea().setType(geoAreaTypeId);
        addressDTO.getInsertedGeoArea().setLatitude(latitude);
        addressDTO.getInsertedGeoArea().setLongitude(longitude);
        addressDTO.getInsertedGeoArea().setElevation(elevation);
        addressDTO.getInsertedGeoArea().setDescription(description);
        addressDTO.getInsertedGeoArea().setLength(length);
        addressDTO.getInsertedGeoArea().setWidth(width);

        //Act

        result = controller.configureHouseLocation(addressDTO);

        //Assert
        assertEquals(expectedResult, result);
    }
}
*/