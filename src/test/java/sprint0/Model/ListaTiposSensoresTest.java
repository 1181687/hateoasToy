package sprint0.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListaTiposSensoresTest {

    @Test
    void testarAdicionarTipoDeSensorAListaPositivo(){
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
    void testarAdicionarTipoDeSensorAListaNegativo(){
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
    void testarNovoTipoSensor(){
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
    void testarContrutorNaoVazio(){
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
}