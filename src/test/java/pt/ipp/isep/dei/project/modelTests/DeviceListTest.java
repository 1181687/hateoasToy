package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceListTest {

    @Test
    public void testAddDeviceTrue() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge();
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1));

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        DeviceList devList = new DeviceList();
        Device dev1 = null;
        List<Device> expectedResult = new ArrayList<>();

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCode() {
        DeviceList deviceList = new DeviceList();
        int expectedResult = Objects.hash(deviceList.getmDeviceList());

        // Act
        int result = deviceList.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange

        DeviceList deviceList1 = new DeviceList();
        DeviceList deviceList2 = new DeviceList();

        //Act
        boolean result = deviceList1.equals(deviceList2);
        //Assert
        assertTrue(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        deviceList.addDeviceToDeviceList(dev1);
        deviceList.addDeviceToDeviceList(dev2);

        String expectedResult =
                "1 - Name of the device: Lamp1\n" +
                        "2 - Name of the device: Lamp2\n";

        // Act
        String result = deviceList.getDeviceListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        DeviceList deviceList = new DeviceList();

        // Act
        boolean result = deviceList.checkIfDeviceListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        DeviceList deviceList = new DeviceList();

        deviceList.addDeviceToDeviceList(dev1);

        // Act
        boolean result = deviceList.checkIfDeviceListIsEmpty();

        // Assert
        assertFalse(result);
    }

}
