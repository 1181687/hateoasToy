package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.ConfigureHouseLocationController;
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
        GeoAreaId geoAreaId = new GeoAreaId("Urban Area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea geographicalArea = new GeographicalArea()


        // GeoArea list
        List<GeographicalArea> geoAreaList = new ArrayList<>();
        geoAreaList.add(geographicalArea);

        when(this.houseService.getAllGeoAreas()).thenReturn(geoAreaList);

        // Geo Area  DTO list
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geographicalArea);
        geographicalAreaDTOList.add(geographicalAreaDTO);

        List<GeographicalAreaDTO> result = this.controller.getGeoAreaList();

        GeographicalAreaDTO anotherGeoAreaDTO = geographicalAreaDTOList.get(0);
        GeographicalAreaDTO geographicalAreaResult = result.get(0);


        //assert
        assertEquals(anotherGeoAreaDTO.getId(), geographicalAreaResult.getId();
    }
}
