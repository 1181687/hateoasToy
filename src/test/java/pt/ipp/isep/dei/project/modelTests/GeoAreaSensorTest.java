package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GeoAreaSensorTest {

    private GeoAreaSensor temperatureSensor;
    private Reading reading;
    private Reading reading1;

    @BeforeEach
    public void StartUp() {
        // GeoAreaSensor
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        SensorId sensorId = new SensorId("R003");
        Location location = new Location(123, 345, 50);
        temperatureSensor = new GeoAreaSensor(sensorId, "A123", startingDate, sensorTypeId, location, "l/m2");

        // Readings
        LocalDateTime dateTime = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        reading = new Reading(11, dateTime);
        temperatureSensor.addReadingsToList(reading);
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        reading1 = new Reading(14, dateTime1);
        temperatureSensor.addReadingsToList(reading1);
    }

    @Test
    public void setAndGetId() {
        //Arrange
        String id = "GeoAreaSensor";
        SensorId sensorId = new SensorId(id);
        //Act
        this.temperatureSensor.setId(sensorId);
        SensorId result = this.temperatureSensor.getId();
        //Assert
        assertEquals(sensorId, result);
    }

    @Test
    public void getSensorName() {
        // Arrange
        String expectedResult = "A123";
        //Act
        String result = this.temperatureSensor.getSensorName();
        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void getStartingDate() {
        // Arrange
        LocalDateTime expectedResult = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        // Act
        LocalDateTime result = this.temperatureSensor.getStartingDate();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorType() {
        // Arrange
        SensorTypeId expectedResult = new SensorTypeId("Temperature");
        // Act
        SensorTypeId result = this.temperatureSensor.getSensorType();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLocation() {
        //Arrange
        Location expectedResult = new Location(123, 345, 50);
        //Act
        Location result = this.temperatureSensor.getLocation();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getUnits() {
        //Arrange
        String units = "3m/s2";
        //Act
        this.temperatureSensor.setUnits(units);
        String result = this.temperatureSensor.getUnits();
        //Assert
        assertEquals(units, result);
    }


    @Test
    public void isActive_ShouldReturnTrue() {
        //Act
        boolean result = this.temperatureSensor.isActive();
        //Assert
        assertTrue(result);
    }

    @Test
    public void deactivateDevice() {
        //Act
        boolean result = this.temperatureSensor.deactivateDevice();
        //Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_SameObject() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean result = this.temperatureSensor.equals(this.temperatureSensor);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsTest_ShouldReturnFalse() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperatura");
        SensorType sensorType = new SensorType(sensorTypeId);
        boolean expectedResult = false;
        //Act
        boolean result = this.temperatureSensor.equals(sensorType);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCode() {
        //Arrange
        int expectedResult = Objects.hash(this.temperatureSensor.getId());

        // Act
        int result = this.temperatureSensor.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCodeNotEquals() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        Location locS1 = new Location(40, 10, 20);
        SensorId sensorId = new SensorId("R");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento, sensorTypeId, locS1, "l/m2");


        // Act
        int hash1 = Objects.hash(this.temperatureSensor.getId());
        int hash2 = Objects.hash(s1.getId());
        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    public void distanceBetweenTwoLocationsTest() {
        //Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        Location locS1 = new Location(40, 10, 20);
        SensorId sensorId = new SensorId("R");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento, sensorTypeId, locS1, "l/m2");

        Location locS2 = new Location(30, 15, 10);
        SensorId sensorId1 = new SensorId("R1");
        GeoAreaSensor s2 = new GeoAreaSensor(sensorId1, "A123", dataFuncionamento, sensorTypeId, locS2, "l/m2");

        double expectedResult = 1201040.7956;

        double result = s1.distanceBetweenTwoLocations(s2);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getMeasurementValueBetweenDatesTest() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(-5.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(-2.0, data7);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>();

        expectedResult.add(-2.0);
        expectedResult.add(-4.0);
        expectedResult.add(-2.0);
        expectedResult.add(-5.0);
        expectedResult.add(-2.0);

        LocalDate searchDate = LocalDate.of(2018, 11, 4);
        LocalDate searchFinalDate = LocalDate.of(2018, 11, 8);

        //Act
        List<Double> result = this.temperatureSensor.getMeasurementValueBetweenDates(searchDate, searchFinalDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void existReadingsBetweenDatesTest_ShouldReturnTrue() {
        //Arrange
        LocalDate searchDate = LocalDate.of(2018, 11, 2);
        LocalDate searchFinalDate = LocalDate.of(2018, 11, 8);

        //Act
        boolean result = this.temperatureSensor.existReadingsBetweenDates(searchDate, searchFinalDate);

        //Assert
        assertTrue(result);
    }

    @Test
    public void existReadingsBetweenDatesTest_ShouldReturnFalse() {
        //Arrange
        LocalDate searchDate = LocalDate.of(2015, 11, 4);
        LocalDate searchFinalDate = LocalDate.of(2016, 11, 8);

        //Act
        boolean result = this.temperatureSensor.existReadingsBetweenDates(searchDate, searchFinalDate);

        //Assert
        assertFalse(result);
    }

    @Test
    public void checkMeasurementExistenceBetweenDates_ShouldReturnTrue() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(19, date1);
        Reading reading2 = new Reading(20.1, date2);
        Reading reading3 = new Reading(21.7, date3);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        boolean result = this.temperatureSensor.checkMeasurementExistenceBetweenDates(date1.toLocalDate(), date2.toLocalDate());

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkMeasurementExistenceBetweenDates_ShouldReturnFalse() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2010, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2010, 8, 15, 6, 02, 0);

        // Act
        boolean result = this.temperatureSensor.checkMeasurementExistenceBetweenDates(date1.toLocalDate(), date2.toLocalDate());

        // Assert
        assertFalse(result);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(19, date1);
        Reading reading2 = new Reading(20.1, date2);
        Reading reading3 = new Reading(21.7, date3);

        double expectedResult = 19;
        LocalDate dayOfMonth = LocalDate.of(2017, 8, 5);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest_AnyReading() {
        // Arrange
        double expectedResult = Double.NaN;
        LocalDate dayOfMonth = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);

        // Act
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest_AnotherMonth() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(20.5, date1);
        Reading reading2 = new Reading(19, date2);
        Reading reading3 = new Reading(21.7, date3);

        double expectedResult = 19;
        LocalDate dayOfMonth = LocalDate.of(2017, 8, 5);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest_AnotherSecondMonth() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(19, date1);
        Reading reading2 = new Reading(22, date2);
        Reading reading3 = new Reading(19, date3);

        double expectedResult = 19;
        LocalDate dayOfMonth = LocalDate.of(2017, 8, 5);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest_AllEquals() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(19, date1);
        Reading reading2 = new Reading(19, date2);
        Reading reading3 = new Reading(19, date3);

        double expectedResult = 19;
        LocalDate dayOfMonth = LocalDate.of(2017, 8, 5);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSmallestMeasurementOfMonthTest_LastDifferent() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2017, 8, 15, 5, 30, 0);
        LocalDateTime date2 = LocalDateTime.of(2017, 8, 15, 6, 02, 0);
        LocalDateTime date3 = LocalDateTime.of(2017, 8, 16, 6, 30, 0);

        Reading reading1 = new Reading(22, date1);
        Reading reading2 = new Reading(22, date2);
        Reading reading3 = new Reading(19, date3);

        double expectedResult = 19;
        LocalDate dayOfMonth = LocalDate.of(2017, 8, 5);

        // Act
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        double result = this.temperatureSensor.getSmallestMeasurementOfMonth(dayOfMonth);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testaGetMaiorRegistoMes() {
        //Arrange
        LocalDateTime date1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime date2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime date3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime date4 = LocalDateTime.of(2018, 2, 12, 15, 20);

        Reading reading1 = new Reading(28, date1);
        Reading reading2 = new Reading(25, date2);
        Reading reading3 = new Reading(26, date3);
        Reading reading4 = new Reading(27, date4);

        double expectedResult = 27;
        LocalDate dayOfMonth = LocalDate.of(2018, 2, 5);

        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);

        //Act
        double result = this.temperatureSensor.getBiggestMeasurementOfMonth(dayOfMonth);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testaGetMaiorRegistoMes2() {
        //Arrange
        LocalDateTime date1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime date2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime date3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime date4 = LocalDateTime.of(2018, 2, 12, 15, 20);

        Reading reading1 = new Reading(28, date1);
        Reading reading2 = new Reading(27, date2);
        Reading reading3 = new Reading(26, date3);
        Reading reading4 = new Reading(28, date4);

        double expectedResult = 28;
        LocalDate dataDoMes = LocalDate.of(2018, 2, 5);

        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);

        //Act
        double result = this.temperatureSensor.getBiggestMeasurementOfMonth(dataDoMes);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testaGetMaiorRegistoMesListaSemRegistos() {
        //Arrange
        LocalDate dateOfMonth = LocalDate.of(2018, 2, 15);
        double expectedResult = Double.NaN;

        //Act
        double result = this.temperatureSensor.getBiggestMeasurementOfMonth(dateOfMonth);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMonthlyAverageMeasurementTest() {
        //Arrange
        LocalDateTime date1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        LocalDateTime date2 = LocalDateTime.of(2018, 2, 1, 6, 25);
        LocalDateTime date3 = LocalDateTime.of(2018, 2, 11, 7, 30);
        LocalDateTime date4 = LocalDateTime.of(2018, 2, 12, 6, 25);

        Reading reading1 = new Reading(21, date1);
        Reading reading2 = new Reading(25, date2);
        Reading reading3 = new Reading(26, date3);
        Reading reading4 = new Reading(27, date4);

        double expectedResult = 26;
        LocalDate dayOfMonth = LocalDate.of(2018, 2, 15);

        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        //Act
        double result = this.temperatureSensor.getMonthlyAverageMeasurement(dayOfMonth);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMonthlyAverageMeasurementTest_NoReadings() {
        //Arrange
        double expectedResult = Double.NaN;
        LocalDate dayOfMonth = LocalDate.of(2018, 2, 20);

        //Act
        double result = this.temperatureSensor.getMonthlyAverageMeasurement(dayOfMonth);
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void readingExistsBySensorIdLocalDateTime_ShouldReturnTrue() {
        //Act
        boolean result = this.temperatureSensor.readingExistsBySensorIdLocalDateTime(this.reading);

        //Assert
        assertTrue(result);
    }

    @Test
    public void readingExistsBySensorIdLocalDateTime_ShouldReturnFalse() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        Reading reading5 = new Reading(21, date1);
        //Act
        boolean result = this.temperatureSensor.readingExistsBySensorIdLocalDateTime(reading5);

        //Assert
        assertFalse(result);
    }

    @Test
    public void isMeasurementListEmpty_ShouldReturnTrue() {
        // Arrange
        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        Location locS1 = new Location(40, 10, 20);
        SensorId sensorId = new SensorId("R");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento, sensorTypeId, locS1, "l/m2");

        //Act
        boolean result = s1.isMeasurementListEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void isMeasurementListEmpty_ShouldReturnFalse() {
        //Act
        boolean result = this.temperatureSensor.isMeasurementListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void getLastMeasurementTest() {
        //Arrange
        Reading expectedResult = this.reading1;

        //Act
        Reading result = this.temperatureSensor.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastMeasurementTestTwoReadings_reading2() {
        //Arrange
        Reading expectedResult = this.reading1;

        //Act
        Reading result = this.temperatureSensor.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastMeasurement_EmptyList() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(this.reading);
        this.temperatureSensor.getListOfReadings().remove(this.reading1);

        Reading expectedResult = null;

        //Act
        Reading result = this.temperatureSensor.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void sensorTypeEqualsSensorTypeTest_ShouldReturnTrue() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        //Act
        boolean result = this.temperatureSensor.sensorTypeEqualsSensorType(sensorTypeId);
        //Assert
        assertTrue(result);
    }

    @Test
    public void sensorTypeEqualsSensorTypeTest_ShouldReturnFalse() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        //Act
        boolean result = this.temperatureSensor.sensorTypeEqualsSensorType(sensorTypeId);
        //Assert
        assertFalse(result);
    }

    @Test
    public void getDailyMeasurementTest() {
        //Arrange
        LocalDateTime sundayDate3 = LocalDateTime.of(2018, 11, 2, 21, 10, 25);

        //act
        List<Reading> result = this.temperatureSensor.getDailyMeasurement(sundayDate3.toLocalDate());

        //assert
        assertEquals(this.temperatureSensor.getListOfReadings(), result);
    }

    @Test
    public void getDailyMeasurementTest_ValueNan() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(this.reading);
        this.temperatureSensor.getListOfReadings().remove(this.reading1);
        LocalDateTime sundayDate = LocalDateTime.of(2000, 1, 2, 21, 10, 25);

        //act
        List<Reading> result = this.temperatureSensor.getDailyMeasurement(sundayDate.toLocalDate());

        //assert
        assertEquals(this.temperatureSensor.getListOfReadings(), result);
    }


    @Test
    public void getDailyMeasurementWithDoubleNaN() {
        //Arrange
        LocalDateTime sundayDate3 = LocalDateTime.of(2018, 11, 2, 21, 10, 25);

        //act
        List<Reading> result = this.temperatureSensor.getDailyMeasurementWithDoubleNaN(sundayDate3.toLocalDate());

        //assert
        assertEquals(this.temperatureSensor.getListOfReadings(), result);
    }

    @Test
    public void checkIfDaysAreEqualTest() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 16, 16, 20, 00);
        LocalDateTime date2 = LocalDateTime.of(2018, 1, 2, 15, 20, 00);
        boolean expectedResult = false;
        boolean result = this.temperatureSensor.checkIfDaysAreEqual(date.toLocalDate(), date2.toLocalDate());

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDaysAreEqualTest_ShouldReturnTrue() {
        //Arrange

        LocalDateTime date = LocalDateTime.of(2018, 10, 2, 15, 20, 00);
        LocalDateTime date2 = LocalDateTime.of(2018, 10, 2, 16, 20, 00);

        boolean expectedResult = true;

        //Act
        boolean result = this.temperatureSensor.checkIfDaysAreEqual(date.toLocalDate(), date2.toLocalDate());

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDaysAreEqualTest_SameDate() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 10, 2, 15, 20, 00);
        LocalDateTime date1 = LocalDateTime.of(2018, 10, 2, 15, 20, 00);

        //Act
        boolean result = this.temperatureSensor.checkIfDaysAreEqual(date.toLocalDate(), date1.toLocalDate());

        //assert
        assertTrue(result);
    }

    @Test
    public void getLowestMeasurementOfDayTest() {
        //Arrange

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(40, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = -2;

        //Act
        double result = this.temperatureSensor.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLowestMeasurementOfDayTest_LowestDay() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(-2, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(-3, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-4, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = -4;

        //Act
        double result = this.temperatureSensor.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLowestMeasurementOfDayTest_Boundaries() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(1, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(2, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = 0;

        //Act
        double result = this.temperatureSensor.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLowestMeasurementOfDayTest_EmptyList() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);

        double expectedResult = Double.NaN;

        //Act
        double result = this.temperatureSensor.getLowestMeasurementOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLowestMeasurementOfDayTest_NaNValue() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        double expectedResult = -2;

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        //Act
        double result = this.temperatureSensor.getLowestMeasurementOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getFirstDayOfWeek() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        LocalDate expectedResult = LocalDate.of(1991, 10, 27);

        LocalDate result = this.temperatureSensor.getFirstDayOfWeek(date.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    public void lowestMeasurementsOfWeekTest() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(-5.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(-2.0, data7);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>();

        expectedResult.add(-2.0);
        expectedResult.add(-4.0);
        expectedResult.add(-2.0);
        expectedResult.add(-5.0);
        expectedResult.add(-2.0);

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void lowestMeasurementsOfWeekTest_DoubleNaN() {
        //Arrange

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(-5, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(-2, data7);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0, -2.0, -5.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void lowestMeasurementsOfWeekTest_FiveReadings() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0, -2.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void lowestMeasurementsOfWeekTest_WithDifferentDayAndWeek() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(0.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(-4, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Reading reading5 = new Reading(-2, data5);


        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(-2.0, -4.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void lowestMeasurementsOfWeekTest_NoReadings() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(this.reading);
        this.temperatureSensor.getListOfReadings().remove(this.reading1);
        List<Double> expectedResult = new ArrayList<>(Arrays.asList());

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        List<Double> result = this.temperatureSensor.lowestMeasurementsOfWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageOfLowestMeasurementsWeekTest() {

        //Arrange

        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(8.9, data7);


        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        double expectedResult = 47.4 / 5;
        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = this.temperatureSensor.getAverageOfLowestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getAverageOfLowestMeasurementsWeekTest_DoubleNan() {

        //Arrange
        double expectedResult = Double.NaN;
        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        double result = this.temperatureSensor.getAverageOfLowestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumValueOfDayTest() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(30, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(40, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(-2, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);
        double expectedResult = 40;

        //Act
        double result = this.temperatureSensor.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumValueOfDayTest_AnotherDay() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(29, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(30, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(31, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = 31;

        //Act
        double result = this.temperatureSensor.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumValueOfDayTest_EmptyList() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        double expectedResult = Double.NaN;

        //Act
        double result = this.temperatureSensor.getMaximumValueOfDay(data.toLocalDate());
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumValueOfDayTest_NaNValue() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 00, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 10, 2, 23, 59, 59);
        Reading reading2 = new Reading(-2, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 10, 2, 17, 20, 00);
        Reading reading3 = new Reading(30, data3);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        double expectedResult = 30;

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        //Act
        double result = this.temperatureSensor.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void biggestWeeklyMeasurementsTest() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(20.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(20.0, data7);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(20.0, 40.0, 20.0, 45.0, 20.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void biggestWeeklyMeasurementsTest_DoubleNaN() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 5, 01, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 6, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 9, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 10, 17, 20, 00);
        Reading reading6 = new Reading(45.0, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 11, 17, 20, 00);
        Reading reading7 = new Reading(20.0, data7);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, 20.0, 40.0, 20.0, 45.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 6);

        //Act
        List<Double> result = this.temperatureSensor.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void biggestWeeklyMeasurementsTest_FiveReadings() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 6, 01, 00, 01);
        Reading reading1 = new Reading(30.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 5, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading3 = new Reading(20.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(30.0, 30.0, 40.0, 20.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 7);

        //Act
        List<Double> result = this.temperatureSensor.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void biggestWeeklyMeasurementsTest_DifferentDayAndWeek() {
        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(45.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(30.0, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(25.0, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(40.0, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 10, 6, 17, 20, 00);
        Reading reading5 = new Reading(20.0, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(25.0, 40.0));

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        List<Double> result = this.temperatureSensor.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void biggestWeeklyMeasurementsTest_NoReadings() {
        //Arrange
        List<Double> expectedResult = new ArrayList<>(Arrays.asList());

        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        List<Double> result = this.temperatureSensor.biggestWeeklyMeasurements(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageOfBiggestMeasurementsWeekTest() {

        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Registo 6
        LocalDateTime data6 = LocalDateTime.of(2018, 11, 7, 17, 20, 00);
        Reading reading6 = new Reading(11.2, data6);

        //Registo 7
        LocalDateTime data7 = LocalDateTime.of(2018, 11, 8, 17, 20, 00);
        Reading reading7 = new Reading(8.9, data7);


        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);
        this.temperatureSensor.addReadingsToList(reading6);
        this.temperatureSensor.addReadingsToList(reading7);

        double expectedResult = 47.4 / 5;

        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = this.temperatureSensor.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getAverageOfBiggestMeasurementsWeekTest_DoubleNan() {

        //Arrange
        double expectedResult = Double.NaN;
        LocalDate searchDate = LocalDate.of(2018, 12, 4);

        //Act
        double result = this.temperatureSensor.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getAverageOfBiggestMeasurementsWeekTest_FiveDaysOfReadings() {

        //Arrange
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 01, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 3, 23, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 4, 17, 20, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 5, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 6, 17, 20, 00);
        Reading reading5 = new Reading(10.1, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        double expectedResult = 9.1;
        LocalDate searchDate = LocalDate.of(2018, 11, 4);

        //Act
        double result = this.temperatureSensor.getAverageOfBiggestMeasurementsWeek(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void distanceBetweenSensorAndLocationTest() {
        double lat2 = 52.3740;
        double lon2 = 4.8897;
        double alt2 = 13;
        Location local2 = new Location(lat2, lon2, alt2);

        LocalDateTime dataFuncionamento = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);

        SensorTypeId sensorTypeId = new SensorTypeId("Temperatura");
        Location locS1 = new Location(40, 10, 20);
        SensorId sensorId = new SensorId("R003");
        GeoAreaSensor s1 = new GeoAreaSensor(sensorId, "A123", dataFuncionamento, sensorTypeId, locS1, "l/m2");

        double expectedResult = 1430143.18061553;

        double result = s1.distanceBetweenSensorAndLocation(local2);
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void distanceBetweenSensorAndLocationTest_OutOfInterval() {

        double lat2 = 52.3740;
        double lon2 = 4.8897;
        double alt2 = 13;
        Location local2 = new Location(lat2, lon2, alt2);

        double expectedResult = Double.NaN;
        double result = this.temperatureSensor.distanceBetweenSensorAndLocation(local2);
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getTotalDailyMeasurementsTest_SameDay() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        double expectedResult = 40;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = this.temperatureSensor.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getTotalDailyMeasurementsTest_Empty() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        double expectedResult = 0.0;
        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = this.temperatureSensor.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getTotalDailyMeasurementsTest_WithDifferentDays() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 10, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(10, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        double expectedResult = 30;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = this.temperatureSensor.getTotalDailyMeasurements(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageTest() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Registo 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Registo 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(9.5, data2);

        //Registo 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(7.5, data3);

        //Registo 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(9.7, data4);

        //Registo 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10.1, data5);

        //Adição das medições
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        double expectedResult = 9.36;

        LocalDateTime searchDate = LocalDateTime.of(2018, 11, 2, 17, 20, 10);

        //Act
        double result = this.temperatureSensor.getDailyAverage(searchDate.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLastLowestReading_SensorWithValidReadings_ShouldReturnLastLowestReading() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(14, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        Reading expectedResult = reading4;
        List<Reading> readings = this.temperatureSensor.getReadingsBetweenDates(startDate, endDate);

        //Act
        Reading result = this.temperatureSensor.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestReading_SensorWithDayWithDoubleNaNReadings_ShouldReturnLastLowestReading() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        Reading expectedResult = reading4;
        List<Reading> readings = this.temperatureSensor.getReadingsBetweenDates(startDate, endDate);

        //Act
        Reading result = this.temperatureSensor.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestReading_SensorWithOnlyDoubleNaNReadings_ShouldReturnReadingWithDoubleNaN() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(Double.NaN, data4);


        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        Reading expectedResult = reading4;
        List<Reading> readings = this.temperatureSensor.getReadingsBetweenDates(startDate, endDate);

        //Act
        Reading result = this.temperatureSensor.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastLowestReading_SensorWithNoReadings_ShouldReturnNull() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        Reading expectedResult = null;
        List<Reading> readings = this.temperatureSensor.getReadingsBetweenDates(startDate, endDate);

        //Act
        Reading result = this.temperatureSensor.getLastLowestReading(readings);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getFirstHighestReading() {

        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate firstDay = LocalDate.of(2018, 11, 2);
        LocalDate nextDay = LocalDate.of(2018, 12, 30);

        //Act
        Reading result = this.temperatureSensor.getFirstHighestReading(firstDay, nextDay);

        //Assert
        assertEquals(reading2, result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralReadingsInOneDay_ShouldReturnHighestReading() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018, 11, 2);

        Reading expectedResult = reading2;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralReadingsInTwoDays_ShouldReturnHighestReadingOfSelectedDay() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(10.0, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(13, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018, 11, 3);

        Reading expectedResult = reading5;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithSeveralHighestReadingsInOneDay_ShouldReturnMostRecentOne() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018, 11, 2);
        Reading expectedResult = reading2;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithNoReadings_ShouldReturnNull() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(11, data2);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);

        LocalDate day = LocalDate.of(2018, 11, 5);

        Reading expectedResult = null;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithDoubleNaNReadings_ShouldReturnHighestReading() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 2, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 2, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate day = LocalDate.of(2018, 11, 2);

        Reading expectedResult = reading1;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHighestReadingOfADay_WithOnlyOneDoubleNaNReadings_ShouldReturnDoubleNaN() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 2, 16, 59, 59);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);

        LocalDate day = LocalDate.of(2018, 11, 2);

        Reading expectedResult = reading2;

        //Act
        Reading result = this.temperatureSensor.getHighestReadingOfADay(day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_With2DatesWithValidReadings_ShouldReturnListWith2Readings() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(14, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(15, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading2);
        expectedResult.add(reading4);

        //Act
        List<Reading> result = this.temperatureSensor.getDailyMaxReadingsInAnInterval(startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_WithOneDateWithOnlyInvalidReadings_ShouldReturnListWith1ValidReadingAnd1DoubleNaN() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(15, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading4);

        //Act
        List<Reading> result = this.temperatureSensor.getDailyMaxReadingsInAnInterval(startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyMaxReadingsInAnInterval_WithDatesWithOnlyDoubleNaNReadings_ShouldReturnListWithDoubleNaNReadings() {
        //Arrange
        this.temperatureSensor.getListOfReadings().remove(reading);
        this.temperatureSensor.getListOfReadings().remove(reading1);
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(Double.NaN, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(Double.NaN, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(Double.NaN, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(Double.NaN, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(Double.NaN, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading3);

        //Act
        List<Reading> result = this.temperatureSensor.getDailyMaxReadingsInAnInterval(startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetListOfReadings_tryingToGetAnExistingList_ShouldReturnTheCorrespondingList() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading);
        expectedResult.add(reading1);

        // Act
        List<Reading> result = temperatureSensor.getListOfReadings();

        // Arrange
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMostRecentValidReading() {
        //Arrange
        //Reading 1
        LocalDateTime data1 = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        Reading reading1 = new Reading(11, data1);

        //Reading 2
        LocalDateTime data2 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        Reading reading2 = new Reading(14, data2);

        //Reading 3
        LocalDateTime data3 = LocalDateTime.of(2018, 11, 3, 17, 15, 00);
        Reading reading3 = new Reading(5, data3);

        //Reading 4
        LocalDateTime data4 = LocalDateTime.of(2018, 11, 3, 17, 20, 00);
        Reading reading4 = new Reading(5, data4);

        //Reading 5
        LocalDateTime data5 = LocalDateTime.of(2018, 11, 3, 17, 20, 10);
        Reading reading5 = new Reading(10, data5);

        //Adding readings to sensor
        this.temperatureSensor.addReadingsToList(reading1);
        this.temperatureSensor.addReadingsToList(reading2);
        this.temperatureSensor.addReadingsToList(reading3);
        this.temperatureSensor.addReadingsToList(reading4);
        this.temperatureSensor.addReadingsToList(reading5);

        LocalDate startDate = LocalDate.of(2018, 11, 2);
        LocalDate endDate = LocalDate.of(2018, 11, 3);

        Reading expectedResult = reading5;
        List<Reading> readings = this.temperatureSensor.getReadingsBetweenDates(startDate, endDate);

        //Act
        Reading result = this.temperatureSensor.getMostRecentValidReading(readings);

        //Assert
        assertEquals(expectedResult, result);
    }
}