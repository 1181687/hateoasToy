package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListOfTypeOfGeoAreaController;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfTypeOfGeoAreaControllerTest {

    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange

        //Instanciar a classe GeographicalAreaTypeList
        GeographicalAreaTypeList geographicalAreaTypeList = new GeographicalAreaTypeList();

        //Tipos de Areas Geográficas
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
        GeographicalAreaType tipo2 = new GeographicalAreaType(nomeDoTipo2);

        //Adicionar os Tipos de Areas Geográficas na lista
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(tipo1);
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(tipo2);

        //Instanciar a classe GeographicalAreaList
        GeographicalAreaList lista = new GeographicalAreaList();

        String tipoPedido = "Cidade";

        String nomeAG1 = "Porto";
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Massarelos";
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoArea(ag1);
        lista.addGeoArea(ag2);

        GetListOfTypeOfGeoAreaController ctrl = new GetListOfTypeOfGeoAreaController(lista, geographicalAreaTypeList);
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        List<String> resultado = ctrl.getListaAGPorTipo(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }


    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange
        //Instanciar a classe GeographicalAreaTypeList
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.addTypeOfGeoAreaToTheList(tipo1);

        //Instanciar a classe GeographicalAreaList
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        GetListOfTypeOfGeoAreaController ctrl = new GetListOfTypeOfGeoAreaController(geographicalAreaList, lista);


        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");


        //Act
        List<String> result = ctrl.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }

}
