package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

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
        boolean expectedResult = true;
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertEquals(expectedResult, result);
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
        boolean expectedResult = false;
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertEquals(expectedResult, result);
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
        double resultado = ag1.distanciaLinearDuasAreas(ag2);

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

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        boolean expectedResult = true;
        //Act
        boolean result = ag1.verificarSeSensorEstaContidoNaAG(s0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSensorNaoContidoEmAreaGeografica() {
        //Arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(45, -20, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        boolean expectedResult = false;
        //Act
        boolean result = ag1.verificarSeSensorEstaContidoNaAG(s0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarSensorNoLimiteEmAreaGeografica() {
        //Arrange

        // Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);

        // Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(46.1496, -13.6109, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        boolean expectedResult = true;
        //Act
        boolean result = ag1.verificarSeSensorEstaContidoNaAG(s0);
        //Assert
        assertEquals(expectedResult, result);
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
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(43, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Instanciar S1
        Calendar calendario1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(43, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        //Instanciar S2
        Calendar calendario2 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar S3
        Calendar calendario3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento3 = calendario3.getTime();
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
        List<Sensor> result = ag1.listarSensoresContidosNaAGPorTipo(sensorTypePedido, lista);
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
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(50, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        //Instanciar S1
        Calendar calendario1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(50, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        //Instanciar S2
        Calendar calendario2 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar S3
        Calendar calendario3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento3 = calendario3.getTime();
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
        List<Sensor> result = ag1.listarSensoresContidosNaAGPorTipo(sensorTypePedido, lista);
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
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2015, 11, 2, 5, 22, 40);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2015, 11, 3, 19, 36, 55);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2016, 1, 2, 8, 59, 13);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2016, 5, 4, 2, 05, 27);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(2016, 0, 1, 18, 24, 10);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(2016, 3, 30, 20, 17, 50);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

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

        Date dataInicial = new GregorianCalendar(2016, 0, 1, 17, 24, 00).getTime();
        Date dataFinal = new GregorianCalendar(2016, 3, 30, 17, 24, 00).getTime();


        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Sensor> result = ag1.listarSensoresDeUmTipoNaAGNumPeriodo(tipoResultado, listaDeSensores, dataInicial, dataFinal);

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
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2015, 11, 2);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2015, 11, 3);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2015, 1, 2);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2015, 5, 4);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(2015, 0, 1);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(2015, 3, 30);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        List<Sensor> listaDeSensores = new ArrayList<>();
        listaDeSensores.add(s0);
        listaDeSensores.add(s1);
        listaDeSensores.add(s2);

        List<Sensor> expectedResult = new ArrayList<>();

        Date dataInicial = new GregorianCalendar(2016, 0, 1, 17, 24, 00).getTime();
        Date dataFinal = new GregorianCalendar(2016, 3, 30, 17, 24, 00).getTime();


        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Sensor> result = ag1.listarSensoresDeUmTipoNaAGNumPeriodo(tipoResultado, listaDeSensores, dataInicial, dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getmNomeAreaGeo() {
        //arrange
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local, area);
        String expectedResult = "Porto";

        //act
        String result = ag1.getmNomeAreaGeo();
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
        GeoAreaType result = ag1.getmGeoAreaType();
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

        Calendar cal = new GregorianCalendar();
        Date data = cal.getTime();
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

        Calendar cal = new GregorianCalendar();
        Date data = cal.getTime();
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

        Calendar cal = new GregorianCalendar();
        Date data = cal.getTime();
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

        ag1.setmAreaInseridaEm(ag1);

        GeographicalArea expectedResult = ag1;

        //Act
        GeographicalArea result = ag1.getmAreaInseridaEm();

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
        Location result = ag1.novaLocalizacao(mLatitude, mLongitude, mAltitude);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarNovoSensor() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Sensor result = ag1.novoSensor(nomeSensor, sensorType2, locS2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastTemperatureInTheAreaTest() {
        //arrange
        //Instanciar AG
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
        ag1.setmAreaInseridaEm(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setmAreaInseridaEm(ag1);


        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        Location location = new Location(0,30,50);

        double expectedResult = 25;

        //Act
        double result = ag.getLastTemperatureInTheArea(location);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTest2() {
        //arrange
        //Instanciar AG
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
        ag1.setmAreaInseridaEm(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setmAreaInseridaEm(ag1);

        Location location = new Location(0,30,50);

        double expectedResult = Double.NaN;

        //Act
        double result = ag.getLastTemperatureInTheArea(location);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getLastTemperatureInTheAreaTest3() {
        //arrange
        //Instanciar AG
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
        ag1.setmAreaInseridaEm(ag2);

        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);
        ag.setmAreaInseridaEm(ag1);


        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(0, 30, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag2.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


        Location location = new Location(0,30,50);

        double expectedResult = Double.NaN;

        //Act
        double result = ag.getLastTemperatureInTheArea(location);

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
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 4, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 5, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);
        Measurement measurement13 = new Measurement(20, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);
        s1.addMeasurementToList(measurement13);

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        ArrayList<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(23.0);
        expectedResult.add(30.0);
        expectedResult.add(22.0);
        expectedResult.add(22.5);

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurementInTheArea(searchType, startDate1, endDate1);

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
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        ArrayList<Double> expectedResult = new ArrayList<>();

        SensorType searchType = new SensorType("Rainfall");

        //Act
        List<Double> result = ag.getDailyAverageMeasurementInTheArea(searchType, startDate1, endDate1);

        //Assert
        assertEquals(expectedResult, result);
    }

}