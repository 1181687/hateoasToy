package pt.ipp.isep.dei.project.controllersTests;

import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.House;

public class GetDevicesInHouseGridControllerTest {
    private GetDevicesInHouseGridController ctrl;
    private House houseEdificioB;

  /*  @BeforeEach
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

        DeviceSpecs specFridge = new FridgeSpecs(100, 100, 100, 100);
        ProgramList wmProgramList = new ProgramList();
        ProgramList dwProgramList = new ProgramList();

        DeviceSpecs specWashing = new WashingMachineSpecs(100, 100, wmProgramList);
        DeviceSpecs specDishWasher = new DishWasherSpecs(100, 100, dwProgramList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        DeviceSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

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
                "- Device Name: DishWasher, Location: Kitchen.\n" +
                "- Device Name: DishWasherTeka, Location: KitchenBasement.\n" +
                "\n" +
                "Electric Water Heater\n" +
                "- Device Name: ElectricWaterHeater, Location: KitchenBasement.\n" +
                "\n" +
                "Washing Machine\n" +
                "- Device Name: WashingMachineBosh, Location: Kitchen.\n" +
                "\n" +
                "Fridge\n" +
                "- Device Name: FridgeAriston, Location: Kitchen.\n" +
                "- Device Name: FridgeSiemens, Location: KitchenBasement.\n\n";

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
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        ProgramList dwProgramList = new ProgramList();

        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(100, 100, dwProgramList);
        ElectricWaterHeaterSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridgeSpecs);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasherSpecs);
        Device dev6 = new Device("ElectricWaterHeaterSpecs", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        houseEdificioB.addRoom(room);
        houseEdificioB.addRoom(room2);

        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        ProgramList dwProgramList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(100, 100, dwProgramList);
        ElectricWaterHeaterSpecs specWaterHeater = new ElectricWaterHeaterSpecs(100, 100, 100, 0.9);
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridgeSpecs);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasherSpecs);
        Device dev6 = new Device("ElectricWaterHeaterSpecs", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        houseEdificioB.addRoom(room2);
        houseEdificioB.addRoom(room);

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
    }*/
}

