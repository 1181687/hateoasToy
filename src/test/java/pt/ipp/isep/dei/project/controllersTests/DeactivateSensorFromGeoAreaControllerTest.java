package pt.ipp.isep.dei.project.controllersTests;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeactivateSensorFromGeoAreaControllerTest {
    private DeactivateSensorFromGeoAreaController controller;
    private GeographicalArea porto;
    private GeographicalAreaDTO portoDTO;
    private Sensor temperatureSensor;
    private SensorDTO temperatureSensorDTO;

    @BeforeEach
    public void StartUp() {
        // Geo Area List
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        // Geographical Area
        GeographicalAreaType city = new GeographicalAreaType("City");
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape shape = new AreaShape(10, 10, location);
        porto = new GeographicalArea("Porto", "City of Porto", city, location, shape);
        geographicalAreaList.addGeoArea(porto);

        // Geographical Area DTO
        portoDTO = GeographicalAreaMapper.mapToDTOwithSensors(porto);

        // Sensors
        SensorType temperature = new SensorType("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new Sensor("S01", "A123", startDate, temperature, sensorLocation, "l/m2");
        porto.addSensor(temperatureSensor);

        // SensorDTOs
        temperatureSensorDTO = SensorMapper.mapToDTOwithSensors(temperatureSensor);

        // Controller
        this.controller = new DeactivateSensorFromGeoAreaController(geographicalAreaList);
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

    @Test
    public void testDeactivateDevice() {
        // Act
        boolean result = controller.deactivateSensor(temperatureSensorDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    public void deactivateDevice() {
        // Act
        temperatureSensorDTO.setActive(false);
        controller.deactivateSensor(temperatureSensorDTO);

        boolean result = temperatureSensor.isActive();
        // Assert
        assertFalse(result);
    }
}*/