package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;

public interface PowerSourceTypeRepository extends CrudRepository<PowerSourceType, Long> {
}
