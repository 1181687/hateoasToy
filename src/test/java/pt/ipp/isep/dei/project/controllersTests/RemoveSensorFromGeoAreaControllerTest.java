package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RemoveSensorFromGeoAreaControllerTest {
    private RemoveSensorFromGeoAreaController controller;
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
        temperatureSensorDTO = SensorMapper.mapToDTO(temperatureSensor);

        // Controller
        this.controller = new RemoveSensorFromGeoAreaController(geographicalAreaList);
    }

    /**
     * Test that tries to get the list of GeographicalAreaDTOs.
     */
    @Test
    public void testGetGeographicalAreaList_ShouldReturnTheCorrespondingList() {
        // Arrange
        List<GeographicalArea> expectedResult = new ArrayList<>();
        expectedResult.add(GeographicalAreaMapper.mapToEntity(portoDTO));

        // Act
        List<GeographicalAreaDTO> firstResult = controller.getGeographicalAreaList();
        List<GeographicalArea> result = new ArrayList<>();
        result.add(GeographicalAreaMapper.mapToEntity(firstResult.get(0)));

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to get the list of SensorDTOs.
     */
    @Test
    public void testGetSensorList_ShouldReturnTheCorrespondingList() {
        // Arrange
        controller.setGeoAreaById("Porto");
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(SensorMapper.mapToEntity(temperatureSensorDTO));

        // Act
        List<SensorDTO> firstResult = controller.getSensorList();
        List<Sensor> result = new ArrayList<>();
        result.add(SensorMapper.mapToEntity(firstResult.get(0)));

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to remove a Sensor from a GeoArea when the Id doesn't correspond to an existing Sensor.
     */
    @Test
    public void testRemoveSensor_whenIdDoesntCorrespond_ShouldReturnFalse() {
        // Arrange
        controller.setGeoAreaById("Porto");

        // Act
        boolean result = controller.removeSensor("S01412312");

        // Assert
        assertFalse(result);
    }

    /**
     * Test that tries to remove a Sensor from a GeoArea when the Id corresponds to an existing Sensor.
     */
    @Test
    public void testRemoveSensor_whenIdCorresponds_ShouldReturnTrue() {
        // Arrange
        controller.setGeoAreaById("Porto");

        // Act
        boolean result = controller.removeSensor("S01");

        // Assert
        assertTrue(result);
    }
}
