package pt.ipp.isep.dei.project.controllersTests;

class GetListOfSensorsAndDevicesRoomControllerTest {

    /*private GetListOfSensorsAndDevicesRoomController controller;
    private House housegrid;
    private RoomList roomList;
    private Room room;

    @BeforeEach
    public void StartUp() {

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        geographicalarea insertedGeoArea = new geographicalarea("Campus do ISEP", geographicalAreaType, location, areaShape);

        // Room 1
        String name = "room1";
        int houseFloor = 3;
        Dimension dimension = new Dimension(3, 3.5, 3.5);
        this.room = new Room(name, houseFloor, dimension);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.housegrid = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        this.roomList = housegrid.getRoomList();

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        housegrid.setAddress(address);
        housegrid.setInsertedGeoArea(insertedGeoArea);

        this.controller = new GetListOfSensorsAndDevicesRoomController(housegrid);
    }

    @Test
    public void getSensorsListContentOfARoomTest() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        sensor s0 = new sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        sensor s1 = new sensor("A456", dataFuncionamento1, sensorType1, locS1);

        this.room.addSensorToListOfSensorsInRoom(s0);
        this.room.addSensorToListOfSensorsInRoom(s1);
        this.housegrid.addRoom(this.room);
        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";

        // Act
        String result = this.controller.getSensorsListContent(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        this.housegrid.addRoom(room);
        int position = 0;

        // Act
        boolean result = controller.isSensorListEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        sensor s0 = new sensor("A123", dataFuncionamento0, sensorType0, locS0);

        room.addSensorToListOfSensorsInRoom(s0);
        housegrid.addRoom(room);
        int position = 0;

        // Act
        boolean result = controller.isSensorListEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        FridgeType fridgeType = new FridgeType();
        Device dev = fridgeType.createDevice("Fridge1", room);

        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        housegrid.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Fridge1\n" + "2 - Name of the device: Lamp1\n";
        // Act
        String result = controller.getDeviceListContent(position);
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        housegrid.addRoom(room);
        int position = 0;
        // Act
        boolean result = controller.isDeviceListEmpty(position);
        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        housegrid.addRoom(room);
        int position = 0;
        // Act
        boolean result = controller.isDeviceListEmpty(position);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        housegrid.addRoom(room);
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
    public void getRoomListTest() {
        // Arrange
        housegrid.addRoom(room);
        RoomList expectedResult = this.roomList;

        // Act
        RoomList result = this.controller.getListOfRooms();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomListEmptyTest() {
        //arrange
        RoomList expectResult = this.roomList;

        //act
        RoomList result = this.controller.getListOfRooms();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("RoomTwo", 1, dim1);
        this.roomList.addRoom(room);
        this.roomList.addRoom(room1);

        String expectedResult = "RoomTwo";
        int roomPos = 1;

        //Act
        String result = this.controller.getRoomNameByPosition(roomPos);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        String expectedResult = null;
        int roomPos = 0;

        //Act
        String result = this.controller.getRoomNameByPosition(roomPos);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        this.housegrid.addRoom(room1);
        this.housegrid.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = this.controller.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        String expectResult = "";

        //act
        String result = this.controller.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }*/
}