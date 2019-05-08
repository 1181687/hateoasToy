package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.InsertedGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class InsertedGeoAreaControllerTest {

    private InsertedGeoAreaController controller;
    private GeographicalArea CidadeDoPorto;

    @Mock
    private GeographicalAreaService geographicalAreaService;

    private GeographicalArea ruaDoBonfim;


    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        //Geographical Area
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Cidade");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId1);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        this.CidadeDoPorto = new GeographicalArea("Porto", "Cidade do Porto", geographicalAreaType, local1, area1);

        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("Rua");
        GeographicalAreaType geographicalAreaType2 = new GeographicalAreaType(geoAreaTypeId2);
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10);
        this.ruaDoBonfim = new GeographicalArea("Rua do Bonfim", "Rua do Bonfim", geographicalAreaType2, local2, area2);
        ruaDoBonfim.setInsertedIn(CidadeDoPorto);

        this.controller = new InsertedGeoAreaController(geographicalAreaService);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
        //Arrange

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserted in: Cidade Cidade do Porto\n";

        when(geographicalAreaService.getGeoAreaListToString(true)).thenReturn(expectResult);
        //Act
        String result = controller.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
        //Arrange

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        when(geographicalAreaService.getGeoAreaListToString(false)).thenReturn(expectResult);

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
        //Arrange

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        when(geographicalAreaService.getGeoAreaListToString(false)).thenReturn(expectResult);

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
        //Arrange

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        when(geographicalAreaService.getGeoAreaListToString(false)).thenReturn(expectResult);

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange

        int selectedOption = 1;

        GeographicalArea expectedResult = ruaDoBonfim;

        when(geographicalAreaService.getGeographicalAreaInTheList(selectedOption)).thenReturn(this.ruaDoBonfim);

        //Act
        GeographicalArea result = controller.getAGNaListaApresentada(selectedOption);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem() {

        //Arrange
        String nomeAG3 = "Sul";
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("Região");
        GeographicalAreaType tipo3 = new GeographicalAreaType(geoAreaTypeId3);
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Região Sul", tipo3, local3, area3);

        when(geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(ag3)).thenReturn(true);

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(ag3);

        //Assert
        assertTrue(result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem() {
        //Arrange

        when(geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(ruaDoBonfim)).thenReturn(false);

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(ruaDoBonfim);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso() {
        //Arrange
        when(geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(2, 0)).thenReturn(false);
        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(2, 0);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testarSeAGEstaInseridaEmAGDiretamenteComCasoVerdadeiro() {
        //Arrange
        when(geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(1, 0)).thenReturn(true);

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1, 0);

        //Assert
        assertTrue(result);
    }

}
