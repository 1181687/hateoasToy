package pt.ipp.isep.dei.project.modelTests;
/*

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.MeasurableList;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeasurableListTest {
    private House house;
    private Device dev1;
    private Device dev2;
    private Device dev3;
    private Room room1;
    private Room room2;
    private MeasurableList mList;
    private static final String FRIDGE_TYPE = "Fridge";
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";
    private static final String DISHWASHER_TYPE = "DishWasher";
    private static final String LAMP_TYPE = "Lamp";
    private static final String WASHING_MACHINE_TYPE = "WashingMachine";


    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        this.house.setAddress(address);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        this.room1 = new Room("Room1", 2, dim);
        this.room2 = new Room("Room2", 2, dim);

        this.dev1 = house.createDevice(FRIDGE_TYPE, "FridgeAriston", room1);
        dev1.setAttributesDevType("Nominal Power",500);

        this.dev2 = house.createDevice(WASHING_MACHINE_TYPE, "WashingMachine", room1);
        dev2.setAttributesDevType("Nominal Power",250);

        this.dev3 = house.createDevice(DISHWASHER_TYPE, "DishWasher", room2);
        dev3.setAttributesDevType("Nominal Power",250);

        this.mList = new MeasurableList();
    }


    @Test
    void getNominalPower() {
        // Arrange
        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        double expectedResult = 750;

        // Act
        double result = mList.getNominalPower();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMeasurableObjIsInListTrue() {
        // Arrange
        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(dev1);

        // assert
        assertTrue(result);
    }

    @Test
    void checkIfMeasurableObjIsInListFalse() {
        // Arrange
        mList.addMeasurable(room2);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(dev1);

        // assert
        assertFalse(result);
    }

    @Test
    void testGetListToString() {
        // Arrange
        mList.addMeasurable(room2);
        mList.addMeasurable(dev1);

        String expectedResult = "Room: Room2\nDevice: FridgeAriston, located in room: Room1\n";

        // act
        String result = mList.getListToString();

        // assert
        assertEquals(expectedResult, result);
    }
}*/