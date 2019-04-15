package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

import java.util.List;

public interface GeoAreaTypeRepository extends CrudRepository<GeographicalAreaType, GeoAreaTypeId> {

    public List<GeographicalAreaType> getAll();
}
