package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricWaterHeaterTypeTest {


    @Test
    public void testCreateDevice() {
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        String name = "EWH Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = new ElectricWaterHeater(name, room);

        Device result = electricWaterHeaterType.createDevice(name, room);

        assertEquals(expectedResult, result);
    }
}
