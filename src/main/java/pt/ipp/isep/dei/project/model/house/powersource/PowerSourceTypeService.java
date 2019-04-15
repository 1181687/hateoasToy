package pt.ipp.isep.dei.project.model.house.powersource;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.PowerSourceTypeRepository;

public class PowerSourceTypeService {
    @Autowired
    PowerSourceTypeRepository powerSourceTypeRepository;

    public PowerSourceTypeService() {
    }
}
