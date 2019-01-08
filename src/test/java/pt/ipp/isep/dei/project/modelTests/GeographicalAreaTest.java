package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaTest {

    @Test
    public void testarEqualsSame() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);
        boolean expectedResult = true;
        //act
        boolean result = ag1.equals(ag1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsTrue() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertTrue(result);
    }

    @Test
    public void testarEqualsFalse() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        GeoAreaType tipo2 = new GeoAreaType("Aldeia");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo1, local, area);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo2, local, area);
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertFalse(result);
    }

    @Test
    public void testarEqualsObjetosDiferentes() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
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
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        GeoAreaType tipo2 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, 10.6109, 50);
        Location local2 = new Location(32.6333, 16.9, 20);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);
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
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

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
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testarSensorNaoContidoEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -20, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testarSensorNoLimiteEmAreaGeografica() {
        //Arrange
        // Instantiate GeoArea
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        // Instantiate Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(46.1496, -13.6109, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Act
        boolean result = ag1.checkIfSensorInInsideOfGeoArea(s0);
        //Assert
        assertTrue(result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipo() {
        //Arrange

        // Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(43, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(43, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType3 = new SensorType("Temperatura");
        Location locS3 = new Location(-4, -10, 65);
        Sensor s3 = new Sensor("A123", dataFuncionamento3, sensorType3, locS3);


        List<Sensor> lista = new ArrayList<>();
        lista.add(s0);
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);

        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s0);
        expectedResult.add(s1);

        SensorType sensorTypePedido = new SensorType("Temperatura");

        //Act
        List<Sensor> result = ag1.sortSensorsInAGeoAreaByType(sensorTypePedido, lista);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipoQueNaoExiste() {
        //Arrange

        // Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        // Instanciar S0
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(50, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Instanciar S1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(50, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType3 = new SensorType("Temperatura");
        Location locS3 = new Location(-4, -10, 65);
        Sensor s3 = new Sensor("A123", dataFuncionamento3, sensorType3, locS3);


        List<Sensor> lista = new ArrayList<>();
        lista.add(s0);
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);

        List<Sensor> expectedResult = new ArrayList<>();

        SensorType sensorTypePedido = new SensorType("Pressão atmosférica");

        //Act
        List<Sensor> result = ag1.sortSensorsInAGeoAreaByType(sensorTypePedido, lista);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 5, 22, 40);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 19, 36, 55);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 2, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 6, 4, 2, 05, 27);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 4, 30, 20, 17, 50);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        List<Sensor> listaDeSensores = new ArrayList<>();
        listaDeSensores.add(s0);
        listaDeSensores.add(s1);
        listaDeSensores.add(s2);

        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Sensor> result = ag1.listSensorsOfACertainTypeInTheGeoAreaInAGivenPeriod(tipoResultado, listaDeSensores, dataInicial, dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo_SensoresSemLeiturasNoPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 0, 0, 0);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2015, 2, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2015, 6, 4, 0, 0, 0);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2015, 4, 30, 0, 0, 0);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        List<Sensor> listaDeSensores = new ArrayList<>();
        listaDeSensores.add(s0);
        listaDeSensores.add(s1);
        listaDeSensores.add(s2);

        List<Sensor> expectedResult = new ArrayList<>();

        LocalDate dataInicial = LocalDate.of(2016, 1, 1);
        LocalDate dataFinal = LocalDate.of(2016, 4, 30);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Sensor> result = ag1.listSensorsOfACertainTypeInTheGeoAreaInAGivenPeriod(tipoResultado, listaDeSensores, dataInicial, dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getmNomeAreaGeoTest() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);
        String expectedResult = "Porto";

        //act
        String result = ag1.getNameOfGeoArea();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetmTipoAreaGeo() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        GeoAreaType expectedResult = tipo;

        //act
        GeoAreaType result = ag1.getGeoAreaType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarAdicaoSensorAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);

        //Act
        boolean resultado = ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicaoDeDoisSensoresAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);
        Sensor s2 = new Sensor("s2", data, tipo, local);
        ag1.getmSensorListInTheGeographicArea().getmSensorList().add(s1);

        //Act
        boolean resultado = ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicaoDeUmSensorApenasAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);
        Sensor s2 = new Sensor("s1", data, tipo, local);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //Act
        boolean resultado = ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetmAreaInseridaEm() {
        //Arrange
        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag2 = new GeographicalArea(nomeAG, tipo, local, area);

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
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        String nomeAG1 = "Porto";
        GeoAreaType tipo1 = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeSensor = "A456";
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(123, 345, 50);
        Sensor s2 = new Sensor(nomeSensor, sensorType2, locS2);

        Sensor expectedResult = s2;
        //Act
        Sensor result = ag1.newSensor(nomeSensor, sensorType2, locS2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeTest() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaType tipo2 = new GeoAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaType tipo1 = new GeoAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        SensorType typeRequired = new SensorType("Temperature");

        //Act
        Boolean result = ag.getTheSensorListInTheFirstAreaWithSensorOfAGivenType(typeRequired).getmSensorList().isEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaType tipo2 = new GeoAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaType tipo1 = new GeoAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);

        SensorType typeRequired = new SensorType("Temperature");

        //Act
        Boolean result = ag.getTheSensorListInTheFirstAreaWithSensorOfAGivenType(typeRequired).getmSensorList().isEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void getLastTemperatureInTheAreaTest() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaType tipo2 = new GeoAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaType tipo1 = new GeoAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);


        Location location = new Location(0,30,50);

        double expectedResult = 25.0;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getTheLastMeasurementInTheArea(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaType tipo2 = new GeoAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaType tipo1 = new GeoAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);

        Location location = new Location(0,30,50);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getTheLastMeasurementInTheArea(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTestWithoutMeasurements() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeoAreaType tipo2 = new GeoAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeoAreaType tipo1 = new GeoAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


        Location location = new Location(0,30,50);

        double expectedResult = Double.NaN;
        SensorType type = new SensorType("Temperature");

        //Act
        double result = ag.getTheLastMeasurementInTheArea(location, type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getAverageRainfallInTheAreaTest() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);
        Measurement measurement13 = new Measurement(20, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);
        s1.addMeasurementToList(measurement13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(23.0);
        expectedResult.add(30.0);
        expectedResult.add(22.0);
        expectedResult.add(22.5);

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurementInTheArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<>();

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurementInTheArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMeasurementsInAListOfSensors() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        double expectedResult = 23.5;

        //Act
        double result = ag.getDailyAverageOfAListOfSensors(ag.getmSensorListInTheGeographicArea().getmSensorList(), searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageMeasurementsInAListOfSensorsDayWithNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        LocalDate searchDate = LocalDate.of(2018, 12, 5);

        double expectedResult = Double.NaN;

        //Act
        double result = ag.getDailyAverageOfAListOfSensors(ag.getmSensorListInTheGeographicArea().getmSensorList(), searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }



    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithTheSameTypeOfSensor (){
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);
        //  add measurements to Sensor 1

        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of SensorList
        List<Sensor> sensorList = new ArrayList<>();
        sensorList.add(s1);
        sensorList.add(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);


        //ACT
        List<Sensor> result =ag.sortSensorTypesOfAGeoAreaInADay(typeOfSensorTested,sensorList, day);


        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaInDifferentDaysWithTheSameTypeOfSensor (){
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 2, 2, 05, 27);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of SensorList
        List<Sensor> sensorList = new ArrayList<>();
        sensorList.add(s1);
        sensorList.add(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s2);


        //ACT
        List<Sensor> result =ag.sortSensorTypesOfAGeoAreaInADay(typeOfSensorTested,sensorList, day);


        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void getListOfSensorsOfCertainTypeInAGeoAreaDuringTheSameDayWithDifferentTypeOfSensors (){
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temp");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of SensorList
        List<Sensor> sensorList = new ArrayList<>();
        sensorList.add(s1);
        sensorList.add(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);

        //ACT
        List<Sensor> result =ag.sortSensorTypesOfAGeoAreaInADay(typeOfSensorTested,sensorList, day);


        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void testingTotalDailyMeasurement (){
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);


        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A124", dataFuncionamento1, sensorType1, locS1);

        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        Measurement measurement11 = new Measurement(11, dataHoraDaMedicao11);
        s1.addMeasurementToList(measurement11);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);
        Measurement measurement12 = new Measurement(11, dataHoraDaMedicao12);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        s2.addMeasurementToList(measurement21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Measurement measurement22 = new Measurement(11, dataHoraDaMedicao22);
        s2.addMeasurementToList(measurement22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = 31;


        //ACT
        double result = ag.getTotalDailyMeasurementInTheArea(typeOfSensorTested, day);

        //ASSERT

        assertEquals(expectedResult, result);

    }
    @Test
    public void testingTotalDailyMeasurementWithAnEmptyList (){
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);


        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A124", dataFuncionamento1, sensorType1, locS1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        Measurement measurement11 = new Measurement(11, dataHoraDaMedicao11);
        s1.addMeasurementToList(measurement11);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Measurement measurement12 = new Measurement(11, dataHoraDaMedicao12);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        s2.addMeasurementToList(measurement21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Measurement measurement22 = new Measurement(11, dataHoraDaMedicao22);
        s2.addMeasurementToList(measurement22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurementInTheArea(typeOfSensorTested, day);

        //ASSERT
        assertEquals(expectedResult, result);
    }
}