package pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapping;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.utils.JSONReader;

import java.util.List;

public class ImportReadingsFromJSONController {
    private GeographicalAreaList geographicalAreaList;

    public ImportReadingsFromJSONController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    public List<GeographicalAreaDTO> readGeoAreaJson(String path) {
        return JSONReader.readJSONFileToList(path);
    }

    public boolean importGeographicalAreaAndSensors(List<GeographicalAreaDTO> geoAreaObjects) {
        for (GeographicalAreaDTO geoObject : geoAreaObjects) {
            GeographicalArea geoArea = GeographicalAreaMapping.mapToEntityGeoArea(geoObject);
            for (SensorDTO sensorDTO : geoObject.getSensors()) {
                geoArea.addSensor(SensorMapper.mapToEntity(sensorDTO));
            }
            geographicalAreaList.addGeoArea(geoArea);
        }
        return true;
    }

}
