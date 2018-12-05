package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloAreaTest {

    @Test
    void verificaSeLocalizacaoEstaContidaNumaAreaComTesteVerdadeiro() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(50,35,65);
        Localizacao cantoSuperiorEsquerdo = new Localizacao(80,20,65);
        Localizacao cantoInferiorDireito = new Localizacao(40,40,65);
        RetanguloArea area = new RetanguloArea(cantoSuperiorEsquerdo, cantoInferiorDireito);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificaSeLocalizacaoEstaContidaNumaAreaComTesteFalso() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(90,35,65);
        Localizacao cantoSuperiorEsquerdo = new Localizacao(80,20,65);
        Localizacao cantoInferiorDireito = new Localizacao(40,40,65);
        RetanguloArea area = new RetanguloArea(cantoSuperiorEsquerdo, cantoInferiorDireito);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificaSeLocalizacaoEstaContidaNumaAreaComTestePositivoNoLimite1() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(80,40,65);
        Localizacao cantoSuperiorEsquerdo = new Localizacao(80,20,65);
        Localizacao cantoInferiorDireito = new Localizacao(40,40,65);
        RetanguloArea area = new RetanguloArea(cantoSuperiorEsquerdo, cantoInferiorDireito);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificaSeLocalizacaoEstaContidaNumaAreaComTestePositivoNoLimite2() {
        // Arrange
        Localizacao localizacao0 = new Localizacao(40,20,65);
        Localizacao cantoSuperiorEsquerdo = new Localizacao(80,20,65);
        Localizacao cantoInferiorDireito = new Localizacao(40,40,65);
        RetanguloArea area = new RetanguloArea(cantoSuperiorEsquerdo, cantoInferiorDireito);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(localizacao0);

        // Assert
        assertTrue(result);
    }
}