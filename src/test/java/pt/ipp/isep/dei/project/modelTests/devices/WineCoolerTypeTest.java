package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.winecooler.WineCoolerType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

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
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
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
        Device result = kitchen.getDevice("Awesome WineCooler");
        //Assert
        assertEquals(result, expectedResult);
    }

}
