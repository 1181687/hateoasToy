package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

public interface GeoAreaTypeRepository extends CrudRepository<GeographicalAreaType,String> {
}
