package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.lamp.LampType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LampTypeTest {
    private LampType lampType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        // Type
        lampType = new LampType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testCreateDevice() {
        // Arrange
        Device expectedResult = lampType.createDevice("TaoTronics Elune TT-DL01");
        kitchen.addDevice(expectedResult);

        // Act
        Device result = kitchen.getDevice("TaoTronics Elune TT-DL01");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "Lamp";

        // Act
        String result = lampType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
}
