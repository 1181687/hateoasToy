package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(AddSensorToGeoAreaControllerTest.Config.class)
public class AddSensorToGeoAreaControllerTest {
    private AddSensorToGeoAreaController controller;
    private GeographicalArea campusDoIsep;
    private SensorTypeList sensorTypeList;
    @InjectMocks
    private GeographicalAreaService geographicalAreaService;

    @Mock
    private GeoAreaRepository geoAreaRepository;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        this.campusDoIsep = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        this.sensorTypeList = new SensorTypeList();

        this.controller = new AddSensorToGeoAreaController(sensorTypeList, geographicalAreaService);


    }

    @Test
    public void testarAdicaoSensorAAreaGeograficaNegativo() {
        //Arrange
        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        GeoAreaSensor s1 = new GeoAreaSensor("123", "s1", sensorType, local, "l/m2");

        sensorTypeList.addSensorType(sensorType);

        geographicalAreaService.getGeoAreaList().add(campusDoIsep);

        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(41.1496, -8.6109, 97);
        controller.criarNovoSensor("s1", "sensor2", "l/m2");
        controller.adicionarSensorAAreaGeografica(s1);

        //Act
        boolean resultado = controller.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndicePrimeiro() {


        String nomeAG2 = "Espinho";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Cidade de Espinho", tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Cidade de Ancora", tipo3, local3, area3);


        geographicalAreaService.addGeoArea(campusDoIsep);
        geographicalAreaService.addGeoArea(ag2);
        geographicalAreaService.addGeoArea(ag3);

        int posicao = 0;
        String expectedResult = "ISEP";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceUltimo() {

        String nomeAG2 = "Espinho";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Cidade de Espinho", tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, "Cidade de Ancora", tipo3, local3, area3);

        geographicalAreaService.addGeoArea(campusDoIsep);
        geographicalAreaService.addGeoArea(ag2);
        geographicalAreaService.addGeoArea(ag3);

        int posicao = 2;
        String expectedResult = "Ancora";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceComApenasUmaArea() {

        // Arrange
        geographicalAreaService.addGeoArea(campusDoIsep);

        int posicao = 0;
        String expectedResult = "ISEP";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeografica() {

        // Arrange
        String nomeAG2 = "Ancora";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Cidade de Ancora", tipo2, local2, area2);


        geographicalAreaService.addGeoArea(campusDoIsep);
        geographicalAreaService.addGeoArea(ag2);

        int expectedResult = 2;

        // Act
        int resultado = controller.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeograficaSemElementos() {

        // Arrange
        int expectedResult = 0;

        // Act
        int resultado = controller.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensor() {

        // Arrange
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        int expectedResult = 2;

        // Act
        int resultado = controller.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensorSemElementos() {

        // Arrange
        int expectedResult = 0;

        // Act
        int resultado = controller.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndicePrimeiro() {

        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        int posicao = 0;
        String expectedResult = "Humidade";

        // Act
        String resultado = controller.getNomeTipoSensorPorIndice(posicao);
        controller.getTipoSensorPorPosicao(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndiceUltimo() {

        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        int posicao = 1;
        String expectedResult = "Temperatura";
        controller.getTipoSensorPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndiceApenasUm() {

        SensorType tipo1 = new SensorType("Humidade");

        sensorTypeList.addSensorType(tipo1);

        int posicao = 0;
        String expectedResult = "Humidade";
        controller.getTipoSensorPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarAdicaoSensorAAreaGeograficaPositivo() {
        //Arrange
        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        GeoAreaSensor s1 = new GeoAreaSensor("123", "s1", sensorType, local, "l/m2");

        sensorTypeList.addSensorType(sensorType);
        geographicalAreaService.getGeoAreaList().add(campusDoIsep);

        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(41.1496, -8.6109, 97);
        controller.criarNovoSensor("s1", "sensor1", "l/m2");

        //Act
        boolean resultado = controller.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarNovoSensor() {
        //Arrange
        String id = "123";
        String name = "A123";
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(45, 45, 45);
        String units = "l/m2";
        GeoAreaSensor s1 = new GeoAreaSensor(id, name, sensorType, locS1, units);

        sensorTypeList.addSensorType(sensorType);
        geographicalAreaService.getGeoAreaList().add(campusDoIsep);
        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(45, 45, 45);

        GeoAreaSensor expectedResult = s1;

        //Act
        GeoAreaSensor result = controller.criarNovoSensor(id, name, units);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Configuration
    static class Config {
    }
}