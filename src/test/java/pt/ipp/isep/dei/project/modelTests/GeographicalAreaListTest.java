package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaListTest {


    @Test
    void testaAdicionarAreaGeoAListaPositivo() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);

        GeographicalAreaList lista = new GeographicalAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);

        //Act
        boolean resultado = lista.addGeoArea(geographicalArea);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaNegativo() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);

        GeographicalAreaList lista = new GeographicalAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);
        lista.addGeoArea(geographicalArea);

        //Act
        boolean resultado = lista.addGeoArea(geographicalArea);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaMesmaAG() {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);

        String nomeAG2 = "Porto";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);

        GeographicalAreaList lista = new GeographicalAreaList();
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoArea(ag1);

        //Act
        boolean resultado = lista.addGeoArea(ag2);

        //Arrange
        assertFalse(resultado);
    }

    @Test
    public void getmListaAG() {

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);

        GeographicalAreaList lista = new GeographicalAreaList();
        GeographicalArea geographicalArea = new GeographicalArea(nomeAG, tipo, local, area);
        lista.addGeoArea(geographicalArea);

        List<GeographicalArea> expectedResult = new ArrayList<>();
        expectedResult.add(geographicalArea);

        List<GeographicalArea> resultado = lista.getGeoAreaList();

        assertEquals(expectedResult, resultado);
    }


    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange
        GeographicalAreaList lista = new GeographicalAreaList();

        String tipoPedido = "Cidade";
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoArea(ag1);
        lista.addGeoArea(ag2);

        List<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        List<String> resultado = lista.getListOfGeographicalAreasByType(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        String expectResult = "1 - Name: Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserted in: Cidade Porto\n";
        //Act
        String result = listaDeAGs.getGeoAreaListToString(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        String expectResult = "1 - Name: Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.getGeoAreaListToString(true);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        String expectResult = "1 - Name: Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.getGeoAreaListToString(false);

        //Assert
        assertEquals(expectResult, result);
    }


    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        String expectResult = "1 - Name: Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = listaDeAGs.getGeoAreaListToString(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange
        GeographicalAreaList lista = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoArea(ag1);
        lista.addGeoArea(ag2);

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
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        boolean expectedResult = true;

        //Act

        boolean result = geographicalAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem() {
        //Arrange
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        boolean expectedResult = false;

        //Act

        boolean result = geographicalAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso() {
        //Arrange
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag2.setInsertedIn(ag1);

        geographicalAreaList.addGeoArea(ag1);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        boolean expectedResult = false;

        //Act

        boolean result = geographicalAreaList.checkIfGeoAreaIsinsertedInAnother(2, 0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        ag2.setInsertedIn(ag1);
        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        //Act
        boolean resultado = listaDeAGs.checkIfGeoAreaIsinsertedInAnother(1, 0);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais() {
        //Arrange
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);


        geographicalAreaList.addGeoArea(ag1);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);


        boolean expectedResult = false;

        //Act

        boolean result = geographicalAreaList.checkIfGeoAreaIsinsertedInAnother(1, 0);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente() {
        //Arrange
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Porto";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Distrito");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Norte";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        ag1.setInsertedIn(ag2);
        ag2.setInsertedIn(ag3);

        geographicalAreaList.addGeoArea(ag1);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);


        boolean expectedResult = true;

        //Act

        boolean result = geographicalAreaList.checkIfGeoAreaIsinsertedInAnother(0, 2);

        //Assert

        assertEquals(expectedResult, result);
    }

    @Test
    public void testaAdicionarAreaGeoAListaNumaPosicaoEspecifica() {

        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoAreaInASpecificPosition(0, ag2);

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea result = listaDeAGs.getGeoAreaList().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeoComTesteTrue() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoArea(ag1);
        listaDeAGs.addGeoArea(ag2);

        //Act
        boolean result = listaDeAGs.removeGeoArea(ag1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeoComTesteFalse() {
        //Arrange
        GeographicalAreaList listaDeAGs = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.addGeoArea(ag2);

        //Act
        boolean result = listaDeAGs.removeGeoArea(ag1);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testarGetAreaGeograficaComResultadoNull() {
        //Arrange
        GeographicalAreaList lista = new GeographicalAreaList();

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        lista.addGeoArea(ag1);

        GeographicalArea expectedResult = null;

        //Act
        GeographicalArea resultado = lista.getGeographicalArea(ag2);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNovaAreaGeografica() {
        //Arrange
        GeographicalAreaList lista = new GeographicalAreaList();

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(40.5, 50.5, 100.0);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea resultado = new GeographicalArea(nomeAG, tipo, local, area);

        GeographicalArea expectedResult = lista.newGeographicalArea("Porto", "Cidade", 40.5, 50.5, 100.0, 10, 10);
        //Act

        //Assert
        assertEquals(resultado, expectedResult);
    }
}