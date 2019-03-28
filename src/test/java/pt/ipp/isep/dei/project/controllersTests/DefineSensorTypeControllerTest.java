package pt.ipp.isep.dei.project.controllersTests;

import org.junit.Test;
import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefineSensorTypeControllerTest {

    @Test
    public void criarEAdicionarTipoDeSensor() {
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        DefineSensorTypeController controller = new DefineSensorTypeController(lista);
        //Act
        boolean resultado = controller.createAndAddSensorType(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void criarEAdicionarTipoDeSensorTipoRepetido() {
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        String outroTipo = "Humidade";
        DefineSensorTypeController controller = new DefineSensorTypeController(lista);
        controller.createAndAddSensorType(novoTipo);
        //Act
        boolean resultado = controller.createAndAddSensorType(outroTipo);
        //Assert
        assertFalse(resultado);
    }
}