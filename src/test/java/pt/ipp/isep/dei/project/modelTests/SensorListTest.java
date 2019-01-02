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
        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

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
        List<Measurement> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaUltimosRegistosPorTipoSensorCasoUltimoRegistoDoubleNan() {
        //Arrange
        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

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
        List<Measurement> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaUltimosRegistosPorTipoSensorListaVazia() {
        //Arrange
        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

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
        List<Measurement> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresDiferentes() {
        //arrange
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Measurement
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(26, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
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

        Measurement expectedResult = measurement12;

        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        //Act
        Measurement result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresIguais() {
        //arrange
        //Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(24, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);


        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 24, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(30, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(27, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        Measurement expectedResult = measurement12;

        SensorType sensorType = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        //Act
        Measurement result = listSens.getLatestMeasurementBySensorType(sensorType);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQueNaoTem() {
        //arrange
        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
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

        Measurement expectedResult = null;

        SensorType tipoResultado = new SensorType("Pluviosidade");
        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        //Act
        Measurement result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
        //arrange
        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();

        Measurement expectedResult = null;
        //Act
        Measurement result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayTwoSensors() {
        //arrange

        Calendar calendar0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendar0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendar1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento1 = calendar1.getTime();
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", dataFuncionamento1, sensorType1, locS1);

        Calendar calendarMeasurement1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure1 = calendarMeasurement1.getTime();

        Calendar calendarMeasurement2 = new GregorianCalendar(1991, 11, 2, 20, 24, 00);
        Date dateTimeDayMeasure2 = calendarMeasurement2.getTime();

        Measurement measurement1 = new Measurement(20.0, dateTimeDayMeasure1);
        Measurement measurement2 = new Measurement(25.0, dateTimeDayMeasure2);

        s0.addMeasurementToList(measurement1);
        s0.addMeasurementToList(measurement2);

        Calendar calendarMeasurement3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure3 = calendarMeasurement3.getTime();

        Calendar calendarMeasurement4 = new GregorianCalendar(1991, 11, 2, 17, 24, 00);
        Date dateTimeDayMeasure4 = calendarMeasurement4.getTime();

        Measurement measurement3 = new Measurement(20.0, dateTimeDayMeasure3);
        Measurement measurement4 = new Measurement(30.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(measurement3);
        s1.addMeasurementToList(measurement4);

        SensorList listOfSensors = new SensorList();
        listOfSensors.addSensorToTheListOfSensors(s0);
        listOfSensors.addSensorToTheListOfSensors(s1);

        double expectedResult = 30.0;

        //Act
        double result = listOfSensors.getMaximumMeasureOfATypeOfSensorInAGivenDay(sensorType0, dateTimeDayMeasure2);


        //Assert
        assertEquals(expectedResult, result);
    }



    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfSensors() {
        //arrange


        Calendar cal = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure = cal.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        SensorList listOfSensors = new SensorList();

        double expectedResult = Double.NaN;

        //Act
        double result = listOfSensors.getMaximumMeasureOfATypeOfSensorInAGivenDay(sensorType0, dateTimeDayMeasure);


        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfMeasurements() {
        //arrange

        Calendar calendar0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date date0 = calendar0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);

        Calendar cal = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure = cal.getTime();

        SensorList listOfSensors = new SensorList();

        double expectedResult = Double.NaN;

        //Act
        double result = listOfSensors.getMaximumMeasureOfATypeOfSensorInAGivenDay(sensorType0, dateTimeDayMeasure);


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayTwoDifferentSensors() {
        //arrange

        Calendar calendar0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date date0 = calendar0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);


        Calendar calendarMeasurement1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure1 = calendarMeasurement1.getTime();

        Calendar calendarMeasurement2 = new GregorianCalendar(1991, 11, 2, 20, 24, 00);
        Date dateTimeDayMeasure2 = calendarMeasurement2.getTime();

        Measurement measurement1 = new Measurement(20.0, dateTimeDayMeasure1);
        Measurement measurement2 = new Measurement(25.0, dateTimeDayMeasure2);

        s0.addMeasurementToList(measurement1);
        s0.addMeasurementToList(measurement2);

        Calendar calendar1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date date1 = calendar1.getTime();
        SensorType sensorType1 = new SensorType("Humidity");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", date1, sensorType1, locS1);

        Calendar calendarMeasurement3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dateTimeDayMeasure3 = calendarMeasurement3.getTime();

        Calendar calendarMeasurement4 = new GregorianCalendar(1991, 11, 2, 17, 24, 00);
        Date dateTimeDayMeasure4 = calendarMeasurement4.getTime();

        Measurement measurement3 = new Measurement(20.0, dateTimeDayMeasure3);
        Measurement measurement4 = new Measurement(30.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(measurement3);
        s1.addMeasurementToList(measurement4);

        SensorList listOfSensors = new SensorList();
        listOfSensors.addSensorToTheListOfSensors(s0);
        listOfSensors.addSensorToTheListOfSensors(s1);

        double expectedResult = 25.0;

        //Act
        double result = listOfSensors.getMaximumMeasureOfATypeOfSensorInAGivenDay(sensorType0, dateTimeDayMeasure2);


        //Assert
        assertEquals(expectedResult, result);
    }

}