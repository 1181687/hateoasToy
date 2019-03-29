package pt.ipp.isep.dei.project.modelTests;

import org.junit.Test;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SensorTypeListTest {

    @Test
    public void testarContrutorNaoVazio() {
        //Arrange
        List<SensorType> lista = new ArrayList<>();
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        SensorTypeList novaLista = new SensorTypeList(lista);
        //Act
        boolean resultado = !(novaLista.getListOfSensorTypes().isEmpty());
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarGetListaTipoSensores() {
        //Arrange
        List<SensorType> lista = new ArrayList<>();
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        SensorTypeList novaLista = new SensorTypeList(lista);
        List<SensorType> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(tipo1);
        resultadoEsperado.add(tipo2);
        //Act
        List<SensorType> resultado = novaLista.getListOfSensorTypes();
        //Assert
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testarAdicionarTipoDeSensorAListaPositivo() {
        //Arrange
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        SensorType novoSensorType = new SensorType(novoTipo);
        //Act
        boolean resultado = lista.addSensorType(novoSensorType);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarAdicionarTipoDeSensorAListaNegativo() {
        //Arrange
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        SensorType novoSensorType = new SensorType(novoTipo);
        lista.addSensorType(novoSensorType);
        //Act
        boolean resultado = lista.addSensorType(novoSensorType);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarNovoTipoSensorMesmoTipo() {
        //Arrange
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        SensorType novoSensorType = new SensorType(novoTipo);
        lista.addSensorType(novoSensorType);
        SensorType outroSensorType = lista.newSensorType("Humidade");
        //Act
        boolean resultado = lista.addSensorType(outroSensorType);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarNovoTipoSensorOutroTipoTrue() {
        //Arrange
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        SensorType novoSensorType = new SensorType(novoTipo);
        lista.addSensorType(novoSensorType);
        SensorType outroSensorType = lista.newSensorType("Temperatura");
        //Act
        boolean resultado = lista.addSensorType(outroSensorType);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void testarGetTipoSensorPorPosicao() {
        //Arrange
        List<SensorType> lista = new ArrayList<>();
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");
        lista.add(tipo1);
        lista.add(tipo2);
        SensorTypeList novaLista = new SensorTypeList(lista);
        int posicao = 0;
        //Act
        SensorType resultado = novaLista.getSensorTypeByPosition(posicao);
        //Assert
        assertEquals(tipo1, resultado);
    }

    @Test
    public void testarGetTipoSensorPorPosicaoComUmTipoApenas() {
        //Arrange
        List<SensorType> lista = new ArrayList<>();
        SensorType tipo1 = new SensorType("Humidade");
        lista.add(tipo1);
        SensorTypeList novaLista = new SensorTypeList(lista);
        int posicao = 0;
        //Act
        SensorType resultado = novaLista.getSensorTypeByPosition(posicao);
        //Assert
        assertEquals(tipo1, resultado);
    }

    @Test
    public void testarGetTipoSensorPorPosicaoMaxima() {
        //Arrange
        List<SensorType> lista = new ArrayList<>();
        SensorType tipo1 = new SensorType("Humidade");
        SensorType tipo2 = new SensorType("Temperatura");
        SensorType tipo3 = new SensorType("Vento");

        lista.add(tipo1);
        lista.add(tipo2);
        lista.add(tipo3);
        SensorTypeList novaLista = new SensorTypeList(lista);
        int posicao = 2;
        //Act
        SensorType resultado = novaLista.getSensorTypeByPosition(posicao);
        //Assert
        assertEquals(tipo3, resultado);
    }

    @Test
    public void testDisplayListOfSensorsType() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Type of sensor
        SensorType sensorType = new SensorType("Temperatura");

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        String expectedResult = "1 - sensor Type: Temperatura\n";

        // Act
        String result = listSensorsType.getSensorTypeListToString();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfTheListOfSensorTypeIsEmptyPositive() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        // Act
        boolean result = listSensorsType.isEmpty();
        // Assert
        assertTrue(result);
    }
}