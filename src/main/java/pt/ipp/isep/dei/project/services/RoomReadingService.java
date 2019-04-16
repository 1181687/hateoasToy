package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomSensorReadingsRepository;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;

@Service
public class RoomReadingService {

    @Autowired
    private RoomSensorReadingsRepository roomSensorReadingsRepository;

    public RoomReadingService() {
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId){
        return roomSensorReadingsRepository.existsById(roomReadingId);
    }

    public boolean addReading(RoomReading roomReading){
        if (isReadingDuplicated(roomReading.getRoomReadingId())){
            return false;
        }
        roomSensorReadingsRepository.save(roomReading);
        return true;
    }
}
