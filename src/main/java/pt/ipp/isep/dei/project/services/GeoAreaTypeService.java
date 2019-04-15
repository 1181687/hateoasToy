package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaTypeRepository;

@Service
public class GeoAreaTypeService {
    @Autowired
    GeoAreaTypeRepository geoAreaTypeRepository;

    public GeoAreaTypeService() {
    }
}
