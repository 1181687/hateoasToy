package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US8Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US8ControllerTest {

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        ag2.setmAreaInseridaEm(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserido Em: Cidade Porto\n";

        //Act
        String result = ctrl.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = ctrl.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        ag2.setmAreaInseridaEm(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = ctrl.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = ctrl.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange
        ListaAG lista = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        lista.adicionarAreaGeoALista(ag1);
        lista.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(lista);

        int opçãoSeleccionada =1;

        GeographicArea expectedResult = ag2;

        //Act
        GeographicArea resultado = ctrl.getAGNaListaApresentada(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        US8Controller ctrl = new US8Controller(listaAG);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        US8Controller ctrl = new US8Controller(listaAG);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);

        US8Controller ctrl = new US8Controller(listaAG);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(2,0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        ag2.setmAreaInseridaEm(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        //Act
        boolean resultado = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1,0);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);


        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);

        US8Controller ctrl = new US8Controller(listaAG);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1,0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Porto";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Distrito");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Norte";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local3);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag1.setmAreaInseridaEm(ag2);
        ag2.setmAreaInseridaEm(ag3);

        listaAG.adicionarAreaGeoALista(ag1);
        listaAG.adicionarAreaGeoALista(ag2);
        listaAG.adicionarAreaGeoALista(ag3);

        US8Controller ctrl = new US8Controller(listaAG);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(0,2);

        //Assert

        assertEquals(expectedResult, result);
    }
}