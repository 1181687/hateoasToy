package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US1Controller;
import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import static org.junit.jupiter.api.Assertions.*;

public class US1ControllerTest {

    @Test
    public void testarCriacaoDeNovoTipoDeAG() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        String novoTipo = "Cidade";
        US1Controller ctrl = new US1Controller(lista);
        //Act
        boolean resultado = ctrl.adicionaNovoTipoAreaGeografica(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarCriacaoDeNovoTipoDeAGFalhar() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        GeoAreaType tipoDaLista = new GeoAreaType("Cidade");
        lista.addTypeOfGeoAreaToTheList(tipoDaLista);
        String novoTipo = "Cidade";
        US1Controller ctrl = new US1Controller(lista);
        //Act
        boolean resultado = ctrl.adicionaNovoTipoAreaGeografica(novoTipo);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaTAG() {
        //Arrange
        GeoAreaTypeList lista = new GeoAreaTypeList();
        US1Controller ctrl = new US1Controller(lista);
        GeoAreaType tipoDaLista = new GeoAreaType("Cidade");
        lista.addTypeOfGeoAreaToTheList(tipoDaLista);
        GeoAreaTypeList expectedResult = lista;
        //Act
        GeoAreaTypeList result = ctrl.getListaTAG();

        //Assert

        assertEquals(expectedResult, result);
    }

}
