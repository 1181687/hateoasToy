package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaGeograficaTest {


    @Test
    public void testarConstrutorNome() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        ag.setmNomeAreaGeo("Braga");
        String expectedResult = "Braga";
        //act
        String result = ag.getmNomeAreaGeo();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsTrue() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo, local, area);
        boolean expectedResult = true;
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsFalse() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo2 = new TipoAreaGeo("Aldeia");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo1, local, area);
        AreaGeografica ag2 = new AreaGeografica(nomeAG, tipo2, local, area);
        boolean expectedResult = false;
        //act
        boolean result = ag1.equals(ag2);
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsObjetosDiferentes() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag = new AreaGeografica(nomeAG, tipo, local, area);
        boolean expectedResult = false;
        //act
        boolean result = ag.equals(area);
        //assert
        assertEquals(expectedResult, result);
    }
}