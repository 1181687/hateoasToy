package sprint0.ModelTests;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

        List<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        List<String> resultado = lista.getListaAGPorTipo(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserido Em: Cidade Porto\n";

        //Act
        String result = listaDeAGs.conteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
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

        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.conteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.conteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
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

        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.conteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
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

        int opçãoSeleccionada =1;

        AreaGeografica expectedResult = ag2;

        //Act
        AreaGeografica resultado = lista.getAreaGeograficaNaListaApresentada(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNAOTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10, local);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        boolean expectedResult = true;

        //Act

        boolean result = listaAG.verificarSeAGNaoTemAreaInserida(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10, local);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        boolean expectedResult = false;

        //Act

        boolean result = listaAG.verificarSeAGNaoTemAreaInserida(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraQuaComCasoFalso () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10, local);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);

        boolean expectedResult = false;

        //Act

        boolean result = listaAG.verificarSeAGEstaContidaNoutra(2,0);

        //Assert

        assertEquals(expectedResult, result);
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

    @Test
    public void testarSeAGEstaContidaNoutraQuaNoCasoDeDuasIguais () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10, local);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);


        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);


        boolean expectedResult = false;

        //Act

        boolean result = listaAG.verificarSeAGEstaContidaNoutra(1,0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraQuaNoCasoDeEstarIndiretamente () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Porto";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Distrito");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local2);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Norte";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local3);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ag1.setmAreaInseridaEm(ag2);
        ag2.setmAreaInseridaEm(ag3);

        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);


        boolean expectedResult = true;

        //Act

        boolean result = listaAG.verificarSeAGEstaContidaNoutra(0,2);

        //Assert

        assertEquals(expectedResult, result);
    }


}

