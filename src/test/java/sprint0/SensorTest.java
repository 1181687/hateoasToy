package sprint0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;


class SensorTest {

    @Test
    void testaConstrutor() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Sensor s2 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        TipoSensor expectedResult = tipoSensor;
        //Act
        TipoSensor result = s1.getmTipoSensor();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsSame() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        boolean expectedResult = false;
        //Act
        boolean result = s1.equals(tipoSensor);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsNomeSensorDiferente() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Sensor s2 = new Sensor("A200", dataFuncionamento, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Localizacao expectedResult = locS1;
        //Act
        Localizacao result = s1.getmLocalizacao();
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarDistanciaLinear() {
        //Arrange
        Calendar calendario = new GregorianCalendar(2018, 11, 27);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(40, 10, 20);
        Sensor s1 = new Sensor("s1", dataFuncionamento, tipoSensor, locS1);

        Localizacao locS2 = new Localizacao(30, 15, 10);
        Sensor s2 = new Sensor("s2", dataFuncionamento, tipoSensor, locS2);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Medicao medicao = new Medicao(20, dataFuncionamento);
        s1.adicionarMedicaoALista(medicao);

        Medicao expectedResult = medicao;

        //Act
        Medicao result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarListaDeMedicoesVazia() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Medicao expectedResult = null;

        //Act
        Medicao result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarListaDeRegistosVazia() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Calendar calendarioDaMedicao1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao1 = calendarioDaMedicao1.getTime();

        Medicao medicao1 = new Medicao(20, dataHoraDaMedicao1);
        s1.adicionarMedicaoALista(medicao1);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Calendar calendarioDaMedicao1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao1 = calendarioDaMedicao1.getTime();

        Calendar calendarioDaMedicao2 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao2 = calendarioDaMedicao2.getTime();

        Medicao medicao1 = new Medicao(20, dataHoraDaMedicao1);
        Medicao medicao2 = new Medicao(25, dataHoraDaMedicao2);
        s1.adicionarMedicaoALista(medicao1);
        s1.adicionarMedicaoALista(medicao2);

        Medicao expectedResult = medicao2;

        //Act
        Medicao result = s1.getUltimoRegisto();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarGetMenorRegistoMes() {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 02, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Medicao registo1 = new Medicao(22.5, data1);
        Medicao registo2 = new Medicao(20.1, data2);
        Medicao registo3 = new Medicao(21.7, data3);

        double expectedResult = 20.1;
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Date data1 = new GregorianCalendar(2017, GregorianCalendar.AUGUST, 1, 0, 0, 1).getTime();
        Date data2 = new GregorianCalendar(2017, GregorianCalendar.AUGUST, 13, 6, 30, 0).getTime();
        Date data3 = new GregorianCalendar(2017, GregorianCalendar.AUGUST, 30, 5, 0, 0).getTime();

        double expectedResult = Double.NaN;
        Date diaDoMes = new GregorianCalendar(2017, GregorianCalendar.AUGUST, 15).getTime();

        // Act
        double result = sensor1.getMenorRegistoDoMes(diaDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMes() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11, 5, 55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 1, 6, 25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11, 7, 30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 31).getTime();

        Medicao registo1 = new Medicao(28, data1);
        Medicao registo2 = new Medicao(25, data2);
        Medicao registo3 = new Medicao(26, data3);
        Medicao registo4 = new Medicao(27, data4);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11, 5, 55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 11, 6, 25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11, 7, 30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 12, 6, 25).getTime();

        Medicao registo1 = new Medicao(21, data1);
        Medicao registo2 = new Medicao(25, data2);
        Medicao registo3 = new Medicao(26, data3);
        Medicao registo4 = new Medicao(27, data4);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        TipoSensor tipoPedido = new TipoSensor("Temperatura");
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        TipoSensor tipoPedido = new TipoSensor("Humidade");
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

        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataDomingo1, tipoSensor, locS1);
        Medicao medicaoDomingo1 = new Medicao(30, dataDomingo1);
        Medicao medicaoDomingo2 = new Medicao(35, dataDomingo2);
        Medicao medicaoSegunda = new Medicao(40, dataSegunda);

        sensor1.adicionarMedicaoALista(medicaoDomingo1);
        sensor1.adicionarMedicaoALista(medicaoDomingo2);
        sensor1.adicionarMedicaoALista(medicaoSegunda);


        List<Medicao> expectedResult = new ArrayList<>();
        expectedResult.add(medicaoDomingo1);
        expectedResult.add(medicaoDomingo2);

        //act
        List<Medicao> result = sensor1.getRegistosDoDia(dataDomingo);

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

        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataDomingo1, tipoSensor, locS1);
        Medicao medicaoDomingo1 = new Medicao(Double.NaN, dataDomingo1);
        Medicao medicaoDomingo2 = new Medicao(35.0, dataDomingo2);
        Medicao medicaoSegunda = new Medicao(40.0, dataSegunda);

