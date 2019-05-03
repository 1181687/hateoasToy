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
import java.util.GregorianCalendar;
import java.util.List;

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
    void getUnits() {
        //Arrange
        String units = "3m/s2";
        //Act
        this.temperatureSensor.setUnits(units);
        String result = this.temperatureSensor.getUnits();
        //Assert
        assertEquals(units, result);
    }


    @Test
    void isActive_ShouldReturnTrue() {
        //Act
        boolean result = this.temperatureSensor.isActive();
        //Assert
        assertTrue(result);
    }

    @Test
    void deactivateDevice() {
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
    public void testarHashCode() {
        // Arrange
        int expectedResult = 1;
        // Act
        int result = this.temperatureSensor.hashCode();
        // Assert
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
    void getMeasurementValueBetweenDates() {


    }

    @Test
    void existReadingsBetweenDates() {


    }

    @Test
    void checkMeasurementExistenceBetweenDates() {


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
    void getMonthlyAverageMeasurement() {
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
    void readingExistsBySensorIdLocalDateTime() {
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
    void getDailyMeasurement() {
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
    void getFirstDayOfWeek() {
    }

    @Test
    void lowestMeasurementsOfWeek() {
    }

    @Test
    void getAverageOfLowestMeasurementsWeek() {
    }

    @Test
    void getMaximumValueOfDay() {
    }

    @Test
    void biggestWeeklyMeasurements() {
    }

    @Test
    void getAverageOfBiggestMeasurementsWeek() {
    }

    @Test
    void distanceBetweenSensorAndLocation() {
    }

    @Test
    void getTotalDailyMeasurements() {
    }

    @Test
    void getDailyAverage() {
    }

    @Test
    void getReadingsBetweenDates() {
    }

    @Test
    void getFirstHighestReading() {
    }

    @Test
    void getHighestReadingOfADay() {
    }

    @Test
    void getDailyMaxReadingsInAnInterval() {
    }

    @Test
    void getLastLowestReading() {
    }

    @Test
    void getListOfReadings() {
    }

    @Test
    void getDailyMeasurementWithDoubleNaN() {
    }

    @Test
    void getMostRecentValidReading() {
    }
}