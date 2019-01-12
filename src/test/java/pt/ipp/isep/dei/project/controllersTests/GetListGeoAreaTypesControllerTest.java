package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListGeoAreaTypesControllerTest {

    @Test
    public void testarGetListaTiposDeAG() {
        //Arrange
        //Instanciar a classe GetListGeoAreaTypesController
        GeoAreaTypeList lista = new GeoAreaTypeList();
        GetListGeoAreaTypesController ctrl2 = new GetListGeoAreaTypesController(lista);

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        GeoAreaType tipo1 = new GeoAreaType(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.addTypeOfGeoAreaToTheList(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List <String> result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    //NOVO
    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        //Instanciar a classe GetListGeoAreaTypesController
        GeoAreaTypeList lista = new GeoAreaTypeList();
        GetListGeoAreaTypesController ctrl2 = new GetListGeoAreaTypesController(lista);

        //Tipos de Areas Geográficas
        String nomeDoTipo1 = "Cidade";
        GeoAreaType tipo1 = new GeoAreaType(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
        GeoAreaType tipo2 = new GeoAreaType(nomeDoTipo2);

        //Adicionar os Tipos de Areas Geográficas na lista
        lista.addTypeOfGeoAreaToTheList(tipo1);
        lista.addTypeOfGeoAreaToTheList(tipo2);


        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");


        //Act
        List<String> result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        //Instanciar a classe GetListGeoAreaTypesController
        GeoAreaTypeList lista = new GeoAreaTypeList();
        GetListGeoAreaTypesController ctrl2 = new GetListGeoAreaTypesController(lista);

        //Expected Result
        List<String> expectedResult = Arrays.asList();


        //Act
        List<String> result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }
}

