package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddSensorToGeoAreaControllerTest {

    @Mock
    private GeoAreaAggregateService geoAreaAggregateService;
    private AddSensorToGeoAreaController controller;

    @BeforeEach
    public void StartUp() {

        MockitoAnnotations.initMocks(this);
        this.controller = new AddSensorToGeoAreaController(geoAreaAggregateService);
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnTrue() {

        when(this.geoAreaAggregateService.isGeoAreaRepositoryEmpty()).thenReturn(true);
        assertTrue(this.controller.isGeoAreaRepositoryEmpty());
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnFalse() {

        when(this.geoAreaAggregateService.isGeoAreaRepositoryEmpty()).thenReturn(false);
        assertFalse(this.controller.isGeoAreaRepositoryEmpty());
    }

    @Test
    public void getGeographicalAreaDTOList_ShouldReturnTrue() {

        Location location = new Location(123, 456, 789);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");

        GeoAreaId geoAreaId = new GeoAreaId("Espinho", location, geoAreaTypeId);
        AreaShape areaShape = new AreaShape(123, 456);

        GeographicalArea geographicalArea = new GeographicalArea(geoAreaId, "Cidade de Espinho", areaShape);
        this.geoAreaAggregateService.addGeographicalArea(geographicalArea);
        
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();
        geographicalAreaList.add(geographicalArea);

        when(this.geoAreaAggregateService.getAllGeoAreas()).thenReturn(geographicalAreaList);

        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geographicalArea);
        geographicalAreaDTOList.add(geographicalAreaDTO);

        List<GeographicalAreaDTO> result = this.controller.getGeographicalAreaDTOList();

        GeographicalAreaDTO geoAreaDTO = geographicalAreaDTOList.get(0);
        GeographicalAreaDTO geoAreaResult = result.get(0);

        //assertEquals(geographicalAreaDTOList, result);
        assertEquals(geoAreaDTO.getId(), geoAreaResult.getId());
        assertEquals(geoAreaDTO.getDescription(), geoAreaResult.getDescription());
        assertEquals(geoAreaDTO.getType(), geoAreaResult.getType());
        assertEquals(geoAreaDTO.getElevation(), geoAreaResult.getElevation());
        assertEquals(geoAreaDTO.getLatitude(), geoAreaResult.getLatitude());
        assertEquals(geoAreaDTO.getLongitude(), geoAreaResult.getLongitude());
        assertEquals(geoAreaDTO.getLength(), geoAreaResult.getLength());
        assertEquals(geoAreaDTO.getWidth(), geoAreaResult.getWidth());
        assertEquals(geoAreaDTO.getSensors(), geoAreaResult.getSensors());
    }

    @Test
    public void getSensorTypeDTOList() {
    }

    @Test
    public void addGeoAreaSensor() {
    }
}