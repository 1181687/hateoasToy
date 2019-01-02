package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.RectangleArea;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RectangleAreaTest {

    @Test
    void testhashCode() {
        //Arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local1);

        int expectedResult = Objects.hash();

        // Act
        int result = area.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsSame() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);

        //act
        boolean result = area1.equals(area1);
        assertTrue(result);
    }

    @Test
    void testarEqualsTrue() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        RectangleArea area2 = new RectangleArea(10, 10, local2);

        //act
        boolean result = area1.equals(area2);
        assertTrue(result);
    }

    @Test
    public void testarEqualsFalse() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        Location local2 = new Location(40, -8, 95);
        RectangleArea area1 = new RectangleArea(12, 8, local1);
        RectangleArea area2 = new RectangleArea(10, 10, local2);

        //act
        boolean result = area1.equals(area2);
        assertFalse(result);
    }

    @Test
    public void testEqualsFalseDiferentObject() {
        //arrange
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(12, 8, local1);

        String name = "roomOne";
        int housefloor = 2;
        Dimensions dim = new Dimensions(4, 10.5, 7.5);
        Room room = new Room(name, housefloor, dim);

        //act
        boolean result = area1.equals(room);
        assertFalse(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMaximo() {
        // Arrange
        Location location0 = new Location(50,40,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaComTesteVerdadeiroNoLimiteMinimo() {
        // Arrange
        Location location0 = new Location(30,0,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertTrue(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoSuperiorEsquerdo() {
        // Arrange
        Location location0 = new Location(51,20,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoSuperiorEsquerdo() {
        // Arrange
        Location location0 = new Location(45,41,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaNumLimiteDeForaDaLatitudeCantoInferiorDireito() {
        // Arrange
        Location location0 = new Location(29,20,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }

    @Test
    void verificarSeLocationEstaContidaNumaAreaNumLimiteDeForaDaLongitudeCantoInferiorDireito() {
        // Arrange
        Location location0 = new Location(51,-1,65);
        Location locationRectangleArea = new Location(40,20,65);
        RectangleArea area = new RectangleArea(20, 40, locationRectangleArea);

        // Act
        boolean result = area.verificaSeLocalizacaoEstaContidaNumaArea(location0);

        // Assert
        assertFalse(result);
    }
}