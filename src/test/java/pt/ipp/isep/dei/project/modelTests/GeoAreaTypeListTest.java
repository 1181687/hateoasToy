package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeoAreaTypeListTest {

    @Test
    void testaadicionarElementoAListaPositivo() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        String novoTipoAG = "Cidade";
        GeoAreaType novoTipo = new GeoAreaType(novoTipoAG);
        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);
        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaadicionarElementoAListaNegativo() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        String novoTipoAG = "Cidade";
        GeoAreaType novoTipo = new GeoAreaType(novoTipoAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);
        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);
        //Arrange
        assertFalse(resultado);
    }

    @Test
    void testarNovoTipoAG() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        String novoTipoAG = "Cidade";
        GeoAreaType novoTipo = new GeoAreaType(novoTipoAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);
        GeoAreaType tipoDiferente = lista.newTypeOfGeoArea("Rua");
        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(tipoDiferente);
        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testarConstrutorNaoVazio(){
        //Arrange
        List<GeoAreaType> lista = new ArrayList<>();
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        lista.add(tipo1);
        lista.add(tipo2);
        GeoAreaTypeList novaLista = new GeoAreaTypeList(lista);
        //Act
        boolean resultado = novaLista.getmListaTAG().isEmpty();
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange
        //Instanciar a classe GeoAreaTypeList
        GeoAreaTypeList lista = new GeoAreaTypeList();

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        GeoAreaType tipo1 = new GeoAreaType(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.addTypeOfGeoAreaToTheList(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");


        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        //Instanciar a classe GeoAreaTypeList
        GeoAreaTypeList lista = new GeoAreaTypeList();

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
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        //Instanciar a classe GeoAreaTypeList
        GeoAreaTypeList lista = new GeoAreaTypeList();

        //Expected Result
        List<String> expectedResult = Arrays.asList();


        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);

    }


}