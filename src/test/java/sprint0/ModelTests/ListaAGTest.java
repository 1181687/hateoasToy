package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US4Controller;
import sprint0.Model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListaAGTest {


    @Test
    void testaAdicionarAreaGeoAListaPositivo() {
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        ListaAG lista = new ListaAG();
        AreaGeografica areaGeografica = new AreaGeografica (nomeAG, tipo, local,area);

        //Act
        boolean resultado = lista.adicionarAreaGeoALista(areaGeografica);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaNegativo() {
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        ListaAG lista = new ListaAG();
        AreaGeografica areaGeografica = new AreaGeografica (nomeAG, tipo, local,area);
        lista.adicionarAreaGeoALista(areaGeografica);

        //Act
        boolean resultado = lista.adicionarAreaGeoALista(areaGeografica);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    public void getmListaAG(){

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        ListaAG lista = new ListaAG();
        AreaGeografica areaGeografica = new AreaGeografica (nomeAG, tipo, local,area);
        lista.adicionarAreaGeoALista(areaGeografica);

        List<AreaGeografica> expectedResult = new ArrayList<>();
        expectedResult.add(areaGeografica);

        List<AreaGeografica> resultado = lista.getmListaAG();

        assertEquals(expectedResult, resultado);
    }



    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange
        ListaAG lista = new ListaAG();

        String tipoPedido = "Cidade";
        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        lista.adicionarAreaGeoALista(ag1);
        lista.adicionarAreaGeoALista(ag2);

        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        ArrayList<String> resultado = lista.getListaAGPorTipo(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        ag2.setmAreaInseridaEm(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        //Act
        boolean resultado = listaDeAGs.verificarSeAGEstaContidaNoutra(1,0);

        //Assert
        assertTrue(resultado);
    }
}

