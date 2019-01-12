package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceListTest {

    @Test
    public void testAddDeviceTrue() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge();
        Device dev1 = new Device("FridgeAriston", room, specFridge, 300);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1));

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge();
        Device dev1 = null;

        List<Device> expectedResult = new ArrayList<>();

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }
}
