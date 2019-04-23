package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.freezer.FreezerType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreezerTypeTest {

    @Test
    public void testCreateDevice() {
        // Room
        String name = "Freezer Ariston 2000";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("Room");
        Room room = new Room(roomId, "room", 2, dim);
        FreezerType freezerType = new FreezerType();

        Device expectedResult = freezerType.createDevice(name);
        room.addDevice(expectedResult);
        //Act
        Device result = room.getDevice(name);
        //Assert
        assertEquals(expectedResult, result);
    }
}
