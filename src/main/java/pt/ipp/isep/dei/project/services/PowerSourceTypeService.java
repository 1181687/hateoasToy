package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.PowerSourceTypeRepository;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;

import java.util.ArrayList;
import java.util.List;
@Service
public class PowerSourceTypeService {

    @Autowired
    private PowerSourceTypeRepository powerSourceTypeRepository;

    public PowerSourceTypeService() {
        //empty
    }

    public List<PowerSourceType> getAllPowerSourceTypes() {
        Iterable<PowerSourceType> iterTypes = this.powerSourceTypeRepository.findAll();
        List<PowerSourceType> types = new ArrayList<>();
        iterTypes.forEach(types::add);
        return types;
    }

    public boolean newPowerSourceType(String id) {
        PowerSourceTypeId typeId = new PowerSourceTypeId(id);
        if (!this.powerSourceTypeRepository.existsById(typeId)) {
            PowerSourceType powerSourceType = new PowerSourceType(typeId);
            this.powerSourceTypeRepository.save(powerSourceType);
            return true;
        }
        return false;
    }
}
