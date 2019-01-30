package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import static org.junit.jupiter.api.Assertions.*;

public class AddGeographicalAreaTypeControllerTest {

    @Test
    public void testarCriacaoDeNovoTipoDeAG() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        String novoTipo = "Cidade";
        AddGeoAreaTypeController ctrl = new AddGeoAreaTypeController(lista);
        //Act
        boolean resultado = ctrl.adicionaNovoTipoAreaGeografica(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarCriacaoDeNovoTipoDeAGFalhar() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        GeographicalAreaType tipoDaLista = new GeographicalAreaType("Cidade");
        lista.addTypeOfGeoAreaToTheList(tipoDaLista);
        String novoTipo = "Cidade";
        AddGeoAreaTypeController ctrl = new AddGeoAreaTypeController(lista);
        //Act
        boolean resultado = ctrl.adicionaNovoTipoAreaGeografica(novoTipo);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaTAG() {
        //Arrange
        GeographicalAreaTypeList lista = new GeographicalAreaTypeList();
        AddGeoAreaTypeController ctrl = new AddGeoAreaTypeController(lista);
        GeographicalAreaType tipoDaLista = new GeographicalAreaType("Cidade");
        lista.addTypeOfGeoAreaToTheList(tipoDaLista);
        GeographicalAreaTypeList expectedResult = lista;
        //Act
        GeographicalAreaTypeList result = ctrl.getListaTAG();

        //Assert

        assertEquals(expectedResult, result);
    }

}
