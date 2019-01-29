package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToGeoAreaControllerTest {

    @Test
    public void testarNomeAreaGeograficaPorIndicePrimeiro () {

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

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

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        listaAreasGeograficas.addGeoArea(ag1);
        listaAreasGeograficas.addGeoArea(ag2);
        listaAreasGeograficas.addGeoArea(ag3);

        int posicao = 0;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Porto";
        ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceUltimo () {

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

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

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        listaAreasGeograficas.addGeoArea(ag1);
        listaAreasGeograficas.addGeoArea(ag2);
        listaAreasGeograficas.addGeoArea(ag3);

        int posicao = 2;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Ancora";
        ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceComApenasUmaArea () {

        // Arrange
        String nomeAG1 = "Espinho";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        listaAreasGeograficas.addGeoArea(ag1);

        int posicao = 0;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Espinho";
        ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeografica () {

        // Arrange
        String nomeAG1 = "Espinho";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Ancora";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        AreaShape area2 = new AreaShape(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        listaAreasGeograficas.addGeoArea(ag1);
        listaAreasGeograficas.addGeoArea(ag2);

        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        int expectedResult = 2;

        // Act
        int resultado = ctrl6.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeograficaSemElementos () {

        // Arrange
        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        int expectedResult = 0;

        // Act
        int resultado = ctrl6.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensor () {

        // Arrange
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        int expectedResult = 2;

        // Act
        int resultado = ctrl6.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensorSemElementos () {

        // Arrange
        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        int expectedResult = 0;

        // Act
        int resultado = ctrl6.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndicePrimeiro () {

        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        int posicao = 0;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Humidade";

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);
        ctrl6.getTipoSensorPorPosicao(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndiceUltimo () {

        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        sensorTypeList.addSensorType(tipo1);
        sensorTypeList.addSensorType(tipo2);

        int posicao = 1;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Temperatura";
        ctrl6.getTipoSensorPorPosicao(posicao);

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeSensorTypePorIndiceApenasUm () {

        SensorType tipo1 = new SensorType("Humidade");

        SensorTypeList sensorTypeList = new SensorTypeList();
        GeographicalAreaList listaAreasGeograficas = new GeographicalAreaList();

        sensorTypeList.addSensorType(tipo1);

        int posicao = 0;
        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(sensorTypeList, listaAreasGeograficas);
        String expectedResult = "Humidade";
        ctrl6.getTipoSensorPorPosicao(posicao);

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarAdicaoSensorAAreaGeograficaNegativo () {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        
        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", sensorType, local);

        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        geographicalAreaList.getGeoAreaList().add(ag1);


        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(listSensorsType, geographicalAreaList);

        ctrl6.getAreaGeograficaNaListaPorPosicao(0);
        ctrl6.getTipoSensorPorPosicao(0);
        ctrl6.criarNovaLocalizacao(41.1496, -8.6109, 97);
        ctrl6.criarNovoSensor("s1");
        ctrl6.adicionarSensorAAreaGeografica(s1);

        //Act
        boolean resultado = ctrl6.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarAdicaoSensorAAreaGeograficaPositivo () {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        SensorType sensorType = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", sensorType, local);

        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        geographicalAreaList.getGeoAreaList().add(ag1);


        AddSensorToGeoAreaController ctrl6 = new AddSensorToGeoAreaController(listSensorsType, geographicalAreaList);

        ctrl6.getAreaGeograficaNaListaPorPosicao(0);
        ctrl6.getTipoSensorPorPosicao(0);
        ctrl6.criarNovaLocalizacao(41.1496, -8.6109, 97);
        ctrl6.criarNovoSensor("s1");

        //Act
        boolean resultado = ctrl6.adicionarSensorAAreaGeografica(s1);

        //Assert
        assertTrue(resultado);
    }
}