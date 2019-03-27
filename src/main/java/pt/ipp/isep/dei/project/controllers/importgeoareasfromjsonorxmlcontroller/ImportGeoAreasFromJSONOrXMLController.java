package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller;

import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.SensorRepository;
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
@Service
public class ImportGeoAreasFromJSONOrXMLController {
    private GeographicalAreaList geographicalAreaList;
    private ProjectFileReader reader;
    private GeoAreaRepository geoAreaRepository;
    private SensorRepository sensorRepository;

    public ImportGeoAreasFromJSONOrXMLController(GeographicalAreaList geographicalAreaList, SensorRepository sensorRepository, GeoAreaRepository geoAreaRepository) {
        this.geographicalAreaList = geographicalAreaList;
        this.sensorRepository = sensorRepository;
        this.geoAreaRepository= GeoAreaService.getInstance().getGeoAreaRepository();
    }

    /**
     * This method import the GeographicalAreaDTO list to be imported
     *
     * @param file
     * @return boolean
     */
    public boolean importGeographicalAreaAndSensors(File file) throws FileNotFoundException {
        boolean imported = false;
        List<Object> geoAreaObjects = readFile(file);

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
    public List<Object> readFile(File file) throws FileNotFoundException {
        List<Object> geographicalAreaDTOList = this.reader.readFile(file);
        return geographicalAreaDTOList;
    }
}
