package sprint0.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        boolean resultado = novaLista.mListaTAG.isEmpty();
        //Assert
        assertFalse(resultado);
    }
}