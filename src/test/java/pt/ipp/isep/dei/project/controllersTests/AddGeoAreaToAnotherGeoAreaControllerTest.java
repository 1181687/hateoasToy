package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddGeoAreaToAnotherGeoAreaControllerTest {

    @Test
    void testeConteudoListaController() {

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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserido Em: Cidade Porto\n";

        //Act
        String result = ctrl.getConteudoLista(true);

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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(lista);

        int opçãoSeleccionada =1;

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea resultado = ctrl.getAGNaListaApresentada(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNAOTem () {
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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(geographicalAreaList);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(geographicalAreaList);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag2);

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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(listaDeAGs);

        ctrl.adicionarAGListaPosicaoEspecifica(0,ag2);

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea result = listaDeAGs.getGeoAreaList().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeo() {
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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(listaDeAGs);

        ctrl.removerAGLista(ag1);

        GeographicalArea expectedResult = ag2;

        //Act
        GeographicalArea result = listaDeAGs.getGeoAreaList().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSizeList() {
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

        AddGeoAreaToAnotherGeoAreaController ctrl = new AddGeoAreaToAnotherGeoAreaController(listaDeAGs);

        ctrl.adicionarAGListaPosicaoEspecifica(0, ag2);

        int expectedResult = 2;

        //Act
        int result = ctrl.getListSize();

        //Assert
        assertEquals(expectedResult, result);
    }
}