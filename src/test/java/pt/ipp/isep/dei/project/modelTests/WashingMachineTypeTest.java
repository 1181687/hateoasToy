package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WashingMachineTypeTest {

    @Test
    public void testCreateDevice() {

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);


        String name = "washingmachine Teka";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        Device expectedResult = house.createDevice("WashingMachine", name, room);
        //Act
        Device result = room.getDeviceByPosition(0);
        //Assert
        assertEquals(expectedResult, result);
    }

}