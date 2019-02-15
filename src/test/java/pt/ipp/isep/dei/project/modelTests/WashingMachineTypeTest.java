package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.WashingMachineType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WashingMachineTypeTest {

    @Test
    public void testCreateDevice() {
        WashingMachineType washingMachineType = new WashingMachineType();
        String name = "WashingMachine Teka";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = washingMachineType.createDevice(name, room);
        //Act
        Device result = room.getDeviceByPosition(0);
        //Assert
        assertEquals(expectedResult, result);
    }

}
