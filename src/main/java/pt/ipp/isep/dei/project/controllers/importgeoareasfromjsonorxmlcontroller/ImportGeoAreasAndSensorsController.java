package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller;


import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImportGeoAreasAndSensorsController {
    private GeographicalAreaService geoAreaService;
    private GeoAreaSensorService geoAreaSensorService;
    private ProjectFileReader reader;
    private List<Object> geoAreaDTOs;

    /**
     * Constructor.
     *
     * @param geoAreaService       Service to be used.
     * @param geoAreaSensorService Service to be used.
     */
    public ImportGeoAreasAndSensorsController(GeographicalAreaService geoAreaService, GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaService = geoAreaService;
        this.geoAreaSensorService = geoAreaSensorService;
    }

    /**
     * Method that creates a reader based on the path.
     *
     * @param path Path of the file.
     */
    public void createReader(String path) {
        this.reader = Utils.createReader(path);
    }

    /**
     * Method that reads the information of the file.
     *
     * @param file File to be analyzed.
     * @param path Path of the file.
     * @return List of GeoAreaDTOs.
     */
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        this.geoAreaDTOs = this.reader.readFile(file);
        return geoAreaDTOs;
    }

    /**
     * Method that imports the geographical areas and its sensors.
     *
     * @return True or false.
     */
    public boolean importGeoAreasAndSensors() {
        boolean imported = false;
        List<GeographicalAreaDTO> geoAreaDTOs = new ArrayList<>();
        for (Object geoObject : this.geoAreaDTOs) {
            GeographicalAreaDTO geoAreaDTO = (GeographicalAreaDTO) geoObject;
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLatitude(geoAreaDTO.getLatitude());
            locationDTO.setLongitude(geoAreaDTO.getLongitude());
            locationDTO.setElevation(geoAreaDTO.getElevation());
            GeoAreaIdDTO geoAreaIdDTO = new GeoAreaIdDTO();
            geoAreaIdDTO.setId(geoAreaDTO.getId());
            geoAreaIdDTO.setLocationDTO(locationDTO);
            geoAreaIdDTO.setGeoAreaType(geoAreaDTO.getType());
            if (!this.geoAreaService.geoAreaExists(geoAreaIdDTO)) {
                geoAreaDTOs.add(geoAreaDTO);
                importSensors(geoAreaDTO);
                imported = true;
            }
        }
        geoAreaService.saveGeoAreas(geoAreaDTOs);
        return imported;
    }

    /**
     * Method that imports the sensors of a given geographical area.
     *
     * @param geoAreaDTO Geographical area to analyze.
     */
    private void importSensors(GeographicalAreaDTO geoAreaDTO) {
        List<GeoAreaSensorDTO> sensorDTOs = new ArrayList<>();
        for (GeoAreaSensorDTO sensorDTO : geoAreaDTO.getSensors()) {
            SensorIdDTO sensorIdDTO = new SensorIdDTO();
            sensorIdDTO.setId(sensorDTO.getId());
            if (!this.geoAreaSensorService.sensorExists(sensorIdDTO)) {
                sensorDTOs.add(sensorDTO);
            }
        }
        geoAreaSensorService.saveSensors(sensorDTOs);
    }
}
