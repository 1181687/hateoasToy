package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.*;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AreaGeograficaTest {

    @Test
    public void testarEqualsSame() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);
        boolean expectedResult = true;
        //act
        boolean result = ag1.equals(ag1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsTrue() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);
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
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo2 = new TipoAreaGeo("Aldeia");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo1, local, area);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo2, local, area);
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
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
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
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, 10.6109, 50);
        Localizacao local2 = new Localizacao(32.6333, 16.9, 20);
        RetanguloArea area1 = new RetanguloArea(10, 10,local1);
        RetanguloArea area2 = new RetanguloArea(10, 10,local2);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);
        double expectedResult = 1099043.7203;

        // act
        double resultado = ag1.distanciaLinearDuasAreas(ag2);

        //assert
        assertEquals(expectedResult, resultado, 0.0001);
    }

    @Test
    public void testargetListaUltimosRegistosPorTipoSensorCasoPositivo() {
        //Arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(20, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(25, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(20, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        List<Medicao> expectedResult = new ArrayList<>();

        expectedResult.add(medicao02);
        expectedResult.add(medicao12);

        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        List<Medicao> result = ag1.getListaDeUltimosRegistosPorTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testargetListaUltimosRegistosPorTipoSensorCasoUltimoRegistoDoubleNan() {
        //Arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(20, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(Double.NaN, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(20, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        List<Medicao> expectedResult = new ArrayList<>();

        expectedResult.add(medicao01);
        expectedResult.add(medicao12);

        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        List<Medicao> result = ag1.getListaDeUltimosRegistosPorTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensores() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(25, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        double expectedResult = 25;

        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        double result = ag1.getUltimoRegistoDeUmTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresMutacao() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Temperatura");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(24, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(27, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        double expectedResult = 25;

        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        double result = ag1.getUltimoRegistoDeUmTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresTipoDiferente() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(25, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        double expectedResult = Double.NaN;

        TipoSensor tipoResultado = new TipoSensor("Pluviosidade");

        //Act
        double result = ag1.getUltimoRegistoDeUmTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        double expectedResult = Double.NaN;

        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        double result = ag1.getUltimoRegistoDeUmTipoDeSensor(tipoResultado);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarHashCode() {
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

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
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(45, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

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
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(45, -20, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

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
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        // Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(46.1496, -13.6109, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

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
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        // Instanciar S0
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(43, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

        //Instanciar S1
        Calendar calendario1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(43, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);

        //Instanciar S2
        Calendar calendario2 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);

        //Instanciar S3
        Calendar calendario3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento3 = calendario3.getTime();
        TipoSensor tipoSensor3 = new TipoSensor("Temperatura");
        Localizacao locS3 = new Localizacao(-4, -10, 65);
        Sensor s3 = new Sensor("A123", dataFuncionamento3, tipoSensor3, locS3);


        List<Sensor> lista = new ArrayList<>();
        lista.add(s0);
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);

        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s0);
        expectedResult.add(s1);

        TipoSensor tipoSensorPedido = new TipoSensor("Temperatura");

        //Act
        List<Sensor> result = ag1.listarSensoresContidosNaAGPorTipo(tipoSensorPedido, lista);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresContidosNaAGPorTipoQueNaoExiste() {
        //Arrange

        // Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        // Instanciar S0
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(50, -10, 65);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

        //Instanciar S1
        Calendar calendario1 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(50, -10, 65);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);

        //Instanciar S2
        Calendar calendario2 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Humidade");
        Localizacao locS2 = new Localizacao(50, -10, 65);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);

        //Instanciar S3
        Calendar calendario3 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento3 = calendario3.getTime();
        TipoSensor tipoSensor3 = new TipoSensor("Temperatura");
        Localizacao locS3 = new Localizacao(-4, -10, 65);
        Sensor s3 = new Sensor("A123", dataFuncionamento3, tipoSensor3, locS3);


        List<Sensor> lista = new ArrayList<>();
        lista.add(s0);
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);

        List<Sensor> expectedResult = new ArrayList<>();

        TipoSensor tipoSensorPedido = new TipoSensor("Pressão atmosférica");

        //Act
        List<Sensor> result = ag1.listarSensoresContidosNaAGPorTipo(tipoSensorPedido, lista);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Temperatura");
        Localizacao locS2 = new Localizacao(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2015, 11, 2,5,22,40);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2015, 11, 3,19,36,55);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(25, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2016, 1, 2,8,59,13);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2016, 5, 4,2,05,27);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(2016, 0, 1,18,24,10);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(2016, 3, 30,20,17,50);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

       List<Sensor> listaDeSensores = new ArrayList<>();
       listaDeSensores.add(s0);
       listaDeSensores.add(s1);
       listaDeSensores.add(s2);

       List<Sensor> expectedResult = new ArrayList<>();
       expectedResult.add(s1);
       expectedResult.add(s2);

        Date dataInicial = new GregorianCalendar(2016, 0, 1, 17, 24, 00).getTime();
        Date dataFinal = new GregorianCalendar(2016, 3, 30, 17, 24, 00).getTime();


        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        List<Sensor> result = ag1.listarSensoresDeUmTipoNaAGNumPeriodo(tipoResultado,listaDeSensores,dataInicial,dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listarSensoresDeUmTipoNaAGNumPeriodo_SensoresSemLeiturasNoPeriodo() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Localizacao locS0 = new Localizacao(40, -5, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag1.adicionarSensorAListaDeSensores(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(41, -6, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag1.adicionarSensorAListaDeSensores(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        TipoSensor tipoSensor2 = new TipoSensor("Temperatura");
        Localizacao locS2 = new Localizacao(42, -7, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, tipoSensor2, locS2);
        ag1.adicionarSensorAListaDeSensores(s2);

        //Instanciar Medicao
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2015, 11, 2);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2015, 11, 3);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(25, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2015, 1, 2);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2015, 5, 4);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(2015, 0, 1);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(2015, 3, 30);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Medicao medicao21 = new Medicao(20, dataHoraDaMedicao21);
        Medicao medicao22 = new Medicao(25, dataHoraDaMedicao22);

        s2.adicionarMedicaoALista(medicao21);
        s2.adicionarMedicaoALista(medicao22);

        List<Sensor> listaDeSensores = new ArrayList<>();
        listaDeSensores.add(s0);
        listaDeSensores.add(s1);
        listaDeSensores.add(s2);

        List<Sensor> expectedResult = new ArrayList<>();

        Date dataInicial = new GregorianCalendar(2016, 0, 1, 17, 24, 00).getTime();
        Date dataFinal = new GregorianCalendar(2016, 3, 30, 17, 24, 00).getTime();


        TipoSensor tipoResultado = new TipoSensor("Temperatura");

        //Act
        List<Sensor> result = ag1.listarSensoresDeUmTipoNaAGNumPeriodo(tipoResultado,listaDeSensores,dataInicial,dataFinal);

        //Assert
        assertEquals(expectedResult, result);
    }
}