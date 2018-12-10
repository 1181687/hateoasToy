package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US1Controller;
import sprint0.Model.ListaTiposAG;

import static org.junit.jupiter.api.Assertions.*;

public class US1ControllerTest {

    @Test
    public void testarCriacaoDeNovoTipoDeAG(){
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
        String novoTipo = "Cidade";
        US1Controller ctrl = new US1Controller(lista);
        //Act
        boolean resultado =ctrl.novoTAG(novoTipo);
        //Assert
        assertTrue(resultado);

    }


}
