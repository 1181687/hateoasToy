package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonandxmlcontroller;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportGeoAreasFromJSONAndXMLController {
    private GeographicalAreaList geographicalAreaList;
    private ProjectFileReader reader;

    public ImportGeoAreasFromJSONAndXMLController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    /**
     * This method import the GeographicalAreaDTO list to be imported
     *
     * @param file
     * @return boolean
     */
    public boolean importGeographicalAreaAndSensors(File file) throws FileNotFoundException {
        boolean imported = false;
        List<Object> geoAreaObjects = readfile(file);


        for (Object geoObject : geoAreaObjects) {
            GeographicalAreaDTO geoDTO = (GeographicalAreaDTO) geoObject;
            GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoDTO);
            for (SensorDTO sensorDTO : geoDTO.getSensors()) {
                geoArea.addSensor(SensorMapper.mapToEntity(sensorDTO));
            }
            if (geographicalAreaList.addGeoArea(geoArea)) {
                imported = true;
            }
        }
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
    public List<Object> readfile(File file) throws FileNotFoundException {
        List<Object> geographicalAreaDTOList = this.reader.readFile(file);
        return geographicalAreaDTOList;
    }
}
