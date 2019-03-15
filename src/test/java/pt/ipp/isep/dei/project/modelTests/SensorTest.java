package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SensorTest {
    private Sensor temperatureSensor;
    private Reading reading;
    private Reading reading1;

    @BeforeEach
    void StartUp() {
        // Sensor
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        SensorType temperature = new SensorType("Temperature");
        Location location = new Location(123, 345, 50);
        temperatureSensor = new Sensor("R003", "A123", startingDate, temperature, location, "l/m2");

        // Readings
        LocalDateTime dateTime = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        reading = new Reading(11, dateTime);
        temperatureSensor.addReadingsToList(reading);
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        reading1 = new Reading(14, dateTime1);
        temperatureSensor.addReadingsToList(reading1);
    }

    @Test
    void testaConstrutor() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        Sensor s2 = new Sensor("R003", "A456", dataFuncionamento, sensorType, locS1, "2/m2");
        //Act
        boolean result = s1.equals(s2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaConstrutorSensor() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        String expectedResult = "A123";
        //Act
        String result = s1.getSensorName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorData() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("S09", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDate expectedResult = dataFuncionamento.toLocalDate();
        //Act
        LocalDate result = s1.getStartingDate().toLocalDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorTipo() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        SensorType expectedResult = sensorType;
        //Act
        SensorType result = s1.getSensorType();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsSame() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        boolean expectedResult = true;
        //Act
        boolean result = s1.equals(s1);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarHashCode() {
        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        int expectedResult = 1;
        // Act
        int result = s1.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsFalse() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        boolean expectedResult = false;
        //Act
        boolean result = s1.equals(sensorType);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsNomeSensorDiferente() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        Sensor s2 = new Sensor("R004", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        boolean expectedResult = false;
        //Act
        boolean result = s1.equals(s2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorLocalizacao() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        Location expectedResult = locS1;
        //Act
        Location result = s1.getLocation();
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarDistanciaLinear() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(40, 10, 20);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        Location locS2 = new Location(30, 15, 10);
        Sensor s2 = new Sensor("R004", "A123", dataFuncionamento, sensorType, locS2, "l/m2");

        double expectedResult = 1201040.7956;

        double result = s1.distanceBetweenTwoLocations(s2);

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    void testarRegistoDeMedicao() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        Reading reading = new Reading(20, dataFuncionamento);
        s1.addReadingsToList(reading);

        Reading expectedResult = reading;

        //Act
        Reading result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarListaDeMedicoesVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        Reading expectedResult = null;

        //Act
        Reading result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarListaDeRegistosVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        //Act
        boolean result = s1.isMeasurementListEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    void testarListaDeRegistosNaoEVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDateTime dataHoraDaMedicao1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        Reading reading1 = new Reading(20, dataHoraDaMedicao1);
        s1.addReadingsToList(reading1);

        //Act
        boolean result = s1.isMeasurementListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    void testarListaDeMedicoesDefinida() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDateTime dataHoraDaMedicao1 = LocalDate.of(1991, 11, 2).atTime(15, 20, 00);

        LocalDateTime dataHoraDaMedicao2 = LocalDate.of(1991, 11, 3).atTime(17, 24, 00);

        Reading reading1 = new Reading(20, dataHoraDaMedicao1);
        Reading reading2 = new Reading(25, dataHoraDaMedicao2);
        s1.addReadingsToList(reading1);
        s1.addReadingsToList(reading2);

        Reading expectedResult = reading2;

        //Act
        Reading result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarGetMenorRegistoMes() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading registo1 = new Reading(19, data1);
        Reading registo2 = new Reading(20.1, data2);
        Reading registo3 = new Reading(21.7, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesListaSemRegistos() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;
        LocalDate diaDoMes = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);

        // Act
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading registo1 = new Reading(20.5, data1);
        Reading registo2 = new Reading(19, data2);
        Reading registo3 = new Reading(21.7, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading registo1 = new Reading(19, data1);
        Reading registo2 = new Reading(22, data2);
        Reading registo3 = new Reading(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesTodosIguais() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading registo1 = new Reading(19, data1);
        Reading registo2 = new Reading(19, data2);
        Reading registo3 = new Reading(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferenteUltimoDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading registo1 = new Reading(22, data1);
        Reading registo2 = new Reading(22, data2);
        Reading registo3 = new Reading(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        double result = sensor1.getSmallestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMes() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDateTime data1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime data2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime data3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime data4 = LocalDateTime.of(2018, 2, 12, 15, 20);

        Reading registo1 = new Reading(28, data1);
        Reading registo2 = new Reading(25, data2);
        Reading registo3 = new Reading(26, data3);
        Reading registo4 = new Reading(27, data4);

        double expectedResult = 27;
        LocalDate dataDoMes = LocalDate.of(2018, 2, 5);

        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        sensor1.addReadingsToList(registo4);

        //Act
        double result = sensor1.getBiggestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMes2() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDateTime data1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime data2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime data3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime data4 = LocalDateTime.of(2018, 2, 12, 15, 20);

        Reading registo1 = new Reading(28, data1);
        Reading registo2 = new Reading(27, data2);
        Reading registo3 = new Reading(26, data3);
        Reading registo4 = new Reading(28, data4);

        double expectedResult = 28;
        LocalDate dataDoMes = LocalDate.of(2018, 2, 5);

        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        sensor1.addReadingsToList(registo4);

        //Act
        double result = sensor1.getBiggestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMesListaSemRegistos() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDate dataDoMes = LocalDate.of(2018, 2, 15);
        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getBiggestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMediaRegistoMes() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        LocalDateTime data1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime data2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime data3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime data4 = LocalDateTime.of(2018, 2, 12, 6, 25);

        Reading registo1 = new Reading(21, data1);
        Reading registo2 = new Reading(25, data2);
        Reading registo3 = new Reading(26, data3);
        Reading registo4 = new Reading(27, data4);

        double expectedResult = 26;
        LocalDate diaDoMes = LocalDate.of(2018, 2, 15);

        sensor1.addReadingsToList(registo1);
        sensor1.addReadingsToList(registo2);
        sensor1.addReadingsToList(registo3);
        sensor1.addReadingsToList(registo4);
        //Act
        double result = sensor1.getMonthlyAverageMeasurement(diaDoMes);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMediaRegistoMes_SemRegistos() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;
        LocalDate diaDoMes = LocalDate.of(2018, 2, 20);

        //Act
        double result = sensor1.getMonthlyAverageMeasurement(diaDoMes);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarSeUmSensorTemOTipoPedido() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        SensorType tipoPedido = new SensorType("Temperatura");
        //Act
        boolean resultado = sensor1.sensorTypeEqualsSensorType(tipoPedido);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeUmSensorNaoTemOTipoPedido() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataFuncionamento, sensorType, locS1, "l/m2");
        SensorType tipoPedido = new SensorType("Humidade");
        //Act
        boolean resultado = sensor1.sensorTypeEqualsSensorType(tipoPedido);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetRegistosDoDia() {
        //Arrange

        LocalDateTime dataDomingo1 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataDomingo2 = LocalDateTime.of(2018, 11, 2, 16, 20, 00);
        LocalDateTime dataSegunda = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        LocalDateTime dataDomingo = LocalDateTime.of(2018, 11, 2, 21, 10, 25);


        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataDomingo1, sensorType, locS1, "l/m2");
        Reading readingDomingo1 = new Reading(30, dataDomingo1);
        Reading readingDomingo2 = new Reading(35, dataDomingo2);
        Reading readingSegunda = new Reading(40, dataSegunda);

        sensor1.addReadingsToList(readingDomingo1);
        sensor1.addReadingsToList(readingDomingo2);
        sensor1.addReadingsToList(readingSegunda);


        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(readingDomingo1);
        expectedResult.add(readingDomingo2);

        //act
        List<Reading> result = sensor1.getDailyMeasurement(dataDomingo.toLocalDate());

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetRegistosDoDiaComValorNaN() {
        //Arrange

        LocalDateTime dataDomingo1 = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDateTime dataDomingo2 = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDateTime dataSegunda = LocalDateTime.of(1991, 11, 3, 21, 10, 25);
        LocalDateTime dataDomingo = LocalDateTime.of(1991, 11, 2, 21, 10, 25);

        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", dataDomingo1, sensorType, locS1, "l/m2");
        Reading readingDomingo1 = new Reading(Double.NaN, dataDomingo1);
        Reading readingDomingo2 = new Reading(35.0, dataDomingo2);
        Reading readingSegunda = new Reading(40.0, dataSegunda);

        sensor1.addReadingsToList(readingDomingo1);
        sensor1.addReadingsToList(readingDomingo2);
        sensor1.addReadingsToList(readingSegunda);


        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(readingDomingo2);

        //act
        List<Reading> result = sensor1.getDailyMeasurement(dataDomingo.toLocalDate());

        //assert
        assertEquals(expectedResult, result);

    }


    @Test
    public void testarVerificaSeDatasSaoIguais() {
        //Arrange
        LocalDateTime data = LocalDateTime.of(2018, 2, 16, 16, 20, 00);
        LocalDateTime data2 = LocalDateTime.of(2018, 1, 2, 15, 20, 00);
        boolean expectedResult = false;
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");
        boolean result = sensor1.checkIfDaysAreEqual(data.toLocalDate(), data2.toLocalDate());

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarVerificaSeDatasSaoIguaisTrue() {
        //Arrange

        LocalDateTime data = LocalDateTime.of(2018, 10, 2, 15, 20, 00);
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 16, 20, 00);

        boolean expectedResult = true;
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Act
        boolean result = sensor1.checkIfDaysAreEqual(data.toLocalDate(), data2.toLocalDate());

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarVerificaSeDatasSaoIguaisMesmaData() {
        //Arrange
        LocalDateTime data = LocalDateTime.of(2018, 10, 2, 15, 20, 00);
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 15, 20, 00);

        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Act
        boolean result = sensor1.checkIfDaysAreEqual(data.toLocalDate(), data2.toLocalDate());

        //assert
        assertTrue(result);
    }

    @Test
    public void testarGetValorMinimoDoDia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(40, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = -2;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testGetLowestValueOfDay() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(-2, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(-3, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-4, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = -4;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testGetLowestValueOfDayBoundaries() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(1, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(2, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = 0;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMinimoDoDiaComListaVazia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMinimoDoDiaComValorNaN() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        double expectedResult = -2;

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        //Act
        double result = sensor1.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarPrimeiroDiaSemana() {
        //Arrange
        LocalDateTime data = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");
        LocalDate expectedResult = LocalDate.of(1991, 10, 27);

        LocalDate result = sensor1.getFirstDayOfWeek(data.toLocalDate());

        assertEquals(expectedResult, result);
    }


    @Test
    public void testarListaMinimosSemana() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(-5.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(-2.0, data7);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>();

        expectedResult.add(-2.0);
        expectedResult.add(-4.0);
        expectedResult.add(-2.0);
        expectedResult.add(-5.0);
        expectedResult.add(-2.0);

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaDoubleNaN() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(-5, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(-2, data7);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0, -2.0, -5.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaComCincoRegistos() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaComDataSemanaDiferente() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);


        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaSemRegistos() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        List<Double> expectedResult = new ArrayList<>(Arrays.asList());

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        List<Double> result = sensor1.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetMediaRegitosMinSemanal() {

        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(8.9, data7);


        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);


        double expectedResult = 47.4 / 5;

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = sensor1.getAverageOfLowestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegitosMinSemanalDoubleNan() {

        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        double result = sensor1.getAverageOfLowestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarValorMaximoDoDia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(30, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(40, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);


        double expectedResult = 40;

        //Act
        double result = sensor1.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testMaximumValueOfDay() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(29, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(31, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);


        double expectedResult = 31;

        //Act
        double result = sensor1.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetValorMaximoDoDiaComListaVazia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getMaximumValueOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMaximoDoDiaComValorNaN() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(-2, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(30, data3);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        double expectedResult = 30;

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        //Act
        double result = sensor1.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarListaMaximosSemana(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(20.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(20.0, data7);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(20.0, 40.0, 20.0, 45.0, 20.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaDoubleNaN(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 5, 01, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 6, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 9, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 10, 17, 20, 00);
        Reading reading6 = new Reading(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 11, 17, 20, 00);
        Reading reading7 = new Reading(20.0, data7);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, 20.0, 40.0, 20.0, 45.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 6);

        //Act
        List<Double> result = sensor1.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaComCincoRegistos(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 6, 01, 00, 01);
        Reading reading1 = new Reading(30.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 5, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, 30.0, 40.0, 20.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 7);

        //Act
        List<Double> result = sensor1.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaComDataSemanaDiferente(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(45.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(25.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(25.0, 40.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = sensor1.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaSemRegistos(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        List <Double> expectedResult = new ArrayList<>(Arrays.asList());

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        List<Double> result = sensor1.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanal(){

        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(8.9, data7);


        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);
        sensor1.addReadingsToList(reading6);
        sensor1.addReadingsToList(reading7);


        double expectedResult = 47.4 / 5;

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = sensor1.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanalDoubleNan(){

        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        double expectedResult= Double.NaN;

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        double result = sensor1.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanalComCincoDiasDeRegisto(){

        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        double expectedResult = 9.1;

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = sensor1.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageTest() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10.1, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        double expectedResult = 9.36;


        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = sensor1.getDailyAverage(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testOfGetTotalDailyMeasurementsOfTheSameDay (){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        double expectedResult = 40;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = sensor1.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testOfGetTotalDailyMeasurementsWithDifferentDays (){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adição das medições
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        double expectedResult = 30;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = sensor1.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testsGetBiggestMeasurement() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading1 = new Reading(15, data1);

        //Registo 2
        Reading reading2 = new Reading(5, data1);

        //Registo 3
        Reading reading3 = new Reading(5, data1);

        //Registo 4
        Reading reading4 = new Reading(10, data1);

        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        double expectedResult = 15;

        //Act
        double result = sensor1.getBiggestMeasurementOfMonth(data1.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testsGetBiggestMeasurementNoValues() {
        //Arrange
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getBiggestMeasurementOfMonth(data.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralReadingsInOneDay_ShouldReturnHighestReading() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018,11,2);

        Reading expectedResult = reading2;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralReadingsInTwoDays_ShouldReturnHighestReadingOfSelectedDay() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(13, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018,11,3);

        Reading expectedResult = reading5;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralHighestReadingsInOneDay_ShouldReturnMostRecentOne(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018,11,2);

        Reading expectedResult = reading2;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHighestReadingOfADay_WithNoReadings_ShouldReturnNull(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);

        LocalDate day = LocalDate.of(2018,11,5);

        Reading expectedResult = null;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHighestReadingOfADay_WithDoubleNaNReadings_ShouldReturnHighestReading() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018, 11, 2);

        Reading expectedResult = reading1;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithOnlyOneDoubleNaNReadings_ShouldReturnDoubleNaN() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 16, 59, 59);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);

        LocalDate day = LocalDate.of(2018, 11, 2);

        Reading expectedResult = reading2;

        //Act
        Reading result = sensor1.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_With2DatesWithValidReadings_ShouldReturnListWith2Readings() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(14, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(15, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018,11,2);
        LocalDate endDate = LocalDate.of(2018,11,3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading2);
        expectedResult.add(reading4);

        //Act
        List<Reading> result = sensor1.getDailyMaxReadingsInAnInterval(startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_WithOneDateWithOnlyInvalidReadings_ShouldReturnListWith1ValidReadingAnd1DoubleNaN() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(15, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading4);

        //Act
        List<Reading> result = sensor1.getDailyMaxReadingsInAnInterval(startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_WithDatesWithOnlyDoubleNaNReadings_ShouldReturnListWithDoubleNaNReadings() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(Double.NaN, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(Double.NaN, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading3);

        //Act
        List<Reading> result = sensor1.getDailyMaxReadingsInAnInterval(startDate,endDate);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getLastLowestReading_SensorWithValidReadings_ShouldReturnLastLowestReading(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(14, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018,11,2);
        LocalDate endDate = LocalDate.of(2018,11,3);

        Reading expectedResult = reading4;
        List<Reading> readings = sensor1.getReadingsBetweenDates(startDate,endDate);

        //Act
        Reading result = sensor1.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getLastLowestReading_SensorWithDayWithDoubleNaNReadings_ShouldReturnLastLowestReading(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);
        sensor1.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018,11,2);
        LocalDate endDate = LocalDate.of(2018,11,3);

        Reading expectedResult = reading4;
        List<Reading> readings = sensor1.getReadingsBetweenDates(startDate,endDate);

        //Act
        Reading result = sensor1.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getLastLowestReading_SensorWithOnlyDoubleNaNReadings_ShouldReturnReadingWithDoubleNaN(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(Double.NaN, data4);


        //Adding readings to sensor
        sensor1.addReadingsToList(reading1);
        sensor1.addReadingsToList(reading2);
        sensor1.addReadingsToList(reading3);
        sensor1.addReadingsToList(reading4);

        LocalDate startDate = LocalDate.of(2018,11,2);
        LocalDate endDate = LocalDate.of(2018,11,3);

        Reading expectedResult = reading4;
        List<Reading> readings = sensor1.getReadingsBetweenDates(startDate,endDate);

        //Act
        Reading result = sensor1.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getLastLowestReading_SensorWithNoReadings_ShouldReturnNull(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("R003", "A123", data, sensorType, locS1, "l/m2");

        LocalDate startDate = LocalDate.of(2018,11,2);
        LocalDate endDate = LocalDate.of(2018,11,3);

        Reading expectedResult = null;
        List<Reading> readings = sensor1.getReadingsBetweenDates(startDate,endDate);

        //Act
        Reading result = sensor1.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult,result);
    }

    /**
     * Test that tries to get an existing list of Readings from the Sensor.
     */
    @Test
    void testGetListOfReadings_tryingToGetAnExistingList_ShouldReturnTheCorrespondingList() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading);
        expectedResult.add(reading1);

        // Act
        List<Reading> result = temperatureSensor.getListOfReadings();

        // Arrange
        assertEquals(expectedResult, result);
    }

    @Test
    void setAndGetId(){
        //Arrange
        String id = "sdfg22";
        String expectedResult = "sdfg22";
        //Act
        this.temperatureSensor.setId(id);
        String result = this.temperatureSensor.getId();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setAndGetUnits(){
        //Arrange
        String units = "Fahrenheit";
        String expectedResult = "Fahrenheit";
        //Act
        this.temperatureSensor.setUnits(units);
        String result = this.temperatureSensor.getUnits();
        //Assert
        assertEquals(expectedResult,result);
    }
}