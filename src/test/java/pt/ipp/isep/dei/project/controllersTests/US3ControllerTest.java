package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US3Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class US3ControllerTest {



    @Test
    public void testarAdicionarNovaAG() {
        //Arrange
        //instanciar us3controller
        GeoAreaList lista = new GeoAreaList();
        GeoAreaTypeList listaTAG = new GeoAreaTypeList();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10,local);

        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        //Act
        boolean resultado = ctrl3.adicionarNovaAG(ag);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicionarNovaAGFalso() {
        //Arrange
        //instanciar us3controller
        GeoAreaList lista = new GeoAreaList();
        GeoAreaTypeList listaTAG = new GeoAreaTypeList();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10,local);

        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ctrl3.adicionarNovaAG(ag);
        //Act
        boolean resultado = ctrl3.adicionarNovaAG(ag);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaTAG() {
        //Arrange
        GeoAreaList lista = new GeoAreaList();
        GeoAreaTypeList listaTAG = new GeoAreaTypeList();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10,local);
        GeographicalArea areaDaLista = new GeographicalArea(nomeAG, tipo, local, area);
        lista.addGeoArea(areaDaLista);
        GeoAreaList expectedResult = lista;
        //Act
        GeoAreaList result = ctrl3.getListaAG();

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange

        GeoAreaList lista = new GeoAreaList();
        GeoAreaTypeList listaTAG = new GeoAreaTypeList();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        GeoAreaType tipo1 = new GeoAreaType(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        listaTAG.addTypeOfGeoAreaToTheList(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");


        //Act
        List<String> result = ctrl3.getListaTAG();

        //Assert
        assertEquals(expectedResult, result);

    }


    @Test
    public void testarCriarNovaAG() {
        //Arrange
        GeoAreaList lista = new GeoAreaList();
        GeoAreaTypeList listaTAG = new GeoAreaTypeList();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(40.5, 50.5, 100.0);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea expectedResult = new GeographicalArea(nomeAG, tipo, local, area);

        GeographicalArea result = ctrl3.criarNovaAG("Porto", "Cidade", 40.5,
                50.5, 100.0, 10, 10);
        //Act

        //Assert
        assertEquals(expectedResult,result);
    }

}
