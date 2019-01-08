package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class SensorTest {

    @Test
    void testaConstrutor() {
        //Arrange
        //Calendar calendario = new GregorianCalendar(1991, 11, 2);
        //Date dataFuncionamento = calendario.getTime();
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Sensor s2 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        String expectedResult = "A123";
        //Act
        String result = s1.getmSensorName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorData() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDate expectedResult = dataFuncionamento.toLocalDate();
        //Act
        LocalDate result = s1.getmStartingDate().toLocalDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorTipo() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        SensorType expectedResult = sensorType;
        //Act
        SensorType result = s1.getmSensorType();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsSame() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Sensor s2 = new Sensor("A200", dataFuncionamento, sensorType, locS1);
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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Location expectedResult = locS1;
        //Act
        Location result = s1.getmLocation();
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarDistanciaLinear() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(40, 10, 20);
        Sensor s1 = new Sensor("s1", dataFuncionamento, sensorType, locS1);

        Location locS2 = new Location(30, 15, 10);
        Sensor s2 = new Sensor("s2", dataFuncionamento, sensorType, locS2);

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
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Measurement measurement = new Measurement(20, dataFuncionamento);
        s1.addMeasurementToList(measurement);

        Measurement expectedResult = measurement;

        //Act
        Measurement result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarListaDeMedicoesVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Measurement expectedResult = null;

        //Act
        Measurement result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarListaDeRegistosVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        //Act
        boolean result = s1.measurementListIsEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    void testarListaDeRegistosNaoEVazia() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        LocalDateTime dataHoraDaMedicao1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);

        Measurement measurement1 = new Measurement(20, dataHoraDaMedicao1);
        s1.addMeasurementToList(measurement1);

        //Act
        boolean result = s1.measurementListIsEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    void testarListaDeMedicoesDefinida() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        LocalDateTime dataHoraDaMedicao1 = LocalDate.of(1991, 11, 2).atTime(15, 20, 00);

        LocalDateTime dataHoraDaMedicao2 = LocalDate.of(1991, 11, 3).atTime(17, 24, 00);

        Measurement measurement1 = new Measurement(20, dataHoraDaMedicao1);
        Measurement measurement2 = new Measurement(25, dataHoraDaMedicao2);
        s1.addMeasurementToList(measurement1);
        s1.addMeasurementToList(measurement2);

        Measurement expectedResult = measurement2;

        //Act
        Measurement result = s1.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarGetMenorRegistoMes() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(20.1, data2);
        Measurement registo3 = new Measurement(21.7, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesListaSemRegistos() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        double expectedResult = Double.NaN;
        LocalDate diaDoMes = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);

        // Act
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Measurement registo1 = new Measurement(20.5, data1);
        Measurement registo2 = new Measurement(19, data2);
        Measurement registo3 = new Measurement(21.7, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(22, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesTodosIguais() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(19, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferenteUltimoDiferente() {

        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        LocalDateTime data1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime data2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime data3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Measurement registo1 = new Measurement(22, data1);
        Measurement registo2 = new Measurement(22, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        LocalDate diaDoMes = LocalDate.of(2017, 8, 5);

        // Act
        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        double result = sensor1.getLowestMeasurementOfMonth(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }



    @Test
    void testaGetMaiorRegistoMes() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        LocalDateTime data1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime data2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime data3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime data4 = LocalDateTime.of(2018, 2, 31, 15, 20);

        Measurement registo1 = new Measurement(28, data1);
        Measurement registo2 = new Measurement(25, data2);
        Measurement registo3 = new Measurement(26, data3);
        Measurement registo4 = new Measurement(27, data4);

        double expectedResult = 27;
        LocalDate dataDoMes = LocalDate.of(2017, 8, 5);

        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        sensor1.addMeasurementToList(registo4);

        //Act
        double result = sensor1.getGreatestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMesListaSemRegistos() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        LocalDate dataDoMes = LocalDate.of(2018, 2, 15);
        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getGreatestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMediaRegistoMes() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        LocalDateTime data1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime data2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime data3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime data4 = LocalDateTime.of(2018, 2, 12, 6, 25);

        Measurement registo1 = new Measurement(21, data1);
        Measurement registo2 = new Measurement(25, data2);
        Measurement registo3 = new Measurement(26, data3);
        Measurement registo4 = new Measurement(27, data4);

        double expectedResult = 26;
        LocalDate diaDoMes = LocalDate.of(2018, 2, 15);

        sensor1.addMeasurementToList(registo1);
        sensor1.addMeasurementToList(registo2);
        sensor1.addMeasurementToList(registo3);
        sensor1.addMeasurementToList(registo4);
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
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
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
        Sensor sensor1 = new Sensor("A123", dataDomingo1, sensorType, locS1);
        Measurement measurementDomingo1 = new Measurement(30, dataDomingo1);
        Measurement measurementDomingo2 = new Measurement(35, dataDomingo2);
        Measurement measurementSegunda = new Measurement(40, dataSegunda);

        sensor1.addMeasurementToList(measurementDomingo1);
        sensor1.addMeasurementToList(measurementDomingo2);
        sensor1.addMeasurementToList(measurementSegunda);


        List<Measurement> expectedResult = new ArrayList<>();
        expectedResult.add(measurementDomingo1);
        expectedResult.add(measurementDomingo2);

        //act
        List<Measurement> result = sensor1.getDailyMeasurement(dataDomingo.toLocalDate());

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetRegistosDoDiaComValorNaN() {
        //Arrange

        LocalDateTime dataDomingo1 = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDateTime dataDomingo2 = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDateTime dataSegunda = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDateTime dataDomingo = LocalDateTime.of(1991, 11, 2, 21, 10, 25);

        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataDomingo1, sensorType, locS1);
        Measurement measurementDomingo1 = new Measurement(Double.NaN, dataDomingo1);
        Measurement measurementDomingo2 = new Measurement(35.0, dataDomingo2);
        Measurement measurementSegunda = new Measurement(40.0, dataSegunda);

        sensor1.addMeasurementToList(measurementDomingo1);
        sensor1.addMeasurementToList(measurementDomingo2);
        sensor1.addMeasurementToList(measurementSegunda);


        List<Measurement> expectedResult = new ArrayList<>();
        expectedResult.add(measurementDomingo2);

        //act
        List<Measurement> result = sensor1.getDailyMeasurement(dataDomingo.toLocalDate());

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);
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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Measurement measurement1 = new Measurement(40, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);


        double expectedResult = -2;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMinimoDoDiaComListaVazia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);

        double expectedResult = -2;

        //Act
        double result = sensor1.getLowestMeasurementOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarPrimeiroDiaSemana() {
        //Arrange
        LocalDateTime data = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);
        LocalDate expectedResult = data.toLocalDate();

        LocalDate result = sensor1.getFirstDayOfWeek(data.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemana() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(-4.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(-2.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(-5.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(-2.0, data7);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0, -2.0, -5.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(-2, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(-5, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(-2, data7);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, -2.0, -4.0, -2.0, -5.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(-2, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(-2, data5);


        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(8.9, data7);


        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1, 11.2, 8.9)); //66.9/7=9.557

        double expectedResult = 66.9 / 7;

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Measurement measurement1 = new Measurement(30, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Measurement measurement2 = new Measurement(40, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);


        double expectedResult = 40;

        //Act
        double result = sensor1.getMaximumValueOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetValorMaximoDoDiaComListaVazia() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Measurement measurement2 = new Measurement(-2, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Measurement measurement3 = new Measurement(30, data3);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);

        double expectedResult = 30;

        //Act
        double result = sensor1.getMaximumValueOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarListaMaximosSemana(){
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(20.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(20.0, data7);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(20.0,30.0,20.0,40.0,20.0,45.0,20.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(20.0, data7);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(30.0,20.0,40.0,20.0,45.0,20.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(30.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(20.0, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(30.0,30.0,20.0,40.0,20.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(45.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(25.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(20.0, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(45.0,30.0,25.0,40.0));

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Measurement measurement6 = new Measurement(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Measurement measurement7 = new Measurement(8.9, data7);


        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);
        sensor1.addMeasurementToList(measurement6);
        sensor1.addMeasurementToList(measurement7);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1, 11.2, 8.9)); //66.9/7=9.557

        double expectedResult=66.9/7;

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Measurement measurement5 = new Measurement(10.1, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1)); //46.8/5=9.36

        double expectedResult=46.8/5;

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

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
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Measurement measurement5 = new Measurement(10.1, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

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
        Sensor sensor1 = new Sensor("T123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Measurement measurement2 = new Measurement(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Measurement measurement3 = new Measurement(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Measurement measurement4 = new Measurement(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Measurement measurement5 = new Measurement(10, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

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
        Sensor sensor1 = new Sensor("T123", data, sensorType, locS1);

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 8, 00, 01);
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Measurement measurement2 = new Measurement(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Measurement measurement3 = new Measurement(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Measurement measurement4 = new Measurement(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Measurement measurement5 = new Measurement(10, data5);

        //Adição das medições
        sensor1.addMeasurementToList(measurement1);
        sensor1.addMeasurementToList(measurement2);
        sensor1.addMeasurementToList(measurement3);
        sensor1.addMeasurementToList(measurement4);
        sensor1.addMeasurementToList(measurement5);

        double expectedResult = 30;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = sensor1.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

}