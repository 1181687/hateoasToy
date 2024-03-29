package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddSensorToGeoAreaControllerTest {

    @Mock
    private GeographicalAreaService geographicalAreaService;
    @Mock
    private SensorTypeService sensorTypeService;
    @Mock
    private GeoAreaSensorService geoAreaSensorService;
    private AddSensorToGeoAreaController controller;

    @BeforeEach
    public void StartUp() {

        MockitoAnnotations.initMocks(this);
        this.controller = new AddSensorToGeoAreaController(geographicalAreaService, sensorTypeService, geoAreaSensorService);
    }

    @Test
    public void testIsGeoAreaRepositoryEmpty_ShouldReturnTrue() {

        when(this.geographicalAreaService.isGeoAreaRepositoryEmpty()).thenReturn(true);
        assertTrue(this.controller.isGeoAreaRepositoryEmpty());
    }

    @Test
    public void testIsGeoAreaRepositoryEmpty_ShouldReturnFalse() {

        when(this.geographicalAreaService.isGeoAreaRepositoryEmpty()).thenReturn(false);
        assertFalse(this.controller.isGeoAreaRepositoryEmpty());
    }

    @Test
    public void testGetGeographicalAreaDTOList() {

        // Geographical area
        Location location = new Location(123, 456, 789);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("city");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);

        AreaShape areaShape = new AreaShape(123, 456);

        GeographicalArea geographicalArea = new GeographicalArea("Espinho", "Cidade de Espinho", geographicalAreaType, location, areaShape);

        // Geographical area list
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();
        geographicalAreaList.add(geographicalArea);

        when(this.geographicalAreaService.getGeoAreaList()).thenReturn(geographicalAreaList);

        // Geographical area DTO list
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTOwithSensors(geographicalArea);
        geographicalAreaDTOList.add(geographicalAreaDTO);

        // result
        List<GeographicalAreaDTO> result = this.controller.getGeographicalAreaDTOList();

        GeographicalAreaDTO geoAreaDTO = geographicalAreaDTOList.get(0);
        GeographicalAreaDTO geoAreaResult = result.get(0);

        // assert
        assertEquals(geoAreaDTO.getId(), geoAreaResult.getId());
        assertEquals(geoAreaDTO.getDescription(), geoAreaResult.getDescription());
        assertEquals(geoAreaDTO.getType(), geoAreaResult.getType());
        assertEquals(geoAreaDTO.getElevation(), geoAreaResult.getElevation());
        assertEquals(geoAreaDTO.getLatitude(), geoAreaResult.getLatitude());
        assertEquals(geoAreaDTO.getLongitude(), geoAreaResult.getLongitude());
        assertEquals(geoAreaDTO.getLength(), geoAreaResult.getLength());
        assertEquals(geoAreaDTO.getWidth(), geoAreaResult.getWidth());
        assertEquals(geoAreaDTO.getSensors(), geoAreaResult.getSensors());
    }

    @Test
    public void testGetSensorTypeDTOList() {

        // Sensor type
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        SensorType sensorType = new SensorType(sensorTypeId);

        // Sensor type list
        List<SensorType> sensorTypeList = new ArrayList<>();
        sensorTypeList.add(sensorType);

        when(this.sensorTypeService.getSensorTypeList()).thenReturn(sensorTypeList);

        // Sensor type DTO list
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        SensorTypeDTO sensorTypeDTO = SensorTypeMapper.mapToDto(sensorType);
        sensorTypeDTOList.add(sensorTypeDTO);

        List<SensorTypeDTO> result = this.controller.getSensorTypeDTOList();

        SensorTypeDTO sensorTypeDTO1 = sensorTypeDTOList.get(0);
        SensorTypeDTO sensorTypeResult = result.get(0);

        //assert
        assertEquals(sensorTypeDTO1.getSensorType(), sensorTypeResult.getSensorType());
    }

    @Test
    public void testAddGeoAreaSensor_ShouldReturnTrue() {

        // GeoAreaSensor
        SensorId geoAreaSensorId = new SensorId("geoAreaSensorId");
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        Location location = new Location(123, 456, 789);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(new GeoAreaTypeId("City"));
        AreaShape areaShape = new AreaShape(25, 23);
        GeographicalArea geoArea = new GeographicalArea("Id", "Description", geographicalAreaType, location, areaShape);
        GeoAreaIdDTO geoAreaIdDTO = GeoAreaIdMapper.mapToDTO(geoArea.getId());
        when(this.geographicalAreaService.getGeoAreaById(geoAreaIdDTO)).thenReturn(geoArea);

        GeoAreaId geoAreaId = new GeoAreaId(location, "id", geographicalAreaType);
        LocalDateTime localDateTime = LocalDate.of(1999, 12, 31).atStartOfDay();
        GeoAreaSensor geoAreaSensor = new GeoAreaSensor(geoAreaSensorId, "GeoAreaSensor", localDateTime, sensorTypeId, location, "1m/s", geoAreaId);

        // GeoAreaSensor DTO
        GeoAreaSensorDTO geoAreaSensorDTO = GeoAreaSensorMapper.mapToDTO(geoAreaSensor);

        when(this.geoAreaSensorService.addGeoAreaSensor(geoAreaSensor)).thenReturn(true);
        assertTrue(this.controller.addGeoAreaSensor(geoAreaSensorDTO));
    }

    @Test
    public void testAddGeoAreaSensor_ShouldReturnFalse() {

        // GeoAreaSensor
        SensorId geoAreaSensorId = new SensorId("geoAreaSensorId");
        SensorTypeId sensorTypeId = new SensorTypeId("Humidity");
        Location location = new Location(123, 456, 789);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(new GeoAreaTypeId("City"));
        AreaShape areaShape = new AreaShape(25, 23);
        GeographicalArea geoArea = new GeographicalArea("Id", "Description", geographicalAreaType, location, areaShape);
        GeoAreaIdDTO geoAreaIdDTO = GeoAreaIdMapper.mapToDTO(geoArea.getId());
        when(this.geographicalAreaService.getGeoAreaById(geoAreaIdDTO)).thenReturn(geoArea);

        GeoAreaId geoAreaId = new GeoAreaId(location, "id", geographicalAreaType);
        LocalDateTime localDateTime = LocalDate.of(1999, 12, 31).atStartOfDay();

        GeoAreaSensor geoAreaSensor = new GeoAreaSensor(geoAreaSensorId, "GeoAreaSensor", localDateTime, sensorTypeId, location, "1m/s", geoAreaId);

        // GeoAreaSensor DTO
        GeoAreaSensorDTO geoAreaSensorDTO = GeoAreaSensorMapper.mapToDTO(geoAreaSensor);

        when(this.geoAreaSensorService.addGeoAreaSensor(geoAreaSensor)).thenReturn(false);
        assertFalse(this.controller.addGeoAreaSensor(geoAreaSensorDTO));
    }
}