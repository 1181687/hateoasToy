package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

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
        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    //NOVO
    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        //Tipos de Areas Geográficas
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
        GeographicalAreaType tipo2 = new GeographicalAreaType(nomeDoTipo2);

        //Adicionar os Tipos de Areas Geográficas na lista
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(tipo1);
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(tipo2);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        //Expected Result
        List<String> expectedResult = Arrays.asList();

        //Act
        List<String> result = this.controller.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }
}

