package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US1Controller;
import sprint0.Model.ListaTiposAG;
import sprint0.Model.TipoAreaGeo;

import static org.junit.jupiter.api.Assertions.*;

public class US1ControllerTest {

    @Test
    public void testarCriacaoDeNovoTipoDeAG() {
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
        String novoTipo = "Cidade";
        US1Controller ctrl = new US1Controller(lista);
        //Act
        boolean resultado = ctrl.novoTAG(novoTipo);
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
        boolean resultado = ctrl.novoTAG(novoTipo);
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
