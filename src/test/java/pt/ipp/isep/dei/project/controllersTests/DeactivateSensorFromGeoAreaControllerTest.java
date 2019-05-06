package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeactivateSensorFromGeoAreaControllerTest {
    private DeactivateSensorFromGeoAreaController controller;
    private GeographicalArea porto;
    private GeoAreaSensor temperatureSensor;

    @Mock
    private GeographicalAreaService geographicalAreaService;
    @Mock
    private GeoAreaSensorService geoAreaSensorService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        // Geographical Area
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId);
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape shape = new AreaShape(10, 10);
        porto = new GeographicalArea("Porto", "City of Porto", city, location, shape);
        geographicalAreaService.addGeoArea(porto);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        SensorId sensorId = new SensorId("S01");
        temperatureSensor = new GeoAreaSensor(sensorId, "A123", startDate, temperature, sensorLocation, "l/m2", porto.getId());
        porto.addSensor(temperatureSensor);

        // Controller
        this.controller = new DeactivateSensorFromGeoAreaController(geographicalAreaService,geoAreaSensorService);
    }

    @Test
    public void testDeactivateDevice_ChecksDeactivated_True() {
        // Act
        when(geographicalAreaService.getSensorById(any(SensorId.class))).thenReturn(temperatureSensor);
        temperatureSensor.deactivateDevice();

        boolean result = temperatureSensor.isActive();
        // Assert
        assertFalse(result);
    }

    @Test
    public void testDeactivateDevice_DeviceActive_ReturnsTrue() {
        // Act
        GeoAreaSensorDTO geoAreaSensorDTO = GeoAreaSensorMapper.mapToDTO(temperatureSensor);
        when(geoAreaSensorService.deactivateSensor(any(GeoAreaSensorDTO.class))).thenReturn(true);

        boolean result = controller.deactivateSensor(geoAreaSensorDTO);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testDeactivateDevice_DeviceDeactivated_ReturnsFalse() {
        // Act
        temperatureSensor.deactivateDevice();
        GeoAreaSensorDTO geoAreaSensorDTO = GeoAreaSensorMapper.mapToDTO(temperatureSensor);
        when(geoAreaSensorService.deactivateSensor(any(GeoAreaSensorDTO.class))).thenReturn(false);

        boolean result = controller.deactivateSensor(geoAreaSensorDTO);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testDeactivateDevice_ChecksDeactivated_DeviceAlreadyDeactivated() {
        // Act
        when(geographicalAreaService.getSensorById(any(SensorId.class))).thenReturn(temperatureSensor);
        temperatureSensor.deactivateDevice();

        boolean result = temperatureSensor.deactivateDevice();
        // Assert
        assertFalse(result);
    }

    @Test
    public void testListOfGeographicalAreas() {
        // Arrange
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();

        geographicalAreaList.add(porto);

        when(geographicalAreaService.getGeoAreaList()).thenReturn(geographicalAreaList);

        String expectedResult = controller.listOfGeographicalAreas().get(0).getId();

        // Act
        String result = "Porto";

        // Assert
        assertEquals(expectedResult, result);
    }
}