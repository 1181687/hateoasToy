package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest {
    private Room kitchen;
    private Room laundry;
    private Device washingMachine;
    private Map<LocalDateTime, Double> map;

    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);

        // Devices
        WashingMachineType washingMachineType = new WashingMachineType();
        washingMachineType.createDevice("Maytag 2.6", kitchen);
        washingMachine = washingMachineType.createDevice("Maytag 3.6", kitchen);
        washingMachine.setAttributesDevType("Capacity", 40);
        washingMachine.setAttributesDevType("Duration", 1);
        washingMachine.setAttributesDevType("Energy Consumption", 1);
        washingMachine.setAttributesDevType("Nominal Power", 1000);

        // Readings
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        washingMachine.addReadingsToTheList(readings0);
        washingMachine.addReadingsToTheList(readings1);
        washingMachine.addReadingsToTheList(readings2);

        // Maps
        map = new TreeMap<>();
        map.put(time0, 3.0);
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    void getNominalPowerTest() {
        //Arrange
        double expectedResult = 1000.0;

        //Act
        double result = washingMachine.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = washingMachine.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "Maytag 3.6";

        // Act
        String result = washingMachine.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Washing Machine";

        // act
        String result = washingMachine.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 0.0;

        // Act
        double result = washingMachine.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> washingMachine.setName("Maytag 3.6"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> washingMachine.setName("Maytag 2.6"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = washingMachine.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = washingMachine.setName("Maytag 4.0");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = washingMachine.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = washingMachine.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Capacity: 40\n" +
                "2 - Nominal Power: 1000.0\n";
        // Act
        String result = washingMachine.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Maytag 3.6\n" +
                "2 - Device1 Specifications\n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = washingMachine.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeTrue(){
        //Act
        boolean result = washingMachine.setAttributesDevType("Nominal Power", 1001);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setAttributesDevTypeFalse(){
        //Act
        boolean result = washingMachine.setAttributesDevType("Nominal Power", 1000);
        //Assert
        assertFalse(result);
    }

    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(washingMachine.getName());

        // Act
        int result = washingMachine.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = washingMachine.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = washingMachine.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Maytag 3.6, located in room: Kitchen\n";

        // Act
        String result = washingMachine.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithoutSolutionsTest() {
        // Arrange
        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = washingMachine.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = washingMachine.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithTwoSolutionsTest() {
        // Arrange
        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 0, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = washingMachine.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        washingMachine.setDeactivateDevice();
        // act
        LocalDateTime result = washingMachine.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        washingMachine.setDeactivateDevice();
        // act
        String result = washingMachine.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = washingMachine.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Assert
        washingMachine.setDeactivateDevice();

        // Act
        boolean result = washingMachine.getIsActive();

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
        Map<LocalDateTime, Double> result = washingMachine.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = washingMachine.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Assert
        double expectedResult = 1000.0;

        // Act
        Object result = washingMachine.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void newProgram() {
        //Arrange
        String programName = "Economic";
        double duration = 0.5;
        double energyConsumption = 12.0;
        Programmable programmable = this.washingMachine.asProgrammable();
        Program expectedResult = new Program(programName, duration, energyConsumption);
        //Act
        Program result = programmable.newProgram(programName, duration, energyConsumption);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        Program program = null;
        boolean expectedResult = false;
        Programmable programmable = this.washingMachine.asProgrammable();
        //Act
        boolean result = programmable.addProgram(program);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.washingMachine.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);
        Program programB = programmable.newProgram(programName, duration, energyConsumption);
        programmable.addProgram(programA);
        boolean expectedResult = false;

        //Act
        boolean result = programmable.addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.washingMachine.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);

        boolean expectedResult = true;

        //Act
        boolean result = programmable.addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }
}