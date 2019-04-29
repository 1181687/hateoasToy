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
        GeographicalAreaType city = new GeographicalAreaType("City");
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape shape = new AreaShape(10, 10, location);
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

    @Test
    public void testDeactivateDevice_ChecksDeactivated_True() {
        // Act
        controller.deactivateSensor(temperatureSensorDTO);

        boolean result = temperatureSensor.isActive();
        // Assert
        assertFalse(result);
    }

    @Test
    public void testListOfGeographicalAreas() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();

        expectedResult.add(portoDTO.getId());

        List<GeographicalAreaDTO> dtoList = controller.listOfGeographicalAreas();

        // Act
        List<String> result = new ArrayList<>();

        result.add(dtoList.get(0).getId());

        // Assert
        assertEquals(expectedResult, result);
    }
}