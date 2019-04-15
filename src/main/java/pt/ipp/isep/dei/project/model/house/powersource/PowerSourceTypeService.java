package pt.ipp.isep.dei.project.model.house.powersource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.PowerSourceTypeRepository;

@Service
public class PowerSourceTypeService {
    @Autowired
    PowerSourceTypeRepository powerSourceTypeRepository;

    public PowerSourceTypeService() {
    }
}
