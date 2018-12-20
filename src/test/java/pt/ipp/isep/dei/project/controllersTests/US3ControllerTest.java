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
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10,local);

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
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10,local);

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
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10,local);
        GeographicalArea areaDaLista = new GeographicalArea(nomeAG, tipo, local, area);
        lista.adicionarAreaGeoALista(areaDaLista);
        ListaAG expectedResult = lista;
        //Act
        ListaAG result = ctrl3.getListaAG();

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange

        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        listaTAG.adicionarElementoALista(tipo1);

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
        ListaAG lista = new ListaAG();
        ListaTiposAG listaTAG = new ListaTiposAG();
        US3Controller ctrl3 = new US3Controller(lista, listaTAG);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(40.5, 50.5, 100.0);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea expectedResult = new GeographicalArea(nomeAG, tipo, local, area);

        GeographicalArea result = ctrl3.criarNovaAG("Porto", "Cidade", 40.5,
                50.5, 100.0, 10, 10);
        //Act

        //Assert
        assertEquals(expectedResult,result);
    }

}
