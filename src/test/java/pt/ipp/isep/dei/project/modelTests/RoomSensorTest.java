package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RoomSensorTest {

    private RoomSensor roomSensor;
    private Reading reading;
    private Reading reading1;

    @BeforeEach
    public void StartUp() {

        // RoomSensor
        SensorId id = new SensorId("S001");
        String sensorName = "Sensor1";
        LocalDateTime startingDate = LocalDateTime.of(1995, 5, 3, 2, 30, 00);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        String units = "1m/s2";

        this.roomSensor = new RoomSensor(id, sensorName, startingDate, sensorTypeId, units);

        // Readings
        LocalDateTime dateTime = LocalDateTime.of(2018, 11, 2, 8, 00, 01);
        this.reading = new Reading(11, dateTime);
        this.roomSensor.addReading(this.reading);
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 11, 2, 15, 59, 59);
        this.reading1 = new Reading(14, dateTime1);
        this.roomSensor.addReading(this.reading1);
    }

    @Test
    public void getIdTest() {
        //Arrange
        String id = "S001";
        SensorId sensorId = new SensorId(id);
        //Act
        SensorId result = this.roomSensor.getId();
        //Assert
        assertEquals(sensorId, result);
    }

    @Test
    public void getStartingDateTest() {
        // Arrange
        LocalDateTime expectedResult = LocalDateTime.of(1995, 5, 3, 2, 30, 00);
        // Act
        LocalDateTime result = this.roomSensor.getStartingDate();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorTypeTest() {
        // Arrange
        SensorTypeId expectedResult = new SensorTypeId("Temperature");
        // Act
        SensorTypeId result = this.roomSensor.getSensorType();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void isActiveTest() {
        //Act
        boolean result = this.roomSensor.isActive();
        //Assert
        assertTrue(result);
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
        this.roomSensor.addReading(reading1);
        this.roomSensor.addReading(reading2);
        this.roomSensor.addReading(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);
        double expectedResult = 40;

        //Act
        double result = this.roomSensor.getMaximumValueOfDay(searchDate);
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
        this.roomSensor.addReading(reading1);
        this.roomSensor.addReading(reading2);
        this.roomSensor.addReading(reading3);

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        double expectedResult = 31;

        //Act
        double result = this.roomSensor.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumValueOfDayTest_EmptyList() {
        //Arrange
        LocalDateTime data = LocalDate.of(1991, 11, 2).atTime(21, 10, 25);
        double expectedResult = Double.NaN;

        //Act
        double result = this.roomSensor.getMaximumValueOfDay(data.toLocalDate());
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
        this.roomSensor.addReading(reading1);
        this.roomSensor.addReading(reading2);
        this.roomSensor.addReading(reading3);

        double expectedResult = 30;

        LocalDate searchDate = LocalDate.of(2018, 10, 2);

        //Act
        double result = this.roomSensor.getMaximumValueOfDay(searchDate);
        //assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyMeasurementTest() {
        //Arrange
        LocalDateTime sundayDate3 = LocalDateTime.of(2018, 11, 2, 21, 10, 25);

        //act
        List<Reading> result = this.roomSensor.getDailyMeasurement(sundayDate3.toLocalDate());

        //assert
        assertEquals(this.roomSensor.getReadings(), result);
    }

    @Test
    public void getDailyMeasurementTest_ValueNan() {
        //Arrange
        this.roomSensor.getReadings().remove(this.reading);
        this.roomSensor.getReadings().remove(this.reading1);
        LocalDateTime sundayDate = LocalDateTime.of(2000, 1, 2, 21, 10, 25);

        //act
        List<Reading> result = this.roomSensor.getDailyMeasurement(sundayDate.toLocalDate());

        //assert
        assertEquals(this.roomSensor.getReadings(), result);
    }

    @Test
    public void checkIfDaysAreEqualTest() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 16, 16, 20, 00);
        LocalDateTime date2 = LocalDateTime.of(2018, 1, 2, 15, 20, 00);
        boolean expectedResult = false;
        boolean result = this.roomSensor.checkIfDaysAreEqual(date.toLocalDate(), date2.toLocalDate());

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
        boolean result = this.roomSensor.checkIfDaysAreEqual(date.toLocalDate(), date2.toLocalDate());

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDaysAreEqualTest_SameDate() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 10, 2, 15, 20, 00);
        LocalDateTime date1 = LocalDateTime.of(2018, 10, 2, 15, 20, 00);

        //Act
        boolean result = this.roomSensor.checkIfDaysAreEqual(date.toLocalDate(), date1.toLocalDate());

        //assert
        assertTrue(result);
    }

    @Test
    public void isMeasurementListEmpty_ShouldReturnTrue() {
        // Arrange
        SensorId id = new SensorId("S002");
        String sensorName = "Sensor2";
        LocalDateTime startingDate = LocalDateTime.of(1995, 5, 3, 2, 30, 00);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        String units = "1m/s2";
        RoomSensor s2 = new RoomSensor(id, sensorName, startingDate, sensorTypeId, units);

        //Act
        boolean result = s2.isMeasurementListEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void isMeasurementListEmpty_ShouldReturnFalse() {
        //Act
        boolean result = this.roomSensor.isMeasurementListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void sensorTypeEqualsSensorTypeTest_ShouldReturnTrue() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        //Act
        boolean result = this.roomSensor.sensorTypeEqualsSensorType(sensorTypeId);
        //Assert
        assertTrue(result);
    }

    @Test
    public void sensorTypeEqualsSensorTypeTest_ShouldReturnFalse() {
        //Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        //Act
        boolean result = this.roomSensor.sensorTypeEqualsSensorType(sensorTypeId);
        //Assert
        assertFalse(result);
    }

    @Test
    public void getLastMeasurementTest() {
        //Arrange
        Reading expectedResult = this.reading1;

        //Act
        Reading result = this.roomSensor.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLastMeasurementTestTwoReadings_reading2() {
        //Arrange
        Reading expectedResult = this.reading1;

        //Act
        Reading result = this.roomSensor.getLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void readingExistsBySensorIdLocalDateTime_ShouldReturnTrue() {
        //Act
        boolean result = this.roomSensor.readingExistsBySensorIdLocalDateTime(this.reading);

        //Assert
        assertTrue(result);
    }

    @Test
    public void readingExistsBySensorIdLocalDateTime_ShouldReturnFalse() {
        // Arrange
        LocalDateTime date1 = LocalDateTime.of(2018, 4, 11, 5, 55);
        Reading reading5 = new Reading(21, date1);
        //Act
        boolean result = this.roomSensor.readingExistsBySensorIdLocalDateTime(reading5);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_SameObject() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean result = this.roomSensor.equals(this.roomSensor);
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
        boolean result = this.roomSensor.equals(sensorType);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCode() {
        //Arrange
        int expectedResult = Objects.hash(this.roomSensor.getId());

        // Act
        int result = this.roomSensor.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCodeNotEquals() {
        //Arrange
        SensorId id = new SensorId("S002");
        String sensorName = "Sensor2";
        LocalDateTime startingDate = LocalDateTime.of(1995, 5, 3, 2, 30, 00);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        String units = "1m/s2";
        RoomSensor s2 = new RoomSensor(id, sensorName, startingDate, sensorTypeId, units);


        // Act
        int hash1 = Objects.hash(this.roomSensor.getId());
        int hash2 = Objects.hash(s2.getId());
        // Assert
        assertNotEquals(hash1, hash2);
    }
}