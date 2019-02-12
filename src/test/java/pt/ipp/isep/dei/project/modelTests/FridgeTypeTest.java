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
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device1 expectedResult = new Device1(name, room, fridgeSpecs);

        Device1 result = fridgeType.createDevice(name, room);

        assertEquals(expectedResult, result);


    }
}
