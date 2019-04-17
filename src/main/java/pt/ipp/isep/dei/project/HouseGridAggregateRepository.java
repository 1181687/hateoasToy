package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseGridAggregateRepository {

    @Autowired
    private HouseGridRepository houseGridRepo;

    @Autowired
    private PowerSourceRepository powerSourceRepo;

    /**
     * Constructor.
     */
    public HouseGridAggregateRepository() {
        // empty
    }

    /**
     * Get method.
     *
     * @return HouseGridRepository.
     */
    public HouseGridRepository getHouseGridRepo() {
        return houseGridRepo;
    }

    /**
     * Get method.
     *
     * @return PowerSourceRepository.
     */
    public PowerSourceRepository getPowerSourceRepo() {
        return powerSourceRepo;
    }
}
