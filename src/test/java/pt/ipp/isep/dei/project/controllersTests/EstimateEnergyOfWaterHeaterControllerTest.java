package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.EstimateEnergyOfWaterHeaterController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstimateEnergyOfWaterHeaterControllerTest {

    @Test
    public void getNumberOfWaterHeatersTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        int expectedResult = 2;

        // Act
        int result = ctrl.getNumberOfWaterHeaters();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameOfWaterHeaterTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        String expectedResult = "Bosch Tronic 3000";

        // Act
        String result = ctrl.getNameOfWaterHeater(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setColdWaterTempTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        double coldWaterTemp = 20;
        ctrl.setColdWaterTemp(0, coldWaterTemp);

        double expectedResult = 20.0;

        // Act
        Object result = electricWaterHeater.getAttributeValue("Cold-water temperature");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setVolumeOfWaterToHeatTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        double volumeOfWaterToHeat = 70;
        ctrl.setVolumeOfWaterToHeat(1, volumeOfWaterToHeat);

        double expectedResult = 70.0;

        // Act
        Object result = electricWaterHeater1.getAttributeValue("Volume of water to heat");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        double coldWaterTemp = 30;
        ctrl.setColdWaterTemp(1, coldWaterTemp);
        double volumeOfWaterToHeat = 70;
        ctrl.setVolumeOfWaterToHeat(1, volumeOfWaterToHeat);

        double expectedResult = 3989.089;

        // Act
        double result = ctrl.getEnergyConsumptionOfAWaterHeater(1);

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getTotalEnergyConsumptionTest() {
        // Arrange
        // Room Instantiation
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimensions dim1 = new Dimensions(3, 3.5, 5.5);
        Room room1 = new Room(name1, 1, dim1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        Device device = new Device("Bosch Tronic 3000", room, electricWaterHeater);
        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        Device device1 = new Device("Bosch Tronic Coiso", room1, electricWaterHeater1);

        // House Instantiation
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Controller Instantiation
        EstimateEnergyOfWaterHeaterController ctrl = new EstimateEnergyOfWaterHeaterController(house);

        double coldWaterTemp = 30;
        ctrl.setColdWaterTemp(0, coldWaterTemp);
        double volumeOfWaterToHeat = 70;
        ctrl.setVolumeOfWaterToHeat(0, volumeOfWaterToHeat);

        double coldWaterTemp1 = 30;
        ctrl.setColdWaterTemp(1, coldWaterTemp1);
        double volumeOfWaterToHeat1 = 70;
        ctrl.setVolumeOfWaterToHeat(1, volumeOfWaterToHeat1);

        double expectedResult = 9117.919;

        // Act
        double result = ctrl.getTotalEnergyConsumptionOfAllDevicesOfAType();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }
}