package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

@Service
public class GeoAreaTypeService {
    @Autowired
    GeoAreaTypeRepository geoAreaTypeRepository;

    public GeoAreaTypeService() {
    }

    public boolean createAndAddGeoAreaType(String geoAreaTypeId) {
        if (!geoAreaTypeRepository.existsById(new GeoAreaTypeId(geoAreaTypeId))) {
            this.geoAreaTypeRepository.save(new GeographicalAreaType(geoAreaTypeId));
            return true;
        }
        return false;
    }
}
