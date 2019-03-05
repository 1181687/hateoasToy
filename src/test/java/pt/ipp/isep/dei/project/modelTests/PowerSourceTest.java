package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.powersource.PowerSourceType;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTest {

    @Test
    public void testingEqualsWithNonRechargeablePowerSourcesPositiveTest(){
        //Arrange
        String powerSourceName1 = "ps1";
        String powerSourceName2 = "ps1";
        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");
        PowerSourceType powerSourceType2 = new PowerSourceType("public electric grid");

        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);
        PowerSource powerSource2 = new PowerSource(powerSourceName2, powerSourceType2);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertTrue(result);
    }


    @Test
    public void testingEqualsNegativeTestDifferentNames() {
        //Arrange
        String powerSourceName1 = "ps1";
        String powerSourceName2 = "ps2";
        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");
        PowerSourceType powerSourceType2 = new PowerSourceType("public electric grid");


        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);
        PowerSource powerSource2 = new PowerSource(powerSourceName2, powerSourceType2);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertFalse(result);
    }


    @Test
    public void testingEqualsComparingTheSameObjectPositiveTest(){
        //Arrange
        String powerSourceName1 = "ps1";

        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");

        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);

        //Act
        boolean result = powerSource1.equals(powerSource1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testingEqualsComparingDifferentObjectTypeNegativeTest(){
        //Arrange
        String powerSourceName1 = "ps1";

        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");

        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);

        //Act
        boolean result = powerSource1.equals(powerSourceType1);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingHashCode() {
        //Arrange
        String powerSourceName1 = "ps1";

        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");

        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);
        int expectedResult = Objects.hash(powerSourceName1);

        // Act
        int result = powerSource1.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateNameNull(){
        //Arrange
        String name = null;
        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");

        //Act
        Throwable exception = assertThrows(NullPointerException.class, () ->
                new PowerSource(name,powerSourceType)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameEmpty(){
        //Arrange
        String name = " ";
        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");

        //Act
        Throwable exception = assertThrows(NullPointerException.class, () ->
                new PowerSource(name,powerSourceType)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidatePowerSourceTypeNull(){
        //Arrange
        String name = "power source 1";
        PowerSourceType powerSourceType = null;

        //Act
        Throwable exception = assertThrows(NullPointerException.class, () ->
                new PowerSource(name,powerSourceType)
        );

        //Assert
        assertEquals("Please select a valid power source type", exception.getMessage());
    }

    @Test
    public void testingGetName(){
        //Arrange
        String powerSourceName1 = "ps1";

        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");

        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);

        String expectedResult = "ps1";

        //Act
        String result = powerSource1.getName();

        //Assert
        assertEquals(expectedResult,result);
    }
}
