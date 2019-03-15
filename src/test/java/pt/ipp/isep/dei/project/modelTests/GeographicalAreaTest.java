package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

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
    private Sensor temperatureSensor;
    private Sensor temperatureSensor1;
    private SensorType temperature;
    private Location location2;
    private GeographicalAreaType city;
    private AreaShape areaShape2;


    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeographicalAreaType region = new GeographicalAreaType("Region");
        GeographicalAreaType district = new GeographicalAreaType("District");
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100, location);
        northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40, location1);
        portoDistrict = new GeographicalArea("Distrito do Porto", "Porto District", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        this.location2 = new Location(42.1496, -8.6109, 97);
        areaShape2 = new AreaShape(10, 10, location2);
        portoCity = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // Sensors
        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        temperatureSensor = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("321", "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

    }

    @Test
    public void testarEqualsSame() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        boolean expectedResult = true;

        //act
        boolean result = ag1.equals(ag1);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsTrue() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //act
        boolean result = ag1.equals(ag2);

        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsFalse() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo2 = new GeographicalAreaType("Aldeia");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo1, local, area);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Aldeia do Porto", tipo2, local, area);

        //act
        boolean result = ag1.equals(ag2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsFalseLocalizacao() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(41.15, -8.62, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo1, local, area);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo1, local2, area);

        //act
        boolean result = ag1.equals(ag2);

        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsObjetosDiferentes() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        boolean expectedResult = false;

        //act
        boolean result = ag.equals(area);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarDistanciaLinearDuasAreas() {
        // arrange
        String nomeAG1 = "Porto";
        String nomeAG2 = "Funchal";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, 10.6109, 50);
        Location local2 = new Location(32.6333, 16.9, 20);
        AreaShape area1 = new AreaShape(10, 10, local1);
        AreaShape area2 = new AreaShape(10, 10, local2);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -5, 50);
        Sensor s0 = new Sensor("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testarSensorNaoContidoEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -20, 50);
        Sensor s0 = new Sensor("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testarSensorNoLimiteEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(46.1496, -13.6109, 65);
        Sensor s0 = new Sensor("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);

        //Assert
        assertTrue(result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipo() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(43, -10, 65);
        Sensor s0 = new Sensor("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(43, -10, 65);
        Sensor s1 = new Sensor("321", "A121", dataFuncionamento1, sensorType1, locS1, "l/m2");

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("123", "A130", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType3 = new SensorType("Temperatura");
        Location locS3 = new Location(-4, -10, 65);
        Sensor s3 = new Sensor("321", "A120", dataFuncionamento3, sensorType3, locS3, "l/m2");

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);
        ag1.getSensorListInTheGeographicArea().addSensor(s3);

        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s0);
        expectedResult.addSensor(s1);

        SensorType sensorTypePedido = new SensorType("Temperatura");

        //Act
        SensorList result = ag1.getSensorsInGeographicalAreaByType(sensorTypePedido);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipoQueNaoExiste() {
        //Arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(50, -10, 65);
        Sensor s0 = new Sensor("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(50, -10, 65);
        Sensor s1 = new Sensor("321", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("5432", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType3 = new SensorType("Temperatura");
        Location locS3 = new Location(-4, -10, 65);
        Sensor s3 = new Sensor("7654", "A123", dataFuncionamento3, sensorType3, locS3, "l/m2");

        SensorList expectedResult = new SensorList();

        SensorType sensorTypePedido = new SensorType("Pressão atmosférica");

        ag1.getSensorListInTheGeographicArea().addSensor(s0);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);
        ag1.getSensorListInTheGeographicArea().addSensor(s3);

        //Act
        SensorList result = ag1.getSensorsInGeographicalAreaByType(sensorTypePedido);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("98765", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("7654", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("53242", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
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

        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        SensorList result = ag1.getSensorListByTypeInAPeriod(tipoResultado, dataInicial, dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo_SensoresSemLeiturasNoPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("654", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("987", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("75474", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
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

        SensorList expectedResult = new SensorList();

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        SensorList result = ag1.getSensorListByTypeInAPeriod(tipoResultado, dataInicial, dataFinal);

        System.out.println(result.getSensorListToString());
        System.out.println(expectedResult.getSensorListToString());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getmNomeAreaGeoTest() {
        //arrange
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag1.setGeographicalAreaType(tipo);
        GeographicalAreaType expectedResult = tipo;

        //act
        GeographicalAreaType result = ag1.getGeoAreaType();

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarAdicaoSensorAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("421", "s1", data, tipo, local, "l/m2");

        //Act
        boolean resultado = ag1.getSensorListInTheGeographicArea().addSensor(s1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicaoDeDoisSensoresAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("54", "s1", data, tipo, local, "l/m2");
        Sensor s2 = new Sensor("876", "s2", data, tipo, local, "l/m2");
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("8765", "s1", data, tipo, local, "l/m2");
        Sensor s2 = new Sensor("8765", "s1", data, tipo, local, "l/m2");
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
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
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("43", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Cidade do Porto", tipo1, local1, area1);

        String nomeSensor = "A456";
        String id = "s1";
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(123, 345, 50);
        String units = "l/m2";
        Sensor s2 = new Sensor(id, nomeSensor, sensorType2, locS2, units);

        Sensor expectedResult = s2;

        //Act
        Sensor result = ag1.newSensor(id, nomeSensor, sensorType2, locS2, units);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeFirstAreaTest() {
        // Arrange
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // Act
        boolean result = portoCity.getTheSensorListOfAGivenType(temperature).getListOfSensors().isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeLastAreaTest() {
        // Arrange
        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // Act
        boolean result = portoCity.getTheSensorListOfAGivenType(temperature).getListOfSensors().isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeNoSensorsTest() {
        //Arrange
        // Geographical Area with no sensors
        Location locationNoSensor = new Location(12.1496, 7.6109, 98);
        AreaShape areaShape2 = new AreaShape(100, 100, locationNoSensor);

        GeographicalArea coimbraCity = new GeographicalArea("Coimbra", "Cidade de Coimbra", city, locationNoSensor, areaShape2);

        // Act
        Boolean result = coimbraCity.getTheSensorListOfAGivenType(temperature).getListOfSensors().isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getLastTemperatureInTheAreaTest() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região do Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("7654", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("43214", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        Location location = new Location(0, 30, 50);

        double expectedResult = 25.0;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região do Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        Location location = new Location(0, 30, 50);
        double expectedResult = Double.NaN;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutMeasurements() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("5432", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("9876#", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s1);
        Location location = new Location(0, 30, 50);
        double expectedResult = Double.NaN;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithSensorInTheSameDistance() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("1231", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(-1, 30, 50);
        Sensor s1 = new Sensor("765", "A12555", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 19, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        Location location = new Location(0, 30, 50);
        double expectedResult = 30.0;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getAverageRainfallInTheAreaTest() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("3214", "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("7554", "Sensor1", dataFuncionamento1, sensorType1, locS1, "l/m2");
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

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(searchType, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestOneSensorWithNoReadings() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("878", "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("87", "Sensor1", dataFuncionamento1, sensorType1, locS1, "l/m2");
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

        ArrayList<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(22.0);
        expectedResult.add(22.5);

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(searchType, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("7654", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("765", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<>();
        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(searchType, local, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMeasurementsInAListOfSensors() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("643", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("i78", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
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
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageMeasurementsOfASensorInADayWithNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("78", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("6546", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
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
        assertEquals(expectedResult, result);
    }


    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithTheSameTypeOfSensor() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("643", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
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
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("463", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of SensorList
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        LocalDate day = LocalDate.of(2016, 1, 1);
        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);

        //ACT
        SensorList result = ag.getSensorListByTypeInADay(typeOfSensorTested, day);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaInDifferentDaysWithTheSameTypeOfSensor() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("235", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
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
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("6435", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //SensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s2);

        //ACT
        SensorList result = ag.getSensorListByTypeInADay(typeOfSensorTested, day);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithDifferentTypeOfSensors() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("7654", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
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
        SensorType sensorType2 = new SensorType("Temp");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("6543", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //SensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);

        //ACT
        SensorList result = ag.getSensorListByTypeInADay(typeOfSensorTested, day);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void testingTotalDailyMeasurement() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.15, -8.6, 97);
        AreaShape area = new AreaShape(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.10, -8.6, 97);
        Sensor s1 = new Sensor("7654", "A124", dataFuncionamento1, sensorType1, locS1, "l/m2");

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
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.20, -8.6, 97);
        Sensor s2 = new Sensor("7654", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        s2.addReadingsToList(reading21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Reading reading22 = new Reading(11, dataHoraDaMedicao22);
        s2.addReadingsToList(reading22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = 11;

        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day, local);

        //ASSERT
        assertEquals(expectedResult, result);

    }


    @Test
    public void testingTotalDailyMeasurementWithAnEmptyList() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day, local);

        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testingTotalDailyMeasurementNoMeasurements_DoubleNan() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.15, -8.6, 97);
        AreaShape area = new AreaShape(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.10, -8.6, 97);
        Sensor s1 = new Sensor("87654", "A124", dataFuncionamento1, sensorType1, locS1, "l/m2");

        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2016, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.20, -8.6, 97);
        Sensor s2 = new Sensor("7654", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");
        //  add sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day, local);

        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getDateLastTemperatureInTheAreaTest() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("6543", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("87654", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag2.getSensorListInTheGeographicArea().addSensor(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        Location location = new Location(0, 30, 50);

        LocalDateTime expectedResult = LocalDateTime.of(2018, 12, 03, 17, 24);
        SensorType type = new SensorType("Temperature");

        //Act
        LocalDateTime result = ag.getDateLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDateLastTemperatureInTheAreaTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, "Região Norte", tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, "Distrito do Porto", tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, "Cidade do Porto", tipo, local, area);
        ag.setInsertedIn(ag1);

        Location location = new Location(0, 30, 50);
        LocalDateTime expectedResult = null;
        SensorType type = new SensorType("Temperature");

        //Act
        LocalDateTime result = ag.getDateLastMeasurementByLocationType(location, type);

        //Assert
        assertEquals(expectedResult, result);
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

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

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

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

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

        //Act
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

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
        Map<LocalDate, Double> result = portoCity.getDailyAmplitudeInInterval(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

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
        Sensor expectedResult = temperatureSensor1;
        //Act
        Sensor result = portoCity.getNearestSensorWithMostRecentReading(this.temperature, this.location2);
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

        Reading expectedResult = reading3;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, this.temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
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

        SensorType sensorType = new SensorType("txuva");
        Reading expectedResult = null;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, sensorType, startDate, endDate);
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

        Reading expectedResult = reading4;
        //Act
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, this.temperature, startDate, endDate);
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
        Reading result = portoCity.getLastLowestMaximumReading(this.location2, this.temperature, startDate, endDate);
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

        Reading expectedResult = reading7;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperature, startDate, endDate);
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

        Reading expectedResult = reading4;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getNearestSensorWithMostRecentReading_isNull() {
        //Arrange
        SensorType rainfall = new SensorType("rainfall");
        Sensor expectedResult = null;
        //Act
        Sensor result = portoCity.getNearestSensorWithMostRecentReading(rainfall, this.location2);
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

        Reading expectedResult = reading5;

        //Act
        Reading result = portoCity.getFirstHighestReading(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_withOnlyADoubleNaN_Reading10() {
        //Arrange

        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        Sensor temperatureSensor3 = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");

        LocalDateTime time10 = LocalDateTime.of(2018, 12, 6, 12, 20, 00);
        Reading reading10 = new Reading(Double.NaN, time10);
        temperatureSensor3.addReadingsToList(reading10);

        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor3);

        //interval LocalDate
        LocalDate initialDate = LocalDate.of(2018, 12, 5);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        Reading expectedResult = reading10;

        //Act
        Reading result = northernRegion.getFirstHighestReading(temperature, initialDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_NoReadings_null() {
        //Arrange

        temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        Sensor temperatureSensor3 = new Sensor("123", "A123", startDate, temperature, sensorLocation, "l/m2");

        northernRegion.getSensorListInTheGeographicArea().addSensor(temperatureSensor3);

        //interval LocalDate
        LocalDate initialDate = LocalDate.of(2018, 12, 5);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        Reading expectedResult = null;

        //Act
        Reading result = northernRegion.getFirstHighestReading(temperature, initialDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAreaShape() {
        //arrange
        AreaShape expectedResult = new AreaShape(10, 10, location2);
        portoCity.setAreaShape(expectedResult);
        //act
        AreaShape result = portoCity.getAreaShape();
        //assert
        assertEquals(expectedResult, result);

    }
}