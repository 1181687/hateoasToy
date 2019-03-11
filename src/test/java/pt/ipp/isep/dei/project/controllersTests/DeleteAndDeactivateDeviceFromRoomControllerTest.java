package pt.ipp.isep.dei.project.controllersTests;

class DeleteAndDeactivateDeviceFromRoomControllerTest {

    /*private DeleteAndDeactivateDeviceFromRoomController controller;
    private House housegrid;

    private static final String ATTRIBUTE_FREEZER_CAPACITY = "freezer Capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        geographicalarea insertedGeoArea = new geographicalarea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.housegrid = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        housegrid.setAddress(address);
        housegrid.setInsertedGeoArea(insertedGeoArea);

        this.controller = new DeleteAndDeactivateDeviceFromRoomController(housegrid);
    }

    @Test
    public void testGetDisplayRoomListTest() {
        //arrange

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        housegrid.addRoom(room1);
        housegrid.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        int position = 0;
        controller.getRoomPosition(position);

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmptyTest() {
        //arrange
        String expectResult = "";

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        housegrid.addRoom(room);

        int position = 0;
        controller.getRoomPosition(position);

        // Act
        boolean result = controller.deviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeAriston", room);
        fridge.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, 100);
        fridge.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);


        housegrid.addRoom(room);

        int position = 0;
        controller.getRoomPosition(position);

        // Act
        boolean result = controller.deviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        housegrid.addRoom(room1);
        housegrid.addRoom(room2);

        int expectResult = 2;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void deleteDeviceTrue() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeAriston", room);
        fridge.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, 100);
        fridge.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);


        int position = 0;
        housegrid.addRoom(room);
        controller.getRoomPosition(position);

        // act
        boolean result = controller.deleteDevice("FridgeAriston");

        // assert
        assertTrue(result);
    }

    @Test
    public void deleteDeviceFalse() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;
        housegrid.addRoom(room);
        controller.getRoomPosition(position);
        // act
        boolean result = controller.deleteDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;

        housegrid.addRoom(room);
        controller.getRoomPosition(position);
        String expectedResult = "There are no devices in the device list.";

        // act
        String result = controller.deviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameByPosition() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeAriston", room);
        fridge.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, 100);
        fridge.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, 100);
        fridge.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);

        int position = 0;

        String expectedResult = "FridgeAriston";
        housegrid.addRoom(room);
        controller.getRoomPosition(position);

        // act
        String result = controller.deviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("FridgeAriston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, 100);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, 100);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, 100);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);

        Device fridge2 = fridgeType.createDevice("fridge Bosch", room);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, 100);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, 100);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, 100);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);

        int position = 0;
        housegrid.addRoom(room);

        controller.getRoomPosition(position);

        int expectResult = 2;
        //act
        int result = controller.deviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //act
        boolean result = controller.roomListEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        housegrid.addRoom(room1);
        //act
        boolean result = controller.roomListEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;

        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = fridgeType.createDevice("fridge Bosch", room);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        String expectedResult =
                "1 - Name of the device: fridge Ariston\n" +
                        "2 - Name of the device: fridge Bosch\n";

        int position = 0;
        housegrid.addRoom(room);

        housegrid.addRoom(room);
        housegrid.addRoom(room);
        housegrid.addRoom(room);
        controller.getRoomPosition(position);
        // Act
        String result = controller.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void deactivateDeviceTrue() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;

        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        int position = 0;
        this.housegrid.addRoom(room);
        this.controller.getRoomPosition(position);

        // act
        boolean result = this.controller.deactivateDevice("fridge Ariston");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivateDeviceFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;
        this.housegrid.addRoom(room);
        this.controller.getRoomPosition(position);
        // act
        boolean result = this.controller.deactivateDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDevice() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;

        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device expectedResult = fridge1;

        int position = 0;
        housegrid.addRoom(room);

        controller.getRoomPosition(position);
        // Act
        Device result = controller.getDevice(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_Active() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);


        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = fridgeType.createDevice("fridge Bosch", room);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        housegrid.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Device name: fridge Ariston - ACTIVATED\n" +
                        "2 - Device name: fridge Bosch - ACTIVATED\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_OneDeviceDeactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = fridgeType.createDevice("fridge Bosch", room);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);
        fridge1.setDeactivateDevice();
        housegrid.addRoom(room);
        int position = 0;

        String expectedResult =
                "1 - Device name: fridge Ariston - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n" +
                        "2 - Device name: fridge Bosch - ACTIVATED\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_Deactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        FridgeType fridgeType = new FridgeType();
        Device fridge1 = fridgeType.createDevice("fridge Ariston", room);
        fridge1.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge1.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge1.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        Device fridge2 = fridgeType.createDevice("fridge Bosch", room);
        fridge2.setAttributesDevType(ATTRIBUTE_FREEZER_CAPACITY, freezerCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        fridge2.setAttributesDevType(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        fridge2.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        int position = 0;

        fridge1.setDeactivateDevice();
        fridge2.setDeactivateDevice();
        housegrid.addRoom(room);

        String expectedResult =
                "1 - Device name: fridge Ariston - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n" +
                        "2 - Device name: fridge Bosch - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }*/
}