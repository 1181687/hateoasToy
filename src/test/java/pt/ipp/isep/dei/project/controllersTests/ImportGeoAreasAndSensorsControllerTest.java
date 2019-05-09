package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.importgeoareasandsensorscontroller.ImportGeoAreasAndSensorsController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ImportGeoAreasAndSensorsControllerTest {
    @Mock
    private GeographicalAreaService geoAreaService;
    @Mock
    private GeoAreaSensorService geoAreaSensorService;
    private ImportGeoAreasAndSensorsController controller;
    private List<Object> readGeoAreas;

    @BeforeEach
    public void StartUp() {
        // Mockito
        MockitoAnnotations.initMocks(this);

        // Controller
        this.controller = new ImportGeoAreasAndSensorsController(geoAreaService, geoAreaSensorService);

        // Geographical Areas and Sensors
        String path = "datasets/sprint7/DataSet_sprint07_GA.json";
        File file = new File(path);
        try {
            readGeoAreas = this.controller.readFile(file, path);
        } catch (FileNotFoundException e) {
            readGeoAreas = null;
        }
    }

    @Test
    public void importGeoAreasAndSensors() {
        // Arrange
        List<GeographicalAreaDTO> geoAreaDTOs = new ArrayList<>();
        List<GeoAreaSensorDTO> sensorDTOs = new ArrayList<>();
        for (Object geoAreaObject : readGeoAreas) {
            GeographicalAreaDTO geoAreaDTO = (GeographicalAreaDTO) geoAreaObject;
            when(geoAreaService.geoAreaExists(geoAreaDTO.getGeoAreaIdDTO())).thenReturn(false);
            geoAreaDTOs.add(geoAreaDTO);
            for (GeoAreaSensorDTO sensorDTO : geoAreaDTO.getSensors()) {
                when(geoAreaSensorService.sensorExists(sensorDTO.getSensorIdDTO())).thenReturn(false);
                sensorDTOs.add(sensorDTO);
            }
            doNothing().when(geoAreaSensorService).saveSensors(sensorDTOs);
        }
        doNothing().when(geoAreaService).saveGeoAreas(geoAreaDTOs);

        // Act
        boolean result = this.controller.importGeoAreasAndSensors();

        // Assert
        assertTrue(result);
    }
}
