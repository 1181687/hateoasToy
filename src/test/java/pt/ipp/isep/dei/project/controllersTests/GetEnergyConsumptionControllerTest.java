package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.ElectricWaterHeater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Devices.Fridge.FridgeType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetEnergyConsumptionControllerTest {
    private GetEnergyConsumptionController ctrl;
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

        FridgeType fridgeType = new FridgeType();
        // ElectricWaterHeaterSpecs Instantiation

        // Device Instantiation
        String freezerCapacity = "Freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Annual Energy Consumption";

        Device device0 = fridgeType.createDevice("Fridgeratah V14", room0);

        device0.setAttributesDevType(freezerCapacity,35);
        device0.setAttributesDevType(refrigeratorCapacity,20);
        device0.setAttributesDevType(annualEnergyConsumption,1000);
        device0.setAttributesDevType(nominalPower,10);

        Device device1 = fridgeType.createDevice("Fridgeratah V15", room0);
        device1.setAttributesDevType(freezerCapacity,35);
        device1.setAttributesDevType(refrigeratorCapacity,20);
        device1.setAttributesDevType(annualEnergyConsumption,1000);
        device1.setAttributesDevType(nominalPower,10);

        Device device2 = fridgeType.createDevice("Fridgeratah V16", room0);
        device2.setAttributesDevType(freezerCapacity,35);
        device2.setAttributesDevType(refrigeratorCapacity,20);
        device2.setAttributesDevType(annualEnergyConsumption,1000);
        device2.setAttributesDevType(nominalPower,10);

        String HOT_WATER_TEMP = "Hot-Water Temperature";
        String PERFORMANCE_RATIO = "Performance Ratio";
        String NOMINAL_POWER = "Nominal Power";

        ElectricWaterHeaterType eWHType = new ElectricWaterHeaterType();
        Device device3 = eWHType.createDevice("Bosh Tronic 3000", room1);
        device3.setAttributesDevType(HOT_WATER_TEMP,50);
        device3.setAttributesDevType(PERFORMANCE_RATIO,0.9);
        device3.setAttributesDevType(NOMINAL_POWER,100);


        Device device4 = eWHType.createDevice("Bosh Tronic 4000", room1);
        device4.setAttributesDevType(HOT_WATER_TEMP,50);
        device4.setAttributesDevType(PERFORMANCE_RATIO,0.9);
        device4.setAttributesDevType(NOMINAL_POWER,100);

        this.ctrl = new GetEnergyConsumptionController(house);
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
        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(reading0);
        room.getDeviceByPosition(0).addReadingsToTheList(reading1);
        room.getDeviceByPosition(0).addReadingsToTheList(reading2);

        ctrl.getDeviceByPosition(0);

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
        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(reading0);
        room.getDeviceByPosition(0).addReadingsToTheList(reading1);
        room.getDeviceByPosition(0).addReadingsToTheList(reading2);

        ctrl.getDeviceByPosition(0);

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
        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        room.getDeviceByPosition(0).addReadingsToTheList(reading0);
        room.getDeviceByPosition(0).addReadingsToTheList(reading1);
        room.getDeviceByPosition(0).addReadingsToTheList(reading2);

        ctrl.getDeviceByPosition(0);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = ctrl.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}