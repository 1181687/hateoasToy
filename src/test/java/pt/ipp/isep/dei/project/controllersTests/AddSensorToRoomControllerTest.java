package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToRoomControllerTest {

    @Test
    void testDisplayRoomsInTheHouse() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);

        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);


        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        //act
        String result = addSensorToRoomController.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void displayListOfSensorsType() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Type of sensor
        SensorType sensorType = new SensorType("Temperatura");

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);


        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        String expectedResult = "1 - Sensor Type: Temperatura\n";

        // Act
        String result = addSensorToRoomController.displayListOfSensorsType();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void createAndAddSensorToTheList() {
        // Arrange

        // Sensor
        SensorType sensorType = new SensorType("Temperatura");
        String sensorName = "A123";

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        // Room list
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);

        // House
        HouseGridList houseGridList = new HouseGridList();
        Location location = new Location(10,10,10);
        Address address = new Address("5000", location);
        AreaShape areaShape = new AreaShape(20, 20, location);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        addSensorToRoomController.getRoomByIndex(0);
        addSensorToRoomController.getSensorTypeByIndex(0);
        addSensorToRoomController.getLocationOfTheHouse();

        // Act
        boolean result = addSensorToRoomController.createAndAddSensorToTheList(sensorName);

        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyPositive() {
        // Arrange
        RoomList roomList = new RoomList();

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        // Act
        boolean result = addSensorToRoomController.isRoomListEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyNegative() {
        // Arrange
        RoomList roomList = new RoomList();

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        // Act
        boolean result = addSensorToRoomController.isRoomListEmpty();
        // Assert
        assertFalse(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyPositive() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        // Act
        boolean result = addSensorToRoomController.isSensorTypeListEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyNegative() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, gridlist, adr, insertedGeoArea);

        // Tipo de sensor
        SensorType sensorType = new SensorType("Temperatura");

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        AddSensorToRoomController addSensorToRoomController = new AddSensorToRoomController(listSensorsType, roomList, house);

        // Act
        boolean result = addSensorToRoomController.isSensorTypeListEmpty();
        // Assert
        assertFalse(result);
    }
}