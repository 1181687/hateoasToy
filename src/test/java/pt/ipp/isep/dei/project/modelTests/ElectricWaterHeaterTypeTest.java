package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.ElectricWaterHeater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricWaterHeaterTypeTest {


    @Test
    public void testCreateDevice() {
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        String name = "EWH Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        Device expectedResult = electricWaterHeaterType.createDevice(name, room);
        //Act
        Device result = room.getDeviceByPosition(0);
        //Assert
        assertEquals(expectedResult, result);
    }
}
