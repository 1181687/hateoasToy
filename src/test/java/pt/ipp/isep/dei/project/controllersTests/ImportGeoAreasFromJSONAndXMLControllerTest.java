package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonandxmlcontroller.ImportGeoAreasFromJSONAndXMLController;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportGeoAreasFromJSONAndXMLControllerTest {

    /**
     * Test that imports imports geo areas and sensors
     */
    @Test
    public void testImportGeographicalAreaAndSensors_True() {
        // arrange

        // DTO's
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1= 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // SensorDTO
        String idSensor = "S1";
        String nameSensor ="sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        SensorDTO sensorDTO = SensorMapper.newSensorDTO();
        sensorDTO.setId(idSensor);
        sensorDTO.setName(nameSensor);
        sensorDTO.setSensorType(typeSensor);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(locationDTO);
        sensorDTO.setUnits(units);

        // Geo area DTO
        String id = "A01";
        String description = "Espinho";
        String type = "Urban area";
        double width = 24;
        double length = 34;
        double latitude = -516;
        double longitude = 12;
        double altitude = 156;
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // Geographical Area List
        GeographicalAreaList geoList = new GeographicalAreaList();

        // add
        geographicalAreaDTOList.add(geographicalAreaDTO);
        geographicalAreaDTO.addSensor(sensorDTO);

        ImportGeoAreasFromJSONAndXMLController ctrl = new ImportGeoAreasFromJSONAndXMLController(geoList);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors(geographicalAreaDTOList);

        // assert
        assertTrue(result);
    }

    /**
     * test that doesn't import geo areas and sensors
     */
    @Test
    public void testImportGeographicalAreaAndSensors_False() {
        // arrange

        // DTO's
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1= 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // SensorDTO
        String idSensor = "S1";
        String nameSensor ="sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        SensorDTO sensorDTO = SensorMapper.newSensorDTO();
        sensorDTO.setId(idSensor);
        sensorDTO.setName(nameSensor);
        sensorDTO.setSensorType(typeSensor);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(locationDTO);
        sensorDTO.setUnits(units);

        // Geo area DTO
        String id = "A01";
        String description = "Espinho";
        String type = "Urban area";
        double width = 24;
        double length = 34;
        double latitude = -516;
        double longitude = 12;
        double altitude = 156;
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // Geographical Area List
        GeographicalAreaList geoList = new GeographicalAreaList();

        // add
        geographicalAreaDTO.addSensor(sensorDTO);

        ImportGeoAreasFromJSONAndXMLController ctrl = new ImportGeoAreasFromJSONAndXMLController(geoList);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors(geographicalAreaDTOList);

        // assert
        assertFalse(result);
    }
}