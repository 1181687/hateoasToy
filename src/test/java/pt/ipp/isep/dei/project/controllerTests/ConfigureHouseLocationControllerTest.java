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

        assertTrue(controller.isGeoAreaRepositoryEmpty());
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnFalse() {

        when(this.houseService.isGeoAreaRepositoryEmpty()).thenReturn(false);

        assertFalse(controller.isGeoAreaRepositoryEmpty());
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
