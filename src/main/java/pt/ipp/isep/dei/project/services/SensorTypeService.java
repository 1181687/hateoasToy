package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.SensorTypeRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

@Service
public class SensorTypeService {
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    public SensorTypeService() {
    }

    public boolean createAndAddSensorType(String sensorTypeId) {
        if (!sensorTypeRepository.existsById(new SensorTypeId(sensorTypeId))) {
            this.sensorTypeRepository.save(new SensorType(sensorTypeId));
            return true;
        }
        return false;
    }
}
