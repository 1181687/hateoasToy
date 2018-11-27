package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaGeograficaTest {

    @Test
    public void testarEqualsSame() {
        //arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);
        boolean expectedResult = true;
        //act
        boolean result = ag1.equals(ag1);
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

    @Test
    public void testarDistanciaLinearDuasAreas() {
        // arrange
        String nomeAG1 = "Porto";
        String nomeAG2 = "Funchal";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        TipoAreaGeo tipo2 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 0);
        Localizacao local2 = new Localizacao(32.6333, -16.9, 0);
        RetanguloArea area1 = new RetanguloArea(10, 4);
        RetanguloArea area2 = new RetanguloArea(10, 4);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);
        AreaGeografica ag2 = new AreaGeografica(nomeAG2, tipo2, local2, area2);
        double expectedResult = 11.8843;

        // act
        double resultado = ag1.distanciaLinearDuasAreas(ag2);

                //assert
        assertEquals (expectedResult,resultado, 0.0001);
    }
}