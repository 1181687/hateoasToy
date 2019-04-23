package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseGridAggregateRepository {

    @Autowired
    private HouseGridRepository houseGridRepository;

    @Autowired
    private PowerSourceRepository powerSourceRepository;

    /**
     * Constructor.
     */
    public HouseGridAggregateRepository() {
        // empty
    }

    public List<HouseGrid> getAllGrids() {
        Iterable<HouseGrid> gridIterables = this.houseGridRepository.findAll();
        List<HouseGrid> grids = new ArrayList<>();
        gridIterables.forEach(grids::add);
        return grids;
    }

    public Long numberOfGeoAreasInRepo() {
        return this.houseGridRepository.count();
    }

    public HouseGrid getGridById(HouseGridId houseGridId) {
        return houseGridRepository.findById(houseGridId).orElse(null);
    }

    public boolean isGridRepositoryEmpty() {
        return this.houseGridRepository.count() == 0;
    }


    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {

        PowerSourceId id = new PowerSourceId(powerSourceId);

        if (!this.powerSourceRepository.existsById(id)) {
            PowerSourceTypeId type = new PowerSourceTypeId(typeId);
            HouseGridId grid = new HouseGridId(gridId);
            PowerSource powerSource = new PowerSource(id, type, grid);
            this.powerSourceRepository.save(powerSource);
            return true;
        }
        return false;
    }

    /**
     * Method that creates a house grid and adds it to the repo.
     * If there isn't already a house grid with that id in the repo, a new house grid is created and added to it.
     * Else, no house grid is created and/or sent to the repo.
     *
     * @param gridId Id to be used.
     * @return True or false.
     */
    public boolean createHouseGrid(String gridId) {
        HouseGridId id = new HouseGridId(gridId);
        if (!houseGridRepository.existsById(id)) {
            HouseGrid houseGrid = new HouseGrid(id);
            houseGridRepository.save(houseGrid);
            return true;
        }
        return false;
    }

}
