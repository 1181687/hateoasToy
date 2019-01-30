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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // ElectricWaterHeater Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(50, 150,
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

        String expectedResult =
                "1 - Name of the device: Fridgeratah V14\n" + "2 - Name of the device: Bosch Tronic 3000\n";

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // ElectricWaterHeater Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(50, 150,
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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgeratah V14", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

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

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator V14", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

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