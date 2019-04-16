package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorRepository;

@Service
public class GeoAreaSensorService {

    // Repository
    @Autowired
    GeoAreaSensorRepository geoAreaSensorRepository;

    // Services
    @Autowired
    GeoAreaSensorReadingsService geoAreaSensorReadingsService;





    /*public boolean addSensor(String id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        if (!geoAreaSensorRepository.existsById(new GeoAreaSensorId(id))) {
            GeoAreaSensor geoAreaSensor = new GeoAreaSensor(id, sensorName, startingDate, sensorTypeId, location, units, geoAreaId);
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }

    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }*/


}