package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;

@Service
public class GeoAreaService {

    @Autowired
    private GeoAreaRepository geoAreaRepository;
}
