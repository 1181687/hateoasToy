package sprint0.ModelTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.Localizacao;
import sprint0.Model.RetanguloArea;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloAreaTest {

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