        sensor1.adicionarMedicaoALista(medicaoDomingo1);
        sensor1.adicionarMedicaoALista(medicaoDomingo2);
        sensor1.adicionarMedicaoALista(medicaoSegunda);


        List<Medicao> expectedResult = new ArrayList<>();
        expectedResult.add(medicaoDomingo2);

        //act
        List<Medicao> result = sensor1.getRegistosDoDia(dataDomingo);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);
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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(40, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);


        double expectedResult = -2;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetValorMinimoDoDiaComListaVazia() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(40, data1);


        double expectedResult = Double.NaN;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetValorMinimoDoDiaComValorNaN() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 10, 2, 00, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 10, 2, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 10, 2, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2, data3);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);

        double expectedResult = -2;

        //Act
        double result = sensor1.getValorMinimoDoDia(data);
        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarPrimeiroDiaSemana() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 11, 2);
        Date data = cal.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);
        Date expectedResult = data;

        Date result = sensor1.getPrimeiroDiaSemana(2018, 49);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testarListaMinimosSemana() {
        //Arrange
        Calendar cal = new GregorianCalendar(2018, 10, 2, 15, 20, 00);
        Date data = cal.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30.0, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2.0, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Medicao medicao4 = new Medicao(-4.0, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Medicao medicao5 = new Medicao(-2.0, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7, 17, 20, 00);
        Date data6 = cal6.getTime();
        Medicao medicao6 = new Medicao(-5.0, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8, 17, 20, 00);
        Date data7 = cal7.getTime();
        Medicao medicao7 = new Medicao(-2.0, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);
        sensor1.adicionarMedicaoALista(medicao4);
        sensor1.adicionarMedicaoALista(medicao5);
        sensor1.adicionarMedicaoALista(medicao6);
        sensor1.adicionarMedicaoALista(medicao7);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(Double.NaN, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Medicao medicao4 = new Medicao(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Medicao medicao5 = new Medicao(-2, data5);

        //Registo 6
        Calendar cal6 = new GregorianCalendar(2018, 11, 7, 17, 20, 00);
        Date data6 = cal6.getTime();
        Medicao medicao6 = new Medicao(-5, data6);

        //Registo 7
        Calendar cal7 = new GregorianCalendar(2018, 11, 8, 17, 20, 00);
        Date data7 = cal7.getTime();
        Medicao medicao7 = new Medicao(-2, data7);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);
        sensor1.adicionarMedicaoALista(medicao4);
        sensor1.adicionarMedicaoALista(medicao5);
        sensor1.adicionarMedicaoALista(medicao6);
        sensor1.adicionarMedicaoALista(medicao7);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Medicao medicao4 = new Medicao(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 11, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Medicao medicao5 = new Medicao(-2, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);
        sensor1.adicionarMedicaoALista(medicao4);
        sensor1.adicionarMedicaoALista(medicao5);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        //Registo 1
        Calendar cal1 = new GregorianCalendar(2018, 11, 2, 01, 00, 01);
        Date data1 = cal1.getTime();
        Medicao medicao1 = new Medicao(0.0, data1);

        //Registo 2
        Calendar cal2 = new GregorianCalendar(2018, 11, 3, 23, 59, 59);
        Date data2 = cal2.getTime();
        Medicao medicao2 = new Medicao(30, data2);

        //Registo 3
        Calendar cal3 = new GregorianCalendar(2018, 11, 4, 17, 20, 00);
        Date data3 = cal3.getTime();
        Medicao medicao3 = new Medicao(-2, data3);

        //Registo 4
        Calendar cal4 = new GregorianCalendar(2018, 11, 5, 17, 20, 00);
        Date data4 = cal4.getTime();
        Medicao medicao4 = new Medicao(-4, data4);

        //Registo 5
        Calendar cal5 = new GregorianCalendar(2018, 10, 6, 17, 20, 00);
        Date data5 = cal5.getTime();
        Medicao medicao5 = new Medicao(-2, data5);

        //Adição das medições
        sensor1.adicionarMedicaoALista(medicao1);
        sensor1.adicionarMedicaoALista(medicao2);
        sensor1.adicionarMedicaoALista(medicao3);
        sensor1.adicionarMedicaoALista(medicao4);
        sensor1.adicionarMedicaoALista(medicao5);

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
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", data, tipoSensor, locS1);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList());

        //Act
        List<Double> result = sensor1.valoresMinimosSemana(2018, 49);

        //Assert
        assertEquals(expectedResult, result);
    }
}
