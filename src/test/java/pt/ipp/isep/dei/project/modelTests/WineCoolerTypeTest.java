package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.winecooler.WineCoolerType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WineCoolerTypeTest {

    private WineCoolerType wineCoolerType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        wineCoolerType = new WineCoolerType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "WineCooler";
        //Act
        String result = wineCoolerType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = wineCoolerType.createDevice("Awesome WineCooler");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}