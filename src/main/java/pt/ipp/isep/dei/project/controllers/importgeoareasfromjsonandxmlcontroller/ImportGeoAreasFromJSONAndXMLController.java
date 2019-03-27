package pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonandxmlcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.SensorRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;

import java.util.List;

public class ImportGeoAreasFromJSONAndXMLController {
    private GeographicalAreaList geographicalAreaList;

    private SensorRepository sensorRepository;

    public ImportGeoAreasFromJSONAndXMLController(GeographicalAreaList geographicalAreaList, SensorRepository sensorRepository) {
        this.geographicalAreaList = geographicalAreaList;
        this.sensorRepository = sensorRepository;
    }


    /**
     * This method import the GeographicalAreaDTO list to be imported
     *
     * @param geoAreaObjects
     * @return boolean
     */
    public boolean importGeographicalAreaAndSensors(List<GeographicalAreaDTO> geoAreaObjects) {
        boolean imported = false;
        for (GeographicalAreaDTO geoObject : geoAreaObjects) {
            GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoObject);
            for (SensorDTO sensorDTO : geoObject.getSensors()) {
                Sensor sensor = SensorMapper.mapToEntity(sensorDTO);
                geoArea.addSensor(sensor);
                sensorRepository.save(sensor);
            }
            if (geographicalAreaList.addGeoArea(geoArea)) {
                imported = true;
            }
        }
        return imported;

    }


}
