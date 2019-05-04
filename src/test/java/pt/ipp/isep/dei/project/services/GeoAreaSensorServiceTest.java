package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GeoAreaSensorServiceTest {

    @Mock
    private GeoAreaSensorRepository geoAreaSensorRepo;
    private GeoAreaSensor geoAreaSensor;
    private GeoAreaSensorDTO geoAreaSensorDTO;
    @InjectMocks
    private GeoAreaSensorService geoAreaSensorService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        // GeoAreaSensor
        Location location = new Location(41.1496, -8.6109, 97);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 05, 02, 11, 45, 00);

        // GeoAreaId
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeoAreaId geoAreaId = new GeoAreaId(location, "Espinho", geographicalAreaType);

        this.geoAreaSensor = new GeoAreaSensor(new SensorId("s1"), "TT123123", startDate, temperature, location, "l/m2", geoAreaId);

        // GeoAreaSensorDTO
        this.geoAreaSensorDTO = new GeoAreaSensorDTO();
    }

    @Test
    public void getSensorById() {
        // Arrange
        SensorIdDTO sensorIdDTO = new SensorIdDTO();
        SensorId sensorId = new SensorId("Temperature");

        when(geoAreaSensorRepo.save(geoAreaSensor)).thenReturn(geoAreaSensor);
        when(geoAreaSensorRepo.findById(any(SensorId.class))).thenReturn(Optional.of(geoAreaSensor));

        GeoAreaSensorDTO expectedResult = GeoAreaSensorMapper.mapToDTO(this.geoAreaSensor);
        // Act
        GeoAreaSensorDTO result = this.geoAreaSensorService.getSensorById(sensorIdDTO);

        // Assert
        assertEquals(expectedResult.getId(), result.getId());
    }

    @Test
    public void saveSensors() {


    }

    @Test
    public void saveGeoAreaSensor() {


    }

    @Test
    public void getSensorsWithReadingsInInterval() {


    }

    @Test
    public void getNearestSensors() {


    }

    @Test
    public void getLatestGeoAreaReadingInInterval() {


    }

    @Test
    public void getNearestSensorWithMostRecentReading() {


    }

    @Test
    public void getDailyAverageBySensorId() {


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
}