package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;

import java.util.List;

public interface GeoAreaRepository extends CrudRepository<GeographicalArea, GeoAreaId> {

    List<GeographicalArea> findById_TypeId(GeoAreaTypeId geoAreaTypeId);
}