package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GeoAreaSensorListTest {
    private GeoAreaSensorList geoAreaSensorList;
    private GeoAreaSensor temperatureSensor1;
    private GeoAreaSensor temperatureSensor2;
    private GeoAreaSensor rainfallSensor;
    private GeoAreaSensor humiditySensor;
    private GeoAreaSensor dummySensor;
    private Reading reading0;
    private Reading reading1;
    private Reading nullReading;
    private GeoAreaSensorList emptyGeoAreaSensorList = new GeoAreaSensorList();
    private Location location;


    @BeforeEach
    public void StartUp() {
        // GeoAreaSensor List
        geoAreaSensorList = new GeoAreaSensorList();

        // Sensors
        location = new Location(41.1496, -8.6109, 97);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        temperatureSensor1 = geoAreaSensorList.newSensor(new SensorId("s1"), "GeoAreaSensor Temp 1", temperature, location, "l/m2");
        geoAreaSensorList.addSensor(temperatureSensor1);
        temperatureSensor2 = geoAreaSensorList.newSensor(new SensorId("s2"), "GeoAreaSensor Temp 2", temperature, location, "l/m2");
        geoAreaSensorList.addSensor(temperatureSensor2);
        SensorTypeId rainfall = new SensorTypeId("Rainfall");
        rainfallSensor = geoAreaSensorList.newSensor(new SensorId("s3"), "GeoAreaSensor Rain", rainfall, location, "l/m2");
        geoAreaSensorList.addSensor(rainfallSensor);
        SensorTypeId humidity = new SensorTypeId("Humidity");
        humiditySensor = geoAreaSensorList.newSensor(new SensorId("s4"), "GeoAreaSensor Humidity", humidity, location, "l/m2");
        geoAreaSensorList.addSensor(humiditySensor);
        dummySensor = geoAreaSensorList.newSensor(new SensorId("s5"), "Dummy", null, null, "l/m2");

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
        boolean result = geoAreaSensorList.addSensor(dummySensor);

        // Assert
        assertTrue(result);
    }

    @Test
    void getSensorListTest() {
        // Arrange
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensor1);
        expectedResult.add(temperatureSensor2);
        expectedResult.add(rainfallSensor);
        expectedResult.add(humiditySensor);

        // Act
        List<GeoAreaSensor> result = geoAreaSensorList.getListOfSensors();

        // Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getListOfLatestMeasurementsBySensorTypePositiveTest() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);

        SensorTypeId temperature = new SensorTypeId("Temperature");

        // Act
        List<Reading> result = geoAreaSensorList.getListOfLatestMeasurementsBySensorType(temperature);

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

        SensorTypeId temperature = new SensorTypeId("Temperature");

        // Act
        List<Reading> result = geoAreaSensorList.getListOfLatestMeasurementsBySensorType(temperature);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListOfLatestMeasurementsBySensorTypeEmptyListTest() {
        // Arrange
        List<Reading> expectedResult = new ArrayList<>();

        SensorTypeId humidity = new SensorTypeId("Humidity");

        // Act
        List<Reading> result = geoAreaSensorList.getListOfLatestMeasurementsBySensorType(humidity);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeTemperatureTest() {
        // Arrange
        Reading expectedResult = reading1;

        SensorTypeId temperature = new SensorTypeId("Temperature");

        // Act
        Reading result = geoAreaSensorList.getLatestMeasurementBySensorType(temperature);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeNullTest() {
        // Arrange
        SensorTypeId humidity = new SensorTypeId("Humidity");

        // Act
        Reading result = geoAreaSensorList.getLatestMeasurementBySensorType(humidity);

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void getLatestMeasurementBySensorTypeEmptySensorListTest() {
        // Arrange
        SensorTypeId humidity = new SensorTypeId("Humidity");

        // Act
        Reading result = geoAreaSensorList.getLatestMeasurementBySensorType(humidity);

        // Assert
        assertEquals(null, result);
    }


    @Test
    public void getMaximumMeasureOfTypeOfSensorInGivenDayTwoTemperatureSensorsTest() {
        // Arrange
        double expectedResult = Double.NaN;

        LocalDate date = LocalDate.of(1991, 11, 3);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        SensorType typeTemperature = new SensorType(temperature);

        // Act
        double result = geoAreaSensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(typeTemperature, date);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getMaximumMeasureOfATypeOfSensorInAGivenDayEmptyListOfSensors() {
        // Arrange
        LocalDate date = LocalDate.of(1991, 11, 2);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        SensorType typeTemperature = new SensorType(temperature);

        double expectedResult = Double.NaN;

        // Act
        double result = emptyGeoAreaSensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(typeTemperature, date);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageTest() {
        // Arrange
        LocalDate searchDate = LocalDate.of(2018, 12, 3);

        double expectedResult = 20.0;

        // Act
        double result = geoAreaSensorList.getDailyAverage(searchDate);

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getDailyAverageMeasurementsInAListOfSensorsDayWithNoMeasurementsTest() {
        // Arrange
        LocalDate searchDate = LocalDate.of(2018, 12, 5);

        double expectedResult = Double.NaN;

        //Act
        double result = geoAreaSensorList.getDailyAverage(searchDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getSensorsListContentTest() {
        // Arrange
        String expectedResult =
                "1 - Name of the sensor: GeoAreaSensor Temp 1\n" +
                        "2 - Name of the sensor: GeoAreaSensor Temp 2\n" +
                        "3 - Name of the sensor: GeoAreaSensor Rain\n" +
                        "4 - Name of the sensor: GeoAreaSensor Humidity\n";

        // Act
        String result = geoAreaSensorList.getSensorListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTrueTest() {
        // Act
        boolean result = emptyGeoAreaSensorList.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyFalseTest() {
        // Act
        boolean result = geoAreaSensorList.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Arrange
        int expectedResult = Objects.hash(emptyGeoAreaSensorList.getListOfSensors());

        // Act
        int result = emptyGeoAreaSensorList.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        // Arrange
        GeoAreaSensorList geoAreaSensorList1 = new GeoAreaSensorList();

        //Act
        boolean result = geoAreaSensorList1.equals(emptyGeoAreaSensorList);

        // Assert
        assertTrue(result);
    }

    @Test
    void testEqualsSameObj() {
        // Act
        boolean result = geoAreaSensorList.equals(geoAreaSensorList);

        // Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        // Act
        boolean result = emptyGeoAreaSensorList.equals(dummySensor);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetSensorWithMostRecentReading() {
        // Arrange
        GeoAreaSensor expectedResult = temperatureSensor2;

        // Act
        GeoAreaSensor result = geoAreaSensorList.getSensorWithMostRecentReading(geoAreaSensorList);

        // Assert
        assertEquals(expectedResult, result);
    }

/*    @Test
    public void checkIfSensorExistsByIdTrueTest() {
        // Act
        boolean result = geoAreaSensorList.checkIfSensorExistsById("s1");

        // Assert
        assertTrue(result);
    }*/

    @Test
    public void checkIfSensorExistsByIdFalseTest() {
        // Act
        boolean result = geoAreaSensorList.checkIfSensorExistsById("s14123");

        // Assert
        assertFalse(result);
    }

    @Test
    public void getSensorByIdPositiveTest() {
        // Arrange
        GeoAreaSensor expectedResult = temperatureSensor1;

        // Act
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor result = geoAreaSensorList.getSensorById(sensorId);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorByIdNullTest() {
        // Act
        SensorId sensorId = new SensorId("s14123");
        GeoAreaSensor result = geoAreaSensorList.getSensorById(sensorId);

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testGetSensorWithMostRecentReadingNoArguments() {
        //arrange
        GeoAreaSensorList geoAreaSensorList = new GeoAreaSensorList();

        //GeoAreaSensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S09"), "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        geoAreaSensorList.addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S02"), "Sensor1", dataFuncionamento1, sensorType1, locS1, "l/m2");
        geoAreaSensorList.addSensor(s1);

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

        GeoAreaSensor expectedResult = s1;

        GeoAreaSensor result = geoAreaSensorList.getSensorWithMostRecentReading();

        //Assert
        assertEquals(expectedResult, result);
    }

 /*   *//**
     * Test that tries to use a valid/existing Id to search for a GeoAreaSensor, which results in True.
     **//*
    @Test
    void testCheckIfSensorExistsById_tryingToTestAnExistingId_ShouldReturnTrue() {
        // Act
        boolean result = geoAreaSensorList.checkIfSensorExistsById("s1");

        //
        assertTrue(result);
    }*/

    /**
     * Test that tries to use an invalid/non-existing Id to search for a GeoAreaSensor, which results in False.
     **/
    @Test
    void testCheckIfSensorExistsById_tryingToTestANonExistingId_ShouldReturnFalse() {
        // Act
        boolean result = geoAreaSensorList.checkIfSensorExistsById("FUKU");

        //
        assertFalse(result);
    }

    /**
     * Test that tries to use a valid/existing Id to get a GeoAreaSensor, which results in returning the corresponding GeoAreaSensor.
     **/
    @Test
    void testGetSensorById_tryingToTestAnExistingId_ShouldReturnTheCorrespondingSensor() {
        // Arrange
        GeoAreaSensor expectedResult = temperatureSensor1;

        // Act
        SensorId sensorId = new SensorId("s1");
        GeoAreaSensor result = geoAreaSensorList.getSensorById(sensorId);

        //
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to use an invalid/non-existing Id to get a GeoAreaSensor, which results in returning a null Object.
     **/
    @Test
    void testGetSensorById_tryingToTestANonExistingId_ShouldReturnNull() {
        // Act
        SensorId sensorId = new SensorId("s11241");
        GeoAreaSensor result = geoAreaSensorList.getSensorById(sensorId);

        //
        assertEquals(null, result);
    }

    @Test
    public void testCheckMeasurementExistenceBetweenDates_false() {
        LocalDate date1 = LocalDate.of(2017, 12, 3);
        LocalDate date2 = LocalDate.of(2017, 12, 4);
        boolean expectedResult = false;

        boolean result = geoAreaSensorList.checkMeasurementExistenceBetweenDates(location, date1, date2);

        assertEquals(expectedResult, result);


    }

    @Test
    public void testCheckMeasurementExistenceBetweenDates_true() {
        LocalDate date1 = LocalDate.of(2018, 12, 3);
        LocalDate date2 = LocalDate.of(2018, 12, 4);
        boolean expectedResult = true;

        boolean result = geoAreaSensorList.checkMeasurementExistenceBetweenDates(location, date1, date2);

        assertEquals(expectedResult, result);


    }

    /**
     * Test that tries to use a valid/existing Id to remove a sensor, which results in true.
     */
    @Test
    public void testRemoveSensorById_tryingWithAnExistingId_ShouldReturnTrue() {
        // Act
        SensorId sensorId = new SensorId("s1");
        boolean result = geoAreaSensorList.removeSensorById(sensorId);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to use an invalid/non-existing Id to remove a sensor, which results in false.
     */
    @Test
    public void testRemoveSensorById_tryingWithANonExistingId_ShouldReturnFalse() {
        // Act
        SensorId sensorId = new SensorId("s11241");
        boolean result = geoAreaSensorList.removeSensorById(sensorId);

        // Assert
        assertFalse(result);
    }
}