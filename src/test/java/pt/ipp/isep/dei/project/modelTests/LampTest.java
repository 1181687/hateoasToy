package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Lamp;

import static org.junit.jupiter.api.Assertions.*;

public class LampTest {

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Lamp Instantiation
        double time = 10.0;
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.getEnergyConsumptionInADay();

        double expectedResult = 1000.0;

        // Act
        double result = lamp.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // Lamp Instantiation

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        double expectedResult = 100.0;

        //Act
        double result = lamp.getmNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void setmNominalPowerFalse() {

        // Arrange
        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.setmNominalPower(20.0);

        //act
        boolean result = lamp.setmNominalPower(20.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        //act
        boolean result = lamp.setmNominalPower(20.0);

        assertTrue(result);
    }

    @Test
    public void setmLuminousFluxFalse() {

        // Arrange
        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.setmLuminousFlux(20.0);

        //act
        boolean result = lamp.setmLuminousFlux(20.0);

        assertFalse(result);
    }

    @Test
    public void setmLuminousFluxTrue() {
        // Arrange

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        //act
        boolean result = lamp.setmLuminousFlux(20.0);

        assertTrue(result);
    }


    @Test
    public void setmTime() {


    }
}
