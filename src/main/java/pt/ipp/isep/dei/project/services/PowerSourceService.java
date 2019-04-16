package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.PowerSourceRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;


@Service
public class PowerSourceService {

    @Autowired
    private PowerSourceRepository powerSourceRepository;


    /**
     * Power Source List constructor
     */
    public PowerSourceService() {
       //empty
    }


    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {

        PowerSourceId id = new PowerSourceId(powerSourceId);

        if(!this.powerSourceRepository.existsById(id)){
            PowerSourceTypeId type = new PowerSourceTypeId(typeId);
            HouseGridId grid = new HouseGridId(gridId);
            PowerSource powerSource = new PowerSource(id,type,grid);
            this.powerSourceRepository.save(powerSource);
            return true;
        }
        return false;
    }

}
