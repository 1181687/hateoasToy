package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

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


    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId) {
        return geoAreaSensorRepository.findById(geoAreaSensorId).orElse(null);
    }


    public boolean addSensor(GeoAreaSensor geoAreaSensor){
        if (!geoAreaSensorRepository.existsById(geoAreaSensor.getId())) {
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }

    public List<GeoAreaSensor> findActiveSensorsByGeoAreaId(GeoAreaId geoAreaId){
        List<GeoAreaSensor> allSensors = this.geoAreaSensorRepository.findByGeoAreaId(geoAreaId);

        for (GeoAreaSensor sensor : allSensors) {
            if (!sensor.isActive()) {
                allSensors.remove(sensor);
            }
        }
        return allSensors;
    }


}