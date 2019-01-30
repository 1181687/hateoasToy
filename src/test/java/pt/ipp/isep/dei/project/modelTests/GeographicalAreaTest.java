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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo2 = new GeographicalAreaType("Aldeia");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        GeographicalAreaType tipo2 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, 10.6109, 50);
        Location local2 = new Location(32.6333, 16.9, 20);
        AreaShape area1 = new AreaShape(10, 10, local1);
        AreaShape area2 = new AreaShape(10, 10, local2);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        Sensor s1 = new Sensor("A121", dataFuncionamento1, sensorType1, locS1);

        //Instanciar S2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("A130", dataFuncionamento2, sensorType2, locS2);

        //Instanciar S3
        LocalDateTime dataFuncionamento3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType3 = new SensorType("Temperatura");
        Location locS3 = new Location(-4, -10, 65);
        Sensor s3 = new Sensor("A120", dataFuncionamento3, sensorType3, locS3);

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

        // Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Instanciar Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 5, 22, 40);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 19, 36, 55);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(25, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 2, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 6, 4, 2, 05, 27);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 4, 30, 20, 17, 50);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

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
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getSensorListInTheGeographicArea().addSensor(s2);

        //Instanciar Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2015, 12, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2015, 12, 3, 0, 0, 0);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(25, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2015, 2, 2, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2015, 6, 4, 0, 0, 0);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2015, 4, 30, 0, 0, 0);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

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
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        GeographicalAreaType expectedResult = tipo;

        //act
        GeographicalAreaType result = ag1.getGeoAreaType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarAdicaoSensorAAreaGeografica() {
        //Arrange
        String nomeAG1 = "Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);

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
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);
        Sensor s2 = new Sensor("s2", data, tipo, local);
        ag1.getSensorListInTheGeographicArea().getSensorList().add(s1);

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
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        LocalDateTime data = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
        SensorType tipo = new SensorType("Humidade");
        Location local = new Location(45, 45, 45);
        Sensor s1 = new Sensor("s1", data, tipo, local);
        Sensor s2 = new Sensor("s1", data, tipo, local);
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
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
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
        GeographicalAreaType tipo1 = new GeographicalAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        AreaShape area1 = new AreaShape(10, 10, local1);
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
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getSensorListInTheGeographicArea().addSensor(s1);


        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        SensorType typeRequired = new SensorType("Temperature");

        //Act
        Boolean result = ag.getTheSensorListOfAGivenType(typeRequired).getSensorList().isEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void getTheSensorListInTheFirstAreaWithSensorOfAGivenTypeTestWithoutSensors() {
        // Arrange
        // Instantiate GeoAreas
        String nomeAG2 = "Região Norte";
        GeographicalAreaType tipo2 = new GeographicalAreaType("Região");
        Location local2 = new Location(32.1496, 7.6109, 98);
        AreaShape area2 = new AreaShape(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);

        SensorType typeRequired = new SensorType("Temperature");

        //Act
        Boolean result = ag.getTheSensorListOfAGivenType(typeRequired).getSensorList().isEmpty();

        //Assert
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
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getSensorListInTheGeographicArea().addSensor(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);


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
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
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
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
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
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG1 = "Distrito Porto";
        GeographicalAreaType tipo1 = new GeographicalAreaType("Distrito");
        Location local1 = new Location(41.1496, -6.6109, 100);
        AreaShape area1 = new AreaShape(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);
        ag1.setInsertedIn(ag2);

        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setInsertedIn(ag1);


        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(-1, 30, 50);
        Sensor s1 = new Sensor("A12555", dataFuncionamento1, sensorType1, locS1);
        ag2.getSensorListInTheGeographicArea().addSensor(s1);

        // Instantiate Measurements
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 19, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);
        Readings readings13 = new Readings(20, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);
        s1.addReadingsToList(readings13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(23.0);
        expectedResult.add(30.0);
        expectedResult.add(22.0);
        expectedResult.add(22.5);

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(searchType, startDate, endDate);

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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        ArrayList<Double> expectedResult = new ArrayList<>();

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurement(searchType, startDate, endDate);

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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        double expectedResult = 23.5;

        //Act
        double result = ag.getDailyAverageOfAListOfSensors(ag.getSensorListInTheGeographicArea(), searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageMeasurementsInAListOfSensorsDayWithNoMeasurements() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        LocalDate searchDate = LocalDate.of(2018, 12, 5);

        double expectedResult = Double.NaN;

        //Act
        double result = ag.getDailyAverageOfAListOfSensors(ag.getSensorListInTheGeographicArea(), searchDate);

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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to Sensor 1

        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of SensorList

        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 2, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 2, 2, 05, 27);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //SensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);


        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temp");
        Location locS2 = new Location(45.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //SensorList to ag
        ag.getSensorListInTheGeographicArea().addSensor(s1);
        ag.getSensorListInTheGeographicArea().addSensor(s2);

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //Expected Result
        SensorList expectedResult = new SensorList();
        expectedResult.addSensor(s1);

        //ACT
        SensorList result = ag.getSensorListByTypeInADay(typeOfSensorTested, day);


        //ASSERT
        assertEquals(expectedResult, result);

    }
/*
    @Test
    public void testingTotalDailyMeasurement() {
        //ARRANGE
        //Instanciar AG
        String nomeAG = "Porto";
        GeographicalAreaType tipo = new GeographicalAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        AreaShape area = new AreaShape(40, 40, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);


        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A124", dataFuncionamento1, sensorType1, locS1);

        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        Readings readings11 = new Readings(11, dataHoraDaMedicao11);
        s1.addReadingsToList(readings11);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 2, 05, 27);
        Readings readings12 = new Readings(11, dataHoraDaMedicao12);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add Sensor to the List of Sensors in the GeoArea
        ag.getSensorListInTheGeographicArea().addSensor(s2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        s2.addReadingsToList(readings21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Readings readings22 = new Readings(11, dataHoraDaMedicao22);
        s2.addReadingsToList(readings22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = 31;


        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day);

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
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);


        //Sensor1
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("A124", dataFuncionamento1, sensorType1, locS1);
        //  add measurements to Sensor 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2016, 1, 1, 8, 59, 13);
        Readings readings11 = new Readings(11, dataHoraDaMedicao11);
        s1.addReadingsToList(readings11);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Readings readings12 = new Readings(11, dataHoraDaMedicao12);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataFuncionamento2 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Rainfall");
        Location locS2 = new Location(42.1496, -8.6109, 97);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        //  add measurements to Sensor 2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(2016, 1, 1, 18, 24, 10);
        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        s2.addReadingsToList(readings21);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(2016, 1, 1, 20, 17, 50);
        Readings readings22 = new Readings(11, dataHoraDaMedicao22);
        s2.addReadingsToList(readings22);

        //Instance of SensorType
        SensorType typeOfSensorTested = new SensorType("Rainfall");

        //Instance of a day
        LocalDate day = LocalDate.of(2016, 1, 1);

        //expected result
        double expectedResult = Double.NaN;

        //ACT
        double result = ag.getTotalDailyMeasurement(typeOfSensorTested, day);

        //ASSERT
        assertEquals(expectedResult, result);
    }
    */
}