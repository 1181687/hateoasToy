package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorListTest {

    @Test
    void addSensorToTheListOfSensorsInTheGeographicalArea() {
        //Arrange
        SensorList newList = new SensorList();
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        //Act
        boolean result = newList.addSensorToTheListOfSensors(s0);
        //Assert
        assertTrue(result);
    }

    @Test
    void getmSensorList() {
        //Arrange
        SensorList newList = new SensorList();

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        newList.addSensorToTheListOfSensors(s0);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s0);
        //Act
        List<Sensor> result = newList.getmSensorList();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void testCreateNewSensor () {
        //Arrange
        String sensorName = "s0";
        SensorType sensorType = new SensorType("Temperatura");
        Location location = new Location(123, 345, 50);
        Sensor s0 = new Sensor(sensorName, sensorType, location);

        SensorList newList = new SensorList();

        Sensor expectedResult = s0;

        // Act
        Sensor result = newList.createNewSensor(sensorName, sensorType, location);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testargetListaUltimosRegistosPorTipoSensorCasoPositivo() {
        //Arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(20, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(20, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        List<Measurement> expectedResult = new ArrayList<>();

        expectedResult.add(measurement02);
        expectedResult.add(measurement12);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Measurement> result = listSens.getListaDeUltimosRegistosPorTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testargetListaUltimosRegistosPorTipoSensorCasoUltimoRegistoDoubleNan() {
        //Arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(20, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(Double.NaN, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(20, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        List<Measurement> expectedResult = new ArrayList<>();

        expectedResult.add(measurement01);
        expectedResult.add(measurement12);

        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        //Act
        List<Measurement> result = listSens.getListaDeUltimosRegistosPorTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testargetListaUltimosRegistosPorTipoSensorListaVazia() {
        //Arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(20, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        List<Measurement> expectedResult = new ArrayList<>();

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Measurement> result = listSens.getListaDeUltimosRegistosPorTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

}