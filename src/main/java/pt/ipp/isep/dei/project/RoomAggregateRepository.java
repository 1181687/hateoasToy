package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import java.util.List;

@Service
public class RoomAggregateRepository {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomSensorRepository roomSensorRepository;

    @Autowired
    private RoomSensorReadingsRepository roomReadingRepository;

    /**
     * Constructor.
     */
    public RoomAggregateRepository() {
        // empty
    }

    public List<Room> findAllByHouseGridIdEquals(HouseGridId houseGridId) {
        return roomRepository.findAllByHouseGridIdEquals(houseGridId);
    }

}
