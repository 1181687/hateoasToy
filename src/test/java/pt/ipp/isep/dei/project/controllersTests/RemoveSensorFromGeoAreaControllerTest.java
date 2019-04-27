package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(RemoveSensorFromGeoAreaControllerTest.Config.class)
public class RemoveSensorFromGeoAreaControllerTest {
    private RemoveSensorFromGeoAreaController controller;
    private GeographicalArea porto;
    private GeographicalAreaDTO portoDTO;
    private GeoAreaSensor temperatureSensor;
    private GeoAreaSensorDTO temperatureSensorDTO;
    @InjectMocks
    private GeographicalAreaService geographicalAreaService;

    @Mock
    private GeoAreaRepository geoAreaRepository;


    @BeforeEach
    public void StartUp() {
        // Geographical Area
        GeographicalAreaType city = new GeographicalAreaType("City");
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape shape = new AreaShape(10, 10, location);
        porto = new GeographicalArea("Porto", "City of Porto", city, location, shape);
        geographicalAreaService.addGeoArea(porto);

        // Geographical Area DTO
        portoDTO = GeographicalAreaMapper.mapToDTOwithSensors(porto);

        // Sensors
        SensorType temperature = new SensorType("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new GeoAreaSensor("S01", "A123", startDate, temperature, sensorLocation, "l/m2");
        porto.addSensor(temperatureSensor);

        // SensorDTOs
        temperatureSensorDTO = GeoAreaSensorMapper.mapToDTO(temperatureSensor);

        // Controller
        this.controller = new RemoveSensorFromGeoAreaController(geographicalAreaService);
    }

    /**
     * Test that tries to get the list of GeographicalAreaDTOs.
     */
    @Test
    public void testGetGeographicalAreaList_ShouldReturnTheCorrespondingList() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(GeographicalAreaMapper.mapToEntity(portoDTO).getId().getId());

        // Act
        List<GeographicalAreaDTO> firstResult = controller.getGeographicalAreaService();
        List<String> result = new ArrayList<>();
        result.add(GeographicalAreaMapper.mapToEntity(firstResult.get(0)).getId().getId());

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
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(GeoAreaSensorMapper.mapToEntity(temperatureSensorDTO));

        // Act
        List<GeoAreaSensorDTO> firstResult = controller.getSensorList();
        List<GeoAreaSensor> result = new ArrayList<>();
        result.add(GeoAreaSensorMapper.mapToEntity(firstResult.get(0)));

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to remove a GeoAreaSensor from a GeoArea when the Id doesn't correspond to an existing GeoAreaSensor.
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
     * Test that tries to remove a GeoAreaSensor from a GeoArea when the Id corresponds to an existing GeoAreaSensor.
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

    @Configuration
    static class Config {
    }
}
