package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetDevicesInHouseGridControllerTest {


    /*@Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room1, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing, 300);
        Device dev3 = new Device("DishWasher", room1, specDishWasher, 400);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater();
        Device dev4 = new Device("FridgeSiemens", room2, specFridge, 300);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher, 400);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater, 25);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        //add to Lists
        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGridToTheList(houseGrid);
        houseGridList1.addHouseGridToTheList(houseGridEmpty);

        //house
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        String expectedResult = "Dish Washer\n- Device Name: DishWasher, Location: Kitchen.\n- Device Name: DishWasherTeka, Location: KitchenBasement.\n\nElectric Water Heater\n- Device Name: ElectricWaterHeater, Location: KitchenBasement.\n\nWashing Machine\n- Device Name: WashingMachineBosh, Location: Kitchen.\n\nFridge\n- Device Name: FridgeAriston, Location: Kitchen.\n- Device Name: FridgeSiemens, Location: KitchenBasement.\n\n";


        String result = ctrl.getDeviceListContentNameTypeLocationByHG(0);

        assertEquals(expectedResult, result);
    }*/
}

