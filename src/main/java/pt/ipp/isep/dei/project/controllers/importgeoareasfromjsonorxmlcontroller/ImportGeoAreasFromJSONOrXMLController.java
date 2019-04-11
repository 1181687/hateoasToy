package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportGeoAreasFromJSONOrXMLController {
    @Autowired
    private GeographicalAreaService geographicalAreaService;
    private ProjectFileReader reader;
    private List<Object> geoAreaDTOList;


    public ImportGeoAreasFromJSONOrXMLController(GeographicalAreaService geographicalAreaService) {
        this.geographicalAreaService = geographicalAreaService;
    }

    /**
     * This method import the GeographicalAreaDTO list to be imported
     *
     * @param
     * @return boolean
     */
    public boolean importGeographicalAreaAndSensors() {
        boolean imported = false;

        for (Object geoObject : this.geoAreaDTOList) {
            GeographicalAreaDTO geoDTO = (GeographicalAreaDTO) geoObject;
            GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoDTO);
            for (GeoAreaSensorDTO sensorDTO : geoDTO.getSensors()) {
                geoArea.addSensor(GeoAreaSensorMapper.mapToEntity(sensorDTO));
            }
            if (geographicalAreaService.addGeoArea(geoArea)) {
                imported = true;
            }
        }
        //geographicalAreaList.updateRepository();
        return imported;

    }

    /**
     * receives the String Path (json or xml) and creates the respective reader (json or xml)
     * and saves it in controller private attribute reader
     *
     * @param path String path of the file to import
     */
    public void createReader(String path) {
        this.reader = Utils.createReader(path);
    }

    /**
     * receives a FileReader and reads
     *
     * @param file
     * @return
     */
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        this.geoAreaDTOList = this.reader.readFile(file);
        return geoAreaDTOList;
    }
}
