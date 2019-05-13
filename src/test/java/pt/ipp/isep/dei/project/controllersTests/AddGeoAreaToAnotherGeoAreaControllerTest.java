package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AddGeoAreaToAnotherGeoAreaControllerTest {
    @Mock
    private GeographicalAreaService geographicalAreaService;
    private GeographicalArea cityOfPorto;
    private GeographicalArea parishOfBonfim;
    private GeographicalArea districtOfPorto;

    private AddGeoAreaToAnotherGeoAreaController controller;


    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AddGeoAreaToAnotherGeoAreaController(geographicalAreaService);
        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType(new GeoAreaTypeId("City"));
        GeographicalAreaType parish = new GeographicalAreaType(new GeoAreaTypeId("Parish"));
        GeographicalAreaType district = new GeographicalAreaType(new GeoAreaTypeId("District"));
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        cityOfPorto = new GeographicalArea("Porto", "City of Porto", city, location, areaShape);

        //geographicalAreaService.addGeoArea(cityOfPorto);

        Location location1 = new Location(40.178553, -8.208035, 112);
        AreaShape areaShape1 = new AreaShape(0.161, 0.149);
        parishOfBonfim = new GeographicalArea("Bonfim", "Parish of Bonfim", parish, location1, areaShape1);
        parishOfBonfim.setInsertedIn(cityOfPorto);

        //geographicalAreaService.addGeoArea(parishOfBonfim);

        Location location2 = new Location(42.178553, -8.708035, 111);
        AreaShape areaShape2 = new AreaShape(0.761, 0.549);
        districtOfPorto = new GeographicalArea("Porto", "Distrito do Porto", district, location2, areaShape2);

    }

    @Configuration
    static class Config {
    }

    @Test
    public void getGeoAreaIdDTOTest() {
        //arrange
        List<GeoAreaIdDTO> geoAreaIdDTOS = new ArrayList<>();
        geoAreaIdDTOS.add(GeoAreaIdMapper.mapToDTO(cityOfPorto.getId()));
        geoAreaIdDTOS.add(GeoAreaIdMapper.mapToDTO(parishOfBonfim.getId()));

        when(geographicalAreaService.getAllGeoAreaIdDTO()).thenReturn(geoAreaIdDTOS);

        List<GeoAreaIdDTO>expectedResult = new ArrayList<>();
        expectedResult.add(GeoAreaIdMapper.mapToDTO(cityOfPorto.getId()));
        expectedResult.add(GeoAreaIdMapper.mapToDTO(parishOfBonfim.getId()));
        //act
        List<GeoAreaIdDTO> result = controller.getGeoAreaIdDTO();
        //assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void isInsertedInNullTest_shouldReturnTrue() {
        //arrange
        GeoAreaIdDTO cityOfPortoIdDTO = GeoAreaIdMapper.mapToDTO(cityOfPorto.getId());
        boolean expectedResult = true;

        when(geographicalAreaService.isInsertedInNull(cityOfPortoIdDTO)).thenReturn(true);
        //act
        boolean result = controller.isInsertedInNull(cityOfPortoIdDTO);
        //assert
        assertTrue(result);
    }

    @Test
    public void getParentGeoAreaIdTest_shouldReturnNull() {
        //arrange
        GeoAreaIdDTO portoCityIdDTO = GeoAreaIdMapper.mapToDTO(cityOfPorto.getId());
        when(geographicalAreaService.getParentGeoAreaId(portoCityIdDTO)).thenReturn(null);

        GeoAreaIdDTO expectedResult = null;
        //act
        GeoAreaIdDTO result = controller.getParentGeoAreaId(portoCityIdDTO);
        //assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getParentGeoAreaIdTest_shouldReturnParishOfBonfim() {
        //arrange
        GeoAreaIdDTO portoCityIdDTO = GeoAreaIdMapper.mapToDTO(cityOfPorto.getId());
        GeoAreaIdDTO parishOfBonfimIdDTO = GeoAreaIdMapper.mapToDTO(parishOfBonfim.getId());
        when(geographicalAreaService.getParentGeoAreaId(parishOfBonfimIdDTO)).thenReturn(portoCityIdDTO);

        GeoAreaIdDTO expectedResult = portoCityIdDTO;
        //act
        GeoAreaIdDTO result = controller.getParentGeoAreaId(parishOfBonfimIdDTO);
        //assert
        assertEquals(expectedResult,result);
    }

    /*@Test
    public void addParentGeoAreaToMainGeoAreaTest(){
        //arrange
        GeoAreaIdDTO portoCityIdDTO = GeoAreaIdMapper.mapToDTO(cityOfPorto.getId());
        GeoAreaIdDTO portoDistrictIdDTO = GeoAreaIdMapper.mapToDTO(districtOfPorto.getId());

        when(geographicalAreaService.addParentGeoAreaToMainGeoArea(portoCityIdDTO,portoDistrictIdDTO)).thenReturn(true);

        //act
        boolean result = controller.addParentGeoAreaToMainGeoArea(portoCityIdDTO,portoCityIdDTO);
        //assert
        assertTrue(result);
    }*/
}