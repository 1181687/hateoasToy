package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomSensorReadingsRepository;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.util.List;

@Service
public class RoomReadingService {

    @Autowired
    private RoomSensorReadingsRepository roomSensorReadingsRepository;

    public List<RoomReading> getListOfRoomReadingByRoomSensorId (RoomSensorId roomSensorId) {
        return roomSensorReadingsRepository.findByRoomReadingId_RoomSensorId(roomSensorId);
    }
}
