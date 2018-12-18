package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US2Controller;
import pt.ipp.isep.dei.project.model.ListaTiposAG;
import pt.ipp.isep.dei.project.model.TipoAreaGeo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US2ControllerTest {

    @Test
    public void testarGetListaTiposDeAG() {
        //Arrange
        //Instanciar a classe US2Controller
        ListaTiposAG lista = new ListaTiposAG();
        US2Controller ctrl2 = new US2Controller(lista);

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.adicionarElementoALista(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");

        //Act
        List < String > result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    //NOVO
    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        //Instanciar a classe US2Controller
        ListaTiposAG lista = new ListaTiposAG();
        US2Controller ctrl2 = new US2Controller(lista);

        //Tipos de Areas Geográficas
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);
        String nomeDoTipo2 = "Freguesia";
        TipoAreaGeo tipo2 = new TipoAreaGeo(nomeDoTipo2);

        //Adicionar os Tipos de Areas Geográficas na lista
        lista.adicionarElementoALista(tipo1);
        lista.adicionarElementoALista(tipo2);


        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade", "Freguesia");


        //Act
        List<String> result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        //Instanciar a classe US2Controller
        ListaTiposAG lista = new ListaTiposAG();
        US2Controller ctrl2 = new US2Controller(lista);

        //Expected Result
        List<String> expectedResult = Arrays.asList();


        //Act
        List<String> result = ctrl2.getListaTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }
}
