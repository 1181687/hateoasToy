package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
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

        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");
        when(geoAreaTypeService.getListOfGeoAreaTypesToString()).thenReturn(expectedResult);
        //Act
        List<String> result = this.controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        List<String> expectedResult = Arrays.asList();
        when(geoAreaTypeService.getListOfGeoAreaTypesToString()).thenReturn(expectedResult);

        //Act
        List<String> result = this.controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }
}
