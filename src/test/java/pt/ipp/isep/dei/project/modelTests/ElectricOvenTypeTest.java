package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.electricoven.ElectricOvenType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricOvenTypeTest {

    private ElectricOvenType electricOvenType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        // Type
        electricOvenType = new ElectricOvenType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", "room", 1, dim);
    }

    @Test
    public void testCreateDevice() {
        // Arrange
        Device expectedResult = electricOvenType.createDevice("Electric Oven Teka");
        kitchen.addDevice(expectedResult);

        // Act
        Device result = kitchen.getDeviceByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "ElectricOven";

        // Act
        String result = electricOvenType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
}
