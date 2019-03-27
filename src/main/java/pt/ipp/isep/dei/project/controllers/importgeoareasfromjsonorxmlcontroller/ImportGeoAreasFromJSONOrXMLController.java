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
    private List<Object> geoAreaDTOList;
    private GeoAreaRepository geoAreaRepository;
    private SensorRepository sensorRepository;

    public ImportGeoAreasFromJSONOrXMLController(GeographicalAreaList geographicalAreaList, SensorRepository sensorRepository, GeoAreaRepository geoAreaRepository) {
        this.geographicalAreaList = geographicalAreaList;
        this.sensorRepository = sensorRepository;
        this.geoAreaRepository = GeoAreaService.getInstance().getGeoAreaRepository();
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
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        this.geoAreaDTOList = this.reader.readFile(file);
        return geoAreaDTOList;
    }
}
