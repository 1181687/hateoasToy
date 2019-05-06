
package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RemoveSensorFromGeoAreaControllerTest {
    private RemoveSensorFromGeoAreaController controller;
    private GeographicalArea porto;
    private GeographicalAreaDTO portoDTO;
    private GeoAreaSensor temperatureSensor;
    private GeoAreaSensorDTO temperatureSensorDTO;
    @Mock
    private GeographicalAreaService geographicalAreaService;
    @Mock
    private GeoAreaSensorService geoAreaSensorService;


    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        // Geographical Area
        GeoAreaTypeId city = new GeoAreaTypeId("City");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(city);
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape shape = new AreaShape(10, 10);
        porto = new GeographicalArea("Porto", "City of Porto", geographicalAreaType, location, shape);
        geographicalAreaService.addGeoArea(porto);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        temperatureSensor = new GeoAreaSensor(new SensorId("S01"), "A123", startDate, temperature, sensorLocation, "l/m2",porto.getId());

        // Geographical Area DTO
        portoDTO = GeographicalAreaMapper.mapToDTOwithSensors(porto);

        // SensorDTOs
        temperatureSensorDTO = GeoAreaSensorMapper.mapToDTO(temperatureSensor);

        // Controller
        this.controller = new RemoveSensorFromGeoAreaController(geographicalAreaService,geoAreaSensorService);
    }


    /**
     * Test that tries to get the list of GeographicalAreaDTOs.
     */

    @Test
    public void testGetGeographicalAreaList_ShouldReturnTheCorrespondingList() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(GeographicalAreaMapper.mapToEntity(portoDTO).getId().getId());

        List<GeographicalArea> geographicalAreas = new ArrayList<>();
        geographicalAreas.add(porto);

        // Act
        when(geographicalAreaService.getGeoAreaList()).thenReturn(geographicalAreas);
        List<GeographicalAreaDTO> firstResult = controller.getGeographicalAreaDTO();
        List<String> result = new ArrayList<>();
        result.add(GeographicalAreaMapper.mapToEntity(firstResult.get(0)).getId().getId());

        // Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test that tries to get the list of SensorDTOs.
     */

    /*@Test
    public void testGetSensorList_ShouldReturnTheCorrespondingList() {
        // Arrange
        when(geographicalAreaService.getGeoAreaById(porto.getId().getId())).thenReturn(porto);
        controller.setGeoAreaById("Porto");
        List<GeoAreaSensorDTO> expectedResult = new ArrayList<>();
        expectedResult.add(temperatureSensorDTO);

        // Act
        GeoAreaIdDTO geoAreaIdDTO = GeoAreaIdMapper.mapToDTO(porto.getId());

        when(geoAreaSensorService.getSensorsByGeoAreaId(geoAreaIdDTO)).thenReturn(expectedResult);
        List<GeoAreaSensorDTO> result = controller.getSensorList();

        // Assert
        assertEquals(expectedResult, result);
    }*/


    /**
     * Test that tries to remove a GeoAreaSensor from a GeoArea when the Id doesn't correspond to an existing GeoAreaSensor.
     */

    @Test
    public void testRemoveSensor_whenIdDoesntCorrespond_ShouldReturnFalse() {
        // Arrange
        when(geographicalAreaService.getGeoAreaById("Porto")).thenReturn(porto);
        controller.setGeoAreaById("Porto");
        SensorId sensorId = new SensorId("S02");
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(sensorId);

        // Act
        when(geoAreaSensorService.removeSensor(sensorIdDTO)).thenReturn(false);
        boolean result = controller.removeSensor(sensorIdDTO);

        // Assert
        assertFalse(result);
    }


    /**
     * Test that tries to remove a GeoAreaSensor from a GeoArea when the Id corresponds to an existing GeoAreaSensor.
     */

    @Test
    public void testRemoveSensor_whenIdCorresponds_ShouldReturnTrue() {
        // Arrange
        when(geographicalAreaService.getGeoAreaById("Porto")).thenReturn(porto);
        controller.setGeoAreaById("Porto");
        SensorId sensorId = new SensorId("S01");
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(sensorId);

        // Act
        when(geoAreaSensorService.removeSensor(sensorIdDTO)).thenReturn(true);
        boolean result = controller.removeSensor(sensorIdDTO);

        // Assert
        assertTrue(result);
    }

}

