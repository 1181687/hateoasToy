package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US1Controller;
import sprint0.Controllers.US3Controller;
import sprint0.Model.*;

import static org.junit.jupiter.api.Assertions.*;

public class US3ControllerTest {



    @Test
    public void testarAdicionarNovaAG() {
        //Arrange
        //instanciar us3controller
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        //Act
        boolean resultado = ctrl3.adicionarNovaAG(ag);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicionarNovaAGFalso() {
        //Arrange
        //instanciar us3controller
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        ctrl3.adicionarNovaAG(ag);
        //Act
        boolean resultado = ctrl3.adicionarNovaAG(ag);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaTAG() {
        //Arrange
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica areaDaLista = new AreaGeografica(nomeAG, tipo,local,area);
        lista.adicionarAreaGeoALista(areaDaLista);
        ListaAG expectedResult = lista;
        //Act
        ListaAG result = ctrl3.getListaAG();

        //Assert

        assertEquals(expectedResult, result);
    }
}
