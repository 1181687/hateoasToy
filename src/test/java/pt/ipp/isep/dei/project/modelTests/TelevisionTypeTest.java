package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.television.TelevisionType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TelevisionTypeTest {

    @Test
    public void testCreateDevice() {
        String name = "Smart TV";
        TelevisionType televisionType = new TelevisionType();
        Dimension dim = new Dimension(3.3, 2.2, 1.0);
        RoomId roomId = new RoomId("Living Room");
        Room room = new Room(roomId, "room", 1, dim);

        Device expectedResult = televisionType.createDevice(name);
        room.addDevice(expectedResult);

        //Act
        Device result = room.getDevice(name);

        //Assert
        assertEquals(expectedResult, result);
    }
}
