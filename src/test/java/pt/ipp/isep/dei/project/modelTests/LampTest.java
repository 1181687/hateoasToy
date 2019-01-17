package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Lamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LampTest {

    @Test
    public void testgetAttributeNames() {
        //Arrange
        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous flux");
        expectedResult.add("Time");
        expectedResult.add("Nominal power");

        //Act
        List<String> result = lamp.getAttributeNames();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueLuminousFlux() {
        //Arrange
        double luminousFlux2 = 30;

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Luminous flux";
        Object obj = new Double(luminousFlux2);
        lamp.setAttributeValue(attributeName, obj);
        double expectedResult = 30.0;

        //Act
        Object result = lamp.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueTime() {
        //Arrange
        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        double time = 20.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Time";
        Object obj = new Double(time);
        lamp.setAttributeValue(attributeName, obj);
        double expectedResult = 20.0;

        //Act
        Object result = lamp.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        //Arrange
        double nominalPower2 = 10;

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Nominal power";
        Object obj = new Double(nominalPower2);
        lamp.setAttributeValue(attributeName, obj);
        double expectedResult = 10;

        //Act
        Object result = lamp.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeNonexistent() {
        //Arrange
        int nominalPower2 = 10;

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "NonExistent";
        Object obj = new Integer(nominalPower2);
        lamp.setAttributeValue(attributeName, obj);
        int expectedResult = 0;

        //Act
        Object result = lamp.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseLuminousFlux() {
        //Arrange
        String luminousFlux2 = "30";

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Luminous flux";
        Object obj = new Integer(luminousFlux2);

        boolean expectedResult = false;

        //Act
        Object result = lamp.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseTime() {
        //Arrange
        String time = "30";

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Time";
        Object obj = new Integer(time);

        boolean expectedResult = false;

        //Act
        Object result = lamp.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseNominalPower() {
        //Arrange
        String nominalPower2 = "30";

        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);
        String attributeName = "Nominal power";
        Object obj = new Integer(nominalPower2);

        boolean expectedResult = false;

        //Act
        Object result = lamp.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Lamp Instantiation
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.setmTime(10.0);

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

        //act
        boolean result = lamp.setmNominalPower(100.0);

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
    public void setmTimeFalse() {

        // Arrange
        // Lamp Instantiation

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.setmTime(50.0);

        //act
        boolean result = lamp.setmTime(50.0);

        assertFalse(result);
    }

    @Test
    public void setmTimeTrue() {
        //Arrange
        // Lamp Instantiation

        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        lamp.setmTime(50.0);

        //act
        boolean result = lamp.setmTime(10.0);

        assertTrue(result);
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
    public void getAttributesToString() {

        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);

        String expectedResult =
                "1 - Luminous Flux: 50.0\n" +
                        "2 - Nominal Power: 100.0\n";

        // Act
        String result = lamp.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeTrueLuminousFlux() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        // Act
        boolean result = lamp.setAttribute(1, 51);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseLuminousFlux() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        // Act
        boolean result = lamp.setAttribute(1, 50.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTrueNominalPower() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        // Act
        boolean result = lamp.setAttribute(2, 101);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseNominalPower() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        // Act
        boolean result = lamp.setAttribute(2, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeFalse() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        // Act
        boolean result = lamp.setAttribute(3, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        double luminousFlux = 50.0;
        double nominalPower = 100.0;
        Lamp lamp = new Lamp(luminousFlux, nominalPower);


        int expectedResult = 2;

        // Act
        int result = lamp.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

}
