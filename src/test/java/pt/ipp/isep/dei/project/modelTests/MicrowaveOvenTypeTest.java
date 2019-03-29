package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.microwaveoven.MicrowaveOvenType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.Assert.assertEquals;

public class MicrowaveOvenTypeTest {

    private MicrowaveOvenType microwaveOvenType;
    private Room kitchen;

    @Before
    public void StartUp() {
        // Type
        microwaveOvenType = new MicrowaveOvenType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", 1, dim);
    }

    @Test
    public void testCreateDevice() {
        // Arrange
        Device expectedResult = microwaveOvenType.createDevice("Microwave Teka");
        kitchen.addDevice(expectedResult);

        // Act
        Device result = kitchen.getDeviceByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "MicrowaveOven";

        // Act
        String result = microwaveOvenType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }

}