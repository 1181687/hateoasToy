package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;

public interface HouseGridRepository extends CrudRepository <HouseGrid, String> {
}
