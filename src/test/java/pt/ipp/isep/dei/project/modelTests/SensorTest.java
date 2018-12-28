package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class SensorTest {

    @Test
    void testaConstrutor() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        String expectedResult = "A123";
        //Act
        String result = s1.getmNomeSensor();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorData() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date expectedResult = calendario.getTime();
        //Act
        Date result = s1.getmDataFuncionamento();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorTipo() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
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
        Calendar calendario = new GregorianCalendar(2018, 11, 27);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(40, 10, 20);
        Sensor s1 = new Sensor("s1", dataFuncionamento, sensorType, locS1);

        Location locS2 = new Location(30, 15, 10);
        Sensor s2 = new Sensor("s2", dataFuncionamento, sensorType, locS2);

        double expectedResult = 1201040.7956;

        double result = s1.distanciaLinearEntreDoisSensores(s2);

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    void testarRegistoDeMedicao() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Measurement measurement = new Measurement(20, dataFuncionamento);
        s1.adicionarMedicaoALista(measurement);

        Measurement expectedResult = measurement;

        //Act
        Measurement result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarListaDeMedicoesVazia() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Measurement expectedResult = null;

        //Act
        Measurement result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarListaDeRegistosVazia() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        //Act
        boolean result = s1.listaDeRegistosEVazia();

        //Assert
        assertTrue(result);
    }

    @Test
    void testarListaDeRegistosNaoEVazia() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Calendar calendarioDaMedicao1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao1 = calendarioDaMedicao1.getTime();

        Measurement measurement1 = new Measurement(20, dataHoraDaMedicao1);
        s1.adicionarMedicaoALista(measurement1);

        //Act
        boolean result = s1.listaDeRegistosEVazia();

        //Assert
        assertFalse(result);
    }

    @Test
    void testarListaDeMedicoesDefinida() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Calendar calendarioDaMedicao1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao1 = calendarioDaMedicao1.getTime();

        Calendar calendarioDaMedicao2 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao2 = calendarioDaMedicao2.getTime();

        Measurement measurement1 = new Measurement(20, dataHoraDaMedicao1);
        Measurement measurement2 = new Measurement(25, dataHoraDaMedicao2);
        s1.adicionarMedicaoALista(measurement1);
        s1.adicionarMedicaoALista(measurement2);

        Measurement expectedResult = measurement2;

        //Act
        Measurement result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarGetMenorRegistoMes() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(20.1, data2);
        Measurement registo3 = new Measurement(21.7, data3);

        double expectedResult = 19;
        Date diaDoMes = new GregorianCalendar(2017, 8, 5).getTime();

        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesListaSemRegistos() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        double expectedResult = Double.NaN;
        Date diaDoMes = new GregorianCalendar(2017, GregorianCalendar.AUGUST, 15).getTime();

        // Act
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesDiferente() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Measurement registo1 = new Measurement(20.5, data1);
        Measurement registo2 = new Measurement(19, data2);
        Measurement registo3 = new Measurement(21.7, data3);

        double expectedResult = 19;
        Date diaDoMes = new GregorianCalendar(2017, 8, 5).getTime();

        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferente() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(22, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        Date diaDoMes = new GregorianCalendar(2017, 8, 5).getTime();

        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesTodosIguais() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Measurement registo1 = new Measurement(19, data1);
        Measurement registo2 = new Measurement(19, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        Date diaDoMes = new GregorianCalendar(2017, 8, 5).getTime();

        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetMenorRegistoMesOutroDiferenteUltimoDiferente() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Measurement registo1 = new Measurement(22, data1);
        Measurement registo2 = new Measurement(22, data2);
        Measurement registo3 = new Measurement(19, data3);

        double expectedResult = 19;
        Date diaDoMes = new GregorianCalendar(2017, 8, 5).getTime();

        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMes() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11, 5, 55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 1, 6, 25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11, 7, 30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 31).getTime();

        Measurement registo1 = new Measurement(28, data1);
        Measurement registo2 = new Measurement(25, data2);
        Measurement registo3 = new Measurement(26, data3);
        Measurement registo4 = new Measurement(27, data4);

        double expectedResult = 27;
        Date dataDoMes = new GregorianCalendar(2018, 2, 5).getTime();

        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        sensor1.adicionarMedicaoALista(registo4);

        //Act
        double result = sensor1.getMaiorRegistoDoMes(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMesListaSemRegistos() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Date dataDoMes = new GregorianCalendar(2018, 2, 15).getTime();
        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getMaiorRegistoDoMes(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMediaRegistoMes() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11, 5, 55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 11, 6, 25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11, 7, 30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 12, 6, 25).getTime();

        Measurement registo1 = new Measurement(21, data1);
        Measurement registo2 = new Measurement(25, data2);
        Measurement registo3 = new Measurement(26, data3);
        Measurement registo4 = new Measurement(27, data4);

        double expectedResult = 26;
        Date diaDoMes = new GregorianCalendar(2018, 2, 15).getTime();

        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        sensor1.adicionarMedicaoALista(registo4);
        //Act
        double result = sensor1.getRegistoMediaMes(diaDoMes);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMediaRegistoMes_SemRegistos() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);

        double expectedResult = Double.NaN;
        Date diaDoMes = new GregorianCalendar(2018, 2, 20).getTime();

        //Act
        double result = sensor1.getRegistoMediaMes(diaDoMes);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarSeUmSensorTemOTipoPedido() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        SensorType tipoPedido = new SensorType("Temperatura");
        //Act
        boolean resultado = sensor1.umTipoDeSensorEIgualAOutro(tipoPedido);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarSeUmSensorNaoTemOTipoPedido() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, sensorType, locS1);
        SensorType tipoPedido = new SensorType("Humidade");
        //Act
        boolean resultado = sensor1.umTipoDeSensorEIgualAOutro(tipoPedido);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetRegistosDoDia() {
        //Arrange

        Calendar calDomingo1 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataDomingo1 = calDomingo1.getTime();
        Calendar calDomingo2 = new GregorianCalendar(2018, 11, 2, 16, 20, 00);
        Date dataDomingo2 = calDomingo2.getTime();
        Calendar calSegunda = new GregorianCalendar(2018, 11, 3, 17, 20, 00);
        Date dataSegunda = calSegunda.getTime();
        Calendar calDomingo = new GregorianCalendar(2018, 11, 2);
        Date dataDomingo = calDomingo.getTime();

        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataDomingo1, sensorType, locS1);
        Measurement measurementDomingo1 = new Measurement(30, dataDomingo1);
        Measurement measurementDomingo2 = new Measurement(35, dataDomingo2);
        Measurement measurementSegunda = new Measurement(40, dataSegunda);

        sensor1.adicionarMedicaoALista(measurementDomingo1);
        sensor1.adicionarMedicaoALista(measurementDomingo2);
        sensor1.adicionarMedicaoALista(measurementSegunda);


        List<Measurement> expectedResult = new ArrayList<>();
        expectedResult.add(measurementDomingo1);
        expectedResult.add(measurementDomingo2);

        //act
        List<Measurement> result = sensor1.getRegistosDoDia(dataDomingo);

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetRegistosDoDiaComValorNaN() {
        //Arrange

        Calendar calDomingo1 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataDomingo1 = calDomingo1.getTime();
        Calendar calDomingo2 = new GregorianCalendar(2018, 11, 2, 16, 20, 00);
        Date dataDomingo2 = calDomingo2.getTime();
        Calendar calSegunda = new GregorianCalendar(2018, 11, 3, 17, 20, 00);
        Date dataSegunda = calSegunda.getTime();
        Calendar calDomingo = new GregorianCalendar(2018, 11, 2);
        Date dataDomingo = calDomingo.getTime();

        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataDomingo1, sensorType, locS1);
        Measurement measurementDomingo1 = new Measurement(Double.NaN, dataDomingo1);
        Measurement measurementDomingo2 = new Measurement(35.0, dataDomingo2);
        Measurement measurementSegunda = new Measurement(40.0, dataSegunda);

        sensor1.adicionarMedicaoALista(measurementDomingo1);
        sensor1.adicionarMedicaoALista(measurementDomingo2);
        sensor1.adicionarMedicaoALista(measurementSegunda);


        List<Measurement> expectedResult = new ArrayList<>();
        expectedResult.add(measurementDomingo2);

        //act
        List<Measurement> result = sensor1.getRegistosDoDia(dataDomingo);

        //assert
        assertEquals(expectedResult, result);

    }


    @Test
    public void testarVerificaSeDatasSaoIguais() {
        //Arrange

        Calendar cal1 = new GregorianCalendar(2018, 1, 2, 15, 20, 00);
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 16, 20, 00);
        Date data = cal2.getTime();
        boolean expectedResult = false;
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);
        boolean result = sensor1.verificaDiasIguais(cal1, cal2);

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarVerificaSeDatasSaoIguaisTrue() {
        //Arrange

        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 16, 20, 00);
        Date data = cal2.getTime();
        boolean expectedResult = true;
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Act
        boolean result = sensor1.verificaDiasIguais(cal1, cal2);

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetValorMinimoDoDia() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(40, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);


        double expectedResult = -2;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMinimoDoDiaComListaVazia() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(40, data1);


        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMinimoDoDiaComValorNaN() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);

        double expectedResult = -2;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarPrimeiroDiaSemana() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);
        Date expectedResult = data;

        Date result = sensor1.getPrimeiroDiaSemana(2018, 49);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemana() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(-4.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(-2.0, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7, 17, 20, 00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(-5.0, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8, 17, 20, 00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(-2.0, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0, -2.0, -5.0, -2.0));

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaDoubleNaN() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(-2, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7, 17, 20, 00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(-5, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8, 17, 20, 00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(-2, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, -2.0, -4.0, -2.0, -5.0, -2.0));

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaComCincoRegistos() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(-2, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0, -2.0));

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaComDataSemanaDiferente() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 10, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(-2, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.0, 30.0, -2.0, -4.0));

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemanaSemRegistos() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList());

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetMediaRegitosMinSemanal() {

        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(10.1, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7, 17, 20, 00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(11.2, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8, 17, 20, 00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(8.9, data7);


        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1, 11.2, 8.9)); //66.9/7=9.557

        double expectedResult = 66.9 / 7;

        //Act
        double result = sensor1.getMediaRegitosMinSemanal(2018, 49);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegitosMinSemanalDoubleNan() {

        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getMediaRegitosMinSemanal(2018, 49);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarValorMaximoDoDia() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(30, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(40, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(-2, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);


        double expectedResult = 40;

        //Act
        double result = sensor1.getMaximumValueOfDay(data);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetValorMaximoDoDiaComListaVazia() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getMaximumValueOfDay(data);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarGetValorMaximoDoDiaComValorNaN() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(-2, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(30, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);

        double expectedResult = 30;

        //Act
        double result = sensor1.getMaximumValueOfDay(data);
        //assert
        assertEquals(expectedResult, result, 0.001);

    }

    @Test
    public void testarListaMaximosSemana(){
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(20.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(20.0, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7,17,20,00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(45.0, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8,17,20,00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(20.0, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(20.0,30.0,20.0,40.0,20.0,45.0,20.0));

        //Act
        List <Double> result = sensor1.valoresMaximosSemana(2018,49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaDoubleNaN(){
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(20.0, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7,17,20,00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(45.0, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8,17,20,00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(20.0, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(30.0,20.0,40.0,20.0,45.0,20.0));

        //Act
        List <Double> result = sensor1.valoresMaximosSemana(2018,49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaComCincoRegistos(){
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(30.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(20.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(20.0, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(30.0,30.0,20.0,40.0,20.0));

        //Act
        List <Double> result = sensor1.valoresMaximosSemana(2018,49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaComDataSemanaDiferente(){
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(45.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(25.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(40.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 10, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(20.0, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList(45.0,30.0,25.0,40.0));

        //Act
        List <Double> result = sensor1.valoresMaximosSemana(2018,49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMaximosSemanaSemRegistos(){
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        List <Double> expectedResult = new ArrayList<>(Arrays.asList());

        //Act
        List <Double> result = sensor1.valoresMaximosSemana(2018,49);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanal(){

        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(10.1, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7,17,20,00);
        Date data6 = cal6.getTime();
        Measurement measurement6 = new Measurement(11.2, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8,17,20,00);
        Date data7 = cal7.getTime();
        Measurement measurement7 = new Measurement(8.9, data7);


        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);
        sensor1.adicionarMedicaoALista(measurement6);
        sensor1.adicionarMedicaoALista(measurement7);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1, 11.2, 8.9)); //66.9/7=9.557

        double expectedResult=66.9/7;

        //Act
        double result = sensor1.getMediaRegistosMaxSemanal(2018,49);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanalDoubleNan(){

        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        double expectedResult= Double.NaN;

        //Act
        double result = sensor1.getMediaRegistosMaxSemanal(2018,49);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testarGetMediaRegistosMaxSemanalComCincoDiasDeRegisto(){

        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2,15,20,00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2,01,00,01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3,23,59,59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4,17,20,00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5,17,20,00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6,17,20,00);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(10.1, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        //   List <Double> registoMinSemana = new ArrayList<>(Arrays.asList(10.0, 9.5, 7.5, 9.7, 10.1)); //46.8/5=9.36

        double expectedResult=46.8/5;

        //Act
        double result = sensor1.getMediaRegistosMaxSemanal(2018,49);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageTest() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date data = cal.getTime();
        SensorType sensorType = new SensorType("Temperatura");
        Location locS1 = new Location(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, sensorType, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 8, 00, 01);
        Date data1 = cal1.getTime();
        Measurement measurement1 = new Measurement(10.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 2, 15, 59, 59);
        Date data2 = cal2.getTime();
        Measurement measurement2 = new Measurement(9.5, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 2, 17, 15, 00);
        Date data3 = cal3.getTime();
        Measurement measurement3 = new Measurement(7.5, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 2, 17, 20, 00);
        Date data4 = cal4.getTime();
        Measurement measurement4 = new Measurement(9.7, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 2, 17, 20, 10);
        Date data5 = cal5.getTime();
        Measurement measurement5 = new Measurement(10.1, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(measurement1);
        sensor1.adicionarMedicaoALista(measurement2);
        sensor1.adicionarMedicaoALista(measurement3);
        sensor1.adicionarMedicaoALista(measurement4);
        sensor1.adicionarMedicaoALista(measurement5);

        double expectedResult = 9.36;

        Calendar searchCal = new GregorianCalendar(2018, 11, 2, 17, 20, 10);
        Date searchDate = searchCal.getTime();

        //Act
        double result = sensor1.getDailyAverage(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }
}