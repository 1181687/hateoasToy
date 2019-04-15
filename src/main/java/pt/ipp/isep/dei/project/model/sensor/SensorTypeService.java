package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.SensorTypeRepository;

public class SensorTypeService {
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    public SensorTypeService() {
    }
}
