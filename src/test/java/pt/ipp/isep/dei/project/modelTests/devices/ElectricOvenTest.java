package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.devices.electricoven.ElectricOvenType;
import pt.ipp.isep.dei.project.model.house.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ElectricOvenTest {
    private Room kitchen;
    private Room laundry;
    private Device electricOven;
    private Device electricOven2;
    private Map<LocalDateTime, Double> map;
    private DeviceReading reading0;
    private DeviceReading reading1;
    private DeviceReading reading2;
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";


    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        RoomId kitchenId = new RoomId("Kitchen");
        RoomId laundryId = new RoomId("Laundry");
        kitchen = new Room(kitchenId, "room", 1, dim);
        laundry = new Room(laundryId, "room", 1, dim);

        // devices
        ElectricOvenType electricOvenType = new ElectricOvenType();
        electricOven2 = electricOvenType.createDevice("Kenmore Elite 95067");
        electricOven = electricOvenType.createDevice("Kenmore Elite 95053");
        electricOven.setLocation(kitchen);
        electricOven2.setLocation(kitchen);
        electricOven.setAttributesDevType("Time", 1);
        electricOven.setAttributesDevType("Nominal Power", 1200);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new DeviceReading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new DeviceReading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new DeviceReading(7, time2);
        electricOven.addReadingsToTheList(reading0);
        electricOven.addReadingsToTheList(reading1);
        electricOven.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void getNominalPowerTest() {
        //Arrange
        double expectedResult = 1200.0;

        //Act
        double result = electricOven.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = electricOven.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameTest() {
        // Arrange
        String expectedResult = "Kenmore Elite 95053";

        // Act
        String result = electricOven.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "ElectricOven";

        // act
        String result = electricOven.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 0;

        // Act
        double result = electricOven.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> electricOven.setName("Kenmore Elite 95053"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> electricOven.setName("Kenmore Elite 95067"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = electricOven.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = electricOven.setName("Kenmore Elite 95054");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest() {
        // Act
        boolean result = electricOven.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = electricOven.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        ElectricOvenType electricOvenType = new ElectricOvenType();
        Device maquina = electricOvenType.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 1200.0\n";
        // Act
        String result = electricOven.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Kenmore Elite 95053\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = electricOven.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeTimeSameValueTest() {
        //Act
        boolean result = electricOven.setAttributesDevType("Time", 1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void setAttributesDevTypeTimeNotValidDataTypeTest() {
        // Act
        boolean result = electricOven.setAttributesDevType("Time", "Not Valid Data Type");

        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributesDevTypeNominalPowerSameValueTest() {
        //Act
        boolean result = electricOven.setAttributesDevType("Nominal Power", 1200);
        //Assert
        assertFalse(result);
    }

    @Test
    public void setAttributesDevTypeNominalPowerNotValidDataTypeTest() {
        // Act
        boolean result = electricOven.setAttributesDevType("Nominal Power", "Not Valid Data Type");

        // Assert
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(electricOven.getName());

        // Act
        int result = electricOven.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Location location = new Location(43, 42, 2);

        // Act
        boolean result = electricOven.equals(location);

        // Assert
        assertFalse(result);
    }

    @Test
    public void equalsSameObjectTest() {

        // Act
        boolean result = electricOven.equals(electricOven);

        // Assert
        assertTrue(result);
    }

    @Test
    public void equalsDiferentElectricOvenTest() {

        // Act
        boolean result = electricOven.equals(electricOven2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 1;

        // Act
        int result = electricOven.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Kenmore Elite 95053, located in room: Kitchen\n";

        // Act
        String result = electricOven.getNameToString();

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
        double result = electricOven.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 7, 45, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = electricOven.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest2() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = electricOven.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = electricOven.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        electricOven.setDeactivateDevice();
        // act
        LocalDateTime result = electricOven.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        electricOven.setDeactivateDevice();
        // act
        String result = electricOven.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = electricOven.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // arrange
        electricOven.setDeactivateDevice();

        // Act
        boolean result = electricOven.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = electricOven.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = electricOven.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTimeTest() {
        // Assert
        double expectedResult = 1.0;

        // Act
        Object result = electricOven.getAttributeValue("Time");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueNominalPowerTest() {
        // Assert
        double expectedResult = 1200.0;

        // Act
        Object result = electricOven.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValueNotValidTest() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = electricOven.getAttributeValue("Not Valid");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // Arrange
        String expectedResult = "Double";

        // Act
        String result = electricOven.getAttributeDataType("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeactivateDevice_true (){
        boolean expectedResult = true;
        boolean result = electricOven.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeactivateDevice_false (){
        boolean expectedResult = false;
        electricOven.setDeactivateDevice();
        boolean result = electricOven.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }
}