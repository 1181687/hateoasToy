package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherTypeTest {

    @Test
    public void testCreateDevice() {
        //Arrange
        DishWasherType dishWasherType = new DishWasherType();
        String name = "Dish Washer Teka";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = dishWasherType.createDevice(name, room);
        //Act
        Device result = room.getDeviceByPosition(0);
        //Assert
        assertEquals(expectedResult, result);
    }
}
