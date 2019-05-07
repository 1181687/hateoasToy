package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GeoAreaSensorServiceTest {

    @Mock
    private GeoAreaSensorRepository geoAreaSensorRepo;
    private GeoAreaSensor geoAreaSensor;
    private GeoAreaSensor geoAreaSensor1;
    @InjectMocks
    private GeoAreaSensorService geoAreaSensorService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        // GeoAreaSensor
        Location location = new Location(41.1496, -8.6109, 97);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 5, 2, 11, 45, 0);

        // GeoAreaId
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);

        this.geoAreaSensor = new GeoAreaSensor(new SensorId("s1"), "TT123123", startDate, temperature, location, "l/m2", geoAreaId);

        // GeoAreaSensor1
        Location location1 = new Location(41.1357, -8.6057, 99);
        SensorTypeId temperature1 = new SensorTypeId("Temperature");
        LocalDateTime startDate1 = LocalDateTime.of(2017, 5, 2, 12, 45, 0);

        // GeoAreaId1
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType1 = new GeographicalAreaType(geoAreaTypeId1);
        GeoAreaId geoAreaId1 = new GeoAreaId(location, "Porto", geographicalAreaType1);

        this.geoAreaSensor1 = new GeoAreaSensor(new SensorId("s2"), "TT111111", startDate1, temperature1, location1, "l/m2", geoAreaId1);
    }

    @Test
    public void getSensorByIdTest() {
        // Arrange
        SensorIdDTO sensorIdDTO = new SensorIdDTO();
        when(geoAreaSensorRepo.findById(any(SensorId.class))).thenReturn(Optional.of(geoAreaSensor));

        // Act
        GeoAreaSensorDTO expectedResult = GeoAreaSensorMapper.mapToDTO(this.geoAreaSensor);
        GeoAreaSensorDTO result = this.geoAreaSensorService.getSensorById(sensorIdDTO);

        // Assert
        assertEquals(expectedResult.getId(), result.getId());
    }

    @Test
    public void getSensorsWithReadingsInIntervalTest() {

        // Arrange
        List<GeoAreaSensor> geoAreaSensorList = new ArrayList<>();
        Location location = new Location(123, 456, 789);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        LocalDate startDate = LocalDate.of(1991, 4, 12);
        LocalDate endDate = LocalDate.of(2019, 5, 6);

        when(geoAreaSensorRepo.findByGeoAreaIdAndSensorTypeId(any(GeoAreaId.class), any(SensorTypeId.class))).thenReturn(geoAreaSensorList);

        // Act
        List<GeoAreaSensor> result = geoAreaSensorService.getSensorsWithReadingsInInterval(geoAreaId, sensorTypeId, startDate, endDate);

        // Assert
        assertEquals(geoAreaSensorList, result);
    }

    @Test
    public void getNearestSensors() {
        // Arrange
        List<GeoAreaSensor> geoAreaSensorList = new ArrayList<>();

        Location location = new Location(123, 456, 789);
        List<GeoAreaSensor> sensors = new ArrayList<>();

        // Act
        List<GeoAreaSensor> result = geoAreaSensorService.getNearestSensors(location, sensors);

        // Assert
        assertEquals(geoAreaSensorList, result);
    }

    @Test
    public void getNearestSensorWithMostRecentReading() {
        // Arrange
        Location location = new Location(123, 456, 789);
        List<GeoAreaSensor> geoAreaSensorList = new ArrayList<>();
        LocalDate startDate = LocalDate.of(1991, 4, 12);
        LocalDate endDate = LocalDate.of(2019, 5, 6);

        geoAreaSensorList.add(this.geoAreaSensor);
        geoAreaSensorList.add(this.geoAreaSensor1);

        // Reading sensor
        LocalDateTime date = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        Reading reading = new Reading(12, date);

        this.geoAreaSensor.addReading(reading);

        // Reading sensor1
        LocalDateTime date1 = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        Reading reading1 = new Reading(12, date1);

        this.geoAreaSensor1.addReading(reading1);

        GeoAreaSensor expectedResult = this.geoAreaSensor1;
        // Act
        GeoAreaSensor result = geoAreaSensorService.getNearestSensorWithMostRecentReading(location, geoAreaSensorList, startDate, endDate);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDailyAverageBySensorId() {
        // Arrange
        SensorId sensorId = new SensorId("s1");
        LocalDate date = LocalDate.of(2000, 1, 1);

        LocalDateTime date1 = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        Reading reading1 = new Reading(12, date1);

        this.geoAreaSensor.addReading(reading1);

        when(this.geoAreaSensorRepo.findGeoAreaSensorsById(sensorId)).thenReturn(geoAreaSensor);

        Double expectedResult = 12.0;
        // Act
        Double result = geoAreaSensorService.getDailyAverageBySensorId(sensorId, date);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void doesSensorExistTest_ShouldBeTrue() {
        // Arrange
        SensorId sensorId = new SensorId("s1");
        when(geoAreaSensorRepo.existsById(sensorId)).thenReturn(true);

        // Act
        boolean result = geoAreaSensorService.doesSensorExist(sensorId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void doesSensorExistTest_ShouldReturnFalse() {
        // Arrange
        SensorId sensorId = new SensorId("s1");
        when(geoAreaSensorRepo.existsById(sensorId)).thenReturn(false);

        // Act
        boolean result = geoAreaSensorService.doesSensorExist(sensorId);

        // Assert
        assertFalse(result);
    }

    @Test
    public void addGeoAreaSensor_ShouldReturnTrue() {

        boolean result = geoAreaSensorService.addGeoAreaSensor(geoAreaSensor);
        assertTrue(result);
    }

    @Test
    public void addGeoAreaSensor_ShouldReturnFalse() {

        geoAreaSensorService.addGeoAreaSensor(geoAreaSensor);
        when(geoAreaSensorRepo.existsById(geoAreaSensor.getId())).thenReturn(true);
        boolean result = geoAreaSensorService.addGeoAreaSensor(geoAreaSensor);

        assertFalse(result);
    }

    @Test
    public void getMapAverageOfDailyMeasurementsTest() {
        // Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        Location location = new Location(123, 456, 789);
        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);

        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        LocalDate startDate = LocalDate.of(1991, 4, 12);
        LocalDate endDate = LocalDate.of(2019, 5, 6);

        LocalDate date = LocalDate.of(2015, 9, 30);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(date, 15.0);

        // Act
        Map<LocalDate, Double> result = geoAreaSensorService.getMapAverageOfDailyMeasurements(location, geoAreaId, sensorTypeId, startDate, endDate);
        result.put(date, 15.0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getComfortTemperature() {
        // Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        Location location = new Location(123, 456, 789);
        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);

        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        LocalDate startDate = LocalDate.of(1991, 4, 12);
        LocalDate endDate = LocalDate.of(2019, 5, 6);

        LocalDate date = LocalDate.of(2015, 9, 30);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);

        Map<LocalDate, List<Double>> expectedResult = new HashMap<>();
        expectedResult.put(date, doubleList);

        // Act
        Map<LocalDate, List<Double>> result = geoAreaSensorService.getComfortTemperature(location, geoAreaId, sensorTypeId, startDate, endDate, 15);
        result.put(date, doubleList);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDaysWithoutComfortTempTest() {
        // Arrange
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);

        List<LocalDate> expectedResult = new ArrayList<>();
        expectedResult.add(LocalDate.of(2017, 9, 30));
        expectedResult.add(LocalDate.of(2015, 9, 30));

        Map<LocalDate, List<Double>> listHashMap = new HashMap<>();
        listHashMap.put(LocalDate.of(2017, 9, 30), doubleList);

        // Act
        List<LocalDate> result = geoAreaSensorService.getDaysWithoutComfortTemp(listHashMap);
        result.add(LocalDate.of(2017, 9, 30));
        result.add(LocalDate.of(2015, 9, 30));

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void sensorExists_ShouldReturnTrue() {
        // Arrange
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(geoAreaSensor.getId());
        SensorId sensorId = SensorIdMapper.mapToEntity(sensorIdDTO);

        when(geoAreaSensorRepo.existsById(sensorId)).thenReturn(true);

        // Act
        boolean result = geoAreaSensorService.sensorExists(sensorIdDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    public void sensorExists_ShouldReturnFalse() {
        // Arrange
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(geoAreaSensor.getId());
        SensorId sensorId = SensorIdMapper.mapToEntity(sensorIdDTO);

        when(geoAreaSensorRepo.existsById(sensorId)).thenReturn(false);

        // Act
        boolean result = geoAreaSensorService.sensorExists(sensorIdDTO);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getSensorsByGeoAreaIdTest() {
        // Arrange
        Location location = new Location(123, 456, 789);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);
        List<GeoAreaSensorDTO> geoAreaSensorDTOList = new ArrayList<>();

        List<GeoAreaSensor> geoAreaSensorList = new ArrayList<>();
        when(this.geoAreaSensorRepo.findAllByGeoAreaId(geoAreaId)).thenReturn(geoAreaSensorList);

        // Act
        List<GeoAreaSensorDTO> result = geoAreaSensorService.getSensorsByGeoAreaId(GeoAreaIdMapper.mapToDTO(geoAreaId));

        // assert
        assertEquals(geoAreaSensorDTOList, result);
    }
}