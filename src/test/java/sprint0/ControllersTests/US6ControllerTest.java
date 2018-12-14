package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US6Controller;
import sprint0.Model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class US6ControllerTest {

    @Test
    public void testarTipoSensorPorPosicao () {
        // Arrange
        String tipo1 = "Humidade";
        String tipo2 = "Temperatura";
        TipoSensor tipoSensor1 = new TipoSensor(tipo1);
        TipoSensor tipoSensor2 = new TipoSensor(tipo2);
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        listaTiposSensores.adicionarTipoSensorALista(tipoSensor1);
        listaTiposSensores.adicionarTipoSensorALista(tipoSensor2);
        ListaAG listaAreasGeograficas = new ListaAG();

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);

        // Act
        TipoSensor resultado = ctrl6.getTipoSensorPorPosicao(posicao);

        // Assert
        assertEquals(tipoSensor1, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndicePrimeiro () {

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Espinho";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local2);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Cidade");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local3);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);
        listaAreasGeograficas.adicionarAreaGeoALista(ag2);
        listaAreasGeograficas.adicionarAreaGeoALista(ag3);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Porto";

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceUltimo () {

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Espinho";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local2);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Cidade");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local3);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);
        listaAreasGeograficas.adicionarAreaGeoALista(ag2);
        listaAreasGeograficas.adicionarAreaGeoALista(ag3);

        int posicao = 2;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Ancora";

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndiceComApenasUmaArea () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Espinho";

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testeGetAreaGeograficaNaListaPorPosicaoPrimeira () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Ancora";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);
        listaAreasGeograficas.adicionarAreaGeoALista(ag2);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);

        // Act
        AreaGeografica resultado = ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Assert
        assertEquals(ag1, resultado);
    }

    @Test
    public void testeGetAreaGeograficaNaListaPorPosicaoUltima () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Ancora";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Porto";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Cidade");
        Localizacao local3 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area3 = new RetanguloArea(10, 10, local3);
        AreaGeografica ag3 = new AreaGeografica(nomeAG3, tipo3, local3, area3);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);
        listaAreasGeograficas.adicionarAreaGeoALista(ag2);
        listaAreasGeograficas.adicionarAreaGeoALista(ag3);

        int posicao = 2;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);

        // Act
        AreaGeografica resultado = ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Assert
        assertEquals(ag3, resultado);
    }

    @Test
    public void testeGetAreaGeograficaNaListaPorPosicaoApenasUma () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);

        // Act
        AreaGeografica resultado = ctrl6.getAreaGeograficaNaListaPorPosicao(posicao);

        // Assert
        assertEquals(ag1, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeografica () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Ancora";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);
        listaAreasGeograficas.adicionarAreaGeoALista(ag2);

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        int expectedResult = 2;

        // Act
        int resultado = ctrl6.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaAreaGeograficaSemElementos () {

        // Arrange
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        int expectedResult = 0;

        // Act
        int resultado = ctrl6.numeroElementosDaListaAreaGeografica();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensor () {

        // Arrange
        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaTiposSensores.adicionarTipoSensorALista(tipo1);
        listaTiposSensores.adicionarTipoSensorALista(tipo2);

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        int expectedResult = 2;

        // Act
        int resultado = ctrl6.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNumeroElementosDaListaTipoDeSensorSemElementos () {

        // Arrange
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        int expectedResult = 0;

        // Act
        int resultado = ctrl6.numeroElementosDaListaTipoDeSensor();

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeTipoSensorPorIndicePrimeiro () {

        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaTiposSensores.adicionarTipoSensorALista(tipo1);
        listaTiposSensores.adicionarTipoSensorALista(tipo2);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Humidade";

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeTipoSensorPorIndiceUltimo () {

        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaTiposSensores.adicionarTipoSensorALista(tipo1);
        listaTiposSensores.adicionarTipoSensorALista(tipo2);

        int posicao = 1;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Temperatura";

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarGetNomeTipoSensorPorIndiceApenasUm () {

        TipoSensor tipo1 = new TipoSensor("Humidade");

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaTiposSensores.adicionarTipoSensorALista(tipo1);

        int posicao = 0;
        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        String expectedResult = "Humidade";

        // Act
        String resultado = ctrl6.getNomeTipoSensorPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarAdicionarSensorAAreaGeografica () {

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

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);

        //Act
        boolean result = ctrl6.adicionarSensorAAreaGeografica(s0, ag1);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testarAdicionarSensorQueJaExisteAAreaGeografica () {

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

        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        ListaAG listaAreasGeograficas = new ListaAG();

        listaAreasGeograficas.adicionarAreaGeoALista(ag1);

        US6Controller ctrl6 = new US6Controller(listaTiposSensores, listaAreasGeograficas);
        ctrl6.adicionarSensorAAreaGeografica(s0, ag1);

        //Act
        boolean result = ctrl6.adicionarSensorAAreaGeografica(s0, ag1);
        //Assert
        assertFalse(result);
    }
}