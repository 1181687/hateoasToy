package pt.ipp.isep.dei.project.model.geographicalarea;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.GeoAreaTypeRepository;

public class GeoAreaTypeService {
    @Autowired
    GeoAreaTypeRepository geoAreaTypeRepository;

    public GeoAreaTypeService() {
    }
}
