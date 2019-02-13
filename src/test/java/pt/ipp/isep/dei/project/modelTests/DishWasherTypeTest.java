package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherTypeTest {
    @Test
    public void testCreateDevice() {
        DishWasherType dishWasherType = new DishWasherType();
        String name = "Dish Washer Teka";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        Device expectedResult = new DishWasher(name, room);

        Device result = dishWasherType.createDevice(name, room);

        assertEquals(expectedResult, result);
    }
}
