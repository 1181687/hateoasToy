package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

@Service
public class GeoAreaSensorService {

    // Repository
    @Autowired
    GeoAreaSensorRepository geoAreaSensorRepository;

    // Services
    @Autowired
    GeoAreaSensorReadingsService geoAreaSensorReadingsService;
    @Autowired
    SensorTypeService sensorTypeService;


    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }

    public boolean addSensor(String id, String sensorName, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        if (!geoAreaSensorRepository.existsById(new GeoAreaSensorId(id))) {
            GeoAreaSensor geoAreaSensor = new GeoAreaSensor(id, sensorName, sensorTypeId, location, units, geoAreaId);
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }

    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }


}