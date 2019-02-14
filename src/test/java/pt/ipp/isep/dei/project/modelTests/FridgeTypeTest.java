package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTypeTest {

    @Test
    public void testCreateDevice() {
        FridgeType fridgeType = new FridgeType();
        String name = "Fridge Ariston 2000";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = new Fridge(name, room);

        Device result = fridgeType.createDevice(name, room);

        assertEquals(expectedResult, result);

    }
}
