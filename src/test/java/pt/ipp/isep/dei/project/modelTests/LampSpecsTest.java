package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LampSpecs;

import static org.junit.jupiter.api.Assertions.*;

public class LampSpecsTest {
    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        lampSpecs.setTime(10.0);

        double expectedResult = 1000.0;

        // Act
        double result = lampSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        double expectedResult = 100.0;

        //Act
        double result = lampSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void testEmptyConstructor() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        double expectedResult = 100.0;

        //Act
        double result = lampSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    /*@Test
    public void setmNominalPowerFalse() {
        // Arrange
        // LampSpecs Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux",50.0);
        lampSpecs.setAttributeValue("Nominal Power",100.0);

        //act
        boolean result = lampSpecs.setNominalPower(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        LampSpecs lampSpecs = new LampSpecs(luminousFlux, nominalPower);

        //act
        boolean result = lampSpecs.setNominalPower(20.0);

        assertTrue(result);
    }


    @Test
    public void setmTimeFalse() {

        // Arrange
        // LampSpecs Instantiation

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        LampSpecs lampSpecs = new LampSpecs(luminousFlux, nominalPower);

        lampSpecs.setTime(50.0);

        //act
        boolean result = lampSpecs.setTime(50.0);

        assertFalse(result);
    }

    @Test
    public void setmTimeTrue() {
        //Arrange
        // LampSpecs Instantiation

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        LampSpecs lampSpecs = new LampSpecs(luminousFlux, nominalPower);

        lampSpecs.setTime(50.0);

        //act
        boolean result = lampSpecs.setTime(10.0);

        assertTrue(result);
    }

    @Test
    public void setmLuminousFluxTrue() {
        // Arrange

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        LampSpecs lampSpecs = new LampSpecs(luminousFlux, nominalPower);

        //act
        boolean result = lampSpecs.setLuminousFlux(20.0);

        assertTrue(result);
    }
*/
    @Test
    public void getAttributesToString() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        String expectedResult =
                "1 - Luminous Flux: 50.0\n" +
                        "2 - Nominal Power: 100.0\n";

        // Act
        String result = lampSpecs.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeTrueLuminousFlux() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        // Act
        boolean result = lampSpecs.setAttribute(1, 51);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseLuminousFlux() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        // Act
        boolean result = lampSpecs.setAttribute(1, 50.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTrueNominalPower() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        // Act
        boolean result = lampSpecs.setAttribute(2, 101);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseNominalPower() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        // Act
        boolean result = lampSpecs.setAttribute(2, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeFalse() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        // Act
        boolean result = lampSpecs.setAttribute(3, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        int expectedResult = 2;

        // Act
        int result = lampSpecs.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }
}
