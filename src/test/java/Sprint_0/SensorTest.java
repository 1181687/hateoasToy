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
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1= new Sensor("s1",dataFuncionamento, tipoSensor, locS1);

        Localizacao locS2 = new Localizacao(300, 425, 100);
        Sensor s2= new Sensor("s2",dataFuncionamento, tipoSensor, locS2);

        double expectedResult = 194.2395;

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
        Date dataHoraDaMedicao2 = calendarioDaMedicao1.getTime();

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
}