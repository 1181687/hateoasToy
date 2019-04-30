package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class DeactivateSensorFromGeoAreaControllerTest {
    private DeactivateSensorFromGeoAreaController controller;
    private GeographicalArea porto;
    private GeographicalAreaDTO portoDTO;
    private GeoAreaSensor temperatureSensor;
    private GeoAreaSensorDTO temperatureSensorDTO;

    @Mock
    private GeographicalAreaService geographicalAreaService;

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

        // Geographical Area DTO
        portoDTO = GeographicalAreaMapper.mapToDTOwithSensors(porto);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        SensorId sensorId = new SensorId("S01");
        temperatureSensor = new GeoAreaSensor(sensorId, "A123", startDate, temperature, sensorLocation, "l/m2");
        porto.addSensor(temperatureSensor);

        // SensorDTOs
        temperatureSensorDTO = GeoAreaSensorMapper.mapToDTO(temperatureSensor);

        // Controller
        this.controller = new DeactivateSensorFromGeoAreaController(geographicalAreaService);
    }

    /*@Test
    public void testDeactivateDevice_ChecksDeactivated_True() {
        // Act
        when(geographicalAreaService.getSensorById(anyString())).thenReturn(temperatureSensor);
        temperatureSensor.deactivateDevice();

        boolean result = temperatureSensor.isActive();
        // Assert
        assertFalse(result);
    }

    @Test
    public void testDeactivateDevice_ChecksDeactivated_DeviceAlreadyDeactivated() {
        // Act
        when(geographicalAreaService.getSensorById(anyString())).thenReturn(temperatureSensor);
        temperatureSensor.deactivateDevice();

        boolean result = temperatureSensor.deactivateDevice();
        // Assert
        assertFalse(result);
    }*/

    @Test
    public void testListOfGeographicalAreas() {
        // Arrange
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();

        geographicalAreaList.add(porto);

        when(geographicalAreaService.getGeoAreaList()).thenReturn(geographicalAreaList);

        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geographicalAreaList.get(0));

        String expectedResult = geographicalAreaDTO.getId();

        // Act
        String result = "Porto";

        // Assert
        assertEquals(expectedResult, result);
    }
}