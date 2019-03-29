package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.*;

public class ElectricWaterHeaterTest {
    private Room kitchen;
    private Room laundry;
    private Device electricWaterHeater;
    private House house;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";


    @Before
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

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);

        house.addRoom(kitchen);
        house.addRoom(laundry);

        // devices
        house.createDevice(ELECTRIC_W_H_TYPE, "Bosch Tronic 2000", kitchen);
        electricWaterHeater = house.createDevice(ELECTRIC_W_H_TYPE,"Bosch Tronic 3000", kitchen);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 55);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Nominal Power", 700);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        electricWaterHeater.addReadingsToTheList(reading0);
        electricWaterHeater.addReadingsToTheList(reading1);
        electricWaterHeater.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<Reading> result = electricWaterHeater.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void getNominalPowerTest() {
        //Arrange
        double expectedResult = 700.0;

        //Act
        double result = electricWaterHeater.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = electricWaterHeater.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameTest() {
        // Arrange
        String expectedResult = "Bosch Tronic 3000";

        // Act
        String result = electricWaterHeater.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "ElectricWaterHeater";

        // act
        String result = electricWaterHeater.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 0;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    /*@Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> electricWaterHeater.setName("Bosch Tronic 3000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> electricWaterHeater.setName("Bosch Tronic 2000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }*/

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = electricWaterHeater.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = electricWaterHeater.setName("Bosch Tronic 4000");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest() {
        // Act
        boolean result = electricWaterHeater.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = electricWaterHeater.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device maquina = electricWaterHeaterType.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Hot Water Temperature: 55.0\n" +
                "2 - Performance Ratio: 0.9\n" +
                "3 - Nominal Power: 700.0\n";
        // Act
        String result = electricWaterHeater.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Bosch Tronic 3000\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = electricWaterHeater.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeTrue(){
        //Act
        boolean result = this.electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 50);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setAttributesDevTypeFalse(){
        //Act
        boolean result = this.electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 55);
        //Assert
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(electricWaterHeater.getName());

        // Act
        int result = electricWaterHeater.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = electricWaterHeater.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = electricWaterHeater.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Bosch Tronic 3000, located in room: Kitchen\n";

        // Act
        String result = electricWaterHeater.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithoutSolutionsTest() {
        // Arrange
        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithThreeSolutionsTest() {
        // Arrange
        double expectedResult = 15;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 23, 23, 45, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        electricWaterHeater.setDeactivateDevice();
        // act
        LocalDateTime result = electricWaterHeater.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        electricWaterHeater.setDeactivateDevice();
        // act
        String result = electricWaterHeater.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = electricWaterHeater.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalseTest() {
        // Assert
        electricWaterHeater.setDeactivateDevice();

        // Act
        boolean result = electricWaterHeater.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = electricWaterHeater.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Hot-Water Temperature");
        expectedResult.add("Performance Ratio");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = electricWaterHeater.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValueTest() {
        // Assert
        double expectedResult = 700.0;

        // Act
        Object result = electricWaterHeater.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }
    @Test
    public void testSetDeactivateDevice_true (){
        boolean expectedResult = true;
        boolean result = electricWaterHeater.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeactivateDevice_false (){
        boolean expectedResult = false;
        electricWaterHeater.setDeactivateDevice();
        boolean result = electricWaterHeater.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

}