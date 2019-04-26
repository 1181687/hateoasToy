package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetDevicesInHouseGridControllerTest {
    private static final String ATTRIBUTE_FREEZER_CAPACITY = "freezer Capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy Consumption";
    private GetDevicesInHouseGridController ctrl;
    private House houseEdificioB;

    @BeforeEach
    public void StartUp() {

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        //Controller
        this.ctrl = new GetDevicesInHouseGridController(houseEdificioB);

        //Room ONE
        String id = "Kitchen";
        String description = "room";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(id, description, 2, dim);

        //Room TWO
        String id2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(id2, description, -1, dim2);

        //devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;

        Device fridge1 = houseEdificioB.createDevice("Fridge", "fridge Ariston", room1);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = houseEdificioB.createDevice("Fridge", "fridge Bosch", room1);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        double energyConsumption = 1000;
        int dishwasherCapacity = 100;
        double duration = 5;
        double nominalPowerWash = 100;
        Device washingMachine1 = houseEdificioB.createDevice("WashingMachine", "Washing Machine Bosh", room1);
        washingMachine1.setAttributesDevType(ATTRIBUTE_CAPACITY, dishwasherCapacity);
        washingMachine1.setAttributesDevType(ATTRIBUTE_DURATION, duration);
        washingMachine1.setAttributesDevType(ATTRIBUTE_ENERGY_CONSUMPTION, energyConsumption);
        washingMachine1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPowerWash);

        Device fridge3 = houseEdificioB.createDevice("Fridge", "Fridge1100", room2);
        fridge3.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge3.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge3.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge3.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge4 = houseEdificioB.createDevice("Fridge", "Fridge5000", room2);
        fridge4.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge4.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge4.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge4.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device washingMachine2 = houseEdificioB.createDevice("WashingMachine", "Washing Machine 625", room2);
        washingMachine2.setAttributesDevType(ATTRIBUTE_CAPACITY, dishwasherCapacity);
        washingMachine2.setAttributesDevType(ATTRIBUTE_DURATION, duration);
        washingMachine2.setAttributesDevType(ATTRIBUTE_ENERGY_CONSUMPTION, energyConsumption);
        washingMachine2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPowerWash);

        //add to Lists
        HouseGrid houseGrid = new HouseGrid("grid1");
        HouseGrid houseGridEmpty = new HouseGrid("grid2");
        houseGrid.addRoom(room1);
        houseGrid.addRoom(room2);
        List<HouseGrid> houseGridList1 = new ArrayList<>();
        houseGridList1.add(houseGrid);
        houseGridList1.add(houseGridEmpty);

        houseEdificioB.addGrid(houseGrid);
        houseEdificioB.addGrid(houseGridEmpty);
    }


    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Arrange
        String expectedResult = "WashingMachine\n" +
                "- Device Name: Washing Machine Bosh, Location: Kitchen.\n" +
                "- Device Name: Washing Machine 625, Location: KitchenBasement.\n" +
                "\n" +
                "Fridge\n" +
                "- Device Name: fridge Ariston, Location: Kitchen.\n" +
                "- Device Name: fridge Bosch, Location: Kitchen.\n" +
                "- Device Name: Fridge1100, Location: KitchenBasement.\n" +
                "- Device Name: Fridge5000, Location: KitchenBasement.\n" +
                "\n";

        //Act
        String result = ctrl.getDeviceListContentNameTypeLocationByGrid(0);
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
        boolean result = ctrl.checkIfThereAreNoDevicesInGridbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionTrue() {

        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesInGridbyPosition(1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void getNameByHGPosition() {
        // Arrange
        int position = 0;
        String expectedResult = "grid1";

        // Act
        String result = ctrl.getGridNameByPosition(position);

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
        String expectedResult = "There are no Grids in the housegrid";

        // Act
        String result = controller.getGridNameByPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }
}
