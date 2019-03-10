package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SensorListTest {
    private SensorList sensorList;
    private Sensor temperatureSensor1;
    private Sensor temperatureSensor2;
    private Sensor rainfallSensor;
    private Sensor humiditySensor;
    private Sensor dummySensor;
    private Reading reading0;
    private Reading reading1;
    private Reading nullReading;
    private SensorList emptySensorList = new SensorList();


    @BeforeEach
    public void StartUp() {
        // Sensor List
        sensorList = new SensorList();

        // Sensors
        Location location = new Location(41.1496, -8.6109, 97);
        SensorType temperature = new SensorType("Temperature");
        temperatureSensor1 = sensorList.newSensor("Sensor Temp 1", temperature, location);
        sensorList.addSensor(temperatureSensor1);
        temperatureSensor2 = sensorList.newSensor("Sensor Temp 2", temperature, location);
        sensorList.addSensor(temperatureSensor2);
        SensorType rainfall = new SensorType("Rainfall");
        rainfallSensor = sensorList.newSensor("Sensor Rain", rainfall, location);
        sensorList.addSensor(rainfallSensor);
        SensorType humidity = new SensorType("Humidity");
        humiditySensor = sensorList.newSensor("Sensor Humidity", humidity, location);
        sensorList.addSensor(humiditySensor);
        dummySensor = sensorList.newSensor("Dummy", null, null);

        // Readings
        LocalDateTime dateTime0 = LocalDateTime.of(2018, 12, 3, 15, 20, 00);
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);
        LocalDateTime dateTime2 = LocalDateTime.of(2018, 12, 4, 19, 24, 00);
        reading0 = new Reading(20, dateTime0);
        reading1 = new Reading(25, dateTime1);
        nullReading = new Reading(Double.NaN, dateTime2);
        temperatureSensor1.addReadingsToList(reading0);
        temperatureSensor2.addReadingsToList(reading1);
        rainfallSensor.addReadingsToList(reading0);
    }

    @Test
    void addSensorTest() {
        // Act
        boolean result = sensorList.addSensor(dummySensor);

        // Assert
        assertTrue(result);
    }

    @Test
    void getSensorListTest() {
        // Arrange
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensor1);
        expectedResult.add(temperatureSensor2);
        expectedResult.add(rainfallSensor);
        expectedResult.add(humiditySensor);

        // Act
        List<Sensor> result = sensorList.getListOfSensors();

        // Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getListOfLatestMeasurementsBySensorTypePositiveTest() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);

        SensorType temperature = new SensorType("Temperature");

        // Act
        List<Reading> result = sensorList.getListOfLatestMeasurementsBySensorType(temperature);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListOfLatestMeasurementsBySensorTypeDoubleNanTest() {
        // Arrange
        temperatureSensor1.addReadingsToList(nullReading);
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);

        SensorType temperature = new SensorType("Temperature");

        // Act
        List<Reading> result = sensorList.getListOfLatestMeasurementsBySensorType(temperature);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListOfLatestMeasurementsBySensorTypeEmptyListTest() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();

        SensorType humidity = new SensorType("Humidity");

        // Act
        List<Reading> result = sensorList.getListOfLatestMeasurementsBySensorType(humidity);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeTemperatureTest() {
        // Arrange
        Reading expectedResult = reading1;

        SensorType temperature = new SensorType("Temperature");

        // Act
        Reading result = sensorList.getLatestMeasurementBySensorType(temperature);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeNullTest() {
        // Arrange
        SensorType humidity = new SensorType("Humidity");

        // Act
        Reading result = sensorList.getLatestMeasurementBySensorType(humidity);

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeEmptySensorListTest() {
        // Arrange
        SensorType humidity = new SensorType("Humidity");

        // Act
        Reading result = sensorList.getLatestMeasurementBySensorType(humidity);

        // Assert
        assertEquals(null, result);
    }


    @Test
    public void getMaximumMeasureOfTypeOfSensorInGivenDayTwoTemperatureSensorsTest() {
        // Arrange
        double expectedResult = Double.NaN;

        LocalDate date = LocalDate.of(1991, 11, 3);
        SensorType temperature = new SensorType("Temperature");

        // Act
        double result = sensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(temperature, date);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfSensors() {
        // Arrange
        LocalDate date = LocalDate.of(1991, 11, 2);
        SensorType temperature = new SensorType("Temperature");

        double expectedResult = Double.NaN;

        // Act
        double result = emptySensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(temperature, date);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageTest() {
        // Arrange
        LocalDate searchDate = LocalDate.of(2018, 12, 3);

        double expectedResult = 20.0;

        // Act
        double result = sensorList.getDailyAverage(searchDate);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageMeasurementsInAListOfSensorsDayWithNoMeasurementsTest() {
        // Arrange
        LocalDate searchDate = LocalDate.of(2018, 12, 5);

        double expectedResult = Double.NaN;

        //Act
        double result = sensorList.getDailyAverage(searchDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorsListContentTest() {
        // Arrange
        String expectedResult =
                "1 - Name of the sensor: Sensor Temp 1\n" +
                        "2 - Name of the sensor: Sensor Temp 2\n" +
                        "3 - Name of the sensor: Sensor Rain\n" +
                        "4 - Name of the sensor: Sensor Humidity\n";

        // Act
        String result = sensorList.getSensorListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTrueTest() {
        // Act
        boolean result = emptySensorList.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyFalseTest() {
        // Act
        boolean result = sensorList.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Arrange
        int expectedResult = Objects.hash(emptySensorList.getListOfSensors());

        // Act
        int result = emptySensorList.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        // Arrange
        SensorList sensorList1 = new SensorList();

        //Act
        boolean result = sensorList1.equals(emptySensorList);

        // Assert
        assertTrue(result);
    }

    @Test
    void testEqualsSameObj() {
        // Act
        boolean result = sensorList.equals(sensorList);

        // Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        // Act
        boolean result = emptySensorList.equals(dummySensor);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetSensorWithMostRecentReading() {
        // Arrange
        Sensor expectedResult = temperatureSensor2;

        // Act
        Sensor result = sensorList.getSensorWithMostRecentReading(sensorList);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorExistsByIdTrueTest() {
        // Act
        boolean result = sensorList.checkIfSensorExistsById("Sensor Temp 1");

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorExistsByIdFalseTest() {
        // Act
        boolean result = sensorList.checkIfSensorExistsById("Sensor Temp 14123");

        // Assert
        assertFalse(result);
    }

    @Test
    public void getSensorByIdPositiveTest() {
        // Arrange
        Sensor expectedResult = temperatureSensor1;

        // Act
        Sensor result = sensorList.getSensorById("Sensor Temp 1");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorByIdNullTest() {
        // Act
        Sensor result = sensorList.getSensorById("Sensor Temp 14123");

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testGetSensorWithMostRecentReadingNoArguments() {
        //arrange
        SensorList sensorList = new SensorList();

        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("Sensor0", dataFuncionamento0, sensorType0, locS0);
        sensorList.addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        Sensor s1 = new Sensor("Sensor1", dataFuncionamento1, sensorType1, locS1);
        sensorList.addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);
        Reading reading13 = new Reading(20, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        s1.addReadingsToList(reading13);

        Sensor expectedResult = s1;

        Sensor result = sensorList.getSensorWithMostRecentReading();

        //Assert
        assertEquals(expectedResult, result);
    }
}