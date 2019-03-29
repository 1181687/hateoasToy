package pt.ipp.isep.dei.project.modelTests;

import org.junit.Test;
import static org.junit.Assert.*;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;


public class TelevisionTypeTest {

    @Test
    public void testCreateDevice() {
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        String name = "Smart TV";
        Dimension dim = new Dimension(3.3, 2.2, 1.0);
        Room room = new Room("Living Room", 1, dim);

        Device expectedResult = house.createDevice("Television", name, room);

        //Act
        Device result = room.getDeviceByPosition(0);

        //Assert
        assertEquals(expectedResult, result);
    }
}
