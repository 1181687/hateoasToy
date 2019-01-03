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
        GeoAreaList listaDeAGs = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoAreaToTheList(ag1);
        listaDeAGs.addGeoAreaToTheList(ag2);

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
        GeoAreaList listaDeAGs = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoAreaToTheList(ag1);
        listaDeAGs.addGeoAreaToTheList(ag2);

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
        GeoAreaList listaDeAGs = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoAreaToTheList(ag1);
        listaDeAGs.addGeoAreaToTheList(ag2);

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
        GeoAreaList listaDeAGs = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoAreaToTheList(ag1);
        listaDeAGs.addGeoAreaToTheList(ag2);

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
        GeoAreaList lista = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoAreaToTheList(ag1);
        lista.addGeoAreaToTheList(ag2);

        US8Controller ctrl = new US8Controller(lista);

        int opçãoSeleccionada =1;

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea resultado = ctrl.getAGNaListaApresentada(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem () {
        //Arrange
        GeoAreaList geoAreaList = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeoAreaType tipo3 = new GeoAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        US8Controller ctrl = new US8Controller(geoAreaList);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
        //Arrange
        GeoAreaList geoAreaList = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeoAreaType tipo3 = new GeoAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        US8Controller ctrl = new US8Controller(geoAreaList);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso () {
        //Arrange
        GeoAreaList geoAreaList = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeoAreaType tipo3 = new GeoAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        geoAreaList.addGeoAreaToTheList(ag1);
        geoAreaList.addGeoAreaToTheList(ag2);
        geoAreaList.addGeoAreaToTheList(ag3);

        US8Controller ctrl = new US8Controller(geoAreaList);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(2,0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        GeoAreaList listaDeAGs = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeoAreaType tipo2 = new GeoAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoAreaToTheList(ag1);
        listaDeAGs.addGeoAreaToTheList(ag2);

        US8Controller ctrl = new US8Controller(listaDeAGs);

        //Act
        boolean resultado = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1,0);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais () {
        //Arrange
        GeoAreaList geoAreaList = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeoAreaType tipo3 = new GeoAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);


        geoAreaList.addGeoAreaToTheList(ag1);
        geoAreaList.addGeoAreaToTheList(ag2);
        geoAreaList.addGeoAreaToTheList(ag3);

        US8Controller ctrl = new US8Controller(geoAreaList);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1,0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente () {
        //Arrange
        GeoAreaList geoAreaList = new GeoAreaList();

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Porto";
        GeoAreaType tipo2 = new GeoAreaType("Distrito");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Norte";
        GeoAreaType tipo3 = new GeoAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag1.setInsertedIn(ag2);
        ag2.setInsertedIn(ag3);

        geoAreaList.addGeoAreaToTheList(ag1);
        geoAreaList.addGeoAreaToTheList(ag2);
        geoAreaList.addGeoAreaToTheList(ag3);

        US8Controller ctrl = new US8Controller(geoAreaList);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(0,2);

        //Assert

        assertEquals(expectedResult, result);
    }
}