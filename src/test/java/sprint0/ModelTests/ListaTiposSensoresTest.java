package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.ListaTiposSensores;
import sprint0.Model.TipoSensor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListaTiposSensoresTest {

    @Test
    void testarContrutorNaoVazio() {
        //Arrange
        List<TipoSensor> lista = new ArrayList<>();
        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        ListaTiposSensores novaLista = new ListaTiposSensores(lista);
        //Act
        boolean resultado = !(novaLista.getmListaTiposSensores().isEmpty());
        //Assert
        assertTrue(resultado);
    }

    @Test
    void testarGetListaTipoSensores() {
        //Arrange
        List<TipoSensor> lista = new ArrayList<>();
        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        ListaTiposSensores novaLista = new ListaTiposSensores(lista);
        List<TipoSensor> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(tipo1);
        resultadoEsperado.add(tipo2);
        //Act
        List<TipoSensor> resultado = novaLista.getmListaTiposSensores();
        //Assert
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void testarAdicionarTipoDeSensorAListaPositivo() {
        //Arrange
        ListaTiposSensores lista = new ListaTiposSensores();
        String novoTipo = "Humidade";
        TipoSensor novoTipoSensor = new TipoSensor(novoTipo);
        //Act
        boolean resultado = lista.adicionarTipoSensorALista(novoTipoSensor);
        //Assert
        assertTrue(resultado);
    }

    @Test
    void testarAdicionarTipoDeSensorAListaNegativo() {
        //Arrange
        ListaTiposSensores lista = new ListaTiposSensores();
        String novoTipo = "Humidade";
        TipoSensor novoTipoSensor = new TipoSensor(novoTipo);
        lista.adicionarTipoSensorALista(novoTipoSensor);
        //Act
        boolean resultado = lista.adicionarTipoSensorALista(novoTipoSensor);
        //Assert
        assertFalse(resultado);
    }

    @Test
    void testarNovoTipoSensor() {
        //Arrange
        ListaTiposSensores lista = new ListaTiposSensores();
        String novoTipo = "Humidade";
        TipoSensor novoTipoSensor = new TipoSensor(novoTipo);
        lista.adicionarTipoSensorALista(novoTipoSensor);
        TipoSensor outroTipoSensor = lista.novoTipoSensor("Temperatura");
        //Act
        boolean resultado = lista.adicionarTipoSensorALista(outroTipoSensor);
        //Assert
        assertTrue(resultado);
    }

    @Test
    void testarGetTipoSensorPorPosicao() {
        //Arrange
        List<TipoSensor> lista = new ArrayList<>();
        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        ListaTiposSensores novaLista = new ListaTiposSensores(lista);
        int posicao = 0;
        //Act
        TipoSensor resultado = novaLista.getTipoSensorPorPosicao(posicao);
        //Assert
        assertEquals(tipo1, resultado);
    }

    @Test
    void testarGetTipoSensorPorPosicaoComUmTipoApenas() {
        //Arrange
        List<TipoSensor> lista = new ArrayList<>();
        TipoSensor tipo1 = new TipoSensor("Humidade");
        lista.add(tipo1);
        ListaTiposSensores novaLista = new ListaTiposSensores(lista);
        int posicao = 0;
        //Act
        TipoSensor resultado = novaLista.getTipoSensorPorPosicao(posicao);
        //Assert
        assertEquals(tipo1, resultado);
    }

    @Test
    void testarGetTipoSensorPorPosicaoMaxima() {
        //Arrange
        List<TipoSensor> lista = new ArrayList<>();
        TipoSensor tipo1 = new TipoSensor("Humidade");
        TipoSensor tipo2 = new TipoSensor("Temperatura");
        TipoSensor tipo3 = new TipoSensor("Vento");

        lista.add(tipo1);
        lista.add(tipo2);
        lista.add(tipo3);
        ListaTiposSensores novaLista = new ListaTiposSensores(lista);
        int posicao = 2;
        //Act
        TipoSensor resultado = novaLista.getTipoSensorPorPosicao(posicao);
        //Assert
        assertEquals(tipo3, resultado);
    }
}