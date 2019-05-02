package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
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
        String id = "GeoAreaSensor";
        SensorId sensorId = new SensorId(id);
        //Act
        this.temperatureSensor.setId(sensorId);
        SensorId result = this.temperatureSensor.getId();
        //Assert
        assertEquals(sensorId, result);
    }

    @Test
    void getSensorName() {
        // Arrange
        String expectedResult = "A123";
        //Act
        String result = this.temperatureSensor.getSensorName();
        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void getStartingDate() {
    }

    @Test
    void getSensorType() {
    }

    @Test
    void getLocation() {
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
    void equals1() {
    }

    @Test
    void hashCode1() {
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