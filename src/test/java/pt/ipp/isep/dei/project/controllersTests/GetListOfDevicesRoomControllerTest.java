package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetListOfDevicesRoomController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class GetListOfDevicesRoomControllerTest {

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
        double annualEnergyConsumption = 3000.0;
        DeviceSpecs deviceSpecs = new Fridge("Fridge", freezerCapacity, refrigeratorCapacity, annualEnergyConsumption);
        double nominalPower = 100.5;
        Device dev = new Device("Fridge1", room, deviceSpecs, nominalPower);

        double luminousFlux = 10.0;
        double energyConsumption1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp("Lamp", luminousFlux, energyConsumption1);
        double nominalPower1 = 1.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);


        room.addDevice(dev);
        room.addDevice(dev1);

        house.addRoom(room);

        GetListOfDevicesRoomController controller = new GetListOfDevicesRoomController(house);

        int position = 0;
        String expectedResult =
                "1 - Name: Fridge1\n" +
                        "2 - Name: Lamp1\n";
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

        GetListOfDevicesRoomController controller = new GetListOfDevicesRoomController(house);

        int position = 0;
        // Act
        boolean result = controller.checkIfListIsEmpty(position);

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
        double energyConsumption1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp("Lamp", luminousFlux, energyConsumption1);
        double nominalPower1 = 1.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);

        room.addDevice(dev1);
        house.addRoom(room);

        GetListOfDevicesRoomController controller = new GetListOfDevicesRoomController(house);

        int position = 0;
        // Act
        boolean result = controller.checkIfListIsEmpty(position);

        // Assert
        assertFalse(result);
    }

}
