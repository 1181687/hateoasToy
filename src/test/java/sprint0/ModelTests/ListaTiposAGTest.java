package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Controllers.US2Controller;
import sprint0.Model.ListaTiposAG;
import sprint0.Model.TipoAreaGeo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListaTiposAGTest {

    @Test
    void testaadicionarElementoAListaPositivo() {
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
        String novoTipoAG = "Cidade";
        TipoAreaGeo novoTipo = new TipoAreaGeo(novoTipoAG);
        //Act
        boolean resultado = lista.adicionarElementoALista(novoTipo);
        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaadicionarElementoAListaNegativo() {
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
        String novoTipoAG = "Cidade";
        TipoAreaGeo novoTipo = new TipoAreaGeo(novoTipoAG);
        lista.adicionarElementoALista(novoTipo);
        //Act
        boolean resultado = lista.adicionarElementoALista(novoTipo);
        //Arrange
        assertFalse(resultado);
    }

    @Test
    void testarNovoTipoAG() {
        //Arrange
        ListaTiposAG lista = new ListaTiposAG();
        String novoTipoAG = "Cidade";
        TipoAreaGeo novoTipo = new TipoAreaGeo(novoTipoAG);
        lista.adicionarElementoALista(novoTipo);
        TipoAreaGeo tipoDiferente = lista.novoTipoAG("Rua");
        //Act
        boolean resultado = lista.adicionarElementoALista(tipoDiferente);
        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testarConstrutorNaoVazio(){
        //Arrange
        List<TipoAreaGeo> lista = new ArrayList<>();
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        lista.add(tipo1);
        lista.add(tipo2);
        ListaTiposAG novaLista = new ListaTiposAG(lista);
        //Act
        boolean resultado = novaLista.getmListaTAG().isEmpty();
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange
        //Instanciar a classe ListaTiposAG
        ListaTiposAG lista = new ListaTiposAG();

        //Tipo de Area Geográfica
        String nomeDoTipo1 = "Cidade";
        TipoAreaGeo tipo1 = new TipoAreaGeo(nomeDoTipo1);

        //Adicionar o Tipo de Area Geográfica na lista
        lista.adicionarElementoALista(tipo1);

        //Expected Result
        List<String> expectedResult = Arrays.asList("Cidade");


        //Act
        List<String> result = lista.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testarGetListaDosTiposDeAGAdicionandoMaisDoQueUmTipo() {
        //Arrange
        //Instanciar a classe ListaTiposAG
        ListaTiposAG lista = new ListaTiposAG();

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
        List<String> result = lista.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAGASemAdicionarNenhumTipo() {
        //Arrange
        //Instanciar a classe ListaTiposAG
        ListaTiposAG lista = new ListaTiposAG();

        //Expected Result
        List<String> expectedResult = Arrays.asList();


        //Act
        List<String> result = lista.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);

    }
}