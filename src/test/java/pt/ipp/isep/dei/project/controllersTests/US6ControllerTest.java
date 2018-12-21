package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US6Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class US6ControllerTest {

    @Test
    public void testarNomeAreaGeograficaPorIndicePrimeiro () {

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Espinho";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

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
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Espinho";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local2);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

        String nomeAG3 = "Ancora";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Cidade");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local3);
        GeographicalArea ag3 = new GeographicalArea(nomeAG3, tipo3, local3, area3);

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
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

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
    public void testarNumeroElementosDaListaAreaGeografica () {

        // Arrange
        String nomeAG1 = "Espinho";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Ancora";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicalArea ag2 = new GeographicalArea(nomeAG2, tipo2, local2, area2);

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

}