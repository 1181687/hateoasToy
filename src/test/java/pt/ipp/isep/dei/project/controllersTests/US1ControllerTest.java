package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US1Controller;
import pt.ipp.isep.dei.project.model.ListaTiposAG;
import pt.ipp.isep.dei.project.model.TipoAreaGeo;

import static org.junit.jupiter.api.Assertions.*;

public class US1ControllerTest {

    @Test
    public void testarCriacaoDeNovoTipoDeAG() {
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
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
        ListaTiposAG lista = new ListaTiposAG();
        TipoAreaGeo tipoDaLista = new TipoAreaGeo("Cidade");
        lista.adicionarElementoALista(tipoDaLista);
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
        ListaTiposAG lista = new ListaTiposAG();
        US1Controller ctrl = new US1Controller(lista);
        TipoAreaGeo tipoDaLista = new TipoAreaGeo("Cidade");
        lista.adicionarElementoALista(tipoDaLista);
        ListaTiposAG expectedResult = lista;
        //Act
        ListaTiposAG result = ctrl.getListaTAG();

        //Assert

        assertEquals(expectedResult, result);
    }

}
