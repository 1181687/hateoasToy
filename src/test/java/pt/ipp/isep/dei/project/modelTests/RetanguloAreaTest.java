package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.AreaGeografica;
import pt.ipp.isep.dei.project.model.Localizacao;
import pt.ipp.isep.dei.project.model.RetanguloArea;
import pt.ipp.isep.dei.project.model.TipoAreaGeo;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloAreaTest {

    @Test
    public void testarEqualsSame() {
        //arrange
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);

        boolean expectedResult = true;
        //act
        boolean result = area1.equals(area1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsTrue() {
        //arrange
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        Localizacao local2 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        RetanguloArea area2 = new RetanguloArea(10, 10, local2);

        boolean expectedResult = true;
        //act
        boolean result = area1.equals(area2);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarEqualsFalse() {
        //arrange
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        Localizacao local2 = new Localizacao(40, -8, 95);
        RetanguloArea area1 = new RetanguloArea(12, 8, local1);
        RetanguloArea area2 = new RetanguloArea(10, 10, local2);

        boolean expectedResult = false;
        //act
        boolean result = area1.equals(area2);
        assertEquals(expectedResult, result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMaximo() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(50,40,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMinimo() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(30,0,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoSuperiorEsquerdo() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(51,20,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoSuperiorEsquerdo() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(45,41,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoInferiorDireito() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(29,20,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoInferiorDireito() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(51,-1,65);
        Localizacao localizacaoRetanguloArea = new Localizacao(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40,localizacaoRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertFalse(result);
    }
}