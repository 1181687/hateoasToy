package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.DeviceTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTypesTest {
    @Test
    public void testGetDeviceTypeNameDishWasher() {
        //Arrange
        String expectedResult = "Dish Washer";

        //Act
        String result = DeviceTypes.DISH_WASHER.getDeviceTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceTypeNameFridge() {
        //Arrange
        String expectedResult = "Fridge";

        //Act
        String result = DeviceTypes.FRIDGE.getDeviceTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceTypeNameLamp() {
        //Arrange
        String expectedResult = "Lamp";

        //Act
        String result = DeviceTypes.LAMP.getDeviceTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceTypeNameWashingMachine() {
        //Arrange
        String expectedResult = "Washing Machine";

        //Act
        String result = DeviceTypes.WASHING_MACHINE.getDeviceTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceTypeNameElectricWaterHeater() {
        //Arrange
        String expectedResult = "Washing Machine";

        //Act
        String result = DeviceTypes.WASHING_MACHINE.getDeviceTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


}
