package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class GeographicalAreaTest {
    private GeographicalArea northernRegion;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private GeoAreaSensor temperatureSensor;
    private GeoAreaSensor temperatureSensor1;
    private GeographicalAreaType city;
    private Location location2;
    private AreaShape areaShape2;
    private SensorTypeId temperatureId;


    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Region");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("District");
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId1);
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("City");
        city = new GeographicalAreaType(geoAreaTypeId2);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        portoDistrict = new GeographicalArea("Distrito do Porto", "Porto District", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        this.location2 = new Location(42.1496, -8.6109, 97);
        areaShape2 = new AreaShape(10, 10);
        portoCity = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // Sensors
        temperatureId = new SensorTypeId("Temperature");
        SensorType temperature = new SensorType(temperatureId);
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 0);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        SensorId sensorId1 = new SensorId("123");
        temperatureSensor = new GeoAreaSensor(sensorId1, "A123", startDate, temperatureId, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 0);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("321");
        temperatureSensor1 = new GeoAreaSensor(sensorId2, "B123", startDate1, temperatureId, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 0);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 0);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 5, 20, 0);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 5, 24, 0);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
    }

    @Test
    public void testarEqualsSame() {
        //act
        boolean result = portoCity.equals(portoCity);

        //assert
        assertTrue(result);
    }

    @Test
    public void equals_WithTwoEqualGeoAreas_ShouldReturnTrue() {
        //arrange

        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("City");
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId2);

        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);
        GeographicalArea portoCity2 = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);


        //act
        boolean result = portoCity.equals(portoCity2);

        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsFalse() {
        //act
        boolean result = portoCity.equals(portoDistrict);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocalizacao() {
        //arrange

        Location loc = new Location(42.15, -8.6109, 97);
        AreaShape aShape = new AreaShape(10, 10);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea ag1 = new GeographicalArea("Porto", "Porto City", city, loc, aShape);

        //act
        boolean result = portoCity.equals(ag1);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsObjetosDiferentes() {
        //act
        boolean result = portoCity.equals(areaShape2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarDistanciaLinearDuasAreas() {
        // arrange
        String nomeAG1 = "Porto";
        String nomeAG2 = "Funchal";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        GeographicalAreaType tipo2 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, 10.6109, 50);
        Location local2 = new Location(32.6333, 16.9, 20);
        AreaShape area1 = new AreaShape(10, 10);
        AreaShape area2 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Cidade do Funchal", tipo2, local2, area2);
        double expectedResult = 1099043.7203;

        // act
        double resultado = ag1.linearDistanceBetweenTwoGeoAreas(ag2);

        //assert
        assertEquals(expectedResult, resultado, 0.0001);
    }

    @Test
    public void testarHashCode() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        int expectedResult = 1;

        //Act
        int result = ag1.hashCode();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSensorContidoEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0Id = new SensorTypeId("Temperature");
        Location locS0 = new Location(45, -5, 50);
        SensorId sensorId = new SensorId ("123");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, sensorType0Id, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void testarSensorNaoContidoEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0Id = new SensorTypeId("Temperature");
        Location locS0 = new Location(45, -20, 50);
        SensorId sensorId = new SensorId ("123");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, sensorType0Id, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testarSensorNoLimiteEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS0 = new Location(46.1496, -13.6109, 65);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void listarSensoresContidosNaAGPorTipo() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS0 = new Location(43, -10, 65);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS1 = new Location(43, -10, 65);
        SensorId sensorId1 = new SensorId("s1");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, "A121", dataFuncionamento1, temperatureId, locS1, "l/m2");

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorTypeId sensorType2 = new SensorTypeId("Humidade");
        Location locS2 = new Location(50, -10, 65);
        SensorId sensorId2 = new SensorId("s2");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A130", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS3 = new Location(-4, -10, 65);
        SensorId sensorId3 = new SensorId("s3");
        GeoAreaSensor s3 = new GeoAreaSensor(sensorId3, "A120", dataFuncionamento3, temperatureId, locS3, "l/m2");

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);
        ag1.getSensorListInTheGeographicArea().addSensor(s3);

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();
        expectedResult.addSensor(s0);
        expectedResult.addSensor(s1);

        //Act
        GeoAreaSensorList result = ag1.getSensorsByType(temperatureId);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipoQueNaoExiste() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS0 = new Location(50, -10, 65);
        SensorId sensorId0 = new SensorId("s0");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId0, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS1 = new Location(50, -10, 65);
        SensorId sensorId1 = new SensorId("s1");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento1, temperatureId, locS1, "l/m2");

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorTypeId sensorType2 = new SensorTypeId("Humidade");
        Location locS2 = new Location(50, -10, 65);
        SensorId sensorId2 = new SensorId("s2");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS3 = new Location(-4, -10, 65);
        SensorId sensorId3 = new SensorId("s3");
        GeoAreaSensor s3 = new GeoAreaSensor(sensorId3, "A123", dataFuncionamento3, temperatureId, locS3, "l/m2");

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();

        SensorTypeId sensorTypePedido = new SensorTypeId("Pressão atmosférica");

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);
        ag1.getSensorListInTheGeographicArea().addSensor(s3);

        //Act
        GeoAreaSensorList result = ag1.getSensorsByType(sensorTypePedido);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS0 = new Location(40, -5, 50);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS1 = new Location(41, -6, 50);
        SensorId sensorId2 = new SensorId("s13");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, temperatureId, locS1, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS2 = new Location(42, -7, 55);
        SensorId sensorId3 = new SensorId("s1d");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId3, "A123", dataFuncionamento2, temperatureId, locS2, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Instanciar Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 5, 22, 40);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 19, 36, 55);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(25, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 2, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 6, 4, 2, 05, 27);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 4, 30, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        //Act
        GeoAreaSensorList result = ag1.getSensorListByTypeInAPeriod(temperatureId, dataInicial, dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo_SensoresSemLeiturasNoPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS0 = new Location(40, -5, 50);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS1 = new Location(41, -6, 50);
        SensorId sensorId2 = new SensorId("987");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, temperatureId, locS1, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        Location locS2 = new Location(42, -7, 55);
        SensorId sensorId3 = new SensorId("75474");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId3, "A123", dataFuncionamento2, temperatureId, locS2, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Instanciar Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 0, 0, 0);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(25, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2015, 2, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2015, 6, 4, 0, 0, 0);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2015, 4, 30, 0, 0, 0);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        //Act
        GeoAreaSensorList result = ag1.getSensorListByTypeInAPeriod(temperatureId, dataInicial, dataFinal);

        System.out.println(result.getSensorListToString());
        System.out.println(expectedResult.getSensorListToString());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getmNomeAreaGeoTest() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        String expectedResult = "Cidade do Porto";

        //act
        String result = ag1.getDescription();

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDescription() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        String expectedResult = "Puorto";
        ag1.setDescription("Puorto");

        //act
        String result = ag1.getDescription();

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetmTipoAreaGeo() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag1.setGeographicalAreaType(tipo);
        GeoAreaTypeId expectedResult = tipo.getGeoAreaType();

        //act
        GeoAreaTypeId result = ag1.getGeoAreaType();

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarAdicaoSensorAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorTypeId tipo = new SensorTypeId("Humidade");
        Location local = new Location(45, 45, 45);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "s1", data, tipo, local, "l/m2");

        //Act
        boolean resultado = ag1.getSensorListInTheGeographicArea().addSensor(s1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicaoDeDoisSensoresAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorTypeId tipo = new SensorTypeId("Humidade");
        Location local = new Location(45, 45, 45);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "s1", data, tipo, local, "l/m2");
        SensorId sensorId2 = new SensorId("s2");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "s2", data, tipo, local, "l/m2");
        ag1.getSensorListInTheGeographicArea().getListOfSensors().add(s1);

        //Act
        boolean resultado = ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicaoDeUmSensorApenasAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorTypeId tipo = new SensorTypeId("Humidade");
        Location local = new Location(45, 45, 45);
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "s1", data, tipo, local, "l/m2");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId, "s1", data, tipo, local, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        //Act
        boolean resultado = ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetmAreaInseridaEm() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Distrito");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId1);
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Distrito do Porto", tipo, local, area);

        ag1.setInsertedIn(ag1);

        GeographicalArea expectedResult = ag1;

        //Act
        GeographicalArea result = ag1.getInsertedIn();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarNovaLocation() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        double mLatitude = 40.487;
        double mLongitude = -9;
        double mAltitude = 98;
        Location local2 = new Location(mLatitude, mLongitude, mAltitude);

        Location expectedResult = local2;

        //Act
        Location result = ag1.newLocation(mLatitude, mLongitude, mAltitude);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarNovoSensor() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDateTime.of(1991, 12, 2, 12, 15, 55);
        Location locS1 = new Location(123, 345, 50);
        SensorId sensorId0 = new SensorId("43");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId0, "A123", dataFuncionamento, temperatureId, locS1, "l/m2");

        String nomeAG1 = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId);
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        String nomeSensor = "A456";
        SensorId sensorId = new SensorId("s1");
        Location locS2 = new Location(123, 345, 50);
        String units = "l/m2";
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId, nomeSensor, temperatureId, locS2, units);

        GeoAreaSensor expectedResult = s2;

        //Act
        GeoAreaSensor result = ag1.newSensor(sensorId, nomeSensor, temperatureId, locS2, units);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to get the first temperature sensors in the hierarchy of geo areas, with the first area
     * having two temperature sensors, which turns out to be the output of the tested method.
     */
    @Test
    public void testGetFirstSensorsOfATypeInHierarchy_withTempSensorsInFirstArea_ShouldReturnTheCorrespondingList() {
        // Arrange
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensor);
        expectedResult.add(temperatureSensor1);

        // Act
        List<GeoAreaSensor> result = portoCity.getFirstSensorsOfATypeInHierarchy(temperatureId).getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to get the first temperature sensors in the hierarchy of geo areas, with the last area
     * having two temperature sensors, which turns out to be the output of the tested method.
     */
    @Test
    public void testGetFirstSensorsOfATypeInHierarchy_withTempSensorsInLastArea_ShouldReturnTheCorrespondingList() {
        // Arrange
        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensor);
        expectedResult.add(temperatureSensor1);

        // Act
        List<GeoAreaSensor> result = portoCity.getFirstSensorsOfATypeInHierarchy(temperatureId).getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to get the first temperature sensors in the hierarchy of geo areas, without any area
     * having temperature sensors, which turns out to be the output of the tested method (an empty list of sensors).
     */
    @org.junit.jupiter.api.Test
    public void testGetFirstSensorsOfATypeInHierarchy_withNoTempSensors_ShouldReturnAnEmptyList() {
        //Arrange
        List<GeoAreaSensor> expectedResult = new ArrayList<>();

        // Act
        List<GeoAreaSensor> result = portoCity.getFirstSensorsOfATypeInHierarchy(temperatureId).getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastTemperatureInTheAreaTest() {
        // Arrange
        northernRegion.addSensor(temperatureSensor);
        northernRegion.addSensor(temperatureSensor1);

        Location location = new Location(0, 30, 50);

        double expectedResult = 25.0;

        //Act
        double result = portoCity.getLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Região");
        GeographicalAreaType tipo2 = new GeographicalAreaType(geoAreaTypeId);
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região do Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Distrito");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId1);
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId2);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        Location location = new Location(0, 30, 50);
        double expectedResult = Double.NaN;

        //Act
        double result = ag.getLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutMeasurements() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("Região");
        GeographicalAreaType tipo2 = new GeographicalAreaType(geoAreaTypeId2);
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Distrito");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId1);
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location locS0 = new Location(-1, 30, 50);
        SensorId sensorId1 = new SensorId("5432");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento0, temperatureId, locS0, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(0, 30, 50);
        SensorId sensorId2 = new SensorId("9876#");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, temperatureId, locS1, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s1);
        Location location = new Location(0, 30, 50);
        double expectedResult = Double.NaN;

        //Act
        double result = ag.getLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithSensorInTheSameDistance() {
        // Arrange
        northernRegion.addSensor(temperatureSensor);
        temperatureSensor1.getLocation().setLatitude(42.1596);
        northernRegion.addSensor(temperatureSensor1);

        Location location = new Location(0, 30, 50);
        double expectedResult = 30.0;

        //Act
        double result = portoCity.getLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getAverageRainfallInTheAreaTest() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("7654");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("7554");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "Sensor1", dataFuncionamento1, sensorType0, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);
        Reading reading13 = new Reading(20, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        s1.addReadingsToList(reading13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(22.0);
        expectedResult.add(22.5);

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(sensorType0, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestOneSensorWithNoReadings() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("7654");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("7654");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "Sensor1", dataFuncionamento1, sensorType0, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);
        Reading reading13 = new Reading(20, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        s1.addReadingsToList(reading13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<>();
        //expectedResult.add(22.0);
        //expectedResult.add(22.5);

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(sensorType0, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("7654");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(42.149, -8.610, 97);
        SensorId sensorId2 = new SensorId("765");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, sensorType0, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<>();

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(sensorType0, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMeasurementsInAListOfSensors() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("643");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(42.149, -8.610, 97);
        SensorId sensorId2 = new SensorId("i78");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, sensorType0, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        LocalDate searchDate = LocalDate.of(2018, 12, 4);
        double expectedResult = 23.5;

        //Act
        double result = ag.getDailyAverageOfASensor(s1, searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageMeasurementsOfASensorInADayWithNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("78");
        GeoAreaSensor s0 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location locS1 = new Location(42.149, -8.610, 97);
        SensorId sensorId2 = new SensorId("6546");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento1, sensorType0, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        LocalDate searchDate = LocalDate.of(2018, 12, 5);
        double expectedResult = Double.NaN;

        //Act
        double result = ag.getDailyAverageOfASensor(s1, searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithTheSameTypeOfSensor() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("643");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to sensor 1

        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location locS2 = new Location(45.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("463");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType1, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //Instance of GeoAreaSensorList
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        LocalDate day = LocalDate.of(2016, 1, 1);
        GeoAreaSensorList expectedResult = new GeoAreaSensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);

        //ACT
        GeoAreaSensorList result = ag.getSensorListByTypeInADay(sensorType1, day);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaInDifferentDaysWithTheSameTypeOfSensor() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId1 = new SensorId("235");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 2, 2, 05, 27);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location locS2 = new Location(45.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("6435");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType1, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //GeoAreaSensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();
        expectedResult.addSensor(s2);

        //ACT
        GeoAreaSensorList result = ag.getSensorListByTypeInADay(sensorType1, day);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithDifferentTypeOfSensors() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        SensorId sensorId = new SensorId("7654");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType2 = new SensorTypeId("Temp");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        SensorId sensorId2 = new SensorId("6543");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //GeoAreaSensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        GeoAreaSensorList expectedResult = new GeoAreaSensorList();
        expectedResult.addSensor(s1);

        //ACT
        GeoAreaSensorList result = ag.getSensorListByTypeInADay(sensorType1, day);

        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testingTotalDailyMeasurement() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");;
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.15, -8.6, 97);
        AreaShape area = new AreaShape(40, 40);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.10, -8.6, 97);
        SensorId sensorId = new SensorId("7654");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A124", dataFuncionamento1, sensorType1, locS1, "l/m2");

        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //  add measurements to sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        Reading reading11 = new Reading(11, dataHoraDaMedicao11);
        s1.addReadingsToList(reading11);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 10, 05, 27);
        Reading reading12 = new Reading(11, dataHoraDaMedicao12);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2016, 12, 2, 15, 20, 00);
        Location locS2 = new Location(42.20, -8.6, 97);
        SensorId sensorId2 = new SensorId("76547");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType1, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        s2.addReadingsToList(reading21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Reading reading22 = new Reading(11, dataHoraDaMedicao22);
        s2.addReadingsToList(reading22);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = 11;

        //ACT
        double result = ag.getTotalDailyMeasurement(sensorType1, day, local);

        //ASSERT
        assertEquals(expectedResult, result, 0.001);

    }


    @Test
    public void testingTotalDailyMeasurementWithAnEmptyList() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(40, 40);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instance of SensorType
        SensorTypeId typeOfSensorTested = new SensorTypeId("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day, local);

        //ASSERT
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testingTotalDailyMeasurementNoMeasurements_DoubleNan() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Coimbra";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId);
        Location local = new Location(42.15, -8.6, 97);
        AreaShape area = new AreaShape(40, 40);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade de Coimbra", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.10, -8.6, 97);
        SensorId sensorId1 = new SensorId("87654");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId1, "A124", dataFuncionamento1, sensorType1, locS1, "l/m2");

        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2016, 12, 2, 15, 20, 00);
        Location locS2 = new Location(42.20, -8.6, 97);
        SensorId sensorId2 = new SensorId("7654");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId2, "A123", dataFuncionamento2, sensorType1, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurement(sensorType1, day, local);

        //ASSERT
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void getDateLastTemperatureInTheAreaTest() {
        // Arrange
        northernRegion.addSensor(temperatureSensor);
        northernRegion.addSensor(temperatureSensor1);

        Location location = new Location(0, 30, 50);

        LocalDateTime expectedResult = LocalDateTime.of(2018, 12, 3, 5, 24);

        //Act
        LocalDateTime result = portoCity.getDateLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDateLastTemperatureInTheAreaTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Região");
        GeographicalAreaType tipo2 = new GeographicalAreaType(geoAreaTypeId);
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Distrito");
        GeographicalAreaType tipo1 = new GeographicalAreaType(geoAreaTypeId1);
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("Cidade");
        GeographicalAreaType tipo = new GeographicalAreaType(geoAreaTypeId2);
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        Location location = new Location(0, 30, 50);

        //Act
        LocalDateTime result = ag.getDateLastMeasurementByLocationType(location, temperatureId);

        //Assert
        assertNull(result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added, included a negative value
     * expected result {2018-12-04=20, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    void getDailyAmplitudeInterval() {

        // Extra Reading
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(-5, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(15, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);


        // Map
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), 20.0);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperatureId, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 12/04/2018 has only a DoubleNan values, so the amplitude in that day will be DoubleNan.
     * expected result {2018-12-04=NaN, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    void getDailyAmplitudeInterval_doubleNanValuesFor4_12_2018() {

        // Extra Reading
        double value = Double.NaN;
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(value, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(value, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), value);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperatureId, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 12/04/2018 has one DoubleNan value and two valid values, so the amplitude in that day will be calculated
     * with that two valid values
     * expected result {2018-12-04=12, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    void getDailyAmplitudeInterval_oneDayOneDoubleNanValueTwoValidValues_For4_12_2018() {

        // Extra Reading
        double value = Double.NaN;
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(value, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(12, time3);
        LocalDateTime time4 = LocalDateTime.of(2018, 12, 4, 13, 20, 00);
        Reading reading8 = new Reading(24, time4);


        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);
        temperatureSensor1.addReadingsToList(reading8);


        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), 12.0);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperatureId, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetLocation() {
        //arrange
        portoCity.setLocation(location2);
        Location expectedResult = location2;
        //act
        Location result = portoCity.getLocation();
        //assert
        assertEquals(expectedResult, result);

    }


    @Test
    void getDailyAmplitudeInterval_emptySensor_emptyMap() {

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 12, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 14, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperatureId, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 4/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 20.
     */
    @Test
    void getHighestDailyAmplitude_4_12_2018_amplitude20() {

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), 20.0);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time2.toLocalDate(), 20.0);

        //Act
        Map<LocalDate, Double> result = portoCity.getHighestDailyAmplitude(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is a doubleNan value for the amplitude in 4/12/2018
     * 2/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 7.
     */
    @Test
    void getHighestDailyAmplitude_doubleNanValuesIn4_12_2018_highestAmplitude7() {

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        double value = Double.NaN;
        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), value);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);

        //Act
        Map<LocalDate, Double> result = portoCity.getHighestDailyAmplitude(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * the Map is empty
     * expected a empty Map.
     */
    @Test
    void getHighestDailyAmplitude_emptyMap_emptyMap() {

        // Maps
        Map<LocalDate, Double> dailyAmplitudesEmpty = new HashMap<>();

        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = portoCity.getHighestDailyAmplitude(dailyAmplitudesEmpty);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNearestSensorWithMostRecentReading() {
        //Arrange
        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        GeoAreaSensor expectedResult = temperatureSensor1;

        //Act
        GeoAreaSensor result = portoCity.getNearestSensorWithMostRecentReading(temperatureId, this.location2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestMaximumReading_WithValidReadings_ShouldReturnReadingWithValue17() {
        //Arrange
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading1 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading2 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading1);
        temperatureSensor1.addReadingsToList(reading2);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading3 = new Reading(17, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading4 = new Reading(10, time3);
        temperatureSensor1.addReadingsToList(reading3);
        temperatureSensor1.addReadingsToList(reading4);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        Reading expectedResult = reading3;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetLastLowestMaximumReading_WithNullSensor_Null() {
        //Arrange
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading1 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading2 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading1);
        temperatureSensor1.addReadingsToList(reading2);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading3 = new Reading(17, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading4 = new Reading(10, time3);
        temperatureSensor1.addReadingsToList(reading3);
        temperatureSensor1.addReadingsToList(reading4);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        SensorTypeId sensorTypeId = new SensorTypeId("Rainfall");
        Reading expectedResult = null;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, sensorTypeId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestMaximumReading_WithOnlyInvalidReadings_ShouldReturnReadingWithDoubleNaN() {
        //Arrange
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 7, 12, 20, 00);
        Reading reading1 = new Reading(Double.NaN, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 8, 13, 20, 00);
        Reading reading2 = new Reading(Double.NaN, time1);
        temperatureSensor1.addReadingsToList(reading1);
        temperatureSensor1.addReadingsToList(reading2);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 8, 06, 20, 00);
        Reading reading3 = new Reading(Double.NaN, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 9, 12, 20, 00);
        Reading reading4 = new Reading(Double.NaN, time3);
        temperatureSensor1.addReadingsToList(reading3);
        temperatureSensor1.addReadingsToList(reading4);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 7);
        LocalDate endDate = LocalDate.of(2018, 12, 10);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        Reading expectedResult = reading4;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestMaximumReading_WithNoReadingsInGivenInterval_ShouldReturnNull() {
        //Arrange

        LocalDate startDate = LocalDate.of(2018, 12, 7);
        LocalDate endDate = LocalDate.of(2018, 12, 10);

        Reading expectedResult = null;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_validReading_reading7() {
        //Arrange
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(30, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(31, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(35, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        Reading expectedResult = reading7;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_allSameValueReadings_reading4() {
        //Arrange
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 18, 12, 20, 00);
        Reading reading4 = new Reading(30, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 19, 13, 20, 00);
        Reading reading5 = new Reading(29, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 20, 06, 20, 00);
        Reading reading6 = new Reading(28, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 21, 12, 20, 00);
        Reading reading7 = new Reading(30, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 18);
        LocalDate endDate = LocalDate.of(2018, 12, 22);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        Reading expectedResult = reading4;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getNearestSensorWithMostRecentReading_isNull() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Rainfall");
        GeoAreaSensor expectedResult = null;
        //Act
        GeoAreaSensor result = portoCity.getNearestSensorWithMostRecentReading(sensorTypeId, this.location2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_withValidValuesAndADoubleNaN_reading5() {
        //Arrange

        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(Double.NaN, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(31, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(31, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        Reading expectedResult = reading5;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperatureId, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_withOnlyADoubleNaN_Reading10() {
        //Arrange

        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        SensorId sensorId = new SensorId("123");
        GeoAreaSensor temperatureSensor3 = new GeoAreaSensor(sensorId, "A123", startDate, temperatureId, sensorLocation, "l/m2");

        LocalDateTime time10 = LocalDateTime.of(2018, 12, 6, 12, 20, 00);
        Reading reading10 = new Reading(Double.NaN, time10);
        temperatureSensor3.addReadingsToList(reading10);

        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor3);

        //interval LocalDate
        LocalDate initialDate = LocalDate.of(2018, 12, 5);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        Reading expectedResult = reading10;

        //Act
        Reading result = northernRegion.getFirstHighestReading(temperatureId, initialDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_NoReadings_null() {
        //Arrange

        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        SensorId sensorId = new SensorId ("123");
        GeoAreaSensor temperatureSensor3 = new GeoAreaSensor(sensorId, "A123", startDate, temperatureId, sensorLocation, "l/m2");

        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor3);

        //interval LocalDate
        LocalDate initialDate = LocalDate.of(2018, 12, 5);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        Reading expectedResult = null;

        //Act
        Reading result = northernRegion.getFirstHighestReading(temperatureId, initialDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAreaShape() {
        //arrange
        AreaShape expectedResult = new AreaShape(10, 10);
        portoCity.setAreaShape(expectedResult);
        //act
        AreaShape result = portoCity.getAreaShape();
        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void addSensor_SensorIsAdded_ShouldReturnTrue() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        SensorId sensorId = new SensorId ("123");
        GeoAreaSensor sensor = new GeoAreaSensor(sensorId, "A123", startDate, sensorTypeId, sensorLocation, "l/m2");
        //Act
        boolean result = this.portoCity.addSensor(sensor);
        //Assert
        assertTrue(result);
    }

    /**
     * Test that tries to use a valid/existing Id to remove a sensor, which results in true.
     */
    @Test
    public void testRemoveSensorById_tryingWithAnExistingId_ShouldReturnTrue() {
        // Assert
        portoCity.addSensor(temperatureSensor);
        portoCity.addSensor(temperatureSensor1);

        // Act
        boolean result = portoCity.removeSensorById(temperatureSensor.getId());

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to use an invalid/non-existing Id to remove a sensor, which results in false.
     */
    @Test
    public void testRemoveSensorById_tryingWithANonExistingId_ShouldReturnFalse() {
        // Act
        SensorId sensorId = new SensorId("321");
        boolean result = portoCity.removeSensorById(sensorId);

        // Assert
        assertFalse(result);
    }
}