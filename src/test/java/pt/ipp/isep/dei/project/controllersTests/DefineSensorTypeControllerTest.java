package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DefineSensorTypeControllerTest {
    @Mock
    private SensorTypeService sensorTypeService;
    private DefineSensorTypeController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        controller = new DefineSensorTypeController(sensorTypeService);
    }


    @Test
    public void criarEAdicionarTipoDeSensor() {
        String novoTipo = "Humidade";
        SensorTypeId sensorTypeId = new SensorTypeId(novoTipo);
        //Act
        when(sensorTypeService.addType(sensorTypeId)).thenReturn(true);
        boolean resultado = controller.createAndAddSensorType(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void criarEAdicionarTipoDeSensorTipoRepetido() {
        String newType = "Temperature";
        String sameType = "Temperature";
        SensorTypeId sensorTypeNewType = new SensorTypeId(newType);
        SensorTypeId sensorTypeSameType = new SensorTypeId(sameType);
        when(sensorTypeService.addType(sensorTypeNewType)).thenReturn(true);
        controller.createAndAddSensorType(newType);
        //Act
        when(sensorTypeService.addType(sensorTypeSameType)).thenReturn(false);
        boolean resultado = controller.createAndAddSensorType(sameType);
        //Assert
        assertFalse(resultado);
    }
}