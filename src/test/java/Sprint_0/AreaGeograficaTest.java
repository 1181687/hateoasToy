package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaGeograficaTest {


    @Test
    public void testarConstrutorNome(){
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10,4);
        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        ag.setmNomeAreaGeo("Braga");
        String expectedResult = "Braga";
        //act
        String result = ag.getmNomeAreaGeo();
        //assert
        assertEquals(expectedResult, result);
    }

    /*@Test
    public void testarConstrutorTipoAreaGeo(){
    //arrange
    String nomeAG = "Porto";
    TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
    Localizacao local = new Localizacao(41.1496, -8.6109, 97);
    RetanguloArea area = new RetanguloArea(10,4);
    AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        ag.setmNomeAreaGeo("Braga");
    String expectedResult = "Braga";
    //act
    String result = ag.getmNomeAreaGeo();
    //assert
    assertEquals(expectedResult, result);
}*/
}