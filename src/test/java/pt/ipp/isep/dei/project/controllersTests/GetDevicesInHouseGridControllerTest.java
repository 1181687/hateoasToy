package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

public class GetDevicesInHouseGridControllerTest {
    private GetDevicesInHouseGridController ctrl;
    private House houseEdificioB;

    private static final String ATTRIBUTE_FREEZER_CAPACITY = "Freezer Capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy Consumption";


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

        houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(insertedGeoArea);

        this.ctrl = new GetDevicesInHouseGridController(houseEdificioB);


        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        // DeviceSpecs specFridge = new FridgeSpecs(100, 100, 100, 100);
        // ProgramList wmProgramList = new ProgramList();
        // ProgramList dwProgramList = new ProgramList();

        // DeviceSpecs specWashing = new WashingMachineSpecs(100, 100, wmProgramList);
        //DeviceSpecs specDishWasher = new DishWasherSpecs(100, 100, dwProgramList);
        //Device dev1 = new Device("FridgeAriston", room1, specFridge);
        //Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        //Device dev3 = new Device("DishWasher", room1, specDishWasher);

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("Fridge Ariston", room1);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = fridgeType.createDevice("Fridge Bosch", room1);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        double energyConsumption = 1000;
        int dishwasherCapacity = 100;
        double duration = 5;
        double nominalPowerWash = 100;
        DishWasherType dishwasherType = new DishWasherType();
        Device washingMachine1 = dishwasherType.createDevice("Washing Machine Bosh", room1);
        washingMachine1.setAttributesDevType(ATTRIBUTE_CAPACITY, dishwasherCapacity);
        washingMachine1.setAttributesDevType(ATTRIBUTE_DURATION, duration);
        washingMachine1.setAttributesDevType(ATTRIBUTE_ENERGY_CONSUMPTION, energyConsumption);
        washingMachine1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPowerWash);

        room1.addDevice(fridge1);
        room1.addDevice(fridge2);
        room1.addDevice(washingMachine1);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        //DeviceSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        //Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        //Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        //Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);


        // FridgeType fridgeType = new FridgeType();
        Device fridge3 = fridgeType.createDevice("Fridge1100", room2);
        fridge3.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge3.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge3.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge3.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge4 = fridgeType.createDevice("Fridge5000", room2);
        fridge4.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge4.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge4.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge4.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device washingMachine2 = dishwasherType.createDevice("Washing Machine 625", room2);
        washingMachine2.setAttributesDevType(ATTRIBUTE_CAPACITY, dishwasherCapacity);
        washingMachine2.setAttributesDevType(ATTRIBUTE_DURATION, duration);
        washingMachine2.setAttributesDevType(ATTRIBUTE_ENERGY_CONSUMPTION, energyConsumption);
        washingMachine2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPowerWash);

        room2.addDevice(fridge3);
        room2.addDevice(fridge4);
        room2.addDevice(washingMachine2);

        //add to Lists


        HouseGrid houseGrid = new HouseGrid("grid1");
        HouseGrid houseGridEmpty = new HouseGrid("grid2");
        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);
        List<HouseGrid> houseGridList1 = new ArrayList<>();
        houseGridList1.add(houseGrid);
        houseGridList1.add(houseGridEmpty);

        houseEdificioB.addGrid(houseGrid);
        houseEdificioB.addGrid(houseGridEmpty);
    }


    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Arrange
        String expectedResult = "Dish Washer\n" +
                "- Device Name: Washing Machine Bosh, Location: Kitchen.\n" +
                "- Device Name: Washing Machine 625, Location: KitchenBasement.\n" +
                "\n" +
                "Fridge\n" +
                "- Device Name: Fridge Ariston, Location: Kitchen.\n" +
                "- Device Name: Fridge Bosch, Location: Kitchen.\n" +
                "- Device Name: Fridge1100, Location: KitchenBasement.\n" +
                "- Device Name: Fridge5000, Location: KitchenBasement.\n" +
                "\n";

        //Act
        String result = ctrl.getDeviceListContentNameTypeLocationByHG(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        String expectedResult = "1 - Name: grid1\n2 - Name: grid2\n";

        // Act
        String result = ctrl.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        House emptyHouse = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Act
        boolean result = emptyHouse.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Act
        boolean result = ctrl.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetHouseGridListLength() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = ctrl.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionFalse() {

        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionTrue() {

        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesHGbyPosition(1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void getNameByHGPosition() {
        // Arrange
        int position = 0;
        String expectedResult = "grid1";

        // Act
        String result = ctrl.getHGNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameByHGPositionEmpty() {
        // Arrange
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        House emptyHouse = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        GetDevicesInHouseGridController controller = new GetDevicesInHouseGridController(emptyHouse);

        int position = 0;
        String expectedResult = "There are no Grids in the house";

        // Act
        String result = controller.getHGNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }
}
