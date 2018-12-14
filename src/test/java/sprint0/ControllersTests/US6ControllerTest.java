package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US6Controller;
import sprint0.Model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US6ControllerTest {

    @Test
    public void testarAdicaoDeDoisSensoresAAreaGeografica () {
        //Arrange

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        Calendar cal = new GregorianCalendar();
        Date data = cal.getTime();
        TipoSensor tipo = new TipoSensor("Hum");
        Localizacao local = new Localizacao(45, 45, 45);
        Sensor s1 = new Sensor("fe",data,tipo,local);
        Sensor s2 = new Sensor ("fe",data,tipo,local);
        ag1.getmListaSensor().add(s1);
        boolean resultado = ag1.adicionarSensorAListaDeSensores(s2);

        assertFalse(resultado);
    }

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
        TipoSensor expectedResult = tipoSensor1;

        // Act
        TipoSensor resultado = ctrl6.getTipoSensorPorPosicao(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarNomeAreaGeograficaPorIndice () {

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
        String expectedResult = "Espinho";

        // Act
        String resultado = ctrl6.getNomeAreaGeograficaPorIndice(posicao);

        // Assert
        assertEquals(expectedResult, resultado);
    }

}