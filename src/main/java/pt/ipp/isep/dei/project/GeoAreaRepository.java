package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;

public interface GeoAreaRepository  extends CrudRepository<GeographicalArea, String>{

}
