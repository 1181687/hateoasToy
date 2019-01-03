package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeoAreaListTest {


    @Test
    void testaAdicionarAreaGeoAListaPositivo() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);

        GeoAreaList lista = new GeoAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);

        //Act
        boolean resultado = lista.addGeoAreaToTheList(geographicalArea);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaNegativo() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);

        GeoAreaList lista = new GeoAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);
        lista.addGeoAreaToTheList(geographicalArea);

        //Act
        boolean resultado = lista.addGeoAreaToTheList(geographicalArea);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaMesmaAG() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);

        String nomeAG2 = "Porto";
        GeoAreaType tipo2 = new GeoAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local2);

        GeoAreaList lista = new GeoAreaList();
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoAreaToTheList(ag1);

        //Act
        boolean resultado = lista.addGeoAreaToTheList(ag2);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    public void getmListaAG() {

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);

        GeoAreaList lista = new GeoAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);
        lista.addGeoAreaToTheList(geographicalArea);

        List<GeographicalArea> expectedResult = new ArrayList<>();
        expectedResult.add(geographicalArea);

        List<GeographicalArea> resultado = lista.getmListaAG();

        assertEquals(expectedResult, resultado);
    }


    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange
        GeoAreaList lista = new GeoAreaList();

        String tipoPedido = "Cidade";
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

        List<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        List<String> resultado = lista.getListOfGeographicalAreasByType(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }

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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserido Em: Cidade Porto\n";

        //Act
        String result = listaDeAGs.listContent(true);

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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.listContent(true);

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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.listContent(false);

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

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.listContent(false);

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

        int opçãoSeleccionada = 1;

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea resultado = lista.getGeographicalAreaInTheList(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }


    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem() {
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

        boolean expectedResult = true;

        //Act

        boolean result = geoAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem() {
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

        boolean expectedResult = false;

        //Act

        boolean result = geoAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso() {
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

        boolean expectedResult = false;

        //Act

        boolean result = geoAreaList.checkIfGeoAreaIsinsertedInAnother(2, 0);

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

        //Act
        boolean resultado = listaDeAGs.checkIfGeoAreaIsinsertedInAnother(1, 0);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais() {
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


        boolean expectedResult = false;

        //Act

        boolean result = geoAreaList.checkIfGeoAreaIsinsertedInAnother(1, 0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente() {
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


        boolean expectedResult = true;

        //Act

        boolean result = geoAreaList.checkIfGeoAreaIsinsertedInAnother(0, 2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testaAdicionarAreaGeoAListaNumaPosicaoEspecifica() {

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
        listaDeAGs.addGeoAreaToTheListInASpecificPosition(0, ag2);

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea result = listaDeAGs.getmListaAG().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeoComTesteTrue() {
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

        //Act
        boolean result = listaDeAGs.removeGeoAreaFromTheList(ag1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeoComTesteFalse() {
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

        listaDeAGs.addGeoAreaToTheList(ag2);

        //Act
        boolean result = listaDeAGs.removeGeoAreaFromTheList(ag1);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testarGetAreaGeograficaComResultadoNull() {
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

        GeographicalArea expectedResult = null;

        //Act
        GeographicalArea resultado = lista.getGeographicalArea(ag2);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNovaAreaGeografica() {
        //Arrange
        GeoAreaList lista = new GeoAreaList();

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(40.5, 50.5, 100.0);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea resultado = new GeographicalArea(nomeAG, tipo, local, area);

        GeographicalArea expectedResult = lista.newGeographicalArea("Porto", "Cidade", 40.5, 50.5, 100.0, 10, 10);
        //Act

        //Assert
        assertEquals(resultado, expectedResult);
    }
}