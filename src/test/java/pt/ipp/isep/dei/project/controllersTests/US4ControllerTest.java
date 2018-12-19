package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US4Controller;
import pt.ipp.isep.dei.project.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US4ControllerTest {

    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange

        //Instanciar a classe ListaTiposAG
        ListaTiposAG listaTiposAG = new ListaTiposAG();

        //Tipos de Areas Geográficas
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
        TipoAreaGeo tipo2 = new TipoAreaGeo(nomeDoTipo2);

        //Adicionar os Tipos de Areas Geográficas na lista
        listaTiposAG.adicionarElementoALista(tipo1);
        listaTiposAG.adicionarElementoALista(tipo2);

        //Instanciar a classe ListaAG
        ListaAG lista = new ListaAG();

        String tipoPedido = "Cidade";

        String nomeAG1 = "Porto";
        Location local1 = new Location(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Massarelos";
        Location local2 = new Location(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        lista.adicionarAreaGeoALista(ag1);
        lista.adicionarAreaGeoALista(ag2);

        US4Controller ctrl = new US4Controller(lista, listaTiposAG);
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        List<String> resultado = ctrl.getListaAGPorTipo(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }


    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange
        //Instanciar a classe ListaTiposAG
        ListaTiposAG lista = new ListaTiposAG();

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.adicionarElementoALista(tipo1);

        //Instanciar a classe ListaAG
        ListaAG listaAG = new ListaAG();
        US4Controller ctrl = new US4Controller(listaAG, lista);


        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");


        //Act
        List<String> result = ctrl.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }

}
