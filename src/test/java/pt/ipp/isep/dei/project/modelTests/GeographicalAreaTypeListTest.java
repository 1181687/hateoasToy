package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaTypeListTest {

    @Test
    void testaadicionarElementoAListaPositivo() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String novoTipoAG = "Cidade";
        GeographicalAreaType novoTipo = new GeographicalAreaType(novoTipoAG);

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaadicionarElementoAListaNegativo() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String novoTipoAG = "Cidade";
        GeographicalAreaType novoTipo = new GeographicalAreaType(novoTipoAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(novoTipo);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    void testarNovoTipoAG() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String novoTipoAG = "Cidade";
        GeographicalAreaType novoTipo = new GeographicalAreaType(novoTipoAG);
        lista.addTypeOfGeoAreaToTheList(novoTipo);
        GeographicalAreaType tipoDiferente = lista.newTypeOfGeoArea("Rua");

        //Act
        boolean resultado = lista.addTypeOfGeoAreaToTheList(tipoDiferente);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testarConstrutorNaoVazio(){
        //Arrange
        List<GeographicalAreaType> lista = new ArrayList<>();
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        lista.add(tipo1);
        lista.add(tipo2);
        GeographicalAreaTypeList novaLista = new GeographicalAreaTypeList(lista);

        //Act
        boolean resultado = novaLista.getGeoAreaTypeList().isEmpty();

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange

        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        lista.addTypeOfGeoAreaToTheList(tipo1);
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List<String> result = lista.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange

        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String nomeDoTipo1 = "Cidade";
        GeographicalAreaType tipo1 = new GeographicalAreaType(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
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