package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.House;

public interface HouseRepository extends CrudRepository<House, Long> {
}
