package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller;


import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImportGeoAreasFromJSONOrXMLController {
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
    public ImportGeoAreasFromJSONOrXMLController(GeographicalAreaService geoAreaService, GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaService = geoAreaService;
        this.geoAreaSensorService = geoAreaSensorService;
    }

    /**
     * Method that creates a reader based on the path.
     *
     * @param path Path of the file.
     * @return ProjectFileReader that corresponds to the required reader.
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
     * Method that imports the geo areas and its sensors.
     *
     * @return True or false.
     */
    public boolean importGeographicalAreaAndSensors() {
        List<GeographicalArea> geographicalAreas = new ArrayList<>();
        List<GeoAreaSensor> sensors = new ArrayList<>();
        for (Object geoObject : this.geoAreaDTOs) {
            GeographicalAreaDTO geoDTO = (GeographicalAreaDTO) geoObject;
            GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoDTO);
            geographicalAreas.add(geoArea);
            for (GeoAreaSensorDTO sensorDTO : geoDTO.getSensors()) {
                GeoAreaSensor sensor = GeoAreaSensorMapper.mapToEntity(sensorDTO);
                sensors.add(sensor);
            }
        }
        return geoAreaService.saveGeoAreas(geographicalAreas) && geoAreaSensorService.saveSensors(sensors);
    }
}
