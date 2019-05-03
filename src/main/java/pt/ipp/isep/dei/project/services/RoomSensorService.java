package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.repositories.RoomSensorRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
     * @param sensorDTOs List of sensors to be analyzed.
     */
    public void saveSensors(List<RoomSensorDTO> sensorDTOs) {
        List<RoomSensor> sensors = new ArrayList<>();
        for (RoomSensorDTO sensorDTO : sensorDTOs) {
            RoomSensor sensor = RoomSensorMapper.mapToEntity(sensorDTO);
            sensors.add(sensor);
        }
        roomSensorRepo.saveAll(sensors);
    }

    /**
     * Method that checks if a sensor exists in the repo by its id.
     *
     * @param sensorIdDTO Id to be used.
     * @return True or false.
     */
    public boolean sensorExists(SensorIdDTO sensorIdDTO) {
        SensorId sensorId = SensorIdMapper.mapToEntity(sensorIdDTO);
        return this.roomSensorRepo.existsById(sensorId);
    }

    /**
     * gets room readings DTO from a sensor by sensorId, in an interval of days
     *
     * @param startDate initial LocalDate
     * @param endDate   final LocalDate
     * @param sensorId  SensorId
     * @return List of ReadingDTO
     */
    public List<ReadingDTO> getReadingsDTO(LocalDate startDate, LocalDate endDate, SensorId sensorId) {
        List<Reading> readings = this.roomSensorRepo.findById(sensorId).orElseGet(null).getReadings(startDate, endDate);
        List<ReadingDTO> readingsDTO = new ArrayList<>();
        for (Reading reading : readings) {
            readingsDTO.add(ReadingMapper.mapToDTO(reading));
        }
        return readingsDTO;
    }


    public boolean existSensors (RoomId roomId, SensorTypeId sensorTypeId){
        return this.roomSensorRepo.existsRoomSensorsByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    public SensorId getSensorId (RoomId roomId){
        RoomSensor roomSensor = this.roomSensorRepo.findByRoomId(roomId);
        return roomSensor.getId();
    }

    public RoomSensor getRoomSensorBy(RoomId roomId, SensorTypeId sensorTypeId) {
        return this.roomSensorRepo.findByRoomIdAndSensorType(roomId, sensorTypeId);
    }

    public ReadingDTO getLastMeasurement(RoomId roomId, SensorTypeId sensorTypeId) {
        RoomSensor roomSensor = getRoomSensorBy(roomId, sensorTypeId);
        Reading reading = roomSensor.getLastMeasurement();
        ReadingDTO lastReadingDTO = ReadingMapper.mapToDTO(reading);
        return lastReadingDTO;
    }

    public double getMaxMeasurementValueOfADay(RoomId roomId, SensorTypeId sensorTypeId, LocalDate date) {
        RoomSensor roomSensor = getRoomSensorBy(roomId, sensorTypeId);
        double maxValue = roomSensor.getMaximumValueOfDay(date);
        return maxValue;
    }
}
