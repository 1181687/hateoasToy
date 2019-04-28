package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller.ImportGeoAreasFromJSONOrXMLController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(AddSensorToGeoAreaControllerTest.Config.class)
public class ImportGeoAreasFromJSONOrXMLControllerTest {
    @Autowired
    private GeographicalAreaService geographicalAreaService;

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    /**
     * Test that imports imports geo areas and sensors
     */
    @Test
    public void testImportGeographicalAreaAndSensors_True() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
        // arrange
        // DTO's
        List<Object> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1 = 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // GeoAreaSensorDTO
        String idSensor = "S1";
        String nameSensor = "sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        GeoAreaSensorDTO sensorDTO = GeoAreaSensorMapper.newSensorDTO();
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
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.newDTO();
        geographicalAreaDTO.setId(id);
        geographicalAreaDTO.setDescription(description);
        geographicalAreaDTO.setType(type);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setElevation(altitude);


        // add
        geographicalAreaDTOList.add(geographicalAreaDTO);
        geographicalAreaDTO.addSensor(sensorDTO);

        String path = "datasets/geoAreas/json/JSONfile.json";
        File file = new File(path);

        ImportGeoAreasFromJSONOrXMLController ctrl = new ImportGeoAreasFromJSONOrXMLController(geographicalAreaService, geoAreaSensorService);
        ctrl.createReader(path);
        ctrl.readFile(file, path);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors();

        // assert
        assertTrue(result);
    }

    @Configuration
    static class Config {
    }

}