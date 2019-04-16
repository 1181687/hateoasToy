package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseGridRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

@Service
public class HouseGridService {

    @Autowired
    private HouseGridRepository houseGridRepository;

    /**
     * Method that creates a house grid and adds it to the repo.
     * If there isn't already a house grid with that id in the repo, a new house grid is created and added to it.
     * Else, no house grid is created and/or sent to the repo.
     *
     * @param id HouseGridId to be used.
     * @return True or false.
     */
    public boolean createHouseGrid(HouseGridId id) {
        if (!houseGridRepository.existsById(id)) {
            HouseGrid houseGrid = new HouseGrid(id);
            houseGridRepository.save(houseGrid);
            return true;
        }
        return false;
    }
}
