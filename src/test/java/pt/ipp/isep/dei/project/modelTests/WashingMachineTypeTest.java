package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.washingmachine.WashingMachineType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WashingMachineTypeTest {

    private WashingMachineType washingMachineType;
    private Room laundry;


    @BeforeEach
    public void StartUp() {
        //Type
        washingMachineType = new WashingMachineType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        laundry = new Room("Laundry", "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "WashingMachine";
        //Act
        String result = washingMachineType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = washingMachineType.createDevice("Ariston Washing Machine");
        laundry.addDevice(expectedResult);
        //Act
        Device result = laundry.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}
