package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.DishWasherType;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherSpecsTest {
    Room kitchen;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        DishWasherType dishWasherType = new DishWasherType();
        dishWasherType.createDevice("DishWasher Bosch", kitchen);
    }
    
    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Dishwasher";

        //Act
        String result = kitchen.getDeviceByPosition(0).getType();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = kitchen.getDeviceByPosition(0).getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = kitchen.getDeviceByPosition(0).getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        String expectedResult = "1 - Capacity: 30\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = kitchen.getDeviceByPosition(0).getSpecsToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        int expectedResult = 2;

        //Act
        int result = kitchen.getDeviceByPosition(0).getNumberOfSpecsAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetEnergyConsumptionInADay() {
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        double expectedResult = 0;

        //Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result);

    }
}