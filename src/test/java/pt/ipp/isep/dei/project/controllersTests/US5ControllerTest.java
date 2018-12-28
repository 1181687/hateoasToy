package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US5Controller;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US5ControllerTest {

    @Test
    void criarEAdicionarTipoDeSensor() {
        SensorTypeList lista = new SensorTypeList();
        String novoTipo = "Humidade";
        US5Controller controller = new US5Controller(lista);
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
        US5Controller controller = new US5Controller(lista);
        controller.criarEAdicionarTipoDeSensor(novoTipo);
        //Act
        boolean resultado = controller.criarEAdicionarTipoDeSensor(outroTipo);
        //Assert
        assertFalse(resultado);
    }
}