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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void getLatestGeoAreaReadingInInterval() {
        // Arrange


        // Act


        // Assert

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

        when(this.geoAreaSensorRepo.findGeoAreaSensorsById(sensorId)).thenReturn(geoAreaSensor);

        Double expectedResult = 5.0;
        // Act
        Double result = geoAreaSensorService.getDailyAverageBySensorId(sensorId, date);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void doesSensorExist() {


    }

    @Test
    public void addGeoAreaSensor() {


    }

    @Test
    public void getMapAverageOfDailyMeasurements() {


    }

    @Test
    public void getComfortTemperature() {


    }

    @Test
    public void getDaysWithoutComfortTemp() {


    }

    @Test
    public void existsDaysWithoutComfortTemp() {


    }

    @Test
    public void sensorExists() {


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