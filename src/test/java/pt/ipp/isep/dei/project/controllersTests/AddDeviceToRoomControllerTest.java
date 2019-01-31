package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddDeviceToRoomControllerTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void getDeviceTypeListToString() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimensions1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);
        house.addRoom(room1);


        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        controller.getRoom(0);
        controller.getDeviceList();

        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- DishWasher\n" +
                "4- WashingMachine\n" +
                "5- ElectricWaterHeater\n";
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "Fridge - Kitchen";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room Gabis", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);


        controller.getRoom(0);
        controller.getDeviceList();
        Device d2 = controller.createNewFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);
        Device expectedResult = d2;

        // act

        Device result = controller.getDevice(0);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFridgeNegative() {

        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name = "FridgeSpecs - Kitchen";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room Gabis", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);


        controller.getRoom(0);
        controller.getDeviceList();
        controller.createNewFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        Throwable exception = assertThrows(RuntimeException.class, () -> controller.createNewFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity));

    assertEquals("Name already exists. Please write a new one.", exception.getMessage());
}

    @Test
    public void testNewLamp() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);

        controller.getRoom(0);
        controller.getDeviceList();
        Device device = controller.createNewLamp("Lamp1", nominalPower, luminousFlux);
        Device expectedResult = device;

        //Act
        Device result = controller.getDevice(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);

        controller.getRoom(0);
        controller.getDeviceList();
        controller.createNewLamp("Lamp1", nominalPower, luminousFlux);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewLamp("Lamp1", nominalPower, luminousFlux)
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);

        controller.getRoom(0);
        controller.getDeviceList();
        Device device = controller.createNewWashingMachine(name, nominalPower, capacity);

        Device expectedResult = device;
        //Act
        Device result = controller.getDevice(0);

        //Assert
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
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);

        controller.getRoom(0);
        controller.getDeviceList();
        controller.createNewWashingMachine(name, nominalPower, capacity);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewWashingMachine(name, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void newElectricWaterHeater() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);


        controller.getRoom(0);
        controller.getDeviceList();
        Device device = controller.createNewElectricWaterHeater("EWH1", hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Device expectedResult = device;
        //Act
        Device result = controller.getDevice(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() throws RuntimeException {

        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        house.addRoom(room);


        controller.getRoom(0);
        controller.getDeviceList();
        controller.createNewElectricWaterHeater("EWH1", hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);


        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewElectricWaterHeater("EWH1", hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }


    @Test
    public void testGetDeviceListContentOfARoomTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
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
    void testCreateNewProgram() {
        // arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        String name = "Electric";
        Device d2 = new Device(name, room, electricWaterHeater1);

        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);
        room.addDevice(d2);
        house.addRoom(room);

        String programName = "program1";
        double duration = 10.2;
        double energyConsuption = 15.0;
        Program expectedResult = new Program(programName, duration, energyConsuption);
        // act
        Program result = controller.createNewProgram(programName, duration, hotWaterTemp0);
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void addProgramToListFalse() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Program program = null;
        boolean expectedResult = false;
        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);


        //Act
        boolean result = controller.addProgramToList(program);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void addProgramToListTrue() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        ProgramList programList = new ProgramList();

        Program program1 = new Program("P1", 15, 5);
        Program program2 = new Program("P2", 15, 5);
        programList.addProgram(program1);
        boolean expectedResult = true;
        AddDeviceToRoomController controller = new AddDeviceToRoomController(house);

        //Act
        boolean result = controller.addProgramToList(program2);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetNumberOfDeviceTypes() {
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        AddDeviceToRoomController ctrl = new AddDeviceToRoomController(house);

        int expectedResult = 5;

        int result = ctrl.getNumberOfDeviceTypes();

        assertEquals(expectedResult, result);

    }

}