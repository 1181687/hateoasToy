package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.devices.winecooler.WineCoolerType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WineCoolerTest {
    private Room kitchen;
    private Room livingRoom;
    private Device wineCooler;
    private Map<LocalDateTime, Double> map;
    private DeviceReading reading0;
    private DeviceReading reading1;
    private DeviceReading reading2;

    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        RoomId kitchenId = new RoomId("Kitchen");
        RoomId laundryId = new RoomId("Laundry");
        kitchen = new Room(kitchenId, "room", 1, dim);
        livingRoom = new Room(laundryId, "Living Room", 1, dim);

        // devices
        WineCoolerType wineCoolerType = new WineCoolerType();
        Device dummyWineCooler = wineCoolerType.createDevice("Awesome Wine Cooler");

        // Freezer
        wineCooler = wineCoolerType.createDevice("Even More Awesome Wine Cooler");
        wineCooler.setLocation(kitchen);
        dummyWineCooler.setLocation(kitchen);
        wineCooler.setAttributesDevType("Nominal Power", 90.0);
        wineCooler.setAttributesDevType("Number of Bottles", 36);
        wineCooler.setAttributesDevType("Annual Energy Consumption", 9000.0);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new DeviceReading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new DeviceReading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new DeviceReading(7, time2);
        wineCooler.addReadingsToTheList(reading0);
        wineCooler.addReadingsToTheList(reading1);
        wineCooler.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = wineCooler.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameTest() {
        // Arrange
        String expectedResult = "Even More Awesome Wine Cooler";

        // Act
        String result = wineCooler.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "WineCooler";

        // act
        String result = wineCooler.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        //9000/365
        double expectedResult = 24.66;

        // Act
        double result = wineCooler.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void setNameWithSameNameTestAwesomeWineCooler() {
        Throwable exception = assertThrows(RuntimeException.class, () -> wineCooler.setName("Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameNameTestEvenMoreAwesomeWineCooler() {
        Throwable exception = assertThrows(RuntimeException.class, () -> wineCooler.setName("Even More Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestWineCooler1() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDevice("Awesome Wine Cooler").setName("Even More Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestWineCooler2() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDevice("Even More Awesome Wine Cooler").setName("Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }


    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = wineCooler.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = wineCooler.setName("The Best Wine Cooler");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest() {
        // Act
        boolean result = wineCooler.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = wineCooler.setLocation(livingRoom);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        WineCoolerType type = new WineCoolerType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(kitchen);


        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest2() {
        // Act
        Room location = kitchen;
        boolean expectedResult = false;

        //Act
        boolean result = wineCooler.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetLocation_AnotherLocation_True() {
        //Arrange
        Room location = livingRoom;
        boolean expectedResult = true;

        //Act
        boolean result = wineCooler.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Number of Bottles: 36\n" +
                "3 - Annual Energy Consumption: 9000.0\n";
        // Act
        String result = wineCooler.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Even More Awesome Wine Cooler\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";

        // Act
        String result = wineCooler.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeFalse() {
        //Act
        boolean result = wineCooler.setAttributesDevType("Refrigerator Capacity", 20);
        //Assert
        assertFalse(result);
    }


    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(wineCooler.getName());

        // Act
        int result = wineCooler.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = wineCooler.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = wineCooler.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Even More Awesome Wine Cooler, located in room: Kitchen\n";

        // Act
        String result = wineCooler.getNameToString();

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
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setDeactivateDeviceTrue() {

        boolean result = wineCooler.setDeactivateDevice();
        assertTrue(result);
    }

    @Test
    public void setDeactivateDeviceAlreadyDeactivatedFalse() {

        wineCooler.setDeactivateDevice();
        boolean result = wineCooler.setDeactivateDevice();
        assertFalse(result);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        wineCooler.setDeactivateDevice();
        // act
        LocalDateTime result = wineCooler.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        wineCooler.setDeactivateDevice();
        // act
        String result = wineCooler.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = wineCooler.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalseTest() {
        // Assert
        wineCooler.setDeactivateDevice();

        // Act
        boolean result = wineCooler.getIsActive();

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
        Map<LocalDateTime, Double> result = wineCooler.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");
        expectedResult.add("Number of Bottles");
        expectedResult.add("Annual Energy Consumption");

        // Act
        List<String> result = wineCooler.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValueTest() {
        // Assert
        double expectedResult = 90.0;

        // Act
        Object result = wineCooler.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Number of Bottles: 36\n" +
                "3 - Annual Energy Consumption: 9000.0\n";
        // Act
        String result = wineCooler.getSpecsToString();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<DeviceReading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<DeviceReading> result = wineCooler.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNominalPowerTest() {
        //Arrange
        double expectedResult = 90.0;

        //Act
        double result = wineCooler.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testIfDeviceIsActiveTrue() {
        //Arrange
        //Act
        boolean result = wineCooler.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    public void testIfDeviceIsActiveFalse() {
        //Arrange
        wineCooler.setDeactivateDevice();
        //Act
        boolean result = wineCooler.getIsActive();
        //Assert
        assertFalse(result);
    }

}
