package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

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

}
