package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoAreaSensorService {
    @Autowired
    private GeoAreaSensorRepository geoAreaSensorRepo;

    /**
     * Method that searches for a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return Sensor required.
     */
    public GeoAreaSensor getSensorById(SensorId id) {
        return geoAreaSensorRepo.findById(id).orElse(null);
    }

    /**
     * Method that saves all the
     */
    public void saveSensor(GeoAreaSensor sensor) {
        geoAreaSensorRepo.save(sensor);
    }

    /**
     * Method that saves all the
     */
    public boolean saveSensors(List<GeoAreaSensor> sensors) {
        boolean saved = false;
        List<GeoAreaSensor> geoAreaSensors = new ArrayList<>();
        for (GeoAreaSensor sensor : sensors) {
            if (!geoAreaSensorRepo.existsById(new SensorId(sensor.getId()))) {
                geoAreaSensors.add(sensor);
                saved = true;
            }
        }
        geoAreaSensorRepo.saveAll(geoAreaSensors);
        return saved;
    }
}
