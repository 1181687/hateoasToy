package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.InsertedGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InsertedGeoAreaControllerTest {

    private InsertedGeoAreaController controller;
    private GeographicalArea CidadeDoPorto;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea RuaDoBonfim;

    @BeforeEach
    public void StartUp() {

        //Geographical Area
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        this.CidadeDoPorto = new GeographicalArea("Cidade do Porto", geographicalAreaType, local1, area1);

        GeographicalAreaType geographicalAreaType2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        this.RuaDoBonfim = new GeographicalArea("Rua do Bonfim", geographicalAreaType2, local2, area2);
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        this.geographicalAreaList = new GeographicalAreaList();

        this.controller = new InsertedGeoAreaController(geographicalAreaList);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
        //Arrange
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - Name: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserted in: Cidade Cidade do Porto\n";

        //Act
        String result = controller.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
        //Arrange
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - Name: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
        //Arrange
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - Name: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
        //Arrange
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - Name: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        int selectedOption = 1;

        GeographicalArea expectedResult = RuaDoBonfim;

        //Act
        GeographicalArea result = controller.getAGNaListaApresentada(selectedOption);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem () {

        //Arrange
        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        boolean expectedResult = true;

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(ag3);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
        //Arrange
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        boolean expectedResult = false;

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(RuaDoBonfim);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso () {
        //Arrange
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

        ag2.setInsertedIn(CidadeDoPorto);

        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        boolean expectedResult = false;

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(2, 0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);
        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(RuaDoBonfim);

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1, 0);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais () {
        //Arrange
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

        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        boolean expectedResult = false;

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1, 0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente () {
        //Arrange
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

        CidadeDoPorto.setInsertedIn(ag2);
        ag2.setInsertedIn(ag3);

        geographicalAreaList.addGeoArea(CidadeDoPorto);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        boolean expectedResult = true;

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(0, 2);

        //Assert
        assertEquals(expectedResult, result);
    }
}