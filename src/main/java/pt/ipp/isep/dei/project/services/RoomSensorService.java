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
import java.util.Objects;

@Service
public class RoomSensorService {
    @Autowired
    private RoomSensorRepository roomSensorRepo;

    /**
     * Method that searches for a sensor by its id.
     *
     * @param sensorIdDTO Id of the sensor.
     * @return Sensor required.
     */
    public RoomSensorDTO getSensorById(SensorIdDTO sensorIdDTO) {
        SensorId sensorId = SensorIdMapper.mapToEntity(sensorIdDTO);
        RoomSensor sensor = roomSensorRepo.findById(sensorId).orElse(null);
        if (Objects.nonNull(sensor)) {
            return RoomSensorMapper.mapToDTO(sensor);
        }
        return null;
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


    public boolean existSensors(RoomId roomId, SensorTypeId sensorTypeId) {
        return this.roomSensorRepo.existsRoomSensorsByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    public SensorId getSensorId(RoomId roomId, SensorTypeId sensorTypeId) {
        RoomSensor roomSensor = this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
        if (Objects.isNull(roomSensor)){
            return null;
        }
        return roomSensor.getId();
    }

    public RoomSensor getRoomSensor(RoomId roomId, SensorTypeId sensorTypeId) {
        return this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);

    }

    public RoomSensorDTO getRoomSensorDTO(RoomId roomId, SensorTypeId sensorTypeId) {
        RoomSensor roomSensor = null;
        RoomSensorDTO roomSensorDTO = null;
        if (!Objects.isNull(this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId))) {
            roomSensor = this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
            roomSensorDTO = RoomSensorMapper.mapToDTO(roomSensor);
        }
        return roomSensorDTO;
    }

    public ReadingDTO getLastMeasurement(RoomId roomId, SensorTypeId sensorTypeId) {
        RoomSensorDTO roomSensorDTO = getRoomSensorDTO(roomId, sensorTypeId);
        if (Objects.isNull(roomSensorDTO)) {
            return null;
        }
        RoomSensor roomSensor = RoomSensorMapper.mapToEntity(roomSensorDTO);
        Reading reading = roomSensor.getLastMeasurement();
        return ReadingMapper.mapToDTO(reading);
    }

    public RoomSensorDTO getRoomSensorByRoomSensorTypeDate(RoomId roomId, SensorTypeId sensorTypeId, LocalDate date) {
        RoomSensorDTO roomSensorDTO = null;
        if (!Objects.isNull(this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId))) {
            RoomSensor roomSensor = this.roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
            if (roomSensor.existReadingsBetweenDates(date, date)) {
                roomSensorDTO = RoomSensorMapper.mapToDTO(roomSensor);
            }
        }
        return roomSensorDTO;
    }

    public double getMaxMeasurementValueOfADay(RoomId roomId, SensorTypeId sensorTypeId, LocalDate date) {
        RoomSensorDTO roomSensorDTO = getRoomSensorByRoomSensorTypeDate(roomId, sensorTypeId, date);
        if (Objects.isNull(roomSensorDTO)) {
            return Double.NaN;
        }
        RoomSensor roomSensor = RoomSensorMapper.mapToEntity(roomSensorDTO);
        return roomSensor.getMaximumValueOfDay(date);
    }

    public boolean newSensor(RoomSensorDTO roomSensorDTO) {
        RoomSensor roomSensor = RoomSensorMapper.mapToEntity(roomSensorDTO);
        if (!roomSensorRepo.existsById(roomSensor.getId())) {
            roomSensorRepo.save(roomSensor);
            return true;
        }
        return false;
    }

    public List<RoomSensor> getAllSensorsOfRoom(RoomId roomId) {
        return this.roomSensorRepo.findAllByRoomId(roomId);
    }

    /*
//GGG////////////////possivelmente p apagar//////////////////////////////////////////
    public boolean hasReadingsInGivenDay(LocalDate date, RoomId roomId, SensorTypeId sensorTypeId) {
        return this.getRoomSensor(roomId, sensorTypeId).hasReadingsInGivenDay(date);
    }
    */

}