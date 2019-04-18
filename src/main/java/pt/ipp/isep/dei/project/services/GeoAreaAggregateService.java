package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaAggregateRepository;

@Service
public class GeoAreaAggregateService {

    @Autowired
    private GeoAreaAggregateRepository geoAreaAggregateRepo;
}
