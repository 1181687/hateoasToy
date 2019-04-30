
package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private House house;
    private Room laundry;
    private Room kitchen;
    private static final String FRIDGE_TYPE = "Fridge";
    private static final String DISHWASHER_TYPE = "DishWasher";
    private static final String LAMP_TYPE = "Lamp";
    private static final String WASHING_MACHINE_TYPE = "WashingMachine";


    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);

        // Room Instantiation
        Dimension dim = new Dimension(3, 3.5, 3.5);
        laundry = new Room("Laundry", "room", 2, dim);
        house.addRoom(laundry);
        kitchen = new Room("Kitchen", "room", 1, dim);
        house.addRoom(kitchen);

    }


    @Test
    public void getDisplayRoomTest() {
        //arrange

        String expectResult = "Name: Kitchen, House Floor: 1, Dimension - Height: 3.0, Length: 3.5, Width: 3.5";

        //act
        String result = kitchen.getRoomToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testHashCode() {
        //Arrange
        RoomId id = new RoomId("Bathroom");
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(id.getId(),"bathroom", 2,dim);

        int expectedResult = Objects.hash(id);

        // Act
        int result = room.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCodeNotEquals() {
        //Arrange
        String name = "roomOne";
        String name2 = "roomtwo";
        int housefloor = 2;
        Dimension dim = new Dimension(4, 10.5, 7.5);

        // Act
        int hash1 = Objects.hash(name, housefloor, dim);
        int hash2 = Objects.hash(name2, housefloor, dim);
        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testEqualsTrue() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room2 = new Room("Laundry", "room", 2, dim);
        //Act
        boolean result = laundry.equals(room2);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testEqualsFalse() {
        //Arrange

        //Act
        boolean result = kitchen.equals(laundry);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testEqualsFalseDifTypes() {
        //Arrange

        SensorTypeId tipo = new SensorTypeId("humidade");

        //Act
        boolean result = kitchen.equals(tipo);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testAddSensorToTheListOfSensorsInTheRoom() {
        // Arrange
        LocalDateTime dataFuncionamento = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType = new SensorTypeId("Temperatura");

        RoomSensor s1 = new RoomSensor(new SensorId("123"), "A123", dataFuncionamento, sensorType, "l/m2");

        // Act
        boolean result = laundry.addSensorToListOfSensorsInRoom(s1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void getSensorList() {
        //Arrange
        RoomSensorList sensorList = new RoomSensorList();

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
        RoomSensor s0 = new RoomSensor(new SensorId("421"), "A123", dataFuncionamento0, sensorType0, "l/m2");

        kitchen.addSensorToListOfSensorsInRoom(s0);
        sensorList.addSensor(s0);
        List<RoomSensor> expectedResult = sensorList.getListOfSensors();
        //Act
        List<RoomSensor> result = kitchen.getSensorList().getListOfSensors();
        //Assert
        assertEquals(result, expectedResult);
    }


    @Test
    public void testValidateNameNull() {
        String name = null;
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, "Room 1", 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameEmpty() {
        String name = "  ";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, "Room 1", 2, dim)
        );
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateDimensions() {
        String name = "Room 1";
        Dimension dim = null;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new Room(name, "Room 1",2, dim)
        );
        assertEquals("Dimension should not be null", exception.getMessage());
    }


    @Test
    public void testGetDeviceList() {

        Device dev1 = house.createDevice(FRIDGE_TYPE, "FridgeAriston", kitchen);
        Device dev2 = house.createDevice(WASHING_MACHINE_TYPE, "WashingMachineBosh", kitchen);
        Device dev3 = house.createDevice(DISHWASHER_TYPE, "DishWasherSpecs", kitchen);


        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev3);

        List<Device> result = kitchen.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        //initiate devices

        Device dev = house.createDevice(FRIDGE_TYPE, "Fridge1", laundry);
        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", laundry);


        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = laundry.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNominalPower() {
        // Arrange
        Device dev1 = house.createDevice(FRIDGE_TYPE, "FridgeAriston", kitchen);
        dev1.setAttributesDevType("Nominal Power", 500);

        Device dev2 = house.createDevice(WASHING_MACHINE_TYPE, "WashingMachineBosh", kitchen);
        dev2.setAttributesDevType("Nominal Power", 250);

        Device dev3 = house.createDevice(DISHWASHER_TYPE, "DishWasherSpecs", kitchen);
        dev3.setAttributesDevType("Nominal Power", 250);


        double expectedResult = 1000;

        // Act
        double result = kitchen.getNominalPower();

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        // Act
        boolean result = kitchen.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);

        // Act
        boolean result = kitchen.isDeviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void getDeviceListSize() {
        // Arrange

        house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        house.createDevice(LAMP_TYPE, "Lamp2", kitchen);


        int expectResult = 2;
        //act
        int result = kitchen.getDeviceList().size();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddDeviceDevicePointer() {
        //Arrange

        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", laundry);


        Room expectedResult = laundry;

        //act
        Room result = dev1.getLocation();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceDeviceListPointer() {
        //Arrange

        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", laundry);


        Device expectedResult = dev1;

        //act
        Device result = laundry.getDeviceList().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameToString() {
        // Arrange

        String expectedResult =
                "Room: Kitchen\n";

        // Act
        String result = kitchen.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriod() {
        // Arrange
        // Device Instantiation
        Device device = house.createDevice(FRIDGE_TYPE, "Fridgerator", kitchen);

        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        device.addReadingsToTheList(reading0);
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = kitchen.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoFullPeriods() {
        // Arrange
        // Device Instantiation
        Device device = house.createDevice(FRIDGE_TYPE, "Fridgerator", kitchen);

        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        device.addReadingsToTheList(reading0);
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = kitchen.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithoutFullPeriods() {
        // Arrange

        // Device Instantiation
        Device device = house.createDevice(FRIDGE_TYPE, "Fridgerator", kitchen);

        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        // List<Reading Configuration
        device.addReadingsToTheList(reading0);
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = kitchen.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriodDeviceListEmpty() {
        // Arrange
        double expectedResult = 0.0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = kitchen.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void deleteDeviceTrue() {
        // Arrange
        house.createDevice(LAMP_TYPE, "Lamp1", kitchen);

        // act
        boolean result = kitchen.deleteDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deleteDeviceFalse() {
        // Arrange
        // act
        boolean result = kitchen.deleteDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
         int position = 0;

        String expectedResult = "There are no devices in the device list.";

        // act
        String result = kitchen.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameByPosition() {
        // Arrange
        house.createDevice(LAMP_TYPE, "Lamp1", kitchen);

        int position = 0;

        String expectedResult = "Lamp1";

        // act
        String result = kitchen.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void deactivationDeviceTrue() {

        // Arrange
        house.createDevice(LAMP_TYPE, "Lamp1", kitchen);

        // act
        boolean result = kitchen.deactivateDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivationDeviceTwoDevicesTrue() {

        // Arrange
        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        Device dev2 = house.createDevice(LAMP_TYPE, "Lamp2", kitchen);

        // act
        boolean result = kitchen.deactivateDevice("Lamp2");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivationDeviceAlreadyDeactivatedFalse() {

        // Arrange
        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        Device dev2 = house.createDevice(LAMP_TYPE, "Lamp2", kitchen);
        dev2.setDeactivateDevice();

        // act
        boolean result = kitchen.deactivateDevice("Lamp2");

        // assert
        assertFalse(result);
    }

    @Test
    public void deactivationDeviceFalse() {
        // Arrange
        // act
        boolean result = kitchen.deactivateDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getActiveDeviceListToString_Active() {

        // Arrange
        house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        house.createDevice(LAMP_TYPE, "Lamp2", kitchen);


        String expectedResult =
                "1 - Device name: Lamp1 - ACTIVATED\n" +
                        "2 - Device name: Lamp2 - ACTIVATED\n";
        // Act
        String result = kitchen.getActiveDeactiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_OneDeviceDeactivated() {

        // Arrange

        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        Device dev2 = house.createDevice(LAMP_TYPE, "Lamp2", kitchen);

        dev1.setDeactivateDevice();

        String expectedResult =
                "1 - Device name: Lamp1 - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n" +
                        "2 - Device name: Lamp2 - ACTIVATED\n";
        // Act
        String result = kitchen.getActiveDeactiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_Deactivated() {

        // Arrange
        Device dev1 = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        Device dev2 = house.createDevice(LAMP_TYPE, "Lamp2", kitchen);


        dev1.setDeactivateDevice();
        dev2.setDeactivateDevice();

        String expectedResult =
                "1 - Device name: Lamp1 - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n" +
                        "2 - Device name: Lamp2 - DEACTIVATED at " + LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5) + "\n";
        // Act
        String result = kitchen.getActiveDeactiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDataSeries() {
        ///Arrange
        Device lamp = house.createDevice(LAMP_TYPE, "LampSpecs", kitchen);
        Device fridge = house.createDevice(FRIDGE_TYPE, "FridgeSpecs", kitchen);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        Map<LocalDateTime, Double> expectedResult = new TreeMap<>();
        expectedResult.put(time0, 6.0);
        expectedResult.put(time1, 10.0);
        expectedResult.put(time2, 14.0);

        //Act
        Map<LocalDateTime, Double> result = kitchen.getDataSeries(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void removeDevice_True() {
        // Arrange
        Device lamp = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);

        // act
        boolean result = kitchen.removeDevice(lamp);

        // assert
        assertTrue(result);
    }

    @Test
    public void removeDevice_False() {
        // Arrange
        Device lamp = house.createDevice(LAMP_TYPE, "Lamp1", kitchen);
        kitchen.removeDevice(lamp);

        // act
        boolean result = kitchen.removeDevice(lamp);

        // assert
        assertFalse(result);
    }


    @Test
    public void testAddDeviceSameDevice_Exception() {

        Device lamp = house.createDevice(LAMP_TYPE, "Lamp", kitchen);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                kitchen.addDevice(lamp)

        );
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void testAddDeviceNull_Exception() {
        Throwable exception = assertThrows(RuntimeException.class, () ->
                kitchen.addDevice(null)

        );
        assertEquals("Device is null.", exception.getMessage());
    }


    @org.junit.jupiter.api.Test
    public void testAddDeviceTrue() {
        Device lamp = house.createDevice(LAMP_TYPE, "Lamp", laundry);

        // act
        boolean result = kitchen.addDevice(lamp);

        // assert
        assertTrue(result);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        Device device = house.createDevice("Fridge", "fridge", kitchen);
        Reading reading1 = new Reading(20, LocalDateTime.now());
        Reading reading2 = new Reading(25, LocalDateTime.now());
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading2);
        //Act
        List<Reading> result = kitchen.getReadings();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsDeviceNameExistant_NoDevices_ReturnFalse() {
        //Arrange
        //Act
        boolean result = kitchen.isDeviceNameExistant("Fridge");
        //Assert
        assertFalse(result);
    }

    @Test
    public void getHouseGridId(){
        HouseGridId id = new HouseGridId("main grid");

        HouseGrid grid = new HouseGrid(id);
    }
}
