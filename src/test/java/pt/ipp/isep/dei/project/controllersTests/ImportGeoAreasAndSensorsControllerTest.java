package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.importgeoareasandsensorscontroller.ImportGeoAreasAndSensorsController;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
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
    private List<Object> geoAreaDTOs;

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
            geoAreaDTOs = this.controller.readFile(file, path);
        } catch (FileNotFoundException e) {
            geoAreaDTOs = null;
        }
    }

    @Test
    public void importGeoAreasAndSensors() {
        // Arrange
        GeographicalAreaDTO geoAreaDTO = new GeographicalAreaDTO();
        List<GeographicalAreaDTO> geoAreas = new ArrayList<>();
        for (Object geoAreaObject : geoAreaDTOs) {
            geoAreaDTO = (GeographicalAreaDTO) geoAreaObject;
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLatitude(geoAreaDTO.getLatitude());
            locationDTO.setElevation(geoAreaDTO.getElevation());
            locationDTO.setLongitude(geoAreaDTO.getLongitude());
            GeoAreaIdDTO geoAreaIdDTO = new GeoAreaIdDTO();
            geoAreaIdDTO.setId(geoAreaDTO.getId());
            geoAreaIdDTO.setGeoAreaType(geoAreaDTO.getType());
            geoAreaIdDTO.setLocationDTO(locationDTO);
            when(geoAreaService.geoAreaExists(geoAreaIdDTO)).thenReturn(false);
            geoAreas.add(geoAreaDTO);
        }
        doNothing().when(geoAreaService).saveGeoAreas(geoAreas);

        // Act
        boolean result = this.controller.importGeoAreasAndSensors();

        // Assert
        assertTrue(result);
    }
}
