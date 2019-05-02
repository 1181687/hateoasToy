package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AddNewGeographicalAreaControllerTest {

    @Mock
    private GeographicalAreaService geographicalAreaService;
    @Mock
    private GeoAreaTypeService geoAreaTypeService;

    private AddNewGeographicalAreaController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddNewGeographicalAreaController(geographicalAreaService, geoAreaTypeService);
    }

    @Test
    public void isGeoAreaExistant_ShouldReturnTrue() {

        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geographicalAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(true);

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

        when(this.geographicalAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(false);

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

        when(this.geoAreaTypeService.getListOfGeoAreaTypes()).thenReturn(geoAreaTypeList);

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

    @Test
    public void addGeographicalArea_True() {
        //Arrange
        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        Location location = new Location(latitude, longitude, elevation);
        String geoAreaTypeId1 = "City";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geoAreaTypeId1);
        GeographicalAreaType geoAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaId geoAreaId1 = new GeoAreaId(location, geoAreaId, geoAreaType);
        String description = "City";
        double width = 12;
        double length = 12;
        AreaShape areaShape = new AreaShape(width, length);

        when(this.geographicalAreaService.addGeoArea(any(GeographicalArea.class))).thenReturn(true);

        GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.newGeoAreaDTO();
        geoAreaDTO.setId(geoAreaId1.getId());
        geoAreaDTO.setType(geoAreaTypeId.getTypeId());
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

    @Test
    public void addGeographicalArea_False() {
        //Arrange
        String geoAreaId = "geoAreaId";
        double latitude = 20;
        double longitude = 12;
        double elevation = 5;
        Location location = new Location(latitude, longitude, elevation);
        String geoAreaTypeId1 = "City";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geoAreaTypeId1);
        GeographicalAreaType geoAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaId geoAreaId1 = new GeoAreaId(location, geoAreaId, geoAreaType);
        String description = "City";
        double width = 12;
        double length = 12;
        AreaShape areaShape = new AreaShape(width, length);

        when(this.geographicalAreaService.addGeoArea(any(GeographicalArea.class))).thenReturn(false);

        GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.newGeoAreaDTO();
        geoAreaDTO.setId(geoAreaId1.getId());
        geoAreaDTO.setType(geoAreaTypeId.getTypeId());
        geoAreaDTO.setElevation(location.getElevation());
        geoAreaDTO.setLongitude(location.getLongitude());
        geoAreaDTO.setLatitude(location.getLatitude());
        geoAreaDTO.setDescription(description);
        geoAreaDTO.setLength(areaShape.getLength());
        geoAreaDTO.setWidth(areaShape.getWidth());


        //Act

        boolean result = controller.addGeographicalArea(geoAreaDTO);

        //Assert
        assertFalse(result);
    }
}
