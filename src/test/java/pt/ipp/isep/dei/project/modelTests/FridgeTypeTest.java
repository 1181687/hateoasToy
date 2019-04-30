package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.fridge.FridgeType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FridgeTypeTest {

    private FridgeType fridgeType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        fridgeType = new FridgeType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fridge";
        //Act
        String result = fridgeType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = fridgeType.createDevice("Fridge 200");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}
