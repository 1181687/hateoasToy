package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SensorListTest {

    @Test
    void addSensorToTheListOfSensorsInTheGeographicalArea() {
        //Arrange
        SensorList newList = new SensorList();
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        //Act
        boolean result = newList.addSensor(s0);
        //Assert
        assertTrue(result);
    }

    @Test
    void getmSensorList() {
        //Arrange
        SensorList newList = new SensorList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        newList.addSensor(s0);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s0);
        //Act
        List<Sensor> result = newList.getListOfSensors();
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
        Sensor result = newList.newSensor(sensorName, sensorType, location);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testargetListaUltimosRegistosPorTipoSensorCasoPositivo() {
        //Arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(20, dataHoraDaMedicao01);
        Reading reading02 = new Reading(25, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading11 = new Reading(20, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading02);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Reading> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaUltimosRegistosPorTipoSensorCasoUltimoRegistoDoubleNan() {
        //Arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(20, dataHoraDaMedicao01);
        Reading reading02 = new Reading(Double.NaN, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading11 = new Reading(20, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading01);

        //Act
        List<Reading> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaUltimosRegistosPorTipoSensorListaVazia() {
        //Arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Reading
        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading11 = new Reading(20, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        List<Reading> expectedResult = new ArrayList<>();

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        List<Reading> result = listSens.getListOfLatestMeasurementsBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresDiferentes() {
        //arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Reading
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(26, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 4, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        Reading expectedResult = reading02;

        //Act
        Reading result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresIguais() {
        //arrange
        //sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(24, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 24, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(30, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 4, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(27, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        SensorType sensorType = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        Reading expectedResult = reading22;

        //Act
        Reading result = listSens.getLatestMeasurementBySensorType(sensorType);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQueNaoTem() {
        //arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(25, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 11, 4, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading21 = new Reading(20, dataHoraDaMedicao21);
        Reading reading22 = new Reading(25, dataHoraDaMedicao22);

        s2.addReadingsToList(reading21);
        s2.addReadingsToList(reading22);

        Reading expectedResult = null;

        SensorType tipoResultado = new SensorType("Pluviosidade");
        SensorList listSens = new SensorList();
        listSens.addSensor(s0);
        listSens.addSensor(s1);
        listSens.addSensor(s2);

        //Act
        Reading result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
        //arrange
        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();

        Reading expectedResult = null;
        //Act
        Reading result = listSens.getLatestMeasurementBySensorType(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayTwoTemperatureSensors() {
        //arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading1 = new Reading(20.0, dateTimeDayMeasure1);
        Reading reading2 = new Reading(25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(reading1);
        s0.addReadingsToList(reading2);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading3 = new Reading(20.0, dateTimeDayMeasure3);
        Reading reading4 = new Reading(30.0, dateTimeDayMeasure4);

        s1.addReadingsToList(reading3);
        s1.addReadingsToList(reading4);

        SensorList listOfSensors = new SensorList();
        listOfSensors.addSensor(s0);
        listOfSensors.addSensor(s1);

        double expectedResult = 30.0;

        //Act
        double result = listOfSensors.getMaximumMeasureOfTypeOfSensorInGivenDay(sensorType1, dateTimeDayMeasure4.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayDifferentDays() {
        //arrange

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading1 = new Reading(20.0, dateTimeDayMeasure1);
        Reading reading2 = new Reading(25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(reading1);
        s0.addReadingsToList(reading2);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 3, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 3, 17, 24, 00);

        Reading reading3 = new Reading(20.0, dateTimeDayMeasure3);
        Reading reading4 = new Reading(30.0, dateTimeDayMeasure4);

        s1.addReadingsToList(reading3);
        s1.addReadingsToList(reading4);

        SensorList listOfSensors = new SensorList();
        listOfSensors.addSensor(s0);
        listOfSensors.addSensor(s1);

        double expectedResult = 25.0;

        //Act
        double result = listOfSensors.getMaximumMeasureOfTypeOfSensorInGivenDay(sensorType0, dateTimeDayMeasure2.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfSensors() {
        //arrange

        LocalDateTime dateTimeDayMeasure = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        SensorList listOfSensors = new SensorList();

        double expectedResult = Double.NaN;

        //Act
        double result = listOfSensors.getMaximumMeasureOfTypeOfSensorInGivenDay(sensorType0, dateTimeDayMeasure.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfMeasurements() {
        //arrange

        LocalDateTime date0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);

        LocalDateTime dateTimeDayMeasure = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        SensorList listOfSensors = new SensorList();

        double expectedResult = Double.NaN;

        //Act
        double result = listOfSensors.getMaximumMeasureOfTypeOfSensorInGivenDay(sensorType0, dateTimeDayMeasure.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayTwoDifferentSensors() {
        //arrange

        LocalDateTime date0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);


        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 11, 2, 20, 24, 00);

        Reading reading1 = new Reading(20.0, dateTimeDayMeasure1);
        Reading reading2 = new Reading(25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(reading1);
        s0.addReadingsToList(reading2);

        LocalDateTime date1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Humidity");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", date1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 11, 2, 17, 24, 00);

        Reading reading3 = new Reading(20.0, dateTimeDayMeasure3);
        Reading reading4 = new Reading(30.0, dateTimeDayMeasure4);

        s1.addReadingsToList(reading3);
        s1.addReadingsToList(reading4);

        SensorList listOfSensors = new SensorList();
        listOfSensors.addSensor(s0);
        listOfSensors.addSensor(s1);

        double expectedResult = 25.0;

        //Act
        double result = listOfSensors.getMaximumMeasureOfTypeOfSensorInGivenDay(sensorType0, dateTimeDayMeasure2.toLocalDate());


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMeasurementsInAListOfSensors() {
        //arrange
        SensorList list = new SensorList();

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        list.addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        list.addSensor(s1);

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
        double result = list.getDailyAverage(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageMeasurementsInAListOfSensorsDayWithNoMeasurements() {
        //arrange
        SensorList list = new SensorList();

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        list.addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        list.addSensor(s1);

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
        double result = list.getDailyAverage(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorsListContentTest () {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        SensorList sensorList = new SensorList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("A456", dataFuncionamento1, sensorType1, locS1);

        sensorList.addSensor(s0);
        sensorList.addSensor(s1);

        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";

        // Act
        String result = sensorList.getSensorListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue () {
        // Arrange
        SensorList sensorList = new SensorList();

        // Act
        boolean result = sensorList.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse () {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        SensorList sensorList = new SensorList();

        sensorList.addSensor(s0);

        // Act
        boolean result = sensorList.isEmpty();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testHashCode() {
        SensorList sensorList = new SensorList();
        int expectedResult = Objects.hash(sensorList.getListOfSensors());

        // Act
        int result = sensorList.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange

        SensorList sensorList1 = new SensorList();
        SensorList sensorList2 = new SensorList();

        //Act
        boolean result = sensorList1.equals(sensorList2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsTrueSameObj() {
        //Arrange

        SensorList sensorList1 = new SensorList();

        //Act
        boolean result = sensorList1.equals(sensorList1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsTrueFalse() {
        //Arrange

        SensorList sensorList1 = new SensorList();
        RoomList list = new RoomList();

        //Act   \
        boolean result = sensorList1.equals(list);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGetSensorWithMostRecentReading() {
        //arrange
        SensorList sensorList = new SensorList();

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("Sensor0", dataFuncionamento0, sensorType0, locS0);
        sensorList.addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("Sensor1", dataFuncionamento1, sensorType1, locS1);
        sensorList.addSensor(s1);

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

        Sensor expectedResult = s1;

        Sensor result = sensorList.getSensorWithMostRecentReading(sensorList);

        //Assert
        assertEquals(expectedResult, result);

    }
}