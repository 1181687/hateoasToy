package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AddNewGeographicalAreaControllerTest {

    @Mock
    private GeoAreaAggregateService geoAreaAggregateService;

    private AddNewGeographicalAreaController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddNewGeographicalAreaController(geoAreaAggregateService);
    }

    @Test
    public void isGeoAreaExistant_ShouldReturnTrue() {

        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaAggregateService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(true);

        assertTrue(controller.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId));
    }

    @Test
    public void isGeoAreaExistant_ShouldReturnFalse() {


        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaAggregateService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(false);

        assertFalse(controller.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId));
    }

    @Test
    public void getGeoAreaTypeList() {

        // GeoArea type
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban Area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        // GeoArea type list
        List<GeographicalAreaType> geoAreaTypeList = new ArrayList<>();
        geoAreaTypeList.add(geographicalAreaType);

        when(this.geoAreaAggregateService.listOfGeoAreaTypes()).thenReturn(geoAreaTypeList);

        // Geo Area type DTO list
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOList = new ArrayList<>();
        GeographicalAreaTypeDTO geographicalAreaTypeDTO = GeographicalAreaTypeMapper.mapToDTO(geographicalAreaType);
        geographicalAreaTypeDTOList.add(geographicalAreaTypeDTO);

        List<GeographicalAreaTypeDTO> result = this.controller.getGeoAreaTypeList();

        GeographicalAreaTypeDTO anotherGeoAreaTypeDTO = geographicalAreaTypeDTOList.get(0);
        GeographicalAreaTypeDTO geographicalAreaTypeResult = result.get(0);


        //assert
        assertEquals(anotherGeoAreaTypeDTO.getGeoAreaType(), geographicalAreaTypeResult.getGeoAreaType());
    }

}
