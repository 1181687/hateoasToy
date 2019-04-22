package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.dishwasher.DishWasherType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherTypeTest {
    private DishWasherType dishWasherType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        // Type
        dishWasherType = new DishWasherType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testCreateDevice() {
        // Arrange
        Device expectedResult = dishWasherType.createDevice("Dish Washer Teka");
        kitchen.addDevice(expectedResult);

        // Act
        Device result = kitchen.getDevice("Dish Washer Teka");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "DishWasher";

        // Act
        String result = dishWasherType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
}
