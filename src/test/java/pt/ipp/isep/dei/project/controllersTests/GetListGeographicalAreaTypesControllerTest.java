package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListGeographicalAreaTypesControllerTest {

    private GetListGeoAreaTypesController controller;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    @BeforeEach
    public void StartUp() {
        //Geographical Area Type List
        this.geographicalAreaTypeList = new GeographicalAreaTypeList();

        //Controller
        this.controller = new GetListGeoAreaTypesController(geographicalAreaTypeList);
    }

    @Test
    public void testarGetListaTiposDeAG() {
        //Arrange
        String nameOfType = "Cidade";
        GeographicalAreaType type = new GeographicalAreaType(nameOfType);
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type);
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        String nameOfType1 = "Cidade";
        GeographicalAreaType type1 = new GeographicalAreaType(nameOfType1);
        String nameOfType2 = "Freguesia";
        GeographicalAreaType type2 = new GeographicalAreaType(nameOfType2);

        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type1);
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type2);

        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        List<String> expectedResult = Arrays.asList();

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }
}
