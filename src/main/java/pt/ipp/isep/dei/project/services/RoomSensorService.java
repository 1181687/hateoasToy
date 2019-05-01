package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.repositories.RoomSensorRepository;

import java.util.List;

@Service
public class RoomSensorService {
    @Autowired
    private RoomSensorRepository roomSensorRepo;

    /**
     * Method that searches for a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return Sensor required.
     */
    public RoomSensor getSensorById(SensorId id) {
        return roomSensorRepo.findById(id).orElse(null);
    }

    /**
     * Method that saves a list of sensors in the repo.
     *
     * @param sensors List of sensors to be analyzed.
     */
    public void saveSensors(List<RoomSensor> sensors) {
        roomSensorRepo.saveAll(sensors);
    }

    /**
     * Method that checks if a sensor exists in the repo by its id.
     *
     * @param sensorId Id to be used.
     * @return True or false.
     */
    public boolean sensorExists(SensorId sensorId) {
        return this.roomSensorRepo.existsById(sensorId);
    }
}
