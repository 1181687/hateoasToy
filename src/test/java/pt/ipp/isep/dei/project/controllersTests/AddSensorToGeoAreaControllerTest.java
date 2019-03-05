package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.GeographicalArea.AreaShape;
import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Sensor.Sensor;
import pt.ipp.isep.dei.project.model.Sensor.SensorType;
import pt.ipp.isep.dei.project.model.Sensor.SensorTypeList;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToGeoAreaControllerTest {
    private AddSensorToGeoAreaController controller;
    private GeographicalArea CampusDoIsep;
    private SensorTypeList sensorTypeList;
    private GeographicalAreaList geographicalAreaList;


    @BeforeEach
    public void StartUp() {

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        this.CampusDoIsep = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        this.geographicalAreaList = new GeographicalAreaList();
        this.sensorTypeList = new SensorTypeList();

        this.controller = new AddSensorToGeoAreaController(sensorTypeList, geographicalAreaList);


    }

    @Test
    public void testarNomeAreaGeograficaPorIndicePrimeiro () {


        String nomeAG2 = "Espinho";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);


        geographicalAreaList.addGeoArea(CampusDoIsep);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        int posicao = 0;
        String expectedResult = "Campus do ISEP";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceUltimo () {

        String nomeAG2 = "Espinho";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        GeographicalAreaType tipo3 = new GeographicalAreaType("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        AreaShape area3 = new AreaShape(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

        geographicalAreaList.addGeoArea(CampusDoIsep);
        geographicalAreaList.addGeoArea(ag2);
        geographicalAreaList.addGeoArea(ag3);

        int posicao = 2;
        String expectedResult = "Ancora";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceComApenasUmaArea () {

        // Arrange
        geographicalAreaList.addGeoArea(CampusDoIsep);

        int posicao = 0;
        String expectedResult = "Campus do ISEP";
        controller.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = controller.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeografica () {

        // Arrange
        String nomeAG2 = "Ancora";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);


        geographicalAreaList.addGeoArea(CampusDoIsep);
        geographicalAreaList.addGeoArea(ag2);

        int expectedResult = 2;

        // Act
        int resultado = controller.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeograficaSemElementos () {

        // Arrange
        int expectedResult = 0;

        // Act
        int resultado = controller.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensor () {

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
    public void testarNumeroElementosDaListaTipoDeSensorSemElementos () {

        // Arrange
        int expectedResult = 0;

        // Act
        int resultado = controller.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndicePrimeiro () {

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
    public void testarGetNomeSensorTypePorIndiceUltimo () {

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
    public void testarGetNomeSensorTypePorIndiceApenasUm () {

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
    public void testarAdicaoSensorAAreaGeograficaNegativo () {
        //Arrange
        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", sensorType, local);


        sensorTypeList.addSensorType(sensorType);

        geographicalAreaList.getGeoAreaList().add(CampusDoIsep);

        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(41.1496, -8.6109, 97);
        controller.criarNovoSensor("s1");
        controller.adicionarSensorAAreaGeografica(s1);

        //Act
        boolean resultado = controller.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarAdicaoSensorAAreaGeograficaPositivo () {
        //Arrange
        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", sensorType, local);

        sensorTypeList.addSensorType(sensorType);
        geographicalAreaList.getGeoAreaList().add(CampusDoIsep);

        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(41.1496, -8.6109, 97);
        controller.criarNovoSensor("s1");

        //Act
        boolean resultado = controller.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    void testarNovoSensor() {
        //Arrange
        String name = "A123";
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(45, 45, 45);
        Sensor s1 = new Sensor(name, sensorType, locS1);

        sensorTypeList.addSensorType(sensorType);
        geographicalAreaList.getGeoAreaList().add(CampusDoIsep);
        controller.getAreaGeograficaNaListaPorPosicao(0);
        controller.getTipoSensorPorPosicao(0);
        controller.criarNovaLocalizacao(45, 45, 45);

        Sensor expectedResult = s1;

        //Act
        Sensor result = controller.criarNovoSensor(name);

        //Assert
        assertEquals(expectedResult, result);
    }
}