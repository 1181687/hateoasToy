package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.RetanguloArea;

import static org.junit.jupiter.api.Assertions.*;

class RetanguloAreaTest {

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMaximo() {
        // Arrange
        Location location0 = new Location(50,40,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMinimo() {
        // Arrange
        Location location0 = new Location(30,0,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoSuperiorEsquerdo() {
        // Arrange
        Location location0 = new Location(51,20,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoSuperiorEsquerdo() {
        // Arrange
        Location location0 = new Location(45,41,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoInferiorDireito() {
        // Arrange
        Location location0 = new Location(29,20,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocalizacaoEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoInferiorDireito() {
        // Arrange
        Location location0 = new Location(51,-1,65);
        Location locationRetanguloArea = new Location(40,20,65);
        RetanguloArea area = new RetanguloArea(20, 40, locationRetanguloArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }
}