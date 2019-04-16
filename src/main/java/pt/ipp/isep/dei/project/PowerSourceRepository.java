package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceId;

public interface PowerSourceRepository extends CrudRepository<PowerSource, PowerSourceId> {
}
