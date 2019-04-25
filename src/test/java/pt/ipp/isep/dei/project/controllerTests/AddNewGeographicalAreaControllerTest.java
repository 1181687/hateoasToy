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

        boolean result = controller.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);

        assertTrue(result);
    }

    @Test
    public void isGeoAreaExistant_ShouldReturnFalse() {


        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaAggregateService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(false);

        boolean result = controller.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);

        assertFalse(result);
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

    /*@Test
    public void addGeographicalArea() {
        //Arrange
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

        when(this.geoAreaAggregateService.addGeographicalArea(geographicalArea)).thenReturn(true);
        when(this.geoAreaAggregateService.addGeographicalArea(any(GeoAreaId.class),anyString(),any(AreaShape.class))).thenReturn(true);

        GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.newGeoAreaDTO();
        geoAreaDTO.setId(geoAreaId1.getId());
        geoAreaDTO.setType(geoAreaTypeId1.getGeoAreaTypeId());
        geoAreaDTO.setElevation(location.getElevation());
        geoAreaDTO.setLongitude(location.getLongitude());
        geoAreaDTO.setLatitude(location.getLatitude());
        geoAreaDTO.setDescription(description);
        geoAreaDTO.setLength(areaShape.getLength());
        geoAreaDTO.setWidth(areaShape.getWidth());


        //Act

        boolean result = controller.addGeographicalArea(geoAreaDTO);

        //Assert
        assertTrue(result);
    }
}*/
