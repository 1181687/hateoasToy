package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetListGeoAreaTypesControllerTest {

    @Mock
    private GeoAreaTypeService geoAreaTypeService;

    private GetListGeoAreaTypesController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new GetListGeoAreaTypesController(geoAreaTypeService);
    }

    @Test
    public void getListOfGeoAreaTypes() {

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

        List<GeographicalAreaTypeDTO> result = this.controller.getListOfGeoAreaTypes();

        GeographicalAreaTypeDTO anotherGeoAreaTypeDTO = geographicalAreaTypeDTOList.get(0);
        GeographicalAreaTypeDTO geographicalAreaTypeResult = result.get(0);


        //assert
        assertEquals(anotherGeoAreaTypeDTO.getGeoAreaType(), geographicalAreaTypeResult.getGeoAreaType());
    }
}
