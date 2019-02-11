package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDeviceController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetEnergyConsumptionDeviceControllerTest {
    private GetEnergyConsumptionDeviceController ctrl;
    private House house;
    private Room room;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        house.addRoom(room0);
        house.addRoom(room1);

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

        this.ctrl = new GetEnergyConsumptionDeviceController(house);
        room = house.getRoomOfTheRoomList(0);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        // Controller Instantiation
        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Fridgeratah V15, located in room: Kitchen\n" +
                        "3 - Device: Fridgeratah V16, located in room: Kitchen\n" +
                        "4 - Device: Bosh Tronic 3000, located in room: Laundry\n" +
                        "5 - Device: Bosh Tronic 4000, located in room: Laundry\n";

        // Act
        String result = ctrl.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfDevicesTest() {
        // Arrange
        int expectedResult = 5;

        // Act
        int result = ctrl.getNumberOfDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneSolution() {
        // Arrange
        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(readings0);
        room.getDeviceByPosition(0).addReadingsToTheList(readings1);
        room.getDeviceByPosition(0).addReadingsToTheList(readings2);

        ctrl.setDevice(0);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = ctrl.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoSolutions() {
        // Arrange
        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(readings0);
        room.getDeviceByPosition(0).addReadingsToTheList(readings1);
        room.getDeviceByPosition(0).addReadingsToTheList(readings2);

        ctrl.setDevice(0);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = ctrl.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithNoSolutions() {
        // Arrange
        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(readings0);
        room.getDeviceByPosition(0).addReadingsToTheList(readings1);
        room.getDeviceByPosition(0).addReadingsToTheList(readings2);

        ctrl.setDevice(0);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = ctrl.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}