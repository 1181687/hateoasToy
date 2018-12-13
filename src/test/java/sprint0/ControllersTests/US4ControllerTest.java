package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US4Controller;
import sprint0.Model.*;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class US4ControllerTest {

    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange
        ListaAG lista = new ListaAG();

        String tipoPedido = "Cidade";
        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area2 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);

        lista.adicionarAreaGeoALista(ag1);
        lista.adicionarAreaGeoALista(ag2);

        US4Controller ctrl = new US4Controller(lista);
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("Porto"));

        //Act
        ArrayList<String> resultado = ctrl.getListaAGPorTipo(tipoPedido);

        //Assert
        assertEquals(expectedResult, resultado);
    }
}
