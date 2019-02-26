package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.Lamp.LampType;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LampTypeTest {


    @Test
    public void testCreateDevice() {
        LampType lampType = new LampType();
        String name = "Lamp Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = lampType.createDevice(name, room);
        //Act
        Device result = room.getDeviceByPosition(0);
        //Assert
        assertEquals(expectedResult, result);

    }

}
