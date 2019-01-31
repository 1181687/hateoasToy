package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDeviceController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetEnergyConsumptionDeviceControllerTest {

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // ElectricWaterHeaterSpecs Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgeratah V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Fridgeratah V15", room0, fridge);
        room0.addDevice(device1);
        Device device2 = new Device("Fridgeratah V16", room0, fridge);
        room0.addDevice(device2);
        Device device3 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device3);
        Device device4 = new Device("Bosh Tronic 4000", room1, electricWaterHeater);
        room1.addDevice(device4);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        // Controller Instantiation
        GetEnergyConsumptionDeviceController controller = new GetEnergyConsumptionDeviceController(house);

        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Fridgeratah V15, located in room: Kitchen\n" +
                        "3 - Device: Fridgeratah V16, located in room: Kitchen\n" +
                        "4 - Device: Bosh Tronic 3000, located in room: Laundry\n" +
                        "5 - Device: Bosh Tronic 4000, located in room: Laundry\n";

        // Act
        String result = controller.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfDevicesTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // ElectricWaterHeaterSpecs Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgeratah V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Bosch Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        // Controller Instantiation
        GetEnergyConsumptionDeviceController controller = new GetEnergyConsumptionDeviceController(house);

        int expectedResult = 2;

        // Act
        int result = controller.getNumberOfDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneSolution() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        // Controller Instantiation
        GetEnergyConsumptionDeviceController controller = new GetEnergyConsumptionDeviceController(house);

        controller.setDevice(0);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = controller.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoSolutions() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgeratah V14", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        // Controller Instantiation
        GetEnergyConsumptionDeviceController controller = new GetEnergyConsumptionDeviceController(house);

        controller.setDevice(0);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = controller.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithNoSolutions() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator V14", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        // Controller Instantiation
        GetEnergyConsumptionDeviceController controller = new GetEnergyConsumptionDeviceController(house);

        controller.setDevice(0);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = controller.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}