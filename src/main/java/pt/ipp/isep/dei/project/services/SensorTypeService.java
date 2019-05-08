package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorTypeService {

    @Autowired
    SensorTypeRepository sensorTypeRepository;


    public List<SensorType> getSensorTypeList() {
        List<SensorType> sensorTypeList = new ArrayList<>();
        for (SensorType sensorType : sensorTypeRepository.findAll()) {
            sensorTypeList.add(sensorType);
        }
        return sensorTypeList;
    }

    public boolean addType(SensorTypeId sensorTypeId) {
        if (!sensorTypeRepository.existsById(sensorTypeId)) {
            sensorTypeRepository.save(new SensorType(sensorTypeId));
            return true;
        }
        return false;
    }
}
