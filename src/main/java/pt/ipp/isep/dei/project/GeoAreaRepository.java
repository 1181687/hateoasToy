package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

import java.util.List;

public interface GeoAreaRepository extends CrudRepository<GeographicalArea, GeoAreaId> {
}