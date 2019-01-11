package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefineSensorTypeControllerTest {

    @Test
    void criarEAdicionarTipoDeSensor() {
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        DefineSensorTypeController controller = new DefineSensorTypeController(lista);
        //Act
        boolean resultado = controller.criarEAdicionarTipoDeSensor(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    void criarEAdicionarTipoDeSensorTipoRepetido() {
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        String outroTipo = "Humidade";
        DefineSensorTypeController controller = new DefineSensorTypeController(lista);
        controller.criarEAdicionarTipoDeSensor(novoTipo);
        //Act
        boolean resultado = controller.criarEAdicionarTipoDeSensor(outroTipo);
        //Assert
        assertFalse(resultado);
    }
}