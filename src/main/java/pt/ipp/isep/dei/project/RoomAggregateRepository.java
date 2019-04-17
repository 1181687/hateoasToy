package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomAggregateRepository {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomSensorRepository roomSensorRepo;

    @Autowired
    private RoomSensorReadingsRepository roomReadingRepo;

    /**
     * Constructor.
     */
    public RoomAggregateRepository() {
        // empty
    }

    /**
     * Get method.
     *
     * @return RoomRepository.
     */
    public RoomRepository getRoomRepo() {
        return roomRepo;
    }

    /**
     * Get method.
     *
     * @return RoomSensorRepository.
     */
    public RoomSensorRepository getRoomSensorRepo() {
        return roomSensorRepo;
    }

    /**
     * Get method.
     *
     * @return RoomSensorReadingsRepository.
     */
    public RoomSensorReadingsRepository getRoomReadingRepo() {
        return roomReadingRepo;
    }
}
