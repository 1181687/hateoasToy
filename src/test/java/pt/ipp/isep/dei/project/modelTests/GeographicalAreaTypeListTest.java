package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaTypeListTest {

    @Test
    public void testaadicionarElementoAListaPositivo() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeoAreaTypeId newTypeAG= new GeoAreaTypeId("Cidade");
        GeographicalAreaType novoTipo = new GeographicalAreaType(newTypeAG);

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    public void testaadicionarElementoAListaNegativo() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeoAreaTypeId newTypeAG= new GeoAreaTypeId("Cidade");
        GeographicalAreaType novoTipo = new GeographicalAreaType(newTypeAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    public void testarNovoTipoAG() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeoAreaTypeId novoTipoAG = new GeoAreaTypeId("Cidade");
        GeographicalAreaType novoTipo = new GeographicalAreaType(novoTipoAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);
        GeographicalAreaType tipoDiferente = lista.newTypeOfGeoArea("Rua");

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(tipoDiferente);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    public void testarConstrutorNaoVazio() {
        //Arrange
        List<GeographicalAreaType> lista = new ArrayList<>();
        GeoAreaTypeId novoTipoAGCidade = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(novoTipoAGCidade);
        GeoAreaTypeId novoTipoAGRua = new GeoAreaTypeId("Rua");
        GeographicalAreaType tipo2 = new GeographicalAreaType(novoTipoAGRua);
        lista.add(tipo1);
        lista.add(tipo2);
        GeographicalAreaTypeList novaLista = new GeographicalAreaTypeList(lista);

        //Act
        boolean resultado = novaLista.getGeoAreaTypeList().isEmpty();

        //Assert
        assertFalse(resultado);
    }

    @org.junit.jupiter.api.Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange

        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeoAreaTypeId nomeDoTipo1 = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        lista.addTypeOfGeoAreaToTheList(tipo1);
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);

    }

    @org.junit.jupiter.api.Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange

        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeoAreaTypeId nomeDoTipo1 = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        GeoAreaTypeId nomeDoTipo2 = new GeoAreaTypeId("Freguesia");
        GeographicalAreaType tipo2 = new GeographicalAreaType(nomeDoTipo2);
        lista.addTypeOfGeoAreaToTheList(tipo1);
        lista.addTypeOfGeoAreaToTheList(tipo2);
        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");

        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        List<String> expectedResult = Arrays.asList();

        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);

    }


}