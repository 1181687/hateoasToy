package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;

public interface PowerSourceTypeRepository extends CrudRepository<PowerSourceType, PowerSourceTypeId> {
}
