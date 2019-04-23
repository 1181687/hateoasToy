package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseGridAggregateRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;

import java.util.List;

@Service
public class HouseGridAggregateService {

    @Autowired
    private HouseGridAggregateRepository houseGridAggregateRepository;

    public List<HouseGrid> getAllGrids() {
        return houseGridAggregateRepository.getAllGrids();
    }

    public boolean numberOfHouseGridsInRepository() {
        return this.houseGridAggregateRepository.numberOfGeoAreasInRepo() == 0;
    }

    public HouseGrid getGridById(HouseGridId houseGridId) {
        return houseGridAggregateRepository.getGridById(houseGridId);
    }

    public boolean isGridRepositoryEmpty() {
        return this.houseGridAggregateRepository.isGridRepositoryEmpty();
    }

    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {
        return this.houseGridAggregateRepository.newPowerSource(powerSourceId,typeId,gridId);
    }

    public boolean createHouseGrid(String gridId){
        return this.houseGridAggregateRepository.createHouseGrid(gridId);
    }

}
