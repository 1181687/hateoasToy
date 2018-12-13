package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US5Controller;
import sprint0.Model.ListaTiposSensores;

import static org.junit.jupiter.api.Assertions.*;

class US5ControllerTest {

    @Test
    void criarEAdicionarTipoDeSensor() {
        ListaTiposSensores lista = new ListaTiposSensores();
        String novoTipo = "Humidade";
        US5Controller controller = new US5Controller(lista);
        //Act
        boolean resultado = controller.criarEAdicionarTipoDeSensor(novoTipo);
        //Assert
        assertTrue(resultado);
    }

    @Test
    void criarEAdicionarTipoDeSensorTipoRepetido() {
        ListaTiposSensores lista = new ListaTiposSensores();
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