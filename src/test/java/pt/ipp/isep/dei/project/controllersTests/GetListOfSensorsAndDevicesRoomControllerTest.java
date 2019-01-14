package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfSensorsAndDevicesRoomControllerTest {

    @Test
    public void getSensorsListContentOfARoomTest () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House (roomList, listHG, address, geo);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("A456", dataFuncionamento1, sensorType1, locS1);

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);

        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);

        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = controller.getSensorsListContent(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House (roomList, listHG, address, geo);

        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);

        int position = 0;
        // Act
        boolean result = controller.checkIfListIsEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse () {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House (roomList, listHG, address, geo);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);

        int position = 0;
        // Act
        boolean result = controller.checkIfListIsEmpty(position);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

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


        room.addDevice(dev);
        room.addDevice(dev1);

        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";
        // Act
        String result = controller.getDeviceListContent(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);


        int position = 0;
        // Act
        boolean result = controller.checkIfDeviceListIsEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);
        house.addRoom(room);

        GetListOfSensorsAndDevicesRoomController controller = new GetListOfSensorsAndDevicesRoomController(house);


        int position = 0;
        // Act
        boolean result = controller.checkIfDeviceListIsEmpty(position);

        // Assert
        assertFalse(result);
    }

}