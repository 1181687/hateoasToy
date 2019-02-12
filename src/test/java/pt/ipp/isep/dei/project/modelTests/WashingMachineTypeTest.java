package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WashingMachineTypeTest {

    @Test
    public void testCreateDevice() {
        WashingMachineType washingMachineType = new WashingMachineType();
        String name = "WashingMachine Teka";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs(capacity, nominalPower, programList);

        Device expectedResult = new WashingMachine(name, room, washingMachineSpecs);

        Device result = washingMachineType.createDevice(name, room);

        assertEquals(result, expectedResult);
    }

}
