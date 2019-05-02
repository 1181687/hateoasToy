package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.RoomSensorRepository;

import java.time.LocalDate;
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

    /**
     * gets room readings from a sensor by sensorId, in an interval of days
     *
     * @param startDate initial LocalDate
     * @param endDate   final LocalDate
     * @param sensorId  SensorId
     * @return List of Reading
     */
    public List<Reading> getReadings(LocalDate startDate, LocalDate endDate, SensorId sensorId) {
        return this.roomSensorRepo.findById(sensorId).orElseGet(null).getReadings(startDate, endDate);
    }


    public boolean existSensors (RoomId roomId, SensorTypeId sensorTypeId){
        SensorType sensorType = new SensorType(sensorTypeId);
        return this.roomSensorRepo.existsRoomSensorsByRoomIdAndSensorType(roomId, sensorType);
    }

    public SensorId getSensorId (RoomId roomId){
        RoomSensor roomSensor = this.roomSensorRepo.findByRoomId(roomId);
        return roomSensor.getId();
    }
}
