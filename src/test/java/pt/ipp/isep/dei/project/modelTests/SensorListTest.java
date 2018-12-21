package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.model.TipoSensor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorListTest {

    @Test
    void addSensorToTheListOfSensorsInTheGeographicalArea() {
        //Arrange
        SensorList newList = new SensorList();
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        //Act
        boolean result = newList.addSensorToTheListOfSensorsInTheGeographicalArea(s0);
        //Assert
        assertTrue(result);
    }

    @Test
    void getmSensorList() {
        //Arrange
        SensorList newList = new SensorList();

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        newList.addSensorToTheListOfSensorsInTheGeographicalArea(s0);
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(s0);
        //Act
        List<Sensor> result = newList.getmSensorList();
        //Assert
        assertEquals(result, expectedResult);
    }


}