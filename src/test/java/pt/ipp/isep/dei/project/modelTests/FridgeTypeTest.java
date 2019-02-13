package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.FridgeType;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTypeTest {


    @Test
    public void testCreateDevice() {
        FridgeType fridgeType = new FridgeType();
        String name = "Fridge Ariston 2000";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        fridgeType.createDevice(name, room);

        Device expectedResult = fridgeType.createDevice(name, room);

        Device result = room.getDeviceByPosition(0);

        assertEquals(expectedResult, result);


    }
}
