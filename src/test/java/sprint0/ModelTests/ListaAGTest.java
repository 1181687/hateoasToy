package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListaAGTest {


    @Test
    void testaAdicionarAreaGeoAListaPositivo() {
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        ListaAG lista = new ListaAG();
        AreaGeografica areaGeografica = new AreaGeografica (nomeAG, tipo, local,area);

        //Act
        boolean resultado = lista.adicionarAreaGeoALista(areaGeografica);

        //Arrange
        assertTrue(resultado);
    }

    @Test
    void testaAdicionarAreaGeoAListaNegativo() {
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 10,local);

        ListaAG lista = new ListaAG();
        AreaGeografica areaGeografica = new AreaGeografica (nomeAG, tipo, local,area);
        lista.adicionarAreaGeoALista(areaGeografica);

        //Act
        boolean resultado = lista.adicionarAreaGeoALista(areaGeografica);

        //Arrange
        assertFalse(resultado);
    }


}
