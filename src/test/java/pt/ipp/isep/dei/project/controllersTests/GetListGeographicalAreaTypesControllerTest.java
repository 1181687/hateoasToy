package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetListGeographicalAreaTypesControllerTest {

    private GetListGeoAreaTypesController controller;
    @Mock
    private GeoAreaTypeService geoAreaTypeService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        this.controller = new GetListGeoAreaTypesController(geoAreaTypeService);
    }

    @Test
    public void testarGetListaTiposDeAG() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType type = new GeographicalAreaType(geoAreaTypeId);
        //this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type);
        List<String> expectedResult = Arrays.asList("Cidade");
        when(geoAreaTypeService.getListOfGeoAreaTypesToString()).thenReturn(expectedResult);
        //Act
        List<String> result = this.controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType type1 = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Freguesia");
        GeographicalAreaType type2 = new GeographicalAreaType(geoAreaTypeId1);

        //this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type1);
       // this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type2);

        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");

        //Act
        List<String> result = this.controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        List<String> expectedResult = Arrays.asList();

        //Act
        List<String> result = this.controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }
}
