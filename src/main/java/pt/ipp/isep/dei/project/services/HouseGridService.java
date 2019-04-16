package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseGridRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseGridService {

    @Autowired
    private HouseGridRepository houseGridRepository;

    @Autowired
    private PowerSourceService powerSourceService;

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

    public boolean isGridRepositoryEmpty() {
        return this.houseGridRepository.count() == 0;
    }

    public List<HouseGrid> getAllGrids() {
        Iterable<HouseGrid> gridIterables = this.houseGridRepository.findAll();
        List<HouseGrid> grids = new ArrayList<>();
        gridIterables.forEach(grids::add);
        return grids;
    }

    public boolean newPowerSource(String powerSourceId, String typeId, String gridId){
        return this.powerSourceService.newPowerSource(powerSourceId,typeId,gridId);
    }

}
