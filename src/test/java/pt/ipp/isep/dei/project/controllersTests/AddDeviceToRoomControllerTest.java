package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDeviceToRoomControllerTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        String expectResult = "";

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void getSelectedRoom() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(name1, houseFloor1, dimension1);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);
        int position = 0;
        controller.getRoom(position);

        Room expectedResult = room;

        // act
        Room result = controller.getSelectedRoom();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room1);
        house.addRoom(room2);

        int expectResult = 2;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }
/*
    @Test
    void getDeviceTypeListToString() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimensions1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        house.addRoom(room1);
        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- Dish Washer\n" +
                "4- Washing Machine\n" +
                "5- Electric Water Heater\n";
        //Act
        String result = controller.getDeviceTypeListToString();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testNewFrigde() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Fridge from Kitchen";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room Gabis", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device(name, room, fridgeSpecs);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);

        Device expectedResult = d2;

        controller.getRoom(0);
        controller.getDeviceList();
        controller.createNewFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        // act

        Device result = controller.getDevice(0);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFridgeNegative(){

    RoomList rList = new RoomList();
    HouseGridList gridlist = new HouseGridList();
    Location local = new Location(10, 10, 10);
    Address adr = new Address("5000", local);
    AreaShape areaShape = new AreaShape(20, 20, local);
    GeoAreaType geoAreaType = new GeoAreaType("Cidade");
    GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
    House house = new House(rList, gridlist, adr, insertedGeoArea);
    String name = "Fridge Balay";

    Dimension dim = new Dimension(3, 3.5, 3.5);
    Room room = new Room("Room", 2, dim);
    double nominalPower = 200;
    double annualEnergyConsumption = 1000;
    double freezerCapacity = 20;
    double refrigeratorCapacity = 50;
    DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

    Device d2 = new Device("Fridge Balay", room, fridgeSpecs);


        room.addDevice(d2);
        house.addRoom(room);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        controller.getRoom(0);
        controller.getDeviceList();

        Throwable exception = assertThrows(RuntimeException.class, () ->
            controller.createNewFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity)
    );

    assertEquals("Name already exists. Please write a new one.", exception.getMessage());
}

    @Test
    public void testNewLamp() {
        String name = "Lamp one";

        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;
        DeviceSpecs lamp = new Lamp(luminousFlux, nominalPower);
        ProgramList programList = new ProgramList();

        Device d2 = new Device("Device2", room, lamp);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);
        Device expectedResult = new Device(name, room, lamp);

        Device result = controller.createNewLamp(name, room, luminousFlux, nominalPower);

        assertEquals(expectedResult, result);
    }

    /* @Test
    public void testNewLampNegative() {
        String name = "Lamp one";

        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);

        Device d2 = new Device("LAMP ONE", room, lamp);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewLamp(name, room, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewWashingMachine() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Device2", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

        Device expectedResult = new Device(name, room, washingMachine);

        Device result = controller.createNewWashingMachine(name, room, nominalPower, capacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewWashingMachineNegative() throws RuntimeException {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Washing Machine Bosh", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);
    }

    @Test
    public void newElectricWaterHeater() {

        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        Device expectedResult = new Device("Electric", room, electricWaterHeater1);
        String name = "Electric";

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

        Device result = controller.createNewElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() throws RuntimeException {

        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        String name = "ELECTRIC2";

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

        room.addDevice(d2);



        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio)
        );

       assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }


    @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(dev);
        room.addDevice(dev1);
        house.addRoom(room);

        // Act
        String result = controller.getDeviceListContentOfARoom(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void createNewProgram() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        Device expectedResult = new Device("Electric", room, electricWaterHeater1);
        String name = "Electric";

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

         String programName = "program1";
         double duration = 10.2;
         double energyConsuption = 15.0;
         Program program = new Program(programName, duration, energyConsuption);
         // act
         Program result = controller.createNewProgram(programName, duration, hotWaterTemp0);
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void addProgramToList() {


    }

    @Test
    void getmProgramList() {


    }*/
}