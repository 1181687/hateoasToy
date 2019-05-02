package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        SensorId id = new SensorId("GeoAreaSensor");
        //Act
        this.temperatureSensor.setId(id);
        SensorId result = this.temperatureSensor.getId();
        //Assert
        assertEquals(id, result);
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


    }

    @Test
    void setUnits() {


    }

    @Test
    void isActive() {


    }

    @Test
    void deactivateDevice() {


    }

    @Test
    public void testarEqualsSame() {
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
    public void testarEqualsFalse() {
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
    void distanceBetweenTwoLocations() {


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
    void getSmallestMeasurementOfMonth() {
    }

    @Test
    void getBiggestMeasurementOfMonth() {
    }

    @Test
    void getMonthlyAverageMeasurement() {
    }

    @Test
    void addReadingsToList() {
    }

    @Test
    void addReading() {
    }

    @Test
    void readingExistsBySensorIdLocalDateTime() {
    }

    @Test
    void isMeasurementListEmpty() {
    }

    @Test
    void getLastMeasurement() {
    }

    @Test
    void sensorTypeEqualsSensorType() {
    }

    @Test
    void getDailyMeasurement() {
    }

    @Test
    void checkIfDaysAreEqual() {
    }

    @Test
    void getLowestMeasurementOfDay() {
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