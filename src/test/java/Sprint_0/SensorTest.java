package Sprint_0;

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
    void testarEqualsSame () {
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
    void testarHashCode () {
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
    void testarEqualsFalse () {
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
    void testarEqualsNomeSensorDiferente () {
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
    void testarDistanciaLinear(){
        //Arrange
        Calendar calendario = new GregorianCalendar(2018, 11, 27);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(40, 10, 20);
        Sensor s1= new Sensor("s1",dataFuncionamento, tipoSensor, locS1);

        Localizacao locS2 = new Localizacao(30, 15, 10);
        Sensor s2= new Sensor("s2",dataFuncionamento, tipoSensor, locS2);

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
    void testarGetMenorRegistoMes () {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 5, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 00, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 16, 6, 30, 0).getTime();

        Medicao registo1 = new Medicao (22.5, data1);
        Medicao registo2 = new Medicao (20.1, data2);
        Medicao registo3 = new Medicao (21.7, data3);

        double expectedResult = 20.1;
        Date inicioDoMes = new GregorianCalendar(2017,8,1).getTime();
        Date finalDoMes = new GregorianCalendar(2017,8,31).getTime();
        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);

        double result = sensor1.getMenorRegistoDoMes(inicioDoMes,finalDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testarGetOutroMenorRegistoMes () {

        // Arrange
        Calendar calendario = new GregorianCalendar(2017, 8, 15);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Date data1 = new GregorianCalendar(2017, 8, 15, 6, 30, 0).getTime();
        Date data2 = new GregorianCalendar(2017, 8, 15, 6, 30, 0).getTime();
        Date data3 = new GregorianCalendar(2017, 8, 15, 6, 30, 0).getTime();

        Medicao registo1 = new Medicao (20.1, data1);
        Medicao registo2 = new Medicao (20.1, data2);
        Medicao registo3 = new Medicao (20.1, data3);

        double expectedResult = 20.1;
        Date inicioDoMes = new GregorianCalendar(2017,8,1).getTime();
        Date finalDoMes = new GregorianCalendar(2017,8,31).getTime();
        // Act
        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);

        double result = sensor1.getMenorRegistoDoMes(inicioDoMes,finalDoMes);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void testaGetMaiorRegistoMes(){
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2,15,20,00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11,5,55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 11,6,25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11,7,30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 12,6,25).getTime();

        Medicao registo1 = new Medicao(21,data1);
        Medicao registo2 = new Medicao(25,data2);
        Medicao registo3 = new Medicao(26,data3);
        Medicao registo4 = new Medicao(27,data4);

        double expectedResult= 27;
        Date dataInicioMes = new GregorianCalendar(2018,2,1).getTime();
        Date dataFinalMes = new GregorianCalendar(2018,2,28).getTime();


        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        sensor1.adicionarMedicaoALista(registo4);
        //Act
        double result=sensor1.getMaiorRegistoDoMes(dataInicioMes, dataFinalMes);
        //Assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testaGetOutroMaiorRegistoMes(){
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2,15,20,00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Date data1 = new GregorianCalendar(2018, 2, 11,6,25).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 11,6,25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11,6,25).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 11,6,25).getTime();

        Medicao registo1 = new Medicao(27,data1);
        Medicao registo2 = new Medicao(27,data2);
        Medicao registo3 = new Medicao(27,data3);
        Medicao registo4 = new Medicao(27,data4);

        double expectedResult= 27;
        Date dataInicioMes = new GregorianCalendar(2018,2,1).getTime();
        Date dataFinalMes = new GregorianCalendar(2018,2,28).getTime();


        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        sensor1.adicionarMedicaoALista(registo4);
        //Act
        double result=sensor1.getMaiorRegistoDoMes(dataInicioMes, dataFinalMes);
        //Assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testaGetMediaRegistoMes(){
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2,15,20,00);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor sensor1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Date data1 = new GregorianCalendar(2018, 4, 11,5,55).getTime();
        Date data2 = new GregorianCalendar(2018, 2, 11,6,25).getTime();
        Date data3 = new GregorianCalendar(2018, 2, 11,7,30).getTime();
        Date data4 = new GregorianCalendar(2018, 2, 12,6,25).getTime();

        Medicao registo1 = new Medicao(21,data1);
        Medicao registo2 = new Medicao(25,data2);
        Medicao registo3 = new Medicao(26,data3);
        Medicao registo4 = new Medicao(27,data4);

        double expectedResult= 26;
        Date dataInicioMes = new GregorianCalendar(2018,2,1).getTime();
        Date dataFinalMes = new GregorianCalendar(2018,2,28).getTime();


        sensor1.adicionarMedicaoALista(registo1);
        sensor1.adicionarMedicaoALista(registo2);
        sensor1.adicionarMedicaoALista(registo3);
        sensor1.adicionarMedicaoALista(registo4);
        //Act
        double result=sensor1.getRegistoMediaMes(dataInicioMes, dataFinalMes);
        //Assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    public void testarSeUmSensorTemOTipoPedido(){
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2,15,20,00);
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
    public void testarSeUmSensorNaoTemOTipoPedido(){
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2,15,20,00);
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

}