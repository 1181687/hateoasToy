package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListGeographicalAreaTypesControllerTest {

    private GetListGeoAreaTypesController controller;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    private GeoAreaTypeService geoAreaTypeService;

    @BeforeEach
    public void StartUp() {
        //Geographical Area Type List
        this.geographicalAreaTypeList = new GeographicalAreaTypeList();

        //Controller
        this.controller = new GetListGeoAreaTypesController(geoAreaTypeService);
    }

    @Test
    public void testarGetListaTiposDeAG() {
        //Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType type = new GeographicalAreaType(geoAreaTypeId);
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type);
        List<String> expectedResult = Arrays.asList("Cidade");

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

        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type1);
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type2);

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
