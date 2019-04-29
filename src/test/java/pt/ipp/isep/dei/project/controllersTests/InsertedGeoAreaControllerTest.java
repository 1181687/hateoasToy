package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.controllers.InsertedGeoAreaController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
public class InsertedGeoAreaControllerTest {

    private InsertedGeoAreaController controller;
    private GeographicalArea CidadeDoPorto;
    @InjectMocks
    private GeographicalAreaService geographicalAreaService;
    private GeographicalArea RuaDoBonfim;

    @Mock
    private GeoAreaRepository geoAreaRepository;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        this.CidadeDoPorto = new GeographicalArea("Porto", "Cidade do Porto", geographicalAreaType, local1, area1);

        GeographicalAreaType geographicalAreaType2 = new GeographicalAreaType("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10);
        this.RuaDoBonfim = new GeographicalArea("Rua do Bonfim", "Rua do Bonfim", geographicalAreaType2, local2, area2);
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        this.controller = new InsertedGeoAreaController(geographicalAreaService);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueComAreaInserida() {
        //Arrange
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserted in: Cidade Cidade do Porto\n";

        //Act
        String result = controller.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioTrueSemAreaInserida() {
        //Arrange
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseComAreaInserida() {
        //Arrange
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarApresentacaoDeListaComCriterioFalseSemAreaInserida() {
        //Arrange
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        String expectResult = "1 - ID: Porto, Description: Cidade do Porto, Type: Cidade, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Rua do Bonfim, Description: Rua do Bonfim, Type: Rua, Latitude: 41.1496, Longitude: -8.6109\n";

        //Act
        String result = controller.getConteudoLista(false);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        int selectedOption = 1;

        GeographicalArea expectedResult = RuaDoBonfim;

        //Act
        GeographicalArea result = controller.getAGNaListaApresentada(selectedOption);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNaoTem() {

        //Arrange
        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Região Sul", tipo3, local3, area3);

        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        boolean expectedResult = true;

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(ag3);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem() {
        //Arrange
        RuaDoBonfim.setInsertedIn(CidadeDoPorto);

        boolean expectedResult = false;

        //Act
        boolean result = controller.verSeAGTemAreaInseridaVazia(RuaDoBonfim);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraComCasoFalso() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Distrito do Porto", tipo, local, area);
        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Região Sul", tipo3, local3, area3);
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(ag2);
        geographicalAreaService.addGeoArea(ag3);
        ag2.setInsertedIn(CidadeDoPorto);
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
        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(RuaDoBonfim);

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1, 0);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeDuasIguais() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Distrito do Porto", tipo, local, area);

        String nomeAG3 = "Sul";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Região Sul", tipo3, local3, area3);

        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(ag2);
        geographicalAreaService.addGeoArea(ag3);

        boolean expectedResult = false;

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(1, 0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSeAGEstaContidaNoutraNoCasoDeEstarIndiretamente() {
        //Arrange
        String nomeAG2 = "Porto";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Distrito");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Distrito do Porto", tipo2, local2, area2);

        String nomeAG3 = "Norte";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Região do Norte", tipo3, local3, area3);

        geographicalAreaService.addGeoArea(CidadeDoPorto);
        geographicalAreaService.addGeoArea(ag2);
        geographicalAreaService.addGeoArea(ag3);
        CidadeDoPorto.setInsertedIn(ag2);
        ag2.setInsertedIn(ag3);

        boolean expectedResult = true;

        //Act
        boolean result = controller.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(0, 2);

        //Assert
        assertEquals(expectedResult, result);
    }
